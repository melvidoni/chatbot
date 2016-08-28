package bot.agent;


import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

/**
 * Class to study if the current state is a goal state reached
 * by the agent. Extends GoalTest from FAIA.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotGoal extends GoalTest {


    /**
     * Method that overrides the parent' goal test, in order to check
     * if the current state is a goal state.
     * @param agentState The current state of the agent.
     * @return true if this is a goal state, false otherwise.
     */
    @Override
    public boolean isGoalState(AgentState agentState) {
        System.out.println("IS GOAL STATE");

        // Get the state casted
        ChatbotAgentState chatbotState = (ChatbotAgentState) agentState;

        // If we already analyzed everything...
        if( chatbotState.getNotAnalyzedWords().isEmpty() ) {
            System.out.println("IS EMPTY");

            // If the words we studied are the same than those we found
            if( chatbotState.getAnalyzedWords().equals(chatbotState.getFoundWords()) ) {
                // Then this is a goal state
                return true;
            }

            System.out.println("NOT EQUALS");
        }

        System.out.println("NOT EMPTY");

        // Otherwise, this is not a goal yet
        return false;
    }


}
