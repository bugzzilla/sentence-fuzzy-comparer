package com.bugzzilla.tools;

public class SentenceWrapper {

    public static String normalize (String sentence) {

        StringBuilder result = new StringBuilder();

        for (char c: sentence.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String[] getTokens(String sentence) {
        return sentence.split(" ");
    }

}
