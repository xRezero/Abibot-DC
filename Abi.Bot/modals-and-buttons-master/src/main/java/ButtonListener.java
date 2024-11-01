import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ButtonListener extends ListenerAdapter {

    // Liste der verbotenen Wörter
    private final String[] BAD_WORDS = {"Fisch", "Idiot"};


    // Methode, die auf normale Nachrichten reagiert
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        // Überprüfe, ob die Nachricht ein verbotenes Wort enthält

        for (String badWord : BAD_WORDS) {
            if (event.getMessage().getContentRaw().contains(badWord)) {
                // Sende eine einfache Nachricht in den aktuellen Kanal
                event.getChannel().sendMessage("Du hast ein böses Wort gesagt! Ich hab dich im Blick").queue();

                // Sende die Nachricht an einen speziellen Textkanal (ServerID)
                TextChannel staffChannel = event.getJDA().getTextChannelById("1201631438450200667");

                if (staffChannel != null) {
                    // Erstellt eine Nachricht mit einer Schaltfläche
                    Message message = new MessageBuilder()
                            .append(Objects.requireNonNull(event.getMember()).getEffectiveName())
                            .append("Achtung böses Wort!! Klicken Sie auf Entfernen, um es zu entfernen.")
                            .setActionRows(
                                    ActionRow.of(Button.danger("remove-msg", "Remove Message"), Button.success("ignore-alert", "Ignore Alert")))
                            .build();

                    // Sende die Nachricht an einen speziellen Textkanal (ServerID)

                    staffChannel.sendMessage(message).queue();
                }

            }
        }

    }

     // Methode, die auf Button-Interaktionen reagiert
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {


            if (Objects.equals(event.getButton().getId(), "remove-msg")) {
                // Lösche die Nachricht, auf die die Schaltfläche geklickt wurde
                event.getMessage().delete().queue();

                // Antworte auf die Interaktion
                event.reply("Message removed.").queue();

            }else if (Objects.equals(event.getButton().getId(), "ignore-alert")) {

                // Antworte auf die Interaktion
                event.reply("Ignoring the alert...").queue();

            }

        }

}