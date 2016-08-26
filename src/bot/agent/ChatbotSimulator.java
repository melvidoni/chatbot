package bot.agent;


import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

/**
 * Main class that simulates the life cycle of the agent.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotSimulator extends SearchBasedAgentSimulator {
    /**
     * Statement read from the interface.
     */
    private String readStatement;

    /**
     * Flag to keep track on case of failure.
     */
    boolean failure;


    /**
     * Default constructor of the class.
     * @param environment The environment in which the agent will work.
     * @param agent The agent to be simulating.
     */
    public ChatbotSimulator(Environment environment, Agent agent) {
        super(environment, agent);
        readStatement = "";
    }



    /**
     * Main method for the simulator to analyze the situation and obtain an answer.
     */
    @Override
    public void start() {
        // TODO START AGENT
        System.out.println("ON SIMULATOR START");
    }




    /**
     * Inherited custom method to obtain a solution for the problem, by using
     * a particular action.
     * @param agent The agent under analysis.
     * @param action The action to be used
     */
    @Override
    public void actionReturned(Agent agent, Action action) {
        // TODO ACTION RETURNED
    }




    /**
     * Inherited method to obtain the agent's name.
     * @return The name of the agent.
     */
    @Override
    public String getSimulatorName() {
        return "ISI Chatbot Simulator";
    }



    /**
     * Method to obtain information if the agent failed.
     * @param actionReturned An action for the agent.
     * @return true if the agent failed, false otherwise.
     */
    @Override
    public boolean agentFailed(Action actionReturned) {
        return failure;
    }


    /**
     * Setter to replace the read statement for a new one.
     * @param s The new statement to use.
     */
    public void setReadStatement(String s) {
        readStatement = s;
    }

}
