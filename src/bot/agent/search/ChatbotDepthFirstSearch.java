package bot.agent.search;

import frsf.cidisi.faia.solver.search.DepthFirstSearch;

/**
 * Depth first search. It is dummy, because it is already implemented on FAIA.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotDepthFirstSearch extends DepthFirstSearch {

    /**
     * Method to obtain the search as a string.
     * @return The name of the string.
     */
    @Override
    public String getStrategyName() {
        return "Chatbot Depth First Search";
    }
}
