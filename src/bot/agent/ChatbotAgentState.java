package bot.agent;


import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.LinkedList;

/**
 * Class representing the state of the chatbot agent. Extends the
 * FAIA class SearchBasedAgentState.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotAgentState extends SearchBasedAgentState {

    private LinkedList<String> analyzedQuestion;
    private LinkedList<String> notAnalyzedQuestion;
    private LinkedList<String> foundRules;
    private String currentWord;


    /**
     * Default constructor of the class.
     */
    public ChatbotAgentState() {
        super();
        initState();
    }


    /**
     * Method to initialize the state as a clean slate.
     */
    @Override
    public void initState() {
        analyzedQuestion = new LinkedList<>();
        notAnalyzedQuestion = new LinkedList<>();
        foundRules = new LinkedList<>();
        currentWord = "";
    }




    @Override
    public void updateState(Perception p) {
        // TODO UPDATE AGENT STATE
    }



    /**
     * Generates another instance of the state. Clones the state
     * without modifying it. This is used for the search.
     * @return A clone of the chatbot's state.
     */
    @Override
    public ChatbotAgentState clone() {
        // Get a new variable
        ChatbotAgentState clonedState = new ChatbotAgentState();

        // Clone all the components
        clonedState.setAnalyzedQuestion( (LinkedList<String>) analyzedQuestion.clone() );
        clone().setNotAnalyzedQuestion( (LinkedList<String>) notAnalyzedQuestion.clone() );
        clonedState.setFoundRules( (LinkedList<String>) foundRules.clone() );
        clonedState.setCurrentWord( currentWord );

        // Return the new state
        return clonedState;
    }



    /**
     * Method that compares the current object to the one received as parameter
     * in other to check if they are equals.
     * @param obj The object to be compared with.
     * @return true if they are equals, false otherwise.
     */
    @Override
    public boolean equals (Object obj) {
        // Check if they are the same class
        if( !(obj instanceof ChatbotAgentState) ) {
            // Then they are not equals
            return false;
        }

        // Cast the object
        ChatbotAgentState otherState = (ChatbotAgentState) obj;

        // Otherwise, compare element by element
        if( !analyzedQuestion.equals(otherState.getAnalyzedQuestion())
                || !notAnalyzedQuestion.equals(otherState.getNotAnalyzedQuestion())
                || !currentWord.equals(otherState.getCurrentWord())
                || !foundRules.equals(otherState.getFoundRules()) ) {
            // If something is not equal, then return false
            return false;
        }

        // Otherwise, return true
        return true;
    }



    /**
     * Convers the state to a one line string.
     * @return The state converted to a one line string.
     */
    @Override
    public String toString() {
        // Prepare the new string
        return "CURRENT STATE" + "\n\tAnalyzed question: " + analyzedQuestion
                        + "\n\tNot analyzed question: " + notAnalyzedQuestion
                        + "\n\tCurrent word: " + currentWord
                        + "\n\tFound rules: " + foundRules;
    }


    /**
     * Getter to obtain the current analyzed question.
     * @return The new question under analysis.
     */
    public LinkedList<String> getAnalyzedQuestion() {
        return analyzedQuestion;
    }


    /**
     * Setter to change the current question under analysis.
     * It replaces the previous one.
     * @param aq New question to be analyzed.
     */
    public void setAnalyzedQuestion(LinkedList<String> aq) {
        analyzedQuestion = aq;
    }


    /**
     * Getter to obtain the current not analyzed question.
     * @return The new not analyzed question.
     */
    public LinkedList<String> getNotAnalyzedQuestion() {
        return notAnalyzedQuestion;
    }


    /**
     * Setter to replace the current not analyzed question with
     * a new not analyzed question
     * @param naq The new question to replace the previous one.
     */
    public void setNotAnalyzedQuestion(LinkedList<String> naq) {
        notAnalyzedQuestion = naq;
    }


    /**
     * Getter to obtain the current rules of the state.
     * @return Current rules of the state;
     */
    public LinkedList<String> getFoundRules() {
        return foundRules;
    }


    /**
     * Setter to replace all of the existent rules, with new ones.
     * @param fr New rules to replace previous ones.
     */
    public void setFoundRules(LinkedList<String> fr) {
        foundRules = fr;
    }



    /**
     * Getter to obtain the current word on the state.
     * @return The current word
     */
    public String getCurrentWord() {
        return currentWord;
    }


    /**
     * Setter to change the word under expansion on the state.
     * @param cw New word to be set.
     */
    public void setCurrentWord(String cw) {
        currentWord = cw;
    }
}
