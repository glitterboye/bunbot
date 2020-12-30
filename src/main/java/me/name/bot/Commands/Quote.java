package me.name.bot.Commands;

import java.util.Random;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Quote extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot())
            return;

        String[] titles = { "Bun cheers you on!", "Bun uses **inspire**!", "Bun likes quotes!", "Bun dug up a quote!",
                "Bun believes in you!" };

        // break up command into strArray
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "quote")) {

            EmbedBuilder result = new EmbedBuilder().setColor(0x61e885)
                    .setTitle(titles[new Random().nextInt(titles.length)]).setDescription("Quote");

            event.getChannel().sendMessage(result.build()).queue();
        }
    }

}