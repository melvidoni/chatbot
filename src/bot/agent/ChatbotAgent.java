package bot.agent;


import bot.knowledge.graph.Graph;
import bot.readers.GraphReader;
import bot.readers.UnimportantWords;
import bot.readers.synonyms.SynonymsList;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;



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

        // Create a new state
        state = new ChatbotAgentState();

        // Read secondary words
        UnimportantWords.getInstance().loadWords();
        SynonymsList.getInstance().loadSynonyms();

        // Read the graph
        GraphReader.loadGraph();
    }




    @Override
    public Action selectAction() {
        return null;
    }


    @Override
    public void see(Perception p) {

    }
}
