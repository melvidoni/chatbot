package bot.inference;

/**
 * Enumerated class for the languages to print the filters.
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum FiltersLanguages {
    SPECIFICITY("SPECIFICITY", "ESPECIFICIDAD"),
    PRIORITY("PRIORITY", "PRIORIDAD"),
    NO_DUPLICATES_FAILED("NO DUPLICATES (FAILED)", "NO DUPLICADOS (FALLIDO)"),
    NO_DUPLICATES_SUCCESSFUL("NO DUPLICATES (SUCCESSFUL)", "NO DUPLICADOS (EXITOSA)"),
    RANDOM_USED("RANDOM (USED RULES)", "ALEATORIO (REGLAS REUTILIZADAS)"),
    RANDOM_UNUSED("RANDOM (UNUSED RULES)", "ALEATORIO (REGLAS NO UTILIZADAS)"),
    HEADER_ON_LOG("USED CRITERIA", "CRITERIOS EMPLEADOS");

    private String englishWord;
    private String spanishWord;



    /**
     * Default constructor of the class.
     * @param e English name of the element.
     * @param s Spanish name of the element.
     */
    FiltersLanguages(String e, String s) {
        englishWord = e;
        spanishWord = s;
    }




    /**
     * Method that allows the system to get a enum that matches with the received label.
     * @param e The word in English to be compared
     * @param s The word in Spanish to be compared
     * @return The enum object if it exists, or a null object.
     */
    public static FiltersLanguages getLabel(String e, String s){
        if(SPECIFICITY.getEnglishWord().equals(e) || SPECIFICITY.getSpanishWord().equals(s))
            return SPECIFICITY;
        else if(PRIORITY.getEnglishWord().equals(e) || PRIORITY.getSpanishWord().equals(s))
            return PRIORITY;
        else if(NO_DUPLICATES_FAILED.getEnglishWord().equals(e) || NO_DUPLICATES_FAILED.getSpanishWord().equals(s))
            return NO_DUPLICATES_FAILED;
        else if(NO_DUPLICATES_SUCCESSFUL.getEnglishWord().equals(e) || NO_DUPLICATES_SUCCESSFUL.getSpanishWord().equals(s))
            return NO_DUPLICATES_SUCCESSFUL;
        else if(RANDOM_USED.getEnglishWord().equals(e) || RANDOM_USED.getSpanishWord().equals(s))
            return RANDOM_USED;
        else if(RANDOM_UNUSED.getEnglishWord().equals(e) || RANDOM_UNUSED.getSpanishWord().equals(s))
            return RANDOM_UNUSED;
        else if(HEADER_ON_LOG.getEnglishWord().equals(e) || HEADER_ON_LOG.getSpanishWord().equals(s))
            return HEADER_ON_LOG;
        return null;
    }



    /**
     * Method that returns the English word of the element
     */
    public String getEnglishWord() {
        return englishWord;
    }


    /**
     * Method that returns the Spanish word of the element
     */
    public String getSpanishWord() {
        return spanishWord;
    }

}