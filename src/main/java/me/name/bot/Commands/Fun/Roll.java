package me.name.bot.Commands.Fun;

import java.util.Random;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Roll extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "roll")) {
            roll(event, args);
        }
    }

    public void roll(MessageReceivedEvent event, String[] args) {
        Random r = new Random();

        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("help")) {
                EmbedBuilder random = new EmbedBuilder().setTitle("🎲 Rolling command")
                        .setDescription("by typing `" + Bun.prefix
                                + "roll <n>`, Bun will return a number from 1 and `<n>`. typing `" + Bun.prefix
                                + "roll` simply returns a number from 1 to 10")
                        .setColor(0x61e885);

                event.getChannel().sendMessage(random.build()).queue();
            } else if (isNumeric(args[1])) {
                String n = Integer.toString(r.nextInt(Integer.parseInt(args[1])) + 1);
                EmbedBuilder random = new EmbedBuilder().setTitle("you rolled a " + n).setColor(0x61e885);

                event.getChannel().sendMessage(random.build()).queue();
            } else {
                EmbedBuilder random = new EmbedBuilder().setTitle("Bun didn't recognize that as a number!")
                        .setColor(0x61e885);

                event.getChannel().sendMessage(random.build()).queue();
            }
        } else {
            String n = Integer.toString(r.nextInt(9) + 1);
            EmbedBuilder random = new EmbedBuilder().setTitle("you rolled a " + n).setColor(0x61e885);

            event.getChannel().sendMessage(random.build()).queue();
        }

    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
