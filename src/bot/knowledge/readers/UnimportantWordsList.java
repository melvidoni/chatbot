package bot.knowledge.readers;

import bot.knowledge.auxialiary.GlossaryFilesLocation;
import bot.knowledge.auxialiary.WordNormalizer;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
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
    public void loadWords() throws IOException{
        // Create the file
        File uWordsFile = new File(GlossaryFilesLocation.UNIMPORTANT_WORDS.toString());

        /*
         Construct BufferedReader from FileReader. Because JavaFX needs to be forced to read
         files encoded as UTF-8, the buffered reader is forced too.
          */
        BufferedReader br = new BufferedReader(new UTF8Reader(new FileInputStream(uWordsFile)));

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



    /**
     * Getter to obtain the unimportant words currently stored.
     * @return A list of the unimportant words.
     */
    public LinkedList<String> getUnimportantWords() {
        return words;
    }


}
