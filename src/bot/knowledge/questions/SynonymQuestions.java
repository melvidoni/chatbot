package bot.knowledge.questions;


import java.util.LinkedList;
import java.util.Random;


/**
 * Class that represents a set of questions that are synonym, and can give
 * the same set of answers. They are stored as QuestionsNodes.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
class SynonymQuestions {
    /**
     * List of synonym questions
     */
    private LinkedList<QuestionNode> synonymQuestions;


    /**
     * Default constructor of the class
     */
    SynonymQuestions() {
        synonymQuestions = new LinkedList<>();
    }


    /**
     * Method that adds the set of questions to the list of synonyms
     * of the instance. It replaces any other question that was on the
     * instance before calling this method.
     * @param questions The set of questions to add.
     */
    void addSynonymQuestions(LinkedList<String> questions) {
        // Clear the list
        synonymQuestions.clear();

        // For each question
        for(String q: questions) {
            synonymQuestions.addLast(new QuestionNode(q));
        }
    }


    /**
     * Method that searches if the statement received as parameter is one of
     * the questions of this set of synonym questions.
     * @param statement The question to look for.
     * @return true if the question is of this set, false otherwise.
     */
    boolean containsQuestion(String statement) {
        // Go through each node
        for(QuestionNode qNode: synonymQuestions) {
            // If this is the question, return true.
            if(qNode.isQuestion(statement)) return true;
            // Otherwise, keep searching
        }

        // If we got here, the question was not found
        return false;
    }


    /**
     * Method that suggests a question from the list of questions from
     * this set of synonyms.
     * @return a randomly picked question in the user's format.
     */
    String suggestQuestion() {
        // Get a random number
        int randomNumber = (new Random()).nextInt(synonymQuestions.size());

        // Return that question
        return synonymQuestions.get(randomNumber).getQuestion();
    }

}
