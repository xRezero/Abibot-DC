import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class SupCommand extends ListenerAdapter {

    // Methode, die auf Slash-Command-Interaktionen reagiert
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // Überprüfe, ob der Slash-Befehl "sup" ist
        if (event.getName().equals("sup")){

            // Erstellt ein Texteingabefeld für den Namen
            TextInput name = TextInput.create("sup-name", "Name", TextInputStyle.SHORT)
                    .setPlaceholder("Gib den Namen ein")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();
            // Erstellt ein Texteingabefeld für die Nachricht
            TextInput message = TextInput.create("sup-message","Message", TextInputStyle.PARAGRAPH)
                    .setMinLength(10)
                    .setMaxLength(100)
                    .setRequired(true)
                    .setValue("Hallo wie gehts?")
                    .build();
            // Erstellt ein Modal für den "sup" Befehl mit den Texteingabefeldern
            Modal modal = Modal.create("sup-modal", "Say Sup")
                    .addActionRows(ActionRow.of(name), ActionRow.of(message))
                    .build();
            // Antworte mit dem Modal
            event.replyModal(modal).queue();

        }else if(event.getName().equals("multiply")){

            // Erstellt ein Texteingabefeld für Operand 1
            TextInput operand1 = TextInput.create("zahl1", "zahl 1", TextInputStyle.SHORT)
                    .setPlaceholder("gib die erste Zahl ein")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();
            // Erstellt ein Texteingabefeld für Operand 2
            TextInput operand2 = TextInput.create("zahl2", "zahl 2", TextInputStyle.SHORT)
                    .setPlaceholder("gib die zweite Zahl ein")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();
            //  ein Modal für den "multiply" Befehl mit den Texteingabefeldern
            Modal modal = Modal.create("multiply-modal", "Multiplizieren")
                    .addActionRows(ActionRow.of(operand1), ActionRow.of(operand2))
                    .build();

            // Antworte mit dem Modal
            event.replyModal(modal).queue();
        }

    }
}