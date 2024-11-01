import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {

        // Erstellt eine JDA-Instanz mit dem Bot-Token
        JDA jda = JDABuilder.createDefault("")
                .setActivity(Activity.listening("CHABOS Babos"))
                .addEventListeners(new SupCommand(), new ModalListener(), new ButtonListener())
                .build().awaitReady(); // Warte, bis der Bot vollständig bereit ist


        //Registriere die Slash-Befehle als Gilden befehl (Guild Command)
        Guild guild = jda.getGuildById("1199631875434631250");

        if (guild != null) {
            // Registriere den "sup"-Befehl mit einer Beschreibung
            guild.upsertCommand("sup", "Sup Nachricht an jemandem").queue();
            // Registriere den "multiply"-Befehl mit einer Beschreibung
            guild.upsertCommand("multiply", "Multipliziere Zwei Nummer miteinander").queue();
        }


        Guild guild2 = jda.getGuildById("1117455265013432351");


        if (guild2 != null) {
            // Registriere den "sup"-Befehl mit einer Beschreibung
            guild2.upsertCommand("sup", "Sup Nachricht an jemandem").queue();
            // Registriere den "multiply"-Befehl mit einer Beschreibung
            guild2.upsertCommand("multiply", "Multipliziere Zwei Nummer miteinander").queue();

        }
    }
}