package me.name.bot.Commands;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerInfo extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot())
            return;

        // break up command into strArray
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        Guild g = event.getGuild();

        String icon = g.getIconUrl();
        String owner = g.getOwner().getAsMention();
        String name = g.getName();
        int member_count = g.getMemberCount();
        String region = g.getRegion().getName();
        String time = g.getTimeCreated().toLocalDate().toString();
        String vc_count = Integer.toString(g.getVoiceChannels().size());
        String tc_count = Integer.toString(g.getTextChannels().size());

        if (args[0].equalsIgnoreCase(Bun.prefix + "serverinfo") || args[0].equalsIgnoreCase(Bun.prefix + "si")) {
            EmbedBuilder si = new EmbedBuilder().setTitle("Server Info").addField("owner", owner, false)
                    .addField("name", name, true).addField("member count", Integer.toString(member_count), false)
                    .addField("region", region, true).addField("date created", time, true)
                    .addField("channels", "💬 " + tc_count + "\n🔊 " + vc_count, true).setThumbnail(icon);

            si.setColor(0x61e885);
            event.getChannel().sendMessage(si.build()).queue();
        }
    }
}
