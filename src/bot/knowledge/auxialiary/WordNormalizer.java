package bot.knowledge.auxialiary;


import bot.knowledge.readers.UnimportantWordsList;
import bot.knowledge.readers.synonyms.SynonymsList;

import java.text.Normalizer;
import java.util.LinkedList;

/**
 * Static class to use as a normalizer, that removes and replaces non basic ASCII letters,
 * removes white spaces and special characters.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class WordNormalizer{


    /**
     * Method to normalize a word. It removes special characters such as commas, colons,
     * punctuation marks, admirations and more. Then removes white spaces, and finally
     * clears the word of special letters such as accentuated vocals, ñ, and so on.
     * @param word The word to be normalized.
     * @return A normalized word.
     */
    public static String normalizeWord(String word){
        // First of all, remove special characters
        String noSpecialChars = word.replaceAll("[\\-\\+\\.\\^:,¡!¿?;%&\\|]", "");

        // Then remove accents, and other special letters
        String noSpecialLetters = Normalizer.normalize(noSpecialChars.replace(" ",""), Normalizer.Form.NFD);
        noSpecialLetters = noSpecialLetters.replaceAll("[^\\p{ASCII}]", "");

        // Return the corrected word in uppercase
        return noSpecialLetters.toUpperCase();
    }


    /**
     * Method that normalizes a complete sentence, and returns it as a linkedlist.
     * It also checks the words against the unimportant list
     * @param sentence The sentences to be normalized.
     * @return The normalized sentence.
     */
    public static LinkedList<String> normalizeSentence(String[] sentence) {
        // Get the list of unimportant words
        LinkedList<String> unimportantWordsList = UnimportantWordsList.getInstance().getUnimportantWords();

        // Create the list
        LinkedList<String> reducedSentence = new LinkedList<>();

        // For each word of the sentence
        for(String word: sentence) {
            // Normalize the word
            String normalizedWord = normalizeWord(word);

            // If the word is important and it is not an empty space
            if(!unimportantWordsList.contains(normalizedWord) && !normalizedWord.isEmpty()) {
                // add it to the list
                reducedSentence.addLast(normalizedWord);
            }
        }

        // Now replace the synonyms and return it
        return SynonymsList.getInstance().replaceWithKeyWords(reducedSentence);
    }

}
