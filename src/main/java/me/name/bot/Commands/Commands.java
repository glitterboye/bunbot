package me.name.bot.Commands;

import java.util.Random;

import me.name.bot.Bun;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {

    private EmbedBuilder help_embed;
    private final String desc = "hello! i am Bun bot, a bot created by ralf\n\nralf is working on implementing my features right now. he'll be updating this message as more and more features get created!",
            list_of_commands = "!greet !weather !roll !clear !prefix !userinfo ... ",
            profile = "https://i.imgur.com/5bQQPis.jpg";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        // break up command into strArray
        String[] args = event.getMessage().getContentRaw().split(" ");

        /** !help - embeds an introduction */
        if (args[0].equalsIgnoreCase(Bun.prefix + "help")) {
            help_embed = new EmbedBuilder().setTitle("🥕 Info About Me!").setDescription(desc)
                    .addField("Commands", list_of_commands, false).setColor(0x61e885).setThumbnail(profile);

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(help_embed.build()).queue();
        }

        /** !greet - greets the user */
        else if (args[0].equalsIgnoreCase(Bun.prefix + "greet")) {
            event.getChannel().sendMessage("Hello, " + "<@" + event.getAuthor().getId() + ">" + "!").queue();
        }

        /** !weather */
        else if (args[0].equalsIgnoreCase(Bun.prefix + "weather")) {
            // TODO: implement this. look into weather api. thining of [region] [zip] format
            weather(event, args);
        }

        /** !roll command */
        else if (args[0].equalsIgnoreCase(Bun.prefix + "roll")) {
            roll(event, args);
        }

    }

    public void weather(MessageReceivedEvent event, String[] args) {

        if (args[1].equalsIgnoreCase("help")) {
            event.getChannel().sendMessage("My owner has not yet implemented this :(").queue();
        } else {
            event.getChannel().sendMessage("Bun did not detect a valid location. Try `!weather help` for more info");
        }
    }

    public void roll(MessageReceivedEvent event, String[] args) {
        Random r = new Random();

        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("help")) {
                EmbedBuilder random = new EmbedBuilder().setTitle("Rolling").setDescription(
                        "by typing `!roll <n>`, bun will return a number from 1 and <n>. typing `!roll` simply returns a number from 1 to 10")
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