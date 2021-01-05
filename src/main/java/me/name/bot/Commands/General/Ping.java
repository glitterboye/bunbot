package me.name.bot.Commands.General;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "ping")) {
            long ping = event.getJDA().getGatewayPing();
            String meter = getMeter(ping);
            EmbedBuilder result = new EmbedBuilder().setColor(0x61e885).setTitle("🧭 Bun dug up your ping")
                    .setDescription(ping + "ms\n" + meter);

            event.getChannel().sendMessage(result.build()).queue();
        }
    }

    public String getMeter(long ping) {
        String meter = "Good [⎯⎯⎯⎯⎯⎯⎯⎯⎯ ? ⎯⎯⎯⎯⎯⎯⎯⎯⎯] Bad";
        int c = 20, i = 0;

        if (ping < 40)
            i = 1;
        else if (ping < 80)
            i = 2;
        else if (ping < 120)
            i = 3;
        else if (ping < 160)
            i = 4;
        else if (ping < 200)
            i = 5;
        else if (ping < 240)
            i = 6;
        else
            i = 7;

        meter = "Good [";
        for (int a = 0; a < i; a++) {
            meter += "▬";
        }
        for (int a = i; a < c - i; a++) {
            meter += "⎯";
        }
        meter += "] Bad";

        return meter;
    }
}
