package bot.knowledge.readers.synonyms;

import bot.knowledge.auxialiary.GlossaryFilesLocation;
import bot.knowledge.auxialiary.WordNormalizer;
import java.io.*;
import java.util.LinkedList;



/**
 * Class that contains a list with all the synonyms that were read from a file.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class SynonymsList {
    /**
     * List of synonyms in node format
     */
    private LinkedList<SynonymsNode> list;

    /**
     * A reference to this instance
     */
    private static SynonymsList instance = null;


    /**
     * Private default constructor of the class.
     */
    private SynonymsList() {
        list = new LinkedList<>();
    }



    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static SynonymsList getInstance () {
        if(instance == null)
            instance = new SynonymsList();
        return instance;
    }



    /**
     * Method to load the words on memory. This words are stored on
     * a text file, in a given directory.
     */
    public void loadSynonyms() throws IOException {
        // Create the file
        File synonymsFile = new File(GlossaryFilesLocation.SYNONYMS.toString());

        // Construct BufferedReader from FileReader
        BufferedReader br = new BufferedReader(new FileReader(synonymsFile));

        // Prepare to read
        String line = null;
        // While there are lines to be read
        while( (line = br.readLine()) != null ) {
            // First, split the line in two
            String[] mainSplit = line.split("\\|");

            // Normalize the keyword
            String normalizedKeyword = WordNormalizer.normalizeWord(mainSplit[0]);

            // Split the synonyms by comma
            String[] synSplit = mainSplit[1].split(",");

            // Now create a list
            LinkedList<LinkedList<String>> synonymsList = new LinkedList<>();

            //Go through each synonym statement
            for(String synonym: synSplit) {
                // Normalize the sentence an add it to the list
                synonymsList.addLast( WordNormalizer.normalizeSentence(synonym.split(" ")) );
            }

            // Now create a new node
            SynonymsNode sNode = new SynonymsNode(normalizedKeyword, synonymsList);
            list.addLast(sNode);
        }

        // Close the buffer
        br.close();
    }


    /**
     * Getter to obtain the list of synonym nodes.
     * @return The list of synonym nodes.
     */
    public LinkedList<SynonymsNode> getSynonymsList() {
        return list;
    }




    /**
     * Searches for synonyms on the statement and replaces it with the key
     * word that it is being used.
     * @param statement Statement to be analyzed and replaced
     * @return The statement with all the words replaced
     */
    public LinkedList<String> replaceWithKeyWords(LinkedList<String> statement) {
        // Prepare the list to return
        LinkedList<String> replacedStatement = statement;

        // Go through each synonym nodes
        for(SynonymsNode sNode: list) {
            // Go through each synonym word
            for(LinkedList<String> synList: sNode.getSynonyms()) {
                // Does this contain all the words?
                if(replacedStatement.containsAll(synList)) {
                    // Then change the synonym
                    replacedStatement = removeSynonym(sNode.getKeyword(), synList, replacedStatement);
                }
            }

        }

        // Return the replaced statement
        return replacedStatement;
    }



    /**
     * Replaces a given synonym on the list of synonyms for the statement to be replaced,
     * and changes it for the key value.
     * @param key Keyword to replace on the statement
     * @param s Synonym to be replaced
     * @param statement Statement from which the synonym must be replaced.
     * @return Statement with the synonym replaced with the key.
     */
    private LinkedList<String> removeSynonym(String key, LinkedList<String> s, LinkedList<String> statement) {
        // Prepare the list to return
        LinkedList<String> replacedStatement = new LinkedList<>();

        // Prepare a flag
        int doNotCopyUntil = 0;

        // For each word on the statement
        for(int i=0; i<statement.size(); i++) {
            // If the first word matches
            if( statement.get(i).equals(s.getFirst()) ) {
                // Check if the following words are ordered too

                // Prepare a flag
                int j = i;
                boolean exists = true;

                // For each word
                for(String w: s) {
                    // If the following word is not the one we need
                    if(!statement.get(j).equals(w)) {
                        // Put the flag on false
                        exists = false;
                    }
                    // Increase the counting
                    j++;
                }

                // If the synonym exists, we need to replace it
                if(exists) {
                    replacedStatement.add(key);
                    doNotCopyUntil = i + s.size();
                }
            }
            // If the first word does not matches
            else if(i >= doNotCopyUntil)
                // Add the word
                replacedStatement.add( statement.get(i) );
        }

        // Return the replaced statement
        return replacedStatement;
    }

}
