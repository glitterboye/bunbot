package me.name.bot.Commands;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        String ralf = event.getGuild().getOwner().getAsMention();
        String desc = "hello! i am Bun bot, a bot created by " + ralf
                + "\nMy goal is to provide quality of life improvements to servers, as well as execute tasks for the sake of convenience. if you'd like to see a feature created, feel free to reach out to my creator! he'll be updating this message as more and more features get created!"
                + "\n\nmy current prefix is `" + Bun.prefix + "`", profile = "https://i.imgur.com/5bQQPis.jpg";

        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "help")) {
            EmbedBuilder help_embed = new EmbedBuilder().setTitle("🥕 Info About Me!").setDescription(desc)
                    .addField("General", "`greet` `ping`", false)
                    .addField("Moderation", "`clear` `kick` `ban` `mute`", false)
                    .addField("Fun", "`coinflip` `roll`", false).addField("Info", "`serverinfo` `userinfo`", false)
                    .setColor(0x61e885).setThumbnail(profile);

            event.getChannel().sendMessage(help_embed.build()).queue();
        }
    }
}
