package bot.knowledge.auxialiary;


/**
 * Enumerated class for the routes of the files regarding the vocabulary
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum GlossaryFilesLocation {
    USER_QUESTIONS("C:/Chatbot/resources/user_questions.txt"),
    UNIMPORTANT_WORDS("C:/Chatbot/resources/unimportant_words.txt"),
    SYNONYMS("C:/Chatbot/resources/synonyms.txt"),
    QUESTIONS_AND_ANSWERS("C:/Chatbot/resources/questions_and_answers.txt"),

    EXTRA_RULES_START("C:/Chatbotresources/extrarules_"),
    EXTRA_RULES_END(".txt"),

    RECORD_FILE_DIRECTORY("C:/Chatbot/records"),
    RECORD_FILE_NAME_START("/record_"),
    RECORD_FILE_NAME_END(".txt");

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

        else if(EXTRA_RULES_START.toString().equals(n))
            return EXTRA_RULES_START;
        else if(EXTRA_RULES_END.toString().equals(n))
            return EXTRA_RULES_END;

        else if(RECORD_FILE_DIRECTORY.toString().equals(n))
            return RECORD_FILE_DIRECTORY;
        else if(RECORD_FILE_NAME_START.toString().equals(n))
            return RECORD_FILE_NAME_START;
        else if(RECORD_FILE_NAME_END.toString().equals(n))
            return RECORD_FILE_NAME_END;

        return null;
    }


    /**
     * Method that converts the current enum on a string.
     */
    public String toString() {
        return route;
    }

}