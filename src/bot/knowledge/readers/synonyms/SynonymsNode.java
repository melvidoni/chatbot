package bot.knowledge.readers.synonyms;


import java.util.LinkedList;

/**
 * Class that represents a key word and its synonyms. It does not perform
 * any type of processing of the words. It uses them as received.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
class SynonymsNode {
    /**
     * Key word that replaces all the other words
     */
    private String keyword;

    /**
     * Synonym words that can be replaced by the key;
     */
    private LinkedList<LinkedList<String>> synonyms;



    /**
     * Constructor that adds the key and the list of synonyms.
     * @param key Key word that represents this node
     * @param s List of synonyms to be added.
     */
    SynonymsNode(String key, LinkedList<LinkedList<String>> s) {
        // Save the keyword
        keyword = key;

        // Prepare the synonyms
        synonyms = s;
    }


    /**
     * Getter to obtain the keyword of this node.
     * @return The keyword of this node.
     */
    String getKeyword() {
        return keyword;
    }


    /**
     * Getter to obtain the list of words and statements that are synonyms
     * for the keyword of the instance.
     * @return List of synonyms.
     */
    LinkedList<LinkedList<String>> getSynonyms() {
        return synonyms;
    }

}
