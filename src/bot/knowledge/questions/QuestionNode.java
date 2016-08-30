package bot.knowledge.questions;


import bot.knowledge.auxialiary.WordNormalizer;

import java.util.LinkedList;

/**
 *
 * Class that represents a question available for the chatbot. It stores both the
 * format that can be asked by the user, and the normalization.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
class QuestionNode {
    /**
     * The question as the user can write it
     */
    private String question;

    /**
     * The normalized question.
     */
    private LinkedList<String> normalizedQuestion;


    /**
     * Default constructor of the class
     * @param q The question to be added to the node.
     */
    QuestionNode(String q) {
        question = q;
        normalizedQuestion = WordNormalizer.normalizeSentence( question.split(" ") );
    }


    /**
     * Getter to obtain the question as asked by the user, represented on this node.
     * @return The question in regular format.
     */
    String getQuestion() {
        return question;
    }


    /**
     * Method to evaluate if an statement belongs to a question asked by the user.
     * @param aStatement The question to be compared.
     * @return true if the normalized statement is equal to the normalized question, and
     * false in the other case.
     */
    boolean isQuestion(String aStatement) {
        // First, normalize the statement, and return if it is equal to this node
        return normalizedQuestion.equals( WordNormalizer.normalizeSentence(aStatement.split(" ")) );
    }
}
