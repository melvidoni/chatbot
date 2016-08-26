package bot.agent.operators.rules;


import java.util.LinkedList;

/**
 * Establishes an association between the destination word of an operator
 * and its rules.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class OperatorRules {
    /**
     * The operator word
     */
    private String operatorWord;

    /**
     * A list of related rules
     */
    LinkedList<Rule> relatedRules;



    /**
     * Default constructor of the class
     * @param operatorName Destination word of the operator.
     */
    public OperatorRules(String operatorName) {
        operatorWord = operatorName;
        relatedRules = new LinkedList<Rule>();
    }



    /**
     * Add a new rule to the list of related rules.
     * @param question The background question for the rule.
     * @param answer The answer that must be given by the bot.
     * @param id The identification of the rule.
     */
    public void addRule(LinkedList<String> question, String answer, String id) {
        // Create a new rule and add it
        relatedRules.addLast(new Rule(question, answer, id));
    }



    /**
     * Getter to obtain the word that identifies the operator.
     * @return Destination word of the operator
     */
    public String getOperatorWord() {
        return operatorWord;
    }



    /**
     * Getter to obtain the rules related to the operator.
     * @return List of rules related to the operator
     */
    public LinkedList<Rule> getRelatedRules() {
        return relatedRules;
    }

}
