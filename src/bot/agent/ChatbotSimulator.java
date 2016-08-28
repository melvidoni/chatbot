package bot.agent;


import bot.agent.operators.MoveToWordAction;
import bot.knowledge.record.Record;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;
import frsf.cidisi.faia.solver.search.NTree;

import java.util.Iterator;
import java.util.LinkedList;

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
    private boolean failure;

    /**
     * An answer to be given by the end of a simulation
     */
    private String finalAnswer;




    /**
     * Default constructor of the class.
     * @param environment The environment in which the agent will work.
     * @param agent The agent to be simulating.
     */
    public ChatbotSimulator(Environment environment, Agent agent) {
        super(environment, agent);
        readStatement = "";
        failure = false;
        finalAnswer = "";
    }


    /**
     * Main method for the simulator to analyze the situation and obtain an answer.
     */
    @Override
    public void start() {
        System.out.println("\n\nON SIMULATOR START");
        finalAnswer = "";

        // First, the environment changes to get a question
        ((ChatbotEnvironmentState) environment.getEnvironmentState()).setQuestionAsked(readStatement);

        System.out.println("\tRead statement: " + readStatement);

        // Get the chatbot and cast it, we can have multiple of them
        ChatbotAgent chatbot = (ChatbotAgent) agents.firstElement();

        // The chatbot must perceive what it is happening
        ChatbotPerception aPerception = new ChatbotPerception();
        aPerception.setQuestionSentence(readStatement);

        /*
        Now, the chatbot must see the perception and understand it
        by normalizing it under their rules
         */
        chatbot.see(aPerception);
        MoveToWordAction selectedAction = chatbot.selectAction();
        System.out.println("\tSelected action: " + selectedAction);

        // Obtain the path of operators
        LinkedList<NTree> searchTreeNodesList = new LinkedList<>();
        searchTreeNodesList.addAll( chatbot.getActionsPath() );

        // Get an iterator
        Iterator<NTree> searchTreeNodesIterator = searchTreeNodesList.iterator();

        // Do at least once
        do {
            // If the list is empty
            // TODO I HAVE MY DOUBTS ABOUT THIS, I THINK IT IS WRONG PLACED
            if( searchTreeNodesList.isEmpty() ) {
                // Mark as failure
                failure = true;

                // And set the unknown state
                ((ChatbotAgentState) chatbot.getAgentState()).setUnknownState(true);
                this.actionReturned(chatbot, new MoveToWordAction("..."));
            }
            // Otherwise, we have nodes
            else {
                // If there is a next node
                if(searchTreeNodesIterator.hasNext()) {
                    // Get that node
                    NTree node = searchTreeNodesIterator.next();

                    // And set this action
                    selectedAction = (MoveToWordAction) node.getAction();
                    this.actionReturned(chatbot, selectedAction);
                }
            }

        // Do this, while the agent has not yet succeeded or failed
        } while (!this.agentSucceeded(selectedAction) && !this.agentFailed(selectedAction));


        finalAnswer = Record.getInstance().getLastAnswer();

        System.out.println("\nAnswer: " +  finalAnswer );
        System.out.println();

        // Close the environment
        this.environment.close();

        // Launch simulationFinished event
        SimulatorEventNotifier.runEventHandlers(EventType.SimulationFinished, null);
    }




    /**
     * Inherited custom method to obtain a solution for the problem, by using
     * a particular action.
     * @param agent The agent under analysis.
     * @param action The action to be used
     */
    @Override
    public void actionReturned(Agent agent, Action action) {
        // Cast the action
        MoveToWordAction mtwAction = (MoveToWordAction) action;
        ChatbotAgent chatbotAgent = (ChatbotAgent) agent;

        // Update the state
        this.updateState(mtwAction);

        // Show the answer
        chatbotAgent.getSolver().showSolution();
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


    /**
     * Method to obtain the final answer of the simulation
     * @return the final answer of the simulation
     */
    public String getFinalAnswer() {
        return finalAnswer;
    }

}
