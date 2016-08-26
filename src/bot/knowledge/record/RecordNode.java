package bot.knowledge.record;

/**
 * Class that represents a node inside the record/log of chat of the robot.
 * Contains the question asked and the identification of the rule used in
 * the answer.
 *
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class RecordNode {

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
     * The criteria used to answer.
     */
    private String criteria;


    /**
     * Default constructor of the class
     */
    public RecordNode() {
        question = "";
        ruleID = "";
        answer = "";
        criteria = "";
    }


    /**
     * Constructor that initializes the values of the node, with those received
     * as parameters.
     * @param q Question written in the environment..
     * @param id Identification of the given answer.
     * @param a The given answer, in text.
     * @param c Criteria used to select the answer.
     */
    public RecordNode(String q, String id, String a, String c) {
        question = q;
        ruleID = id;
        answer = a;
        criteria = c;
    }


    /**
     * Method to print a node with the format required in the log that is
     * generated on a TXT file.
     * @return String with the node in printing format.
     */
    public String print() {
        // Create the string to be return
        String nodeToPrint = "";

        // Agrego los componentes
        nodeToPrint = RecordLanguage.QUESTION.getEnglishWord() + ": " + question + "\r\n";
        nodeToPrint = nodeToPrint + RecordLanguage.RULE.getEnglishWord() + ": " + ruleID + "\r\n";
        nodeToPrint = nodeToPrint + RecordLanguage.ANSWER.getEnglishWord() + ": " + getAnswer() + "\r\n";
        nodeToPrint = nodeToPrint + RecordLanguage.CRITERIA.getEnglishWord() + ": " + criteria;

        // Devuelvo el string
        return nodeToPrint;
    }


    /**
     * Getter to obtain the question on this node.
     * @return Current question on the node
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Setter to replace the current question with a new one.
     * @param q New question to be stored in the node.
     */
    public void setQuestion(String q) {
        question = q;
    }

    /**
     * Getter to obtain the id currently stored on the node.
     * @return Currently stored ID on the node.
     */
    public String getRuleID() {
        return ruleID;
    }

    /**
     * Setter to replace the currently stored rule id on the node
     * and change it for a new one.
     * @param rid New ID to be stored on the node.
     */
    public void setRuleID(String rid) {
        ruleID = rid;
    }

    /**
     * Setter to set a new answer on the node. It replaces the previously
     * stored answer, and that one cannot be retrieved again.
     * @param a New textual answer to be stored.
     */
    public void setAnswer(String a) {
        answer = a;
    }

    /**
     * Getter to obtain the current answer stored on the node.
     * @return The answer (text form) stored on the node.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter to replace the current criteria on the node, for a new one
     * that matches the value passed as a parameter.
     * @param c New criteria to be stored on the node.
     */
    public void setCriteria(String c) {
        criteria = c;
    }

    /**
     * Getter to obtain the current criteria stored on the node.
     * @return The criteria of the node.
     */
    public String getCriteria() {
        return criteria;
    }



}
