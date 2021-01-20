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
    private static HashMap<String, Command> testingCommands = new HashMap<>();
    private static HashMap<String, Command> otherCommands = new HashMap<>();

    @Override
    public String getCommandLabel() {
        return "help";
    }
    
    @Override
    public String getParameters() {
        return null;
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
                embedHelp.addField(Commands.prefix + command.getCommandLabel() + " " + command.getParameters(), command.getDescription(), true);
            }
        }

        if (testingCommands.size() > 0) {
            embedHelp.addField("**Testing Commands**", "For testing purposes! (Doesn't provide anything useful to the general player)", false);
            for (Command command : testingCommands.values()) {
                embedHelp.addField(Commands.prefix + command.getCommandLabel() + " " + command.getParameters(), command.getDescription(), true);
            }
        }

        if (otherCommands.size() > 0) {
            embedHelp.addField("**Other Commands**", "Commands without a category.", false);
            for (Command command : otherCommands.values()) {
                embedHelp.addField(Commands.prefix + command.getCommandLabel() + " " + command.getParameters(), command.getDescription(), true);
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
                case TESTING:
                    testingCommands.put(command.getCommandLabel(), command);
                    break;
                default:
                    otherCommands.put(command.getCommandLabel(), command);
                    break;
            }
        }
    }
}
