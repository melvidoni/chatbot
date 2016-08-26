package bot.agent.operators.rules;

import java.util.LinkedList;

/**
 * Class that represents each rule, and saves the list of
 * filtered questions that this rule is useful for, the id of
 * the rule and the answer that must be given to the user-
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class Rule {
    /**
     * Filtered question
     */
    private LinkedList<String> normalizedQuestion;

    /**
     * The answer that can be given
     */
    private String answer;

    /**
     * The identification of the rule
     */
    private String ruleID;



    /**
     * Default constructor of the class
     * @param q List of questions answered with this rule
     * @param a Answer given to the user
     * @param id ID of the rule that is instanced
     */
    public Rule(LinkedList<String> q, String a, String id) {
        answer = a;
        normalizedQuestion = q;
        ruleID = id;
    }


    /**
     * Convert this rule to a one line statement
     * @return the string that belongs to this rule
     */
    @Override
    public String toString() {
        return "QUESTION: " + normalizedQuestion + " HAS ANSWER: " + ruleID + ": " + answer;
    }



    /**
     * Overrides the default comparison to give the Rule a custom
     * comparison process.
     * @param obj The object to be compared with.
     * @return true if the compared objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // If this is a rule
        if(obj instanceof Rule)
            // Then compare the ids
            return ruleID.equals(((Rule) obj).getRuleID());
            // Otherwise it is false
        else return false;
    }



    /**
     * Getter to obtain the question on its filtered format
     * @return The question on filtered format
     */
    public LinkedList<String> getNormalizedQuestion() {
        return normalizedQuestion;
    }


    /**
     * Getter to obtain the answer on this rule.
     * @return The answer on this rule.
     */
    public String getAnswer() {
        return answer;
    }


    /**
     * Getter to obtain the id of the rule.
     * @return the id saved for this rule
     */
    public String getRuleID() {
        return ruleID;
    }


    /**
     * Calculates the specificity level of the background question. This is actually
     * given by the amount of words from the question asked that are contained
     * in the filtered question that identifies the rule's background.
     * @param questionAsked Question asked by the user after the filtering process.
     * @return Specificity level of this rule for the question.
     */
    public int getSpecificityLevel(LinkedList<String> questionAsked) {
        // Get a counter at zero
        int quantity = 0;

        // Now for each word
        // TODO THIS SPEC IS WRONG, THE NUMBER DEPENDS ON THE WORDS, IT SHOULD BE EVEN TO 100%
        for(String word: questionAsked)
            // If the word exists, then increase the count
            if(normalizedQuestion.contains(word)) quantity++;

        // Return the value
        return quantity;
    }



    /**
     * Obtains the priority of this rule. It actually is a dynamic priority
     * that depends on the question that was asked.
     * The priority is:
     *  - 100% if the question asked is the same to the background of the rule.
     *  - 50% if the question asked is an ordered subset of the filtered question.
     *  - 0% otherwise.
     * @param questionAsked Question asked by the user, that underwent the filtering process.
     * @return The dynamic priority of the rule.
     */
    public int getPriorityLevel(LinkedList<String> questionAsked) {
        // If the filtered question is the same than asked
        if(normalizedQuestion.equals(questionAsked))
            return 100;
        // If it is an ordered subset
        else if(subList(questionAsked))
            return 50;
            // Otherwise
        else
            return 0;
    }


    /**
     * Evaluates if the asked question is a subset of the filtered question
     * that composes the background of the rule.
     * @param questionAsked The question under study.
     * @return true if it is a subset, false otherwise.
     */
    private boolean subList(LinkedList<String> questionAsked) {
        // If this is a substring
        if(normalizedQuestion.size() < questionAsked.size())
            // Return false
            return false;
            // Otherwise...
        else {
            // For each word
            for(int i = 0; i< normalizedQuestion.size() && i+questionAsked.size() <= normalizedQuestion.size(); i++) {
                // If both first elements match
                if(this.normalizedQuestion.get(i).equals( questionAsked.getFirst()) ) {
                    // Get the subset
                    LinkedList<String> subset = new LinkedList(normalizedQuestion.subList(i, i + questionAsked.size()));

                    // If both subsets are equal
                    if(subset.equals(questionAsked))
                        // Return true
                        return true;
                }
            }
        }

        // Otherwise, return false
        return false;
    }


}
