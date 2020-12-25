package me.name.bot.Commands;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Prefix extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "prefix")) {
            if (args.length == 2) {
                if (args[1].equalsIgnoreCase("help")) {
                    help(event);
                } else {
                    try {
                        Bun.set_prefix(args[1]);

                        EmbedBuilder success = new EmbedBuilder().setTitle("New prefix set to " + args[1])
                                .setColor(0x61e885);
                        event.getChannel().sendMessage(success.build()).queue();

                    } catch (Exception e) {
                        error_1(event);
                    }
                } // END else
            } else {
                error_1(event);
            } // END else
        } // END if
    }

    public void error_1(MessageReceivedEvent e) {
        EmbedBuilder error = new EmbedBuilder().setTitle("Bun does not like that prefix")
                .setDescription("try `!prefix help` for more info").setColor(0xf05b5b);

        e.getChannel().sendMessage(error.build()).queue();
    }

    public void help(MessageReceivedEvent e) {
        EmbedBuilder error = new EmbedBuilder().setTitle("The !prefix command").setDescription(
                "typing `!prefix <p>` allows you to assign Bun a new prefix\n`<p>` must be 3 characters or less")
                .setColor(0x61e885);

        e.getChannel().sendMessage(error.build()).queue();
    }
}
