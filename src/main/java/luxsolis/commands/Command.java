package luxsolis.commands;

import net.dv8tion.jda.api.entities.Message;

/* Command
 *      Interface for creating commands.
 */
public abstract class Command {
    public abstract String getCommandLabel();
    public abstract String getParameters();
    public abstract String getDescription();
    public abstract CommandGroup getCommandGroup();
    public abstract void execute(Message message, String[] args);
}
