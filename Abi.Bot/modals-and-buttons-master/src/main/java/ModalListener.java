import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class ModalListener extends ListenerAdapter {

    // Methode, die auf Modal-Interaktionen reagiert
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {


        // Überprüfe, ob das Modal die ID "sup-modal" hat
        if (event.getModalId().equals("sup-modal")) {

            // Hole den Benutzernamen und die Nachricht aus den Modal-Parametern
            String name = Objects.requireNonNull(event.getValue("sup-name")).getAsString();
            String message = Objects.requireNonNull(event.getValue("sup-message")).getAsString();

            // Versuche, das Mitglied mit dem angegebenen Namen zu finden
            Optional<Member> memberOptional = Objects.requireNonNull(event.getGuild()).getMembersByName(name, true).stream().findFirst();

            // Überprüfe, ob das Mitglied gefunden wurde
            if (memberOptional.isPresent()) {
                // Wenn das Mitglied gefunden wurde, antworte mit einer persönlichen Nachricht an das Mitglied
                event.reply("Sup, " + memberOptional.get().getAsMention() + "! " + message).queue();
            }else{
                // Wenn das Mitglied nicht gefunden wurde, antworte mit einer allgemeinen Nachricht
                event.reply("Sup, " + name + "! " + message).queue();
            }

        } else if (event.getModalId().equals("multiply-modal")) {

            // Holen Sie sich die beiden Zahlen aus dem Modal und parsen Sie sie in Integer
            String num1 = Objects.requireNonNull(event.getValue("zahl1")).getAsString();
            String num2 = Objects.requireNonNull(event.getValue("zahl2")).getAsString();

            try {
                // Versuche, die beiden Zahlen zu Integer zu parsen
                int num1Int = Integer.parseInt(num1);
                int num2Int = Integer.parseInt(num2);

                // Berechne das Produkt der beiden Zahle
                int product = num1Int * num2Int;

                // Antworte mit dem Produkt und setze es als ephemeral (nur für den Benutzer sichtbar)
                event.reply("Das Ergebnis ist: " + product).setEphemeral(true).queue();
                // Wenn eine NumberFormatException auftritt, antworte mit einer Fehlermeldung
            } catch (NumberFormatException e) {
                event.reply("eins der Zahlen ist keine Zahl.").setEphemeral(true).queue();
            }
        }
    }
}