package bot.readers.auxialiary;


/**
 * Enumerated class for the routes of the files regarding the vocabulary
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum GlossaryFilesLocation {
    USER_QUESTIONS("C:/Chatbot/Resources/user_questions.txt"),
    UNIMPORTANT_WORDS("C:/Chatbot/Resources/unimportant_words.txt"),
    SYNONYMS("C:/Chatbot/Resources/synonyms.txt"),
    QUESTIONS_AND_ANSWERS("C:/Chatbot/Resources/questions_and_answers.txt"),
    RULES_AND_IDS("");

    private String route;



    /**
     * Default constructor of the class.
     * @param l name of the element.
     */
    GlossaryFilesLocation(String l) {
        route = l;
    }




    /**
     * Method that allows the system to get a enum that matches with the received label.
     * @param n The label to be compared with.
     * @return The enum object if it exists, or a null object.
     */
    public static GlossaryFilesLocation getLabel(String n){
        if(USER_QUESTIONS.toString().equals(n))
            return USER_QUESTIONS;
        else if(UNIMPORTANT_WORDS.toString().equals(n))
            return UNIMPORTANT_WORDS;
        else if(SYNONYMS.toString().equals(n))
            return SYNONYMS;
        else if(QUESTIONS_AND_ANSWERS.toString().equals(n))
            return QUESTIONS_AND_ANSWERS;
        else if(RULES_AND_IDS.toString().equals(n))
            return RULES_AND_IDS;
        return null;
    }



    /**
     * Method that converts the current enum on a string.
     */
    public String toString() {
        return route;
    }

}