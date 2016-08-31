package bot.agent;


import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

/**
 * Class that represents the environment in which the agent works.
 * Extends Environment from FAIA.
 * @see frsf.cidisi.faia.environment.Environment
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotEnvironment extends Environment{


    /**
     * Default constructor of the class.
     */
    public ChatbotEnvironment(){
        // Start the state of this environment
        this.environmentState = new ChatbotEnvironmentState();
    }


    /**
     * Method to obtain the perception of the environment.
     * @return Always null, because the environment has no perception.
     */
    @Override
    public Perception getPercept() {
        return null;
    }


    /**
     * Method to obtain the environment in a string format.
     * @return The environment in string format.
     */
    @Override
    public String toString() {
        return "Environment. " + environmentState.toString();
    }



    /**
     * Method to set a new question on the environment state.
     * @param q The question asked by the user.
     */
    public void setQuestionAsked(String q) {
        // Set the question on the state
        ((ChatbotEnvironmentState) environmentState).setQuestionAsked(q);
    }


    /**
     * A getter to obtain which one is the current question
     * @return the current question, as asked by the user.
     */
    public String getQuestionAsked() {
        // Obtain the current question on the state
        return ((ChatbotEnvironmentState) environmentState).getQuestionAsked();
    }

}
