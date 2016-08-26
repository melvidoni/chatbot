package bot.knowledge.record;

/**
 * Enumerated class for languages to print on the record.
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum RecordLanguage {
    QUESTION("Question", "Pregunta"),
    ANSWER("Answer", "Respuesta"),
    RULE("Rule", "Regla"),
    CRITERIA("Applied Criteria", "Criterios Aplicados");

    private String englishWord;
    private String spanishWord;



    /**
     * Default constructor of the class.
     * @param e English name of the element.
     * @param s Spanish name of the element.
     */
    RecordLanguage(String e, String s) {
        englishWord = e;
        spanishWord = s;
    }




    /**
     * Method that allows the system to get a enum that matches with the received label.
     * @param e The word in English to be compared
     * @param s The word in Spanish to be compared
     * @return The enum object if it exists, or a null object.
     */
    public static RecordLanguage getLabel(String e, String s){
        if(QUESTION.getEnglishWord().equals(e) || QUESTION.getSpanishWord().equals(s))
            return QUESTION;
        else if(ANSWER.getEnglishWord().equals(e) || ANSWER.getSpanishWord().equals(s))
            return ANSWER;
        else if(RULE.getEnglishWord().equals(e) || RULE.getSpanishWord().equals(s))
            return RULE;
        else if(CRITERIA.getEnglishWord().equals(e) || CRITERIA.getSpanishWord().equals(s))
            return CRITERIA;
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