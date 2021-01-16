package luxsolis;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Pitterbot {
    public static Dotenv dotenv;
    public static String version = "0.1.0-alpha";

    public static void main(String[] arguments) throws Exception {
        dotenv = Dotenv.load();
        String botToken = dotenv.get("TOKEN");

        JDA api = JDABuilder.createDefault(botToken)
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS
                ).build();

        api.getPresence().setActivity(Activity.playing("Coromon || v" + version));
    }
}
