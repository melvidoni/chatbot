package bot.agent.operators;


import bot.agent.ChatbotAgentState;
import bot.agent.ChatbotEnvironmentState;
import bot.agent.ChatbotGoal;
import bot.agent.operators.rules.Rule;
import bot.inference.InferenceMachine;
import bot.knowledge.graph.Graph;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import java.util.LinkedList;




/**
 * Class that represents the structure of actions that will be used during the
 * search to understand the question asked and answer to it.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class MoveToWordAction extends SearchAction {

    /**
     * Word under study on this action.
     */
    private String word;


    /**
     * List of rules to answer.
     */
    private LinkedList<Rule> rulesList;




    /**
     * Default constructor of the class.
     * @param w Word to add to this action.
     */
    public MoveToWordAction(String w) {
        super();
        word = w;
        rulesList = new LinkedList<>();
    }


    /**
     * Updates the agent state and the real environment's state.
     * @param aState This is the agent's state to be updated.
     * @param eState This is the agent's environment to be updated.
     * @return The updated environment state.
     */
    @Override
    public EnvironmentState execute(AgentState aState, EnvironmentState eState) {
        // Execute the other action
        this.execute((SearchBasedAgentState) aState);

        // Cast the state and get the goal
        ChatbotAgentState chatbotState = (ChatbotAgentState) aState;
        ChatbotGoal chatbotGoal = new ChatbotGoal();

        // If this is a goal or unknown...
        if( chatbotGoal.isGoalState(chatbotState) || chatbotState.isUnknownState() ) {
            // Update the environment
            updateEnvironment(aState, eState);
        }

        // Return the environment's state
        return eState;
    }




    /**
     * Updates how the state will end on a node of the search tree, after applying
     * the operator action during the run of the search algorithm. It does not update
     * the real world state, only changes it for the search.
     * @param s The state of the agent
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        System.out.println("Execute falso");


        // Cast the state
        ChatbotAgentState chatbotState = (ChatbotAgentState) s;

        // Get the word to expand
        // TODO THIS IS WRONG? SHOULD IT BE THE CURRENT WORD?
        String wordToSearch = chatbotState.getFoundWords().getLast();

        // Now get the two lists of words
        LinkedList<String> analyzedWords = chatbotState.getAnalyzedWords();
        LinkedList<String> notAnalyzedWords = chatbotState.getNotAnalyzedWords();

        // If the word was not visited
        //      ...or if it is an unknown state
        //      ...or if there are no more words to analyze
        if( chatbotState.getFoundWords().contains(word)
                || chatbotState.isUnknownState()
                || chatbotState.getNotAnalyzedWords().isEmpty() ) {
            // Then we return null
            return null;
        }

        /*
        Now we have a complex situation.
        If the destination word belongs to the successors of the current word,
        then this action cannot be applied. Otherwise, it can also be a leaf word.
         */
        if(Graph.getInstance().successorsName(wordToSearch).contains(word) ) {

            System.out.println("Complex situation");

            // Add the word to the visited successors
            chatbotState.addFoundWord(word);

            // Add the current word to the list of analyzed words
            analyzedWords.addLast(notAnalyzedWords.removeFirst());

            // Update both lists
            chatbotState.setAnalyzedWords(analyzedWords);
            chatbotState.setNotAnalyzedWords(notAnalyzedWords);

            // Get the rules
            LinkedList<Rule> foundRules = chatbotState.getFoundRules();

            // And for each rule...
            for(Rule rule: rulesList) {
                // If the agent does not contain the rule
                if( !foundRules.contains(rule) ) {
                    // Then, add it last
                    foundRules.addLast(rule);
                }
            }

            // And update the rules
            chatbotState.setFoundRules(foundRules);

            // If all words were studied
            if( notAnalyzedWords.isEmpty() ) {
                // Put an empty word to expand
                chatbotState.setCurrentWord("");
            }
            // Otherwise
            else {
                // Set the new word
                chatbotState.setCurrentWord( notAnalyzedWords.getFirst() );
            }

            // And return the agent state
            return chatbotState;
        }
        // If this is not a successor
        else {
            return null;
        }
    }





    /**
     * This method updates the environment when the agent arrived to a goal state or to
     * an unknown state. On the first case, it answers according to the rules it found, and
     * in the second one, it will say it does not understand the question.
     * Also, it can understand the question but do not know how to answer it.
     * @param aState The agent's state.
     * @param eState The environment's state.
     */
    private void updateEnvironment(AgentState aState, EnvironmentState eState) {
        System.out.println("Reached update environment");

        // Get the goal and the state
        ChatbotGoal chatbotGoal = new ChatbotGoal();
        ChatbotAgentState chatbotState = (ChatbotAgentState) aState;


        System.out.println("After getting the state");

        // If this is a goal state...
        if( chatbotGoal.isGoalState(chatbotState) ) {
            // Get the environment's state
            ChatbotEnvironmentState chatbotEnvState = (ChatbotEnvironmentState) eState;

            // If there are no rules...
            if( chatbotState.getFoundRules().isEmpty() ) {
                System.out.println("Found rules empty");


                /*
                The agent understands the question, but cannot answer it.
                So it creates a new special rule to answer.
                 */
                // TODO CHECK HOW TO CHANGE THE LANGUAGE HERE
                Rule unknownAnswerRule = new Rule(null, "No puedo responderte porque no lo sé", "RR");

                // Update the record so it has the node
                chatbotEnvState.addNodeToRecord(unknownAnswerRule, "UNKNOWN ANSWER");
            }
            // Otherwise, we have rules!
            else {
                System.out.println("we have rules");


                /*
                This means the chatbot understood the question, studied it, and found at least
                one rule to be able to answer the question.
                 */

                // Get an inference machine
                InferenceMachine iMachine = InferenceMachine.getInstance();
                System.out.println("we got imachine");


                // Select an answer
                iMachine.selectAnswer(chatbotState.getFoundRules(), chatbotState.getFoundWords());

                System.out.println("iMachine selected answer?");

                // Get the elements
                Rule selectedRule = iMachine.getSuccessfulRule();
                LinkedList<String> usedFilters = iMachine.getUsedFilters();

                // Then, update the record on the environment
                // TODO THE USED STRATEGIES HAD A PARTICULAR CONVERSION METHOD
                chatbotEnvState.addNodeToRecord(selectedRule, usedFilters.toString());
            }
        }
        // Otherwise, we are not on a goal state
        else {
            System.out.println("else");

            /*
            On this case, the chatbot does not understand the question and cannot answer it
             */
            Rule cannotUnderstandRule = new Rule(null, "Lo siento, pero no te entiendo.", "RE");

            // Add it to the record anyways
            // TODO CHANGE LANGUAGE
            ((ChatbotEnvironmentState) eState).addNodeToRecord(cannotUnderstandRule, "CANNOT UNDERSTAND");
        }


        System.out.println("at the end of the method");
    }



    /**
     * Method that returns the cost of the action.
     * @return The action has no cost, so it always returns zero.
     */
    @Override
    public Double getCost() {
        return 0.0;
    }


    /**
     * Method that converts this action into a string.
     * @return The action on a one line string.
     */
    @Override
    public String toString() {
        return "Action: move to word \'" + word + "\'";
    }


    /**
     * Getter to obtain the current list of rules.
     * @return The current list of rules.
     */
    public LinkedList<Rule> getRulesList() {
        return rulesList;
    }


    /**
     * Setter to replace the current list of rules for a new one, without
     * saving the previous list.
     * @param rulesList New list of rules.
     */
    public void setRulesList(LinkedList<Rule> rulesList) {
        this.rulesList = rulesList;
    }
}
