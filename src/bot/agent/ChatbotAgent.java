package bot.agent;


import bot.agent.operators.MoveToWordAction;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Search;
import gui.language.bundles.BundlesKeywords;
import gui.view.ExceptionAlert;
import java.util.LinkedList;
import java.util.Vector;



/**
 * Class representing the state of the chatbot agent. Extends the
 * FAIA class SearchBasedAgentState.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotAgent extends SearchBasedAgent{
    /**
     * A reference to the search solver used by the agent. Uses
     * the FAIA class Search.
     * @see Search
     */
    private Search searchSolver;



    /**
     * Default constructor of the class.
     */
    public ChatbotAgent(LinkedList<MoveToWordAction> ops) {
        // Call super
        super();

        // Now read the operators
        Vector<SearchAction> operators = new Vector<>();
        operators.addAll( ops );

        // Create a new state
        ChatbotAgentState state = new ChatbotAgentState();
        this.setAgentState(state);

        // Create the problem
        this.setProblem( new Problem(new ChatbotGoal(), state, operators) );
    }




    /**
     * This is a method executed by the simulator to ask the agent for an
     * action.
     * @return The action chosen by the agent.
     */
    @Override
    public MoveToWordAction selectAction() {
        // Get the search strategy
        DepthFirstSearch searchStrategy = new DepthFirstSearch();
        searchSolver = new Search(searchStrategy);

        // Do not show the search tree
        searchSolver.setVisibleTree(Search.WHITHOUT_TREE);
        this.setSolver(searchSolver);

        // Now run the action selection process
        MoveToWordAction selectedAction = null;
        try {
            selectedAction = (MoveToWordAction) this.getSolver().solve( new Object[] {this.getProblem()} );
        }
        catch (Exception e) {
            // Show an exception alert
            ExceptionAlert.showExceptionAlert(BundlesKeywords.EXCEPTION_STACKTRACE_2);
        }

        // And return the selected action
        return selectedAction;
    }




    /**
     * Because the agent just "saw" something (in this case that a new question
     * appeared on the environment), then it must update its state to acknowledge
     * that change and act upon it.
     * @param p The perception that the agent saw.
     */
    @Override
    public void see(Perception p) {
        // Simply update the state of the agent to understand this perception
        state.updateState(p);
    }


    /**
     * Method to obtain the best path of actions to arrive at an
     * answer. This are obtained from the search solved.
     * @return Best path of actions if successful, empty path otherwise.
     */
    public Vector<NTree> getActionsPath() {
        try {
            // If this works well, return from the search solver
            return searchSolver.getBestPath();
        }
        catch(Exception e) {
            // If it goes wrong, return an empty list
            return new Vector<>();
        }
    }
}
