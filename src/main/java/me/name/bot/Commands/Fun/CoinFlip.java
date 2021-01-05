package me.name.bot.Commands.Fun;

import java.util.Random;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CoinFlip extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        String[] titles = { "Bun flipped a coin!", "Bun rewards you with great fortune!", "Ooo shiny coin!",
                "Bun loves shiny coins!", "Bun likes the odds!" };

        if (args[0].equalsIgnoreCase(Bun.prefix + "coinflip") || args[0].equalsIgnoreCase(Bun.prefix + "cf")) {
            Random r = new Random();
            String flip = "null";

            if (r.nextInt(2) == 1)
                flip = "heads";
            else
                flip = "tails";

            EmbedBuilder result = new EmbedBuilder().setColor(0x61e885)
                    .setTitle(titles[new Random().nextInt(titles.length)])
                    .setDescription("You flipped a **" + flip + "**");

            event.getChannel().sendMessage(result.build()).queue();
        }
    }
}
