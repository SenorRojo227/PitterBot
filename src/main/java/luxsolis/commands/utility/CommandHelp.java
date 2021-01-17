package luxsolis.commands.utility;

import luxsolis.commands.Command;
import luxsolis.commands.CommandGroup;
import luxsolis.commands.Commands;
import luxsolis.utils.Logger;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.HashMap;

/* CommandHelp
 *      Provides the 'help' command
 */

public class CommandHelp extends Command {
    private static HashMap<String, Command> utilityCommands = new HashMap<>();
    private static HashMap<String, Command> otherCommands = new HashMap<>();

    @Override
    public String getCommandLabel() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Displays this help list!";
    }

    @Override
    public CommandGroup getCommandGroup() {
        return CommandGroup.UTILITY;
    }

    @Override
    public void execute(Message message, String[] args) {
        Command[] commands = Commands.getCommands();

        EmbedBuilder embedHelp = new EmbedBuilder();
        embedHelp.setTitle("Help");
        embedHelp.setColor(new Color(31, 133, 222));

        if (utilityCommands.size() > 0) {
            embedHelp.addField("**Utility Commands**", "These commands are quite useful!", false);
            for (Command command : utilityCommands.values()) {
                embedHelp.addField(Commands.prefix + command.getCommandLabel(), command.getDescription(), true);
            }
        }

        embedHelp.setDescription("Here's a list of commands you can use!\n");
        embedHelp.setFooter("Requested by " + message.getAuthor().getAsTag());
        embedHelp.setTimestamp(OffsetDateTime.now());

        message.getChannel().sendMessage(embedHelp.build()).queue();
    }

    public void cacheCommandCategories() {
        Logger.info("> Caching 'help' command...", "CMD");

        Command[] commands = Commands.getCommands();

        for (Command command : commands) {
            switch (command.getCommandGroup()) {
                case UTILITY:
                    utilityCommands.put(command.getCommandLabel(), command);
                    break;
                default:
                    otherCommands.put(command.getCommandLabel(), command);
                    break;
            }
        }
    }
}
