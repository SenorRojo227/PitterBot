package commands;

import net.dv8tion.jda.api.entities.Message;
import utils.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Roll extends Command {

    public String getCommandLabel() {
        return "Roll";
    }

    public String getDescription() {
        return "Tests the success of a Skill, Trait, or other probability-based effect.";
    }

    public CommandGroup getCommandGroup() {
        return null;
    }

    public void execute(Message message, String[] args) {

        final int NUM_SKILLS = 71, NUM_TRAITS = 32;
        String[][] skills = new String[NUM_SKILLS][9];
        String[] traits = new String[NUM_TRAITS];

        try {
            Scanner scan;

            //Skill list scan
            scan = new Scanner(new File("src/main/resources/skill_list.txt"));
            for (int i = 0; i < NUM_SKILLS; i++) {
                skills[i] = scan.nextLine().split("\t");
            }

            //Trait list scan
            scan = new Scanner(new File("src/main/resources/trait_list.txt"));
            traits = scan.nextLine().split(",");

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int roll = -1;
        for (String[] skill : skills) {
            if (args[1].equalsIgnoreCase(skill[1])) {
                if (skill[6].equals("-"))
                    roll = 100;
                else
                    roll = Integer.parseInt(skill[6]);
                break;
            }
        }

        for (String trait : traits) {
            if (args[1].equalsIgnoreCase(trait)) {
                switch(trait) {
                    case "Clumsy Power":
                        roll = 90;
                        break;
                    case "Gravity Pull":
                        roll = 25;
                        break;
                    case "Molter":
                        roll = 30;
                        break;
                    case "Static Body":
                        roll = 50;
                        break;
                }
            }
        }

        if (getSuccess(roll))
            Logger.info("Success!", "Roll");
        else if (roll < 0)
            Logger.info("Error: Could not locate " + args[1] + "!", "Roll");
        else
            Logger.info("Failure!", "Roll");

    }

    private static boolean getSuccess(int Acc) {
        int rand = new Random().nextInt(100) + 1;
        return rand <= Acc;
    }

}
