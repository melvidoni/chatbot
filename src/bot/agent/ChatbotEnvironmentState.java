package bot.agent;

import bot.agent.operators.rules.Rule;
import bot.knowledge.record.Record;
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
        Record.getInstance();
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



    /**
     * Method that adds a new node to the record, when the answer is already defined.
     * @param rule The rule used to answer.
     * @param criteria Criteria used to obtain the rule.
     */
    public void addNodeToRecord(Rule rule, String criteria) {
        // Add the current question to the node, with the info about the answer
        Record.getInstance().addNode(questionAsked, rule.getRuleID(), rule.getAnswer(), criteria);
    }

}
