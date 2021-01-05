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
                    String title = "🔢 The " + Bun.prefix + "math command",
                            desc = "Bun is able to do simple calculations\nFollowing `" + Bun.prefix
                                    + "math`, type a problem in the form: `<A> <operator> <B>`\nAnswers will be rounded to two decimal places",
                            op1 = "[ +  -  *  /  %  ^ ]";

                    EmbedBuilder help = new EmbedBuilder().setColor(0x61e885).setTitle(title).setDescription(desc)
                            .addField("Supported Operators", op1, false);

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
                        EmbedBuilder error = new EmbedBuilder().setColor(0xf05b5b).addField("Sorry",
                                "Bun did not recognize that operator!\nsupported operators [ +  -  *  /  %  ^ ]",
                                false);

                        event.getChannel().sendMessage(error.build()).queue();
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
        String title = "Bun was unable to compute", desc = "Please try `" + Bun.prefix + "math help` for more info!";

        EmbedBuilder error = new EmbedBuilder().setColor(0xf05b5b).setTitle(title).setDescription(desc);

        e.getChannel().sendMessage(error.build()).queue();
    }

    public void error_2(MessageReceivedEvent e) {
        String title = "Bun can only operate on numbers!",
                desc = "Please try `" + Bun.prefix + "math help` for more info!";

        EmbedBuilder error = new EmbedBuilder().setColor(0xf05b5b).setTitle(title).setDescription(desc);

        e.getChannel().sendMessage(error.build()).queue();
    }

    public void embed_answer(MessageReceivedEvent e, String[] args, double a, double b, double result) {
        DecimalFormat df = new DecimalFormat("#.##");

        String title = "Answer: " + df.format(a) + " " + args[2] + " " + df.format(b) + " = " + df.format(result),
                desc = "If bun's calculations are incorrect, please let ralf know!";

        EmbedBuilder answer = new EmbedBuilder().setColor(0x61e885).setTitle(title).setDescription(desc);
        e.getChannel().sendMessage(answer.build()).queue();
    }
}
