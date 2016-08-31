package bot.knowledge.record;

import gui.ViewFilesLocation;
import gui.language.bundles.BundlesKeywords;
import gui.language.CurrentLocale;

import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Class that represents a node inside the record/log of chat of the robot.
 * Contains the question asked and the identification of the rule used in
 * the answer.
 *
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
class RecordNode {

    /**
     * The question to be stored.
     */
    private String question;

    /**
     * The identification of the rule to be stored.
     */
    private String ruleID;

    /**
     * The answer given by the chatbot.
     */
    private String answer;

    /**
     * The filtersUsed used to answer.
     */
    private String filtersUsed;



    /**
     * Constructor that initializes the values of the node, with those received
     * as parameters.
     * @param q Question written in the environment..
     * @param id Identification of the given answer.
     * @param a The given answer, in text.
     * @param c Criteria used to select the answer.
     */
    RecordNode(String q, String id, String a, String c) {
        question = q;
        ruleID = id;
        answer = a;
        filtersUsed = c;
    }


    /**
     * Method to print a node with the format required in the log that is
     * generated on a TXT file.
     * @return String with the node in printing format.
     */
    LinkedList<String> print() {
        // Get the bundle for the locale
        ResourceBundle rBundle = ResourceBundle.getBundle(ViewFilesLocation.LOCALE_BUNDLE.toString(),
                CurrentLocale.getInstance().getLocale());

        // Create the node to print and return it
        LinkedList<String> nodeToPrint = new LinkedList<>();

        // Add the parts
        nodeToPrint.addLast( rBundle.getString(BundlesKeywords.RECORD_QUESTION.toString()) + ": " + question );
        nodeToPrint.addLast( rBundle.getString(BundlesKeywords.RECORD_RULE.toString()) + ": " + ruleID );
        nodeToPrint.addLast( rBundle.getString(BundlesKeywords.RECORD_ANSWER.toString()) + ": " + answer );
        nodeToPrint.addLast( rBundle.getString(BundlesKeywords.RECORD_FILTER.toString()) + ": " + filtersUsed );

        // Return the list
        return ( nodeToPrint );
    }


    /**
     * Getter to obtain the question on this node.
     * @return Current question on the node
     */
    String getQuestion() {
        return question;
    }

    /**
     * Setter to replace the current question with a new one.
     * @param q New question to be stored in the node.
     */
    void setQuestion(String q) {
        question = q;
    }

    /**
     * Getter to obtain the id currently stored on the node.
     * @return Currently stored ID on the node.
     */
    String getRuleID() {
        return ruleID;
    }

    /**
     * Setter to replace the currently stored rule id on the node
     * and change it for a new one.
     * @param rid New ID to be stored on the node.
     */
    void setRuleID(String rid) {
        ruleID = rid;
    }

    /**
     * Setter to set a new answer on the node. It replaces the previously
     * stored answer, and that one cannot be retrieved again.
     * @param a New textual answer to be stored.
     */
    void setAnswer(String a) {
        answer = a;
    }

    /**
     * Getter to obtain the current answer stored on the node.
     * @return The answer (text form) stored on the node.
     */
    String getAnswer() {
        return answer;
    }

    /**
     * Setter to replace the current filtersUsed on the node, for a new one
     * that matches the value passed as a parameter.
     * @param c New filtersUsed to be stored on the node.
     */
    void setFiltersUsed(String c) {
        filtersUsed = c;
    }

    /**
     * Getter to obtain the current filtersUsed stored on the node.
     * @return The filtersUsed of the node.
     */
    String getFiltersUsed() {
        return filtersUsed;
    }



}
