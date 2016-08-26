package bot.agent.search;

import bot.agent.ChatbotAgentState;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

import java.util.LinkedList;

/**
 * Class that represents the cost function of a node in the tree.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class CostFunction implements IEstimatedCostFunction {


    /**
     * Method that calculates the cost for the node that
     * has be passed as a parameter.
     * @param node Node to get its cost calculated.
     */
    @Override
    public double getEstimatedCost(NTree node) {
        // Get the state on this node
        ChatbotAgentState chatbotState = (ChatbotAgentState) node.getAgentState();

        // Get some of the agent's parameters
        LinkedList<String> notAnalyzed = chatbotState.getNotAnalyzedWords();
        LinkedList<String> analyzed = chatbotState.getAnalyzedWords();
        LinkedList<String> found = chatbotState.getFoundWords();

        // If it is the same as the goal...
        if(notAnalyzed.isEmpty() && analyzed.equals(found)) {
            // Return GOAL
            return 0;
        }
        // If this is the word to be expanded
        else if(!notAnalyzed.isEmpty() && analyzed.equals(found)) {
            // Return EXPAND
            return 1;
        }
        // If it is none
        else {
            // Return DIFFERENT
            return 2;
        }
    }


}
