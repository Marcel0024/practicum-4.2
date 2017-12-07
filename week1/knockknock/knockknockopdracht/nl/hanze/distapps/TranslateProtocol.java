package nl.hanze.distapps;

public class TranslateProtocol {
    private static final int WAITING = 0;
    private static final int CHOOSE_LANGUAGE = 1;
    private static final int WAITING_TO_TRANSLATE = 2;

    private int state = WAITING;
    private int chosenlanguage = -1;

    private String[] supportedLanguages = { "Dutch", "English", "Spanish" };

    private String[][] words = {
            { "Blauw", "Blue" , "Azul" },
            { "Rood", "Red", "Yellow" },
            { "Groen", "Green", "Verde" },
            { "Geel", "Yellow","Amarillo" }
    };

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Choose language: " + _arrayToString(supportedLanguages);
            state = CHOOSE_LANGUAGE;
        } else if (state == CHOOSE_LANGUAGE) {

            int languageIndex = _getLanguageIndex(supportedLanguages, theInput);

            if (languageIndex >= 0) {
                theOutput = supportedLanguages[languageIndex] + " chosen. Send text to translate";
                chosenlanguage = languageIndex;
                state = WAITING_TO_TRANSLATE;
            } else {
                theOutput = "Not a valid language! Try again";
            }

        } else if (state == WAITING_TO_TRANSLATE) {
            String result = translate(theInput);

            if (result != null && !result.isEmpty()) {
                theOutput = "Translation: " + result;
            }else{
                theOutput = "Could not translate at this time";
            }
            theOutput += "-- Choose language: " + _arrayToString(supportedLanguages);
            state = CHOOSE_LANGUAGE;
        }
        return theOutput;
    }

    private String translate(String input){
        for(int i = 0; i < words.length; i++) {
            if(input.equalsIgnoreCase(words[i][0])){
                return words[i][chosenlanguage];
            }
        }
        return null;
    }

    private String _arrayToString(String[] array){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            builder.append("[" + i + "] " + array[i] + ", ");
        }
        return builder.toString();
    }

    private int _getLanguageIndex(String[] languages, String chosen){
        for(int i = 0; i < languages.length; i++) {
            if(chosen.equalsIgnoreCase(languages[i])){
                return i;
            }
        }

        int intParseResult = _parseInt(chosen);

        return intParseResult;
    }

    private int  _parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
           return -1;
        }
    }
}
