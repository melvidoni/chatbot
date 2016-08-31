package bot.knowledge.readers;


import bot.agent.operators.MoveToWordAction;
import bot.agent.operators.rules.OperatorRulesMap;
import bot.knowledge.auxialiary.GlossaryFilesLocation;
import bot.knowledge.questions.QuestionsList;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import java.io.*;
import java.util.LinkedList;




/**
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class RulesReader {


    /**
     * Method to initialize the operators and rules map, first by
     * reading what it is available on the graph, and then by
     * adding the information of the file.
     * @return A list of unordered actions.
     */
    public static LinkedList<MoveToWordAction> loadActionsList() throws IOException{
        // Get a reference to the map
        OperatorRulesMap map = OperatorRulesMap.getInstance();

        // Read the questions and answers
        readQuestionsAndAnswers();

        // Return the information
        return map.getActionsList();
    }


    /**
     * Method that reads all the questions an answers available, in order to
     * create the maps. It also initializes the list of questions with the
     * sets of synonym questions.
     */
    private static void readQuestionsAndAnswers() throws IOException{
        // Get a reference to the map
        OperatorRulesMap map = OperatorRulesMap.getInstance();

        // Prepare an instance of the questions list
        QuestionsList questionsList = QuestionsList.getInstance();

        // Create the file
        File qaFile = new File(GlossaryFilesLocation.QUESTIONS_AND_ANSWERS.toString());

        // Construct BufferedReader from FileReader. Force to UTF-8.
        BufferedReader br = new BufferedReader(new UTF8Reader(new FileInputStream(qaFile)));

        // Get the number of questions
        int numberOfQuestions = Integer.parseInt(br.readLine());

        // While there are questions...
        while(numberOfQuestions != 0) {
            // Create the list of questions and answers
            LinkedList<String> questions = new LinkedList<>();
            LinkedList<String> answers = new LinkedList<>();

            // Read all the questions
            for(int i=0; i<numberOfQuestions; i++) {
                // And add them to the list
                questions.add(br.readLine());
            }

            // Now get the number of answers
            int numberOfAnswers = Integer.parseInt(br.readLine());

            // Read all the answers
            for(int j=0; j<numberOfAnswers; j++) {
                // And add them to the list
                answers.add(br.readLine());
            }

            /*
            At this point I have a block of questions that can be answered with a given
            block of answers. I need to associate them in someway, and create the rules
            to answer the question/s with that answer/s.
             */
            map.createRulesFor(questions, answers);

            // Update to read the next set of questions
            numberOfQuestions = Integer.parseInt(br.readLine());

            // Add all the group of questions to the list
            questionsList.addSynonymQuestions(questions);
        }

        // Close the buffer
        br.close();

        /*
        Now we have the questions and the rules, but they are not linked through with the
        actions that the agent needs. We need to set this up.
         */
        map.linkActionsWithRules();
    }




}
