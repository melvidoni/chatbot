package bot.knowledge.auxialiary;

/**
 * Enumerated class for languages to use on the chatbot.
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum ChatbotLanguage {
    ENGLISH("english", "EN"),
    SPANISH("spanish", "ES");

    private String name;
    private String acronym;



    /**
     * Default constructor of the class.
     * @param e English name of the element.
     * @param s Spanish name of the element.
     */
    ChatbotLanguage(String e, String s) {
        name = e;
        acronym = s;
    }




    /**
     * Method that allows the system to get a enum that matches with the received label.
     * @param e The word in English to be compared
     * @param s The word in Spanish to be compared
     * @return The enum object if it exists, or a null object.
     */
    public static ChatbotLanguage getLabel(String e, String s){
        if(ENGLISH.getName().equals(e) || ENGLISH.getAcronym().equals(s))
            return ENGLISH;
        else if(SPANISH.getName().equals(e) || SPANISH.getAcronym().equals(s))
            return SPANISH;
        return null;
    }



    /**
     * Method that returns the English word of the element
     */
    public String getName() {
        return name;
    }


    /**
     * Method that returns the Spanish word of the element
     */
    public String getAcronym() {
        return acronym;
    }

}