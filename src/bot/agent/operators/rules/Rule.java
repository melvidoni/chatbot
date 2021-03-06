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
     * @param r The object to be compared with.
     * @return true if the compared objects are equal, false otherwise.
     */
    public boolean equals(Rule r) {
        // If this is a rule return the id, otherwise false
        return ruleID.equals((r.getRuleID()));
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
     * Obtains the specificity of the rule for a given question. There are three
     * possibilities:
     *  - If the question asked is the same of the rule, the rate is 100%.
     *  - If the question asked is a subset of the question of rule, then the rate
     *      is the percentage of words of the question asked found on the question
     *      of the rule, rounded up.
     *  - Otherwise, it is zero.
     * @param questionAsked The question asked to be compared.
     * @return The specificity level of the rule for the question asked.
     */
    public int getSpecificityLevel(LinkedList<String> questionAsked) {
        // If they are equals, then max priority
        if( normalizedQuestion.equals(questionAsked) ) {
            return 100;
        }
        // If questionAsked is a subset
        else if(isSubset(questionAsked)) {
            // Get the rate
            Double rate = ( (double) questionAsked.size()) / normalizedQuestion.size();

            // Return the ceil of the rate*100
            return (int) Math.ceil(rate*100);

        }
        // TODO MAYBE REMOVE THIS
        else {
            // Get the amount of contained words
            int contained = containsWords(questionAsked);

            // If there are contained words...
            if(contained > 0) {
                // Get the rate
                Double halfRate = ( (double) contained) / normalizedQuestion.size();

                // Return the ceil of the rate*100
                return (int) Math.ceil(halfRate*100);
            }
            // Otherwise, it is too different, and we return 0
            else return 0;
        }
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
        // TODO I'M NOT SURE IF THIS IS WORKING
        else if(isSubset(questionAsked))
            return 65;
        else if(containsWords(questionAsked)>0)
            return 30;
        // Otherwise
        else
            return 0;
    }


    /**
     * Evaluates if the normalized questions contains the words of the question asked
     * and returns the amount of words found.
     * TODO I DON'T KNOW IF THIS IS SLOPPY OR IT SHOULD WORK LIKE THIS
     * @param questionAsked The question to compare.
     * @return The amount of words of questionAsked found in normalizedQuestion.
     */
    private int containsWords(LinkedList<String> questionAsked) {
        // Create a counter
        int containedWords = 0;

        // For each word asked
        for(String wordAsked : questionAsked) {
            // If found, we add one, otherwise, we add zero
            containedWords += normalizedQuestion.contains(wordAsked) ? 1 : 0;
        }

        // Return the value
        return containedWords;
    }



    /**
     * Evaluates if the question received as a parameter is a subset of
     * the normalized question on this rule.
     * @param questionAsked The question to compare.
     * @return true if it is a subset, false otherwise.
     */
    private boolean isSubset(LinkedList<String> questionAsked) {
        // If the normalized question is bigger
        if(normalizedQuestion.size() >= questionAsked.size()) {
            // Get an index for the normalized question
            int nqIndex = 0;

            // While this index is smaller than the size
            while( nqIndex < normalizedQuestion.size() ) {

                // If both first words are the same
                if( normalizedQuestion.get(nqIndex).equals(questionAsked.getFirst()) ) {
                    // Get index for both sentences
                    int copyIndex = nqIndex;
                    int qaIndex = 0;

                    // Get a flag to know if they are still equal
                    boolean equalsFlag = true;

                    // While both indexes are less than the size, and the words are equal
                    while(copyIndex<normalizedQuestion.size() && qaIndex<questionAsked.size() && equalsFlag) {
                        // If they are not equals
                        if( !normalizedQuestion.get(copyIndex).equals(questionAsked.get(qaIndex)) ) {
                            // Change the flag
                            equalsFlag = false;
                        }

                        // Increase the indexes
                        copyIndex++;
                        qaIndex++;
                    }

                    // If we got here, and the flag is true, return true!
                    if(equalsFlag) return true;

                    // Otherwise, keep checking!
                    nqIndex++;
                }
                // If they are not...
                else {
                    // Increase the index
                    nqIndex++;
                }
            }

        }

        /*
        Otherwise, the normalized question is smaller, and the asked cannot be a subset.
        On this case, return false.
         */
        return false;
    }




}
