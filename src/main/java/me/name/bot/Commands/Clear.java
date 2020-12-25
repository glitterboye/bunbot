package me.name.bot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import me.name.bot.Bun;

public class Clear extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "clear")) {
            if (args.length < 2) {
                System.out.println("no number specified");
                EmbedBuilder error = new EmbedBuilder();

                String title = "Bun didn't receive a number", desc = "Try `!clear help` for more info";

                error.setTitle(title).setDescription(desc).setColor(0xf05b5b);
                event.getChannel().sendMessage(error.build()).queue();

            } else {
                // !clear help
                if (args[1].equalsIgnoreCase("help")) {
                    EmbedBuilder help = new EmbedBuilder();

                    String title = "The !clear command",
                            desc = "Typing `!clear <n>` will allow Bun to clear <n> messages in recent history\nBun can clear between 1 and 99 messages";

                    help.setTitle(title).setDescription(desc).setColor(0x61e885);
                    event.getChannel().sendMessage(help.build()).queue();
                } else {
                    // then
                    try {
                        // number of messages to clear
                        int amount = Integer.parseInt(args[1]) + 1;

                        List<Message> messages = event.getChannel().getHistory().retrievePast(amount).complete();
                        event.getChannel().purgeMessages(messages);

                        EmbedBuilder success = new EmbedBuilder();
                        success.setTitle("✔ Bun cleared " + args[1] + " message(s)").setColor(0x61e885);
                        event.getChannel().sendMessage(success.build()).queue();

                    } catch (Exception e) {

                        if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {
                            EmbedBuilder error = new EmbedBuilder();

                            String title = "Bun cannot clear that many messages!",
                                    desc = "Bun can only delete between 1 and 99 messages";

                            error.setTitle(title).setDescription(desc).setColor(0xf05b5b);
                            event.getChannel().sendMessage(error.build()).queue();

                        } else if (e.toString().startsWith("java.lang.NumberFormatException: For input string:")) {
                            EmbedBuilder error = new EmbedBuilder();

                            String title = "Bun did not recognize that number",
                                    desc = "Bun can handle numbers from 1 - 99";

                            error.setTitle(title).setDescription(desc).setColor(0xf05b5b);
                            event.getChannel().sendMessage(error.build()).queue();

                        }
                    } // END catch
                } // END else
            } // END else
        } // END if
    } // END fxn
}
