package bot.agent;


import bot.agent.operators.rules.OperatorRulesMap;
import bot.agent.operators.rules.Rule;
import bot.knowledge.auxialiary.WordNormalizer;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.LinkedList;

/**
 * Class representing the state of the chatbot agent. Extends the
 * FAIA class SearchBasedAgentState.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotAgentState extends SearchBasedAgentState {

    private LinkedList<String> analyzedWords;
    private LinkedList<String> notAnalyzedWords;
    private LinkedList<Rule> foundRules;
    private LinkedList<String> foundWords;
    private String currentWord;
    private boolean unknownState = false;


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
        analyzedWords = new LinkedList<>();
        notAnalyzedWords = new LinkedList<>();
        foundRules = new LinkedList<>();
        foundWords = new LinkedList<>();
        currentWord = "";
        unknownState = false;
    }






    @Override
    public void updateState(Perception p) {
        try {
            /*
            Since when the state is updated the agent just got a new perception,
            then we need to clear everything and start again.
             */

            // No word has been analyzed, so clear them
            notAnalyzedWords.clear();
            notAnalyzedWords = WordNormalizer.normalizeSentence( ((ChatbotPerception) p).getQuestionSentence().split(" ") );

            // So far, it is not unknown
            unknownState = false;

            // TODO REMOVE PRINT LINES
            System.out.println("\tThe agent saw: " + notAnalyzedWords);

            // Because nothing is analyzed, the agent takes the first word
            currentWord = notAnalyzedWords.removeFirst();

            // Now it also needs to update what it is analyzing
            analyzedWords.clear();
            analyzedWords.addFirst(currentWord);

            // Renews the words that it found on the graph
            foundWords.clear();
            foundWords.add(currentWord);

            // And get possible rules for this word
            foundRules.clear();
            foundRules.addAll(OperatorRulesMap.getInstance().getRulesOperators(currentWord));

            System.out.println("\tAnd so far it found: " + foundRules);
        }
        catch(Exception e) {
            unknownState = true;

            System.out.println("UNKNOWN STATE WARNING");
        }





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
        clonedState.setAnalyzedWords( (LinkedList<String>) analyzedWords.clone() );
        clonedState.setNotAnalyzedWords( (LinkedList<String>) notAnalyzedWords.clone() );
        clonedState.setFoundRules( (LinkedList<Rule>) foundRules.clone() );
        clonedState.setFoundWords( (LinkedList<String>) foundWords.clone() );
        clonedState.setCurrentWord( currentWord );
        clonedState.setUnknownState( unknownState );

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
        if( !analyzedWords.equals(otherState.getAnalyzedWords())
                || !notAnalyzedWords.equals(otherState.getNotAnalyzedWords())
                || !currentWord.equals(otherState.getCurrentWord())
                || !foundRules.equals(otherState.getFoundRules())
                || !foundWords.equals(otherState.getFoundWords())
                || unknownState != otherState.isUnknownState() ) {
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
        return "CURRENT STATE" + "\n\tAnalyzed question: " + analyzedWords
                        + "\n\tNot analyzed question: " + notAnalyzedWords
                        + "\n\tCurrent word: " + currentWord
                        + "\n\tFound words: " + foundWords
                        + "\n\tFound rules: " + foundRules
                        + "\n\tUnknown state: " + unknownState;
    }


    /**
     * Getter to obtain the current analyzed question.
     * @return The new question under analysis.
     */
    public LinkedList<String> getAnalyzedWords() {
        return analyzedWords;
    }


    /**
     * Setter to change the current question under analysis.
     * It replaces the previous one.
     * @param aq New question to be analyzed.
     */
    public void setAnalyzedWords(LinkedList<String> aq) {
        analyzedWords = aq;
    }


    /**
     * Getter to obtain the current not analyzed question.
     * @return The new not analyzed question.
     */
    public LinkedList<String> getNotAnalyzedWords() {
        return notAnalyzedWords;
    }


    /**
     * Setter to replace the current not analyzed question with
     * a new not analyzed question
     * @param naq The new question to replace the previous one.
     */
    public void setNotAnalyzedWords(LinkedList<String> naq) {
        notAnalyzedWords = naq;
    }


    /**
     * Getter to obtain the current rules of the state.
     * @return Current rules of the state;
     */
    public LinkedList<Rule> getFoundRules() {
        return foundRules;
    }


    /**
     * Setter to replace all of the existent rules, with new ones.
     * @param fr New rules to replace previous ones.
     */
    public void setFoundRules(LinkedList<Rule> fr) {
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


    /**
     * Getter to obtain the list of words that were found in the
     * parth the agent followed through the graph.
     * @return The list of words on the path.
     */
    public LinkedList<String> getFoundWords() {
        return foundWords;
    }


    /**
     * Setter to change the words of the path into a new different
     * list of words. It replaces the previous ones.
     * @param fw New list of words.
     */
    public void setFoundWords(LinkedList<String> fw) {
        foundWords = fw;
    }


    /**
     * Getter to obtain if this is an unknown state.
     * @return true if this is unknown, false otherwise.
     */
    public boolean isUnknownState() {
        return unknownState;
    }


    /**
     * Setter to change the value of the unknown state.
     * @param us New value to be set.
     */
    public void setUnknownState(boolean us) {
        unknownState = us;
    }


    /**
     * Method to add a new found word to the current list of words.
     * it does not replace the existent words.
     * @param word Word to be added.
     */
    public void addFoundWord(String word) {
        foundWords.addLast(word);
    }
}
