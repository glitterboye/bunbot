package me.name.bot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.DecimalFormat;

import me.name.bot.Bun;

public class Math extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Bun.prefix + "math")) {
            if (args.length == 4 || args.length == 2) {
                if (args[1].equalsIgnoreCase("help")) {
                    EmbedBuilder help = new EmbedBuilder();
                    help.setColor(0x61e885);

                    String title = "🔢 The !math command",
                            desc = "Bun is able to do simple calculations\nFollowing `!math`, type a problem in the form: `<A> <operator> <B>`\nAnswers will be rounded to two decimal places",
                            foot = "[ +  -  *  /  %  ^ ]";
                    help.setTitle(title).setDescription(desc).addField("Supported Operators", foot, false);

                    event.getChannel().sendMessage(help.build()).queue();

                } else if (!isNumeric(args[1]) || !isNumeric(args[3])) {
                    error_2(event);
                } else {
                    if (args[2].equalsIgnoreCase("+")) {
                        Double a = Double.parseDouble(args[1]), b = Double.parseDouble(args[3]);
                        Double result = Double.sum(a, b);

                        embed_answer(event, args, a, b, result);
                    } else if (args[2].equalsIgnoreCase("-")) {
                        Double a = Double.parseDouble(args[1]), b = Double.parseDouble(args[3]);
                        Double result = a - b;

                        embed_answer(event, args, a, b, result);
                    } else if (args[2].equalsIgnoreCase("*")) {
                        Double a = Double.parseDouble(args[1]), b = Double.parseDouble(args[3]);
                        Double result = a * b;

                        embed_answer(event, args, a, b, result);
                    } else if (args[2].equalsIgnoreCase("/")) {
                        Double a = Double.parseDouble(args[1]), b = Double.parseDouble(args[3]);
                        Double result = a / b;

                        embed_answer(event, args, a, b, result);
                    } else if (args[2].equalsIgnoreCase("%")) {
                        Double a = Double.parseDouble(args[1]), b = Double.parseDouble(args[3]);
                        Double result = a % b;

                        embed_answer(event, args, a, b, result);
                    } else if (args[2].equalsIgnoreCase("^")) {
                        Double a = Double.parseDouble(args[1]), b = Double.parseDouble(args[3]);
                        Double result = java.lang.Math.pow(a, b);

                        embed_answer(event, args, a, b, result);
                    } else {
                        event.getChannel().sendMessage("Error in code. Entered nested else block");
                    }
                }
            } else {
                error_1(event);
            } // END else
        } // END if
    } // END fxn

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void error_1(MessageReceivedEvent e) {
        EmbedBuilder error = new EmbedBuilder();
        error.setColor(0xf05b5b);

        String title = "Bun was unable to compute", desc = "Please try `!math help` for more info!";
        error.setTitle(title).setDescription(desc);

        e.getChannel().sendMessage(error.build()).queue();
    }

    public void error_2(MessageReceivedEvent e) {
        EmbedBuilder error = new EmbedBuilder();
        error.setColor(0xf05b5b);

        String title = "Bun can only operate on numbers!", desc = "Please try `!math help` for more info!";
        error.setTitle(title).setDescription(desc);

        e.getChannel().sendMessage(error.build()).queue();
    }

    public void embed_answer(MessageReceivedEvent e, String[] args, double a, double b, double result) {
        EmbedBuilder answer = new EmbedBuilder();
        answer.setColor(0x61e885);

        DecimalFormat df = new DecimalFormat("#.##");

        String title = "Answer: " + df.format(a) + " " + args[2] + " " + df.format(b) + " = " + df.format(result),
                desc = "If bun's calculations are incorrect, please let ralf know!";

        answer.setTitle(title).setDescription(desc);
        e.getChannel().sendMessage(answer.build()).queue();
    }
}
