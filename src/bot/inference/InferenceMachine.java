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


    /**
     * Method that selects an answer by applying different filters available to the machine. In
     * order, it applies specificity first, if failed or multiple answers applies priority, if failed
     * or multiple answers applies no duplication. If no duplication fails, applies random over priority,
     * but if no duplication succeeds with multiple answers, applies random over no duplication.
     * @param foundRules The list of currently available rules.
     * @param notAnalyzedWords The list of normalized words not yet analyzed.
     */
    public void selectAnswer(LinkedList<Rule> foundRules, LinkedList<String> notAnalyzedWords) {
        // Initialize everything
        usedFilters = new LinkedList<>();
        givenAnswer = "";
        successfulRule = null;

        /*
        Start with the specificity filter, in order to see what it has. Send the found rules, and
        the words not yet analyzed.
         */
        System.out.println("NOT ANALYZED WORDS ON INF MACHINE = " + notAnalyzedWords);
        LinkedList<Rule> rulesFilteredBySpecificity = this.specificityFilter(foundRules, notAnalyzedWords);
        // TODO CHANGE LANGUAGE?
        usedFilters.addLast(FiltersLanguages.SPECIFICITY.getEnglishWord());

        // If the selected rules are empty
        if( rulesFilteredBySpecificity.isEmpty() ) {
            // The chatbot did understood, but cannot answer
            // TODO CHANGE LANGUAGE IN HERE
            successfulRule = new Rule(null, "No puedo responderte porque no lo sé", "RR");

            // Set this as the given answer
            givenAnswer = successfulRule.getAnswer();
        }
        // If there is only one rule
        else if( rulesFilteredBySpecificity.size() == 1 ) {
            // Then send this rule
            successfulRule = rulesFilteredBySpecificity.getFirst();
            givenAnswer = successfulRule.getAnswer();
        }
        // But if there is more than one, we need to operate this
        else {
            /*
            Since we have several rules, we now need to apply another filter. The first filter
            to be used will be priority, since we have a lot of rules here (at least 2).
             */
            LinkedList<Rule> rulesFilteredByPriority = this.priorityFilter(rulesFilteredBySpecificity, notAnalyzedWords);
            // TODO CHANGE LANGUAGE
            usedFilters.addLast(FiltersLanguages.PRIORITY.getEnglishWord());

            // If no rules are available
            if( rulesFilteredByPriority.isEmpty() ) {
                // The chatbot did not understood, but it cannot answer
                // TODO CHANGE LANGUAGE IN HERE
                successfulRule = new Rule(null, "No puedo responderte porque no lo sé", "RR");

                // Set this as the given answer
                givenAnswer = successfulRule.getAnswer();
            }
            // If only one rule remains
            if( rulesFilteredByPriority.size() == 1 ) {
                // Store this rule
                successfulRule = rulesFilteredByPriority.getFirst();
                givenAnswer = successfulRule.getAnswer();
            }
            // Otherwise, there are more than one rule
            else {
                /*
                Because we still have several answers, let's get the chatbot to use an answer that
                was not previously used to answer before.
                 */
                LinkedList<Rule> rulesFilteredByNoDuplication = this.noDuplicatesFilter(rulesFilteredByPriority);

                // Like before, if no rule is availble
                if( rulesFilteredByNoDuplication.isEmpty() ) {
                    /*
                    On this case, this means that all answer were used before, so the chatbot will be
                    sassy and say that it already answered, by altering one of the existent rules.
                    */
                    // TODO CHANGE LANGUAGE
                    usedFilters.addLast(FiltersLanguages.NO_DUPLICATES_FAILED.getEnglishWord());

                    // Randomly select a rule among the used rules
                    Rule randomRule = this.randomFilter(rulesFilteredByPriority);
                    usedFilters.addLast(FiltersLanguages.RANDOM_USED.getEnglishWord());

                    // Change the answer and store it
                    givenAnswer = "Creo que ya te respondí... " + randomRule.getAnswer();
                    successfulRule = new Rule(randomRule.getNormalizedQuestion(), givenAnswer, randomRule.getRuleID()+"\'");
                }
                // Otherwise, we have rules
                else {
                    // And the filter was successful
                    // TODO CHANGE LANGUAGE
                    usedFilters.addLast(FiltersLanguages.NO_DUPLICATES_SUCCESSFUL.getEnglishWord());

                    // If we have only one rule
                    if( rulesFilteredByNoDuplication.size() == 1 ) {
                        // Store this rule
                        successfulRule = rulesFilteredByNoDuplication.getFirst();
                        givenAnswer = successfulRule.getAnswer();
                    }
                    // Otherwise, we have at least two, and we need to pick one
                    else {
                        // Select a random rule among the unused rules
                        successfulRule = this.randomFilter(rulesFilteredByNoDuplication);
                        givenAnswer = successfulRule.getAnswer();

                        // Add the filter
                        // TODO CHANGE LANGUAGE
                        usedFilters.addLast(FiltersLanguages.RANDOM_UNUSED.getEnglishWord());
                    }
                } // No duplicates else
            } // Priority else
        } // Specificity else
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
            System.out.println("QUESTION ASKED ON BETTER RULES = " + questionAsked);

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
