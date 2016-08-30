package bot.knowledge.readers;


import bot.knowledge.graph.Graph;
import bot.knowledge.graph.GraphNode;
import bot.knowledge.auxialiary.GlossaryFilesLocation;
import bot.knowledge.auxialiary.WordNormalizer;
import java.io.*;
import java.util.LinkedList;


/**
 * Class that generates the graph that represents the complete known glossary
 * based on the questions and statements that will be understood by the agent.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class GraphReader {


    /**
     * Method that reads the questions, split them into words, and normalizes them.
     * Then, it adds them on the graph as nodes, and checks if they already exist.
     */
    public static void loadGraph() {
        try {
            // Get the instances
            Graph graph = Graph.getInstance();

            // Create the file
            File uWordsFile = new File(GlossaryFilesLocation.USER_QUESTIONS.toString());

            // Construct BufferedReader from FileReader
            BufferedReader br = new BufferedReader(new FileReader(uWordsFile));

            // While there are lines to be read
            String questionLine = null;
            while( (questionLine = br.readLine()) != null ) {
                // Normalize the sentence
                LinkedList<String> normalizedQuestion = WordNormalizer.normalizeSentence(questionLine.split(" "));

                // Now for each word
                for(String questionWord: normalizedQuestion) {
                    /*
                    Since normalizing a sentences already removes the unimportant words, we
                    just simply add the words to the graph.
                     */
                    graph.addNode( new GraphNode(questionWord));
                }

                /*
                Once all the words of this question have been added to the list and put
                on the map, we need to add the relations between them.
                 */
                // Now for each word
                for(int index=1; index<normalizedQuestion.size(); index++) {
                    // Associate the words
                    graph.addAssociation(normalizedQuestion.get(index - 1), normalizedQuestion.get(index));
                }
            }

            // Close the buffer
            br.close();
        }
        catch (FileNotFoundException e) {
            // TODO CHANGE THIS STACKTRACE
            e.printStackTrace();
        }
        catch(IOException e) {
            // TODO CHANGE THIS STACKTRACE
            e.printStackTrace();
        }
    }


}
