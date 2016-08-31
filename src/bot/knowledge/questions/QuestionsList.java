package bot.knowledge.questions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


/**
 *
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class QuestionsList {
    /**
     * A list of all available questions
     */
    private LinkedList<SynonymQuestions> availableQuestions;

    /**
     * A list of asked questions
     */
    private LinkedList<SynonymQuestions> usedQuestions;

    /**
     * A reference to itself, because it is singleton
     */
    private static QuestionsList instance = null;


    /**
     * Default constructor of the class
     */
    private QuestionsList() {
        availableQuestions = new LinkedList<>();
        usedQuestions = new LinkedList<>();
    }


    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static QuestionsList getInstance () {
        if(instance == null)
            instance = new QuestionsList();
        return instance;
    }


    /**
     * Method that adds a new group of synonym questions to the available list.
     * @param synonymQuestions new group of questions to add.
     */
    public void addSynonymQuestions(LinkedList<String> synonymQuestions) {
        // Create the node list
        SynonymQuestions nodesList = new SynonymQuestions();

        // Add all the questions
        nodesList.addSynonymQuestions(synonymQuestions);

        // Then add the node
        availableQuestions.addLast(nodesList);
    }




    /**
     * Method that clears all the used questions, by adding them back to the
     * list of available questions. This method avoids re-loading the file
     * with the questions.
     */
    public void clearQuestionsUsed() {
        // Add all the used questions back on the available ones
        availableQuestions.addAll(usedQuestions);

        // And clear the used ones
        usedQuestions = new LinkedList<>();
    }



    /**
     * Method that given an statement entered by the user, compares it to
     * the list of available questions (in normalized mode). If found, then
     * it adds it to the list of used and removes it from available. If not
     * found, ignores the statement and does nothing.
     * @param readStatement The statement to be compared.
     */
    public void setUsedQuestion(String readStatement) {
        // Save the set found, and use it as a flag
        SynonymQuestions foundSet = null;

        // Go through each set of synonym
        Iterator<SynonymQuestions> iterator = availableQuestions.iterator();

        // While there are questions and the set was not found
        while(foundSet==null && iterator.hasNext()) {
            // Get the next node
            SynonymQuestions qSet = iterator.next();

            // If this question is the one asked
            if(qSet.containsQuestion(readStatement)) {
                // Update the found set flag
                foundSet = qSet;
            }
        }

        // If we are here, if something was found
        if(foundSet!=null) {
            // Add it to the used questions
            usedQuestions.addLast(foundSet);

            // And remove it from the available questions
            availableQuestions.remove(foundSet);
        }
    }


    /**
     * Randomly suggest a question. For this, it simply randomly selects
     * a set of synonym questions, and then ask the set to pick one of
     * the questions.
     * If there are available unused questions, then those are picked,
     * otherwise, it selects from the used questions.
     * @return a randomly selected question.
     */
    public String suggestQuestion() {
        // If there are available questions
        if(!availableQuestions.isEmpty()) {
            // Get a random number
            int randomNumber = (new Random()).nextInt(availableQuestions.size());

            /*
            At this point we do not pass the question from a list to another. Either if the
            user presses enter or if the question is automatically answered, it will be removed
            later. If the question needs enter input, then it will not be marked as used until
            the user effectively asks it (by pressing enter).
             */

            // Get the first synonym question
            return availableQuestions.get(randomNumber).suggestQuestion();
        }
        // If all questions were used
        else {
            // Then, there are used questions, so we should use this
            int randomUsedNumber = (new Random()).nextInt(usedQuestions.size());

            //Return a synonym question
            return usedQuestions.get(randomUsedNumber).suggestQuestion();
        }
    }


}
