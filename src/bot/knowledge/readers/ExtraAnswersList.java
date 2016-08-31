package bot.knowledge.readers;


import bot.agent.operators.rules.Rule;
import bot.knowledge.auxialiary.GlossaryFilesLocation;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

import java.io.*;
import java.util.LinkedList;

/**
 * Class that reads the extra rules from a file, and then ads them to the current list.
 * It is singleton, and can only be instantiated once.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ExtraAnswersList {
    /**
     * Reference to the rule RR, in the language marked by the interface.
     */
    private Rule ruleRR;

    /**
     * Reference to the rule RE, in the language marked by the interface.
     */
    private Rule ruleRE;

    /**
     * Reference to the rule RM, in the language marked by the interface.
     */
    private Rule ruleRM;


    /**
     * An instance to itself
     */
    private static ExtraAnswersList instance = null;


    /**
     * Private default empty constructor of the class.
     */
    private ExtraAnswersList() {
        ruleRR = null;
        ruleRE = null;
        ruleRM = null;
    }


    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static ExtraAnswersList getInstance () {
        if(instance == null)
            instance = new ExtraAnswersList();
        return instance;
    }


    /**
     * Method that reads the extra rules from an external file, in the language offered
     * by the interface. When the agent starts, it initializes it on the default language.
     * @param language the language to be used.
     */
    public void loadExtraAnswers(String language) {
        try {
            // Create the file
            File extraRulesFile = new File(GlossaryFilesLocation.EXTRA_RULES_START.toString()
                                                + language.toLowerCase()
                                                + GlossaryFilesLocation.EXTRA_RULES_END.toString());

            // Construct BufferedReader from FileReader. Forced to UTF-8.
            BufferedReader br = new BufferedReader(new UTF8Reader(new FileInputStream(extraRulesFile)));

            // Prepare to read
            String line = null;
            // While there are lines to be read
            while( (line = br.readLine()) != null ) {
                // First, split the line in two
                String[] mainSplit = line.split("\\|");

                // Check with the ID
                switch (mainSplit[0]) {
                    case "RR": // Create the rule RR
                               ruleRR = new Rule(new LinkedList<>(), mainSplit[1], mainSplit[0]);
                               break;

                    case "RE": // Create the rule RE
                               ruleRE = new Rule(new LinkedList<>(), mainSplit[1], mainSplit[0]);
                               break;

                    case "RM": // Create the rule RM
                               ruleRM = new Rule(new LinkedList<>(), mainSplit[1], mainSplit[0]);
                               break;
                }
            }

            // Close the buffer
            br.close();
        }
        catch (FileNotFoundException e) {
            // TODO CHANGE THIS STACKTRACE
            e.printStackTrace();
        }
        catch(IOException e) {
            // TODO CHANGE THIS STACKTRACE
            e.printStackTrace();
        }
    }


    /**
     * Getter to obtain the information about the RR rule.
     * @return The data about rule RR in Rule format.
     */
    public Rule getRuleRR() {
        return ruleRR;
    }


    /**
     * Getter to obtain the information about the RE rule.
     * @return The data about rule RE in Rule format.
     */
    public Rule getRuleRE() {
        return ruleRE;
    }


    /**
     * Getter to obtain the information about the RM rule.
     * @return The data about rule RM in Rule format.
     */
    public Rule getRuleRM() {
        return ruleRM;
    }
}
