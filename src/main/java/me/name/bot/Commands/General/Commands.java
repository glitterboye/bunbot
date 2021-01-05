package me.name.bot.Commands.General;

import me.name.bot.Bun;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        // break up command into strArray
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "greet")) {
            event.getChannel().sendMessage("Hello, " + "<@" + event.getAuthor().getId() + ">" + "!").queue();
        }

        /** !weather */
        else if (args[0].equalsIgnoreCase(Bun.prefix + "weather")) {
            // TODO: implement this. look into weather api. thining of [region] [zip] format
            weather(event, args);
        }
    }

    public void weather(MessageReceivedEvent event, String[] args) {

        if (args[1].equalsIgnoreCase("help")) {
            event.getChannel().sendMessage("My owner has not yet implemented this :(").queue();
        } else {
            event.getChannel().sendMessage(
                    "Bun did not detect a valid location. Try `" + Bun.prefix + "weather help` for more info");
        }
    }
}