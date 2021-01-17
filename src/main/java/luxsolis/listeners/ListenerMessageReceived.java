package luxsolis.listeners;

import luxsolis.commands.Command;
import luxsolis.commands.Commands;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/* ListenerMessageReceived
 *      Implements behaviour for when messages are received
 */

public class ListenerMessageReceived extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;  // Exclude bots from the listener

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.startsWith(Commands.prefix)) {
            String[] commandArgs = content.replace(Commands.prefix, "").split( " ");
            Command command = Commands.getCommand(commandArgs[0]);

            if (command != null)
                command.execute(message, commandArgs);
        }
    }
}
