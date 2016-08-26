package bot.agent;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * Created by Melina on 26/08/2016.
 */
public class ChatbotEnvironmentState extends EnvironmentState {
    /**
     * The statement written by the user
     */
    private String questionAsked;


    /**
     * Default constructor of the class.
     */
    public ChatbotEnvironmentState() {
        // Call the initialization
        initState();
    }


    /**
     * Custom method to initialize the environment.
     */
    @Override
    public void initState() {
        // Set a new empty question
        questionAsked = "";
    }


    /**
     * Custom method to obtain the environment state on a one line string.
     * @return The state on a one line string.
     */
    @Override
    public String toString() {
        return "Current question: " + questionAsked;
    }


    /**
     * Getter to obtain the current question.
     * @return The current question, as written by the user.
     */
    public String getQuestionAsked() {
        return questionAsked;
    }


    /**
     * Setter to change the question that appeared on the environment.
     * @param qa The question that appeared on the environment.
     */
    public void setQuestionAsked(String qa) {
        questionAsked = qa;
    }
}
