package luxsolis.commands;

import luxsolis.utils.Logger;

import java.util.HashMap;

/* Commands
 *      Handles registering and storing commands
 */

public class Commands {
    private static HashMap<String, Command> commands = new HashMap<>();

    public static String prefix;

    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    public static void registerCommand(Command command) {
        if (commands.containsKey(command.getCommandLabel())) {
            Logger.warn("> Command '" + command.getCommandLabel() + "' already registered!", "CMD");
            return;
        }

        commands.put(command.getCommandLabel(), command);
        Logger.info("> New command registered: '" + command.getCommandLabel() + "'.", "CMD");
    }

    public static Command getCommand(String label) {
        return commands.get(label);
    }

    public static Command[] getCommands() {
        return commands.values().toArray(new Command[0]);
    }
}
