package me.name.bot;

import me.name.bot.Commands.*;
import me.name.bot.Commands.Math;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Bun {

    private static String token;
    private static File prefix_file, token_file;

    public static JDA bun;
    public static String prefix;

    public static void main(String[] args) throws LoginException, InterruptedException {
        try {
            prefix_file = new File("src\\main\\java\\me\\name\\bot\\Resources\\prefix");
            token_file = new File("src\\main\\java\\me\\name\\bot\\Resources\\token");

            Scanner reader = new Scanner(prefix_file);
            Scanner reader2 = new Scanner(token_file);

            prefix = reader.next();
            token = reader2.next();

            reader.close();
            reader2.close();

            bun = JDABuilder.createDefault(token).build().awaitReady();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR");
        }

        bun.getPresence().setActivity(Activity.playing("with my toy carrot"));

        bun.addEventListener(new Commands(), new Clear(), new Math(), new UserInfo(), new Prefix(), new ServerInfo(),
                new Quote(), new Roll(), new Help());

        System.out.println("I finished building!");
    }

    public static void set_prefix(String p) throws Exception {
        if (p.length() < 3 && !p.isEmpty()) {
            FileWriter writer = new FileWriter(prefix_file);
            writer.write(p);
            prefix = p;
            writer.close();
        } else {
            throw new Exception("BadPrefix");
        }
    }
}