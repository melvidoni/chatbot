package bot.knowledge.readers;

import bot.knowledge.auxialiary.GlossaryFilesLocation;
import bot.knowledge.auxialiary.WordNormalizer;

import java.io.*;
import java.util.LinkedList;


/**
 * Singleton class that contains the words that are deemed unimportant, and that
 * are later removed from the statements to be processed.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class UnimportantWordsList {
    /**
     * The list of unimportant words
     */
    private LinkedList<String> words;

    /**
     * An instance to itself
     */
    private static UnimportantWordsList instance = null;


    /**
     * Private default constructor of the class.
     */
    private UnimportantWordsList() {
        words = new LinkedList<>();
    }


    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static UnimportantWordsList getInstance () {
        if(instance == null)
            instance = new UnimportantWordsList();
        return instance;
    }


    /**
     * Method to load the words on memory. This words are stored on
     * a text file, in a given directory.
     */
    public void loadWords() {
        try {
            // Create the file
            File uWordsFile = new File(GlossaryFilesLocation.UNIMPORTANT_WORDS.toString());

            // Construct BufferedReader from FileReader
            BufferedReader br = new BufferedReader(new FileReader(uWordsFile));

            // Prepare to read
            String line = null;
            // While there are lines to be read
            while( (line = br.readLine()) != null ) {
                // Save the normalized word
                words.addLast( WordNormalizer.normalizeWord(line) );
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



    /**
     * Getter to obtain the unimportant words currently stored.
     * @return A list of the unimportant words.
     */
    public LinkedList<String> getUnimportantWords() {
        return words;
    }


}
