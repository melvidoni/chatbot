package gui.language;


import java.util.Locale;


/**
 * Class that stores the information about the current locale of the
 * agent. It stores a Locale and a custom enum for the language. It is
 * initialized on Spanish.
 * @see ChatbotLanguage
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class CurrentLocale {
    /**
     * Current locale of the chatbot. It is updated
     * jointly with the language.
     */
    private Locale locale;

    /**
     * Reference to the enum containing the language.
     * It is updated jointly with the locale.
     */
    private ChatbotLanguage cLanguage;

    /**
     * A reference to itself, because the class is singleton.
     */
    private static CurrentLocale instance = null;


    /**
     * Private default constructor of the class
     */
    private CurrentLocale() {
        // Set default locale
        locale = new Locale(ChatbotLanguage.SPANISH.getAcronym().toLowerCase(),
                ChatbotLanguage.SPANISH.getAcronym().toUpperCase());

        // Set the language
        cLanguage = ChatbotLanguage.SPANISH;
    }


    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static CurrentLocale getInstance () {
        if(instance == null)
            instance = new CurrentLocale();
        return instance;
    }


    /**
     * Getter to obtain the current locale of the chatbot.
     * @return The current locale of the chatbot.
     */
    public Locale getLocale() {
        return locale;
    }


    /**
     * Getter to obtain the current language of the chatbot.
     * @return The current language in enum format.
     */
    public ChatbotLanguage getcLanguage() {
        return cLanguage;
    }


    /**
     * Setter to change the current locale of the chatbot.
     * @param cl The new language to be used.
     */
    public void changeLocale(ChatbotLanguage cl) {
        // Set the new locale
        locale = new Locale(cl.getAcronym().toLowerCase(), cl.getAcronym().toUpperCase());

        // Set the language
        cLanguage = cl;
    }
}
