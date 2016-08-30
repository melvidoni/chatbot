package gui.language;


import java.util.Locale;

/**
 * Created by Melina on 30/08/2016.
 */
public class CurrentLocale {
    private Locale locale;
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
