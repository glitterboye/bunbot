package me.name.bot.Commands;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        String ralf = event.getGuild().getOwner().getAsMention();
        String desc = "hello! i am Bun bot, a bot created by " + ralf + "\n\nmy current prefix is `" + Bun.prefix
                + "`\nralf is working on implementing my features right now. he'll be updating this message as more and more features get created!",
                list_of_commands = "`greet` `weather` `roll` `clear` `prefix` `userinfo`\n`serverinfo` `quote` `coinflip` ... ",
                profile = "https://i.imgur.com/5bQQPis.jpg";

        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "help")) {
            EmbedBuilder help_embed = new EmbedBuilder().setTitle("🥕 Info About Me!").setDescription(desc)
                    .addField("Commands", list_of_commands, false).setColor(0x61e885).setThumbnail(profile);

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(help_embed.build()).queue();
        }
    }
}
