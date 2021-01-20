package luxsolis.commands.testing;

import luxsolis.commands.Command;
import luxsolis.commands.CommandGroup;
import luxsolis.utils.Logger;
import net.dv8tion.jda.api.entities.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* CommandGroup
 *      Provides the 'roll' command
 */

public class CommandRoll extends Command {
    private final int NUM_SKILLS = 71, NUM_TRAITS = 32;
    private String[][] skillList = new String[NUM_SKILLS][9];
    private String[] traitList = new String[NUM_TRAITS];

    private boolean initialisedLists = false;

    public String getCommandLabel() {
        return "roll";
    }

    public String getParameters() {
        return "[Skill/Trait] (Trait)";
    }

    public String getDescription() {
        return "Tests the success of a Skill, Trait, or other probability-based effect.";
    }

    public CommandGroup getCommandGroup() {
        return CommandGroup.TESTING;
    }

    public void execute(Message message, String[] args) {
        if (!initialisedLists) {
            Logger.warn("Skill and Trait lists haven't been initialised! Initialising them now...");

            try {
                Scanner scan;

                // Skill list scan
                scan = new Scanner(new File("src/main/resources/skill_list.txt"));
                for (int i = 0; i < NUM_SKILLS; i++) {
                    skillList[i] = scan.nextLine().split("\t");
                }

                // Trait list scan
                scan = new Scanner(new File("src/main/resources/trait_list.txt"));
                traitList = scan.nextLine().split(",");

                scan.close();
                initialisedLists = true;

                Logger.success("Successfully initialised Skill and Trait lists!");
            } catch (FileNotFoundException e) {
                Logger.error("An error occurred (" + e + ")", "ROLL");
            }
        }

        double acc = -1;    //Accuracy
        double crit = 6.25;   //Critical Hit
        String[] scnd = {"", "-1"};        //Secondary Effect


        ArrayList<String> skillName = new ArrayList<>();

        //Skill Check
        outerloop:
        for (int i = 0; i < args.length - 1; i++) {
            skillName.add(args[i+1]);
            for (String[] skill : skillList) {
                if (toString(skillName).equalsIgnoreCase(skill[1])) {
                    if (skill[6].equals("-")) {
                        acc = 100;
                    } else {
                        acc = Integer.parseInt(skill[6]);
                    }
                    break outerloop;
                }
            }
        }

        //Other Skill Checks
        switch(toString(skillName)) {
            case "Body Blast":
                scnd[0] = "[Coromon] got knocked down!";
                scnd[1] = "30";
                break;
            case "Boulder Barrage":
            case "Propellor Punch":
                scnd[0] = "[Coromon] got knocked down!";
                scnd[1] = "25";
                break;
            case "Bubble Burst":
                //scnd = ??
                break;
            case "Cinder":
                scnd[0] = "[Coromon] got burned!";
                scnd[1] = "10";
                break;
            case "Claw":
                scnd[0] = "[Coromon]'s defense was lowered!";
                scnd[1] = "10";
                break;
            case "Electric Pound":
                scnd[0] = "[Coromon] got knocked down!";
                scnd[1] = "6";
                //Multihit Check
                break;
            case "Face Slap":
                scnd[0] = "[Coromon] was confused!";
                scnd[1] = "50";
                break;
            case "Frost Chomp":
                scnd[0] = "[Coromon] was frozen!";
                scnd[1] = "10";
                break;
            case "Glacier":
                scnd[0] = "[Coromon] was frozen!";
                scnd[1] = "25";
                break;
            case "Piercing Horns":
                scnd[0] = "[Coromon] was burned!";
                scnd[1] = "20";
                break;
            case "Poison Chomp":
                scnd[0] = "[Coromon] was poisoned!";
                scnd[1] = "50";
                break;
            case "Poison Sting":
                scnd[0] = "[Coromon] was poisoned!";
                scnd[1] = "20";
                break;
            case "Quad Volt":
                scnd[0] = "[Coromon] was shocked!";
                scnd[1] = "10";
                break;
            case "Sandball":
                scnd[0] = "[Coromon]'s accuracy was lowered!";
                scnd[1] = "20";
                break;
            case "Scratch":
                crit *= 1.2;
                break;
            case "Shadewalker":
                scnd[0] = "[Coromon] was confused!";
                scnd[1] = "20";
                break;
            case "Spark Disc":
            case "Tail Spin":
                //Multi-hit Check
                break;
            case "Voodoo":
                //Voodoo Check
                break;

        }

        ArrayList<String> traitName = new ArrayList<>();

        //Trait Check
        if (acc < 0)
            skillName.clear();
        if (acc < 0 || !skillName.get(skillName.size() - 1).equalsIgnoreCase(args[args.length - 1])) {
            outerloop:
            for (int i = 0; i < args.length - 1; i++) {
                traitName.add(args[i + skillName.size() + 1]);
                for (String trait : traitList) {
                    if (toString(traitName).equalsIgnoreCase(trait)) {
                        switch (trait) {
                            case "Clumsy Power":
                                acc *= 0.9;
                                break;
                            case "Gravity Pull":
                                if (getSuccess(25))
                                    message.getChannel().sendMessage("The opposing [Coromon] was prevented from switching!").queue();
                                else
                                    message.getChannel().sendMessage("Nothing happened!").queue();
                                return;
                            case "Lucky":
                                crit *= 1.25;
                                break;
                            case "Molter":
                                if (getSuccess(30))
                                    message.getChannel().sendMessage("The [Coromon]'s status problems disappeared!").queue();
                                else
                                    message.getChannel().sendMessage("Nothing happened!").queue();
                                return;
                            case "Static Body":
                                if (getSuccess(50))
                                    message.getChannel().sendMessage("The [Coromon] was paralyzed by [Coromon]'s Static Body!").queue();
                                else
                                    message.getChannel().sendMessage("Nothing happened!").queue();
                                return;
                            default:
                                message.getChannel().sendMessage("**__Error:__**\nInvalid Skill/Trait").queue();
                                return;
                        }
                        break outerloop;
                    }
                }
            }
        }

        if (acc < 0) {
            message.getChannel().sendMessage("**__Error:__**\nInvalid Skill/Trait").queue();
        } else {
            message.getChannel().sendMessage("[Coromon] used " + toString(skillName) + "!").queue();
            if (!getSuccess(acc)) {
                message.getChannel().sendMessage("It missed!").queue();
            } else {
                if (getSuccess(crit))
                    message.getChannel().sendMessage("A critical hit!").queue();
                if (Integer.parseInt(scnd[1]) >= 0 && getSuccess(Integer.parseInt(scnd[1])))
                    message.getChannel().sendMessage(scnd[0]).queue();
            }
        }
    }

    private static boolean getSuccess(double roll) {
        int rand = new Random().nextInt(100) + 1;
        return rand <= roll;
    }

    private static String toString(ArrayList<String> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i);
            if (i < arr.size() - 1)
                str += " ";
        }
        return str;
    }

}
