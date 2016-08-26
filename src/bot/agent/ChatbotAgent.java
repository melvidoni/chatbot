package bot.agent;


import bot.agent.operators.MoveToWordAction;
import bot.knowledge.graph.Graph;
import bot.readers.GraphReader;
import bot.readers.RulesReader;
import bot.readers.UnimportantWords;
import bot.readers.synonyms.SynonymsList;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;

import java.util.Vector;


/**
 * Class representing the state of the chatbot agent. Extends the
 * FAIA class SearchBasedAgentState.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotAgent extends SearchBasedAgent{

    /**
     * The graph with all the knowledge of the agent
     */
    private Graph glossaryGraph;

    /**
     * Default constructor of the class.
     */
    public ChatbotAgent() {
        // Call super
        super();

        // Read secondary words
        UnimportantWords.getInstance().loadWords();
        SynonymsList.getInstance().loadSynonyms();

        // Read the graph
        GraphReader.loadGraph();

        // Now read the operators
        Vector<SearchAction> operators = new Vector<>();
        operators.addAll( RulesReader.loadActionsList() );

        // Create a new state
        ChatbotAgentState state = new ChatbotAgentState();
        this.setAgentState(state);

        // Create the problem
        this.setProblem( new Problem(new ChatbotGoal(), state, operators) );
    }




    @Override
    public MoveToWordAction selectAction() {
        return null;
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
        ((ChatbotAgentState) state).updateState(p);
    }
}
