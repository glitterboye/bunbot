package me.name.bot.Commands;

import java.time.OffsetDateTime;
import java.util.List;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserInfo extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (event.getAuthor().isBot())
            return;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "userinfo") || args[0].equalsIgnoreCase(Bun.prefix + "ui")) {
            if (args.length == 1) {
                error_1(event);

            } else if (args.length > 1 && args.length < 3) {
                if (args[1].equalsIgnoreCase("help")) {
                    EmbedBuilder help = new EmbedBuilder();

                    String title = "👤 The " + Bun.prefix + "userinfo command",
                            desc = "By typing `" + Bun.prefix + "userinfo @<user>`, Bun can look up their information!";

                    help.setTitle(title).setDescription(desc).setColor(0x61e885);
                    event.getChannel().sendMessage(help.build()).queue();
                } else {
                    Member name;

                    try {
                        name = event.getMessage().getMentionedMembers().get(0);

                        OffsetDateTime date = name.getTimeJoined();
                        String date_field = date.toLocalDate().toString();

                        List<Role> roles = name.getRoles();
                        String roles_field = "[ no roles ]";

                        if (!roles.isEmpty()) {
                            roles_field = roles.get(0).getName();

                            for (int i = 1; i < roles.size(); i++) {
                                roles_field += ", " + roles.get(i).getName();
                            }
                        }

                        EmbedBuilder ui = new EmbedBuilder().setColor(0x61e885).setImage(name.getUser().getAvatarUrl())
                                .setTitle("Bun dug up user details").setDescription("about " + name.getAsMention())
                                .addField("date joined", date_field, true).addField("role", roles_field, true);

                        event.getChannel().sendMessage(ui.build()).queue();

                    } catch (IndexOutOfBoundsException e) {
                        error_1(event);
                    }
                }
            }
        }
    }

    public void error_1(MessageReceivedEvent e) {
        EmbedBuilder error = new EmbedBuilder();

        String title = "User not found", desc = "see `" + Bun.prefix + "userinfo help` for more info";

        error.setTitle(title).setDescription(desc).setColor(0xf05b5b);
        e.getChannel().sendMessage(error.build()).queue();
    }
}
