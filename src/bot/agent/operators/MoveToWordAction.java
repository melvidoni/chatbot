package bot.agent.operators;


import bot.agent.operators.rules.Rule;
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




    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        // TODO COMPLETE ACTION EXECUTE
        return null;
    }





    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        // TODO COMPLETE ACTION EXECUTE
        return null;
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
