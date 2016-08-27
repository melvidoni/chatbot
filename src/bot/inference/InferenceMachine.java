package bot.inference;


import bot.agent.operators.rules.Rule;
import bot.knowledge.record.Record;

import java.util.LinkedList;
import java.util.Random;

/**
 * Class that selects the rule used to answer the received question.
 * For this, it applies different filters to select it.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class InferenceMachine {

    /**
     * The list of used strategies.
     */
    private LinkedList<String> usedFilters;

    /**
     * The rule selected to answer
     */
    private Rule successfulRule;

    /**
     * The given answer
     */
    private String givenAnswer;



    /**
     * A reference to itself, because it is singleton
     */
    private static InferenceMachine instance = null;



    /**
     * Default private empty constructor of the class
     */
    private InferenceMachine() { }


    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static InferenceMachine getInstance () {
        if(instance == null)
            instance = new InferenceMachine();
        return instance;
    }





    public void selectAnswer(LinkedList<Rule> foundRules, LinkedList<String> notAnalyzedWords) {
        // Initialize everything
        usedFilters = new LinkedList<>();
        givenAnswer = "";
        successfulRule = null;


        // TODO COMPLETE THIS THING HERE

    }




    /**
     * Filter that randomly selects one of the available rules and returns
     * it as an aswer. Uses Random from java utils.
     * @param rulesToFilter The list of available rules.
     * @return A randomly selected rule.
     */
    private Rule randomFilter(LinkedList<Rule> rulesToFilter) {
        // Get a random number
        Random random = new Random();

        /*
        int maxValue = rulesToFilter.size() - 1;
        int minValue = 0;
        random.nextInt( maxValue - minValue + 1 );
        random.nextInt( rulesToFilter.size() - 1 - 0 + 1 );
        random.nextInt( rulesToFilter.size() - 1     + 1 );
        random.nextInt( rulesToFilter.size() );
         */
        return rulesToFilter.get( random.nextInt( rulesToFilter.size()) );
    }



    /**
     * Filter that selects those rules that have not been used before to answer
     * any other given question during the chat.
     * @param rulesToFilter The rules to filter.
     * @return A subset of the available rules that are unused.
     */
    private LinkedList<Rule> noDuplicatesFilter(LinkedList<Rule> rulesToFilter) {
        // Create a new list
        LinkedList<Rule> unusedRules = new LinkedList<>();

        // For each available rule
        for(Rule aRule: rulesToFilter) {
            // If this rule is not on the record
            if( !Record.getInstance().isRuleUsed(aRule.getRuleID()) ) {
                // Then add it to the list
                unusedRules.addLast(aRule);
            }
            // Otherwise, ignore this rule
        }

        // Return the list of unused rules
        return unusedRules;
    }




    /**
     * Filters the rules by specificity while removing those that have a zero specificity.
     * @param rulesToFilter The list of rules to be filtered.
     * @param questionAsked The normalized question asked.
     * @return A list of rules filtered by specificity.
     */
    private LinkedList<Rule> specificityFilter(LinkedList<Rule> rulesToFilter, LinkedList<String> questionAsked) {
        // Call the better rules method.
        return betterRules(rulesToFilter, questionAsked, true);
    }





    /**
     * Filters the rules by their priority while removing those that have a zero priority.
     * @param rulesToFilter The list of rules to be filtered.
     * @param questionAsked The normalized question asked.
     * @return a list of rules filtered by priority.
     */
    private LinkedList<Rule> priorityFilter(LinkedList<Rule> rulesToFilter, LinkedList<String> questionAsked) {
        // Call the better rules method
        return betterRules(rulesToFilter, questionAsked, false);
    }


    /**
     * Method that given a list of rules, filters those that have a level of zero, and leaves only
     * those with the biggest matching level.
     * @param rulesToFilter The list of rules to be filtered.
     * @param questionAsked The normalized question asked.
     * @param specificityFilter This is true if the method should filter by specificity. If this is
     *                          false, then the method filters by priority. This should be changed if more
     *                          filters with levels need to be added to this method.
     * @return A list of rules filtered with the selected criteria (specificity or priority).
     */
    private LinkedList<Rule> betterRules(LinkedList<Rule> rulesToFilter, LinkedList<String> questionAsked,
                                            boolean specificityFilter) {


        // Prepare the list of filtered rules
        LinkedList<Rule> filteredRules = new LinkedList<>();

        /*
        Prepare a flag for the current maximum value. Because this starts as one,
        the filter will directly ignore any rule that has a value of zero.
         */
        int currentMaxValue = 1;

        // Now, for each rule to filter
        for(Rule aRule: rulesToFilter) {
            // If this is for specificity, then get that level, otherwise get priority level
            // TODO THIS SHOULD CHANGE IF MORE FILTERS WITH LEVELS ARE ADDED
            int ruleValue = specificityFilter ? aRule.getSpecificityLevel(questionAsked) : aRule.getPriorityLevel(questionAsked);

            // If the current rule's priority is the same as the current maximum
            if(currentMaxValue == ruleValue) {
                // Add the rule at the bottom and do not change anything
                filteredRules.addLast(aRule);
            }
            // Otherwise, if it is bigger
            else if(ruleValue > currentMaxValue) {
                // Set this as the maximum
                currentMaxValue = ruleValue;

                // Restart the list
                filteredRules = new LinkedList<>();

                // An add the element
                filteredRules.addLast(aRule);
            }
            /*
            On the case where the rule's priority is smaller than the current maximum value
            it gets ignored and it is not considered for this.
             */
        }

        // Return the ordered list
        return filteredRules;
    }






    /**
     * Getter to obtain the list of used strategies
     * @return A list of used strategies
     */
    public LinkedList<String> getUsedFilters() {
        return usedFilters;
    }


    /**
     * Getter to obtain the reference to the selected rule.
     * @return The selected rule.
     */
    public Rule getSuccessfulRule() {
        return successfulRule;
    }


    /**
     * Getter to obtain the given answer.
     * @return The given answer, in a format understood by the user.
     */
    public String getGivenAnswer() {
        return givenAnswer;
    }

}
