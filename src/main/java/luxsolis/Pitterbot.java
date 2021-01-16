package luxsolis;

import io.github.cdimascio.dotenv.Dotenv;
import luxsolis.utils.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

/* Pitterbot
 *      Entry-point and main class for the Bot
 */

public class Pitterbot {
    public static Dotenv dotenv;
    public static String version;

    public static void main(String[] arguments) throws Exception {
        Logger.info("Loading .env", "ENV");

        dotenv = Dotenv.load();
        String botToken = dotenv.get("TOKEN");
        version = dotenv.get("VERSION");

        Logger.info("Building JDA API", "JDA");
        JDA api = JDABuilder.createDefault(botToken)
                .enableIntents(
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MEMBERS
                ).build();
        Logger.success("Finished building JDA API", "JDA");

        Logger.info("Setting Bot Presence", "BOT");
        api.getPresence().setActivity(Activity.playing("Coromon! || v" + version));
    }
}
