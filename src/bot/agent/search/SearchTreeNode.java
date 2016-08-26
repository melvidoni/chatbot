package bot.agent.search;

import bot.agent.ChatbotAgentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.solver.search.Search;

import java.util.LinkedList;

/**
 * Class that represents a node on the search tree that is used by the
 * ISIChatbot. Extends NTree and has its own parameters.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class SearchTreeNode extends NTree{
    /**
     * The word that is part of this node.
     */
    private String word;

    /**
     * A list of rules for the node.
     */
    private LinkedList<String> rules;


    /**
     * Default constructor of the class.
     */
    public SearchTreeNode() {
        super();
        word = "";
        rules = new LinkedList<String>();
    }


    /**
     * Constructor that initializes the instance with the values that
     * were received as parameters.
     * @param w Received word.
     * @param r List of rules.
     * @param firstNode First node of the tree.
     * @param action Search action.
     * @param cstate Chatbot state.
     * @param order Order of the current node.
     */
    public SearchTreeNode(String w, LinkedList<String> r, NTree firstNode,
                          SearchAction action, ChatbotAgentState cstate, int order) {
        // Call parent constructor
        super(firstNode, action, cstate, order);

        // Initialize the elements
        word = w;
        rules = r;
    }


    /**
     * Setter for the variable word.
     * @param w New value to be assigned to the variable.
     */
    public void setPalabra(String w) {
        word = w;
    }


    /**
     * Getter for the variable word.
     * @return Current value of the word.
     */
    public String getWord() {
        return word;
    }


    /**
     * Setter for the rules of the nodes. It changes the current rules
     * to be exactly as the ones received as parameters. Previous existing
     * rules are removed from the node.
     * @param r New rules to be assigned.
     */
    public void setRules(LinkedList<String> r) {
        rules = r;
    }


    /**
     * Adds a new rule to the ones already existent on the node. It does not
     * remove any rule, and does not check if the rule already exist.
     * @param r New rule to be added.
     */
    public void addRule(String r) {
        rules.add(r);
    }


    /**
     * Getter to obtain the current rules of the node.
     * @return current rules of the node.
     */
    public LinkedList<String> getRules() {
        return rules;
    }

}
