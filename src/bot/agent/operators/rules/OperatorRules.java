package bot.agent.operators.rules;


import java.util.LinkedList;

/**
 * Establishes an association between the destination word of an operator
 * and its rules.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
class OperatorRules {
    /**
     * The operator word
     */
    private String operatorWord;

    /**
     * A list of related rules
     */
    private LinkedList<Rule> relatedRules;



    /**
     * Default constructor of the class
     * @param operatorName Destination word of the operator.
     */
    OperatorRules(String operatorName) {
        operatorWord = operatorName;
        relatedRules = new LinkedList<>();
    }



    /**
     * Add a new rule to the list of related rules.
     * @param question The background question for the rule.
     * @param answer The answer that must be given by the bot.
     * @param id The identification of the rule.
     */
    void addRule(LinkedList<String> question, String answer, String id) {
        // Create a new rule and add it
        relatedRules.addLast(new Rule(question, answer, id));
    }



    /**
     * Getter to obtain the word that identifies the operator.
     * @return Destination word of the operator
     */
    String getOperatorWord() {
        return operatorWord;
    }



    /**
     * Getter to obtain the rules related to the operator.
     * @return List of rules related to the operator
     */
    LinkedList<Rule> getRelatedRules() {
        return relatedRules;
    }





    /**
     * Method that checks if a rule with the same answer as the given as parameter already exists
     * on the operator. If found, it returns the ID, otherwise it returns an empty string.
     * @param anAnswer The answer in text for to look for on the operator's list.
     * @return If such rule was found, returns the ID, otherwise it returns an empty string.
     */
    String ifExistsGetRuleID(String anAnswer) {
        // Go through each available rule
        for(Rule aRule: relatedRules) {
            // If this rule has the same text
            if(aRule.getAnswer().equals(anAnswer)) {
                // Return its ID and cut down the method
                return aRule.getRuleID();
            }
        }

        // If we got here, the rule does not exists on this operator
        return "";
    }

}
