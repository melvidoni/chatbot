package bot.agent;


import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

/**
 * Class that represents the perceptions that the agent has regarding
 * what happens on the environment. Extends Perception from FAIA.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotPerception extends Perception {

    /**
     * Question entered to the agent
     */
    private String questionSentence;



    /**
     * Empty default constructor
     */
    public ChatbotPerception() {
        // Call the parent's constructor
        super();
        questionSentence = "";
    }


    /**
     * Default constructor that initializes the perception with the agent
     * and the environments.
     * @param agent The agent to work with.
     * @param environment The environment in which the agent is localted.
     */
    public ChatbotPerception(Agent agent, Environment environment) {
        // Call the parent's constructor
        super(agent, environment);
        questionSentence = "";
    }


    /**
     * Method that initializes the perception, in order to get a clean slate.
     * @param a The agent to work with.
     * @param e The environment where everything happens.
     */
    @Override
    public void initPerception(Agent a, Environment e) {
        // Cast the state
        ChatbotEnvironment environment = (ChatbotEnvironment) e;

        // Set the statement
        questionSentence = environment.getQuestionAsked();
    }


    /**
     * Getter to obtain the current question on the perception.
     * @return The current question.
     */
    public String getQuestionSentence() {
        return questionSentence;
    }



    /**
     * Setter to set a new question as a perception.
     * @param qs The new question to be set.
     */
    public void setQuestionSentence(String qs) {
        questionSentence = qs;
    }
}
