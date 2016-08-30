package gui.language;


/**
 * Enumerated class for keywords on the localization bundles files.
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum BundlesKeywords {
    SUGGESTIONS_LABEL("suggestionsLabel"),
    LANGUAGES_MENU("languagesMenu"),
    ENGLISH_MENU_ITEM("englishMenuItem"),
    SPANISH_MENU_ITEM("spanishMenuItem"),
    HELP_MENU("helpMenu"),
    CREDITS_MENU("creditsMenu"),

    LOADING_MESSAGE("loadingMessage"),
    OBTAINING_ENV_MESSAGE("obtainingEnvironmentMessage"),
    LEARNING_MESSAGE("learningMessage"),
    MORE_LEARNING_MESSAGE("moreLearningMessage"),
    WAKE_UP_CHATBOT_MESSAGE("wakingUpChatbotMessage"),
    STAR_SIMULATION_MESSAGE("startingSimulationMessage"),

    CREDITS_WINDOW_TITLE("creditsWindowTitle"),
    CREDITS_WINDOW_HEADER("creditsWindowHeader"),
    CREDITS_WINDOW_MSG("creditsWindowMessage"),

    SPECIFICITY_FILTER("specificityFilter"),
    PRIORITY_FILTER("priorityFilter"),
    NO_DUPLICATION_FAILED_FILTER("noDuplicationFailedFilter"),
    NO_DUPLICATION_SUCCESS_FILTER("noDuplicationSuccessFilter"),
    RANDOM_USED_FILTER("randomUsedFilter"),
    RANDOM_UNUSED_FILTER("randomUnusedFilter"),
    LOG_HEADER("logHeader"),

    UNKNOWN_ANSWER("unkwnownAnswer"),
    CANNOT_UNDERSTAND("cannotUnderstand"),

    RECORD_QUESTION("recordQuestion"),
    RECORD_ANSWER("recordAnswer"),
    RECORD_RULE("recordRule"),
    RECORD_FILTER("recordFilters");


    private String keyword;



    /**
     * Default constructor of the class.
     * @param l name of the element.
     */
    BundlesKeywords(String l) {
        keyword = l;
    }




    /**
     * Method that allows the system to get a enum that matches with the received label.
     * @param n The label to be compared with.
     * @return The enum object if it exists, or a null object.
     */
    public static BundlesKeywords getLabel(String n){
        if(SUGGESTIONS_LABEL.toString().equals(n))
            return SUGGESTIONS_LABEL;

        else if(LANGUAGES_MENU.toString().equals(n))
            return LANGUAGES_MENU;
        else if(ENGLISH_MENU_ITEM.toString().equals(n))
            return ENGLISH_MENU_ITEM;
        else if(SPANISH_MENU_ITEM.toString().equals(n))
            return SPANISH_MENU_ITEM;
        else if(HELP_MENU.toString().equals(n))
            return HELP_MENU;
        else if(CREDITS_MENU.toString().equals(n))
            return CREDITS_MENU;

        else if(LOADING_MESSAGE.toString().equals(n))
            return LOADING_MESSAGE;
        else if(OBTAINING_ENV_MESSAGE.toString().equals(n))
            return OBTAINING_ENV_MESSAGE;
        else if(LEARNING_MESSAGE.toString().equals(n))
            return LEARNING_MESSAGE;
        else if(MORE_LEARNING_MESSAGE.toString().equals(n))
            return MORE_LEARNING_MESSAGE;
        else if(WAKE_UP_CHATBOT_MESSAGE.toString().equals(n))
            return WAKE_UP_CHATBOT_MESSAGE;
        else if(STAR_SIMULATION_MESSAGE.toString().equals(n))
            return STAR_SIMULATION_MESSAGE;

        else if(CREDITS_WINDOW_TITLE.toString().equals(n))
            return CREDITS_WINDOW_TITLE;
        else if(CREDITS_WINDOW_MSG.toString().equals(n))
            return CREDITS_WINDOW_MSG;
        else if(CREDITS_WINDOW_HEADER.toString().equals(n))
            return CREDITS_WINDOW_HEADER;

        else if(SPECIFICITY_FILTER.toString().equals(n))
            return SPECIFICITY_FILTER;
        else if(PRIORITY_FILTER.toString().equals(n))
            return PRIORITY_FILTER;
        else if(NO_DUPLICATION_FAILED_FILTER.toString().equals(n))
            return NO_DUPLICATION_FAILED_FILTER;
        else if(NO_DUPLICATION_SUCCESS_FILTER.toString().equals(n))
            return NO_DUPLICATION_SUCCESS_FILTER;
        else if(RANDOM_USED_FILTER.toString().equals(n))
            return RANDOM_USED_FILTER;
        else if(RANDOM_UNUSED_FILTER.toString().equals(n))
            return RANDOM_UNUSED_FILTER;
        else if(LOG_HEADER.toString().equals(n))
            return LOG_HEADER;

        else if(UNKNOWN_ANSWER.toString().equals(n))
            return UNKNOWN_ANSWER;
        else if(CANNOT_UNDERSTAND.toString().equals(n))
            return CANNOT_UNDERSTAND;

        else if(RECORD_QUESTION.toString().equals(n))
            return RECORD_QUESTION;
        else if(RECORD_ANSWER.toString().equals(n))
            return RECORD_ANSWER;
        else if(RECORD_RULE.toString().equals(n))
            return RECORD_RULE;
        else if(RECORD_FILTER.toString().equals(n))
            return RECORD_FILTER;

        return null;
    }


    /**
     * Method that converts the current enum on a string.
     */
    public String toString() {
        return keyword;
    }

}