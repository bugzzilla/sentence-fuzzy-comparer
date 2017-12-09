package com.bugzzilla.tools;

import com.bugzzilla.models.Sentence;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FuzzyComparer {

    private double thresholdSentence;
    private double thresholdWord;
    private int minWordLength;
    private int subTokenLength;

    public FuzzyComparer(double thresholdSentence, double thresholdWord, int minWordLength, int subTokenLength) {
        this.thresholdSentence = thresholdSentence;
        this.thresholdWord = thresholdWord;
        this.minWordLength = minWordLength;
        this.subTokenLength = subTokenLength;
    }

    private boolean isTokensEqual(String first, String second) {

        int equalSubtokensCount = 0;
        String subTokenFirst = "";
        String subTokenSecond = "";
        boolean[] usedTokens = new boolean[second.length() - subTokenLength + 1];

        for (int i = 0; i < first.length(); ++i) {
            subTokenFirst = StringFactory.substring(first, i, subTokenLength); // first.substring(i, subTokenLength);
            for (int j = 0; j < second.length() - subTokenLength + 1; ++j) {
                if (!usedTokens[j]) {
                    subTokenSecond = StringFactory.substring(second, j, subTokenLength); // second.substring(j, subTokenLength);
                    if (subTokenFirst.equals(subTokenSecond)) {
                        equalSubtokensCount++;
                        usedTokens[j] = true;
                        break;
                    }
                }
            }
        }
        int subtokenFirstCount = first.length() - subTokenLength + 1;
        int subtokenSecondCount = second.length() - subTokenLength + 1;
        double tanimoto = (1.0 * equalSubtokensCount) / (subtokenFirstCount + subtokenSecondCount - equalSubtokensCount);
        return thresholdWord <= tanimoto;
    }


    private ArrayList<String> getEqualsTokens(String[] first, String[] second) {
        ArrayList<String> result = new ArrayList<String>();
        boolean[] usedToken = new boolean[second.length];

        for (int i = 0; i < first.length; ++i) {
            for (int j = 0; j < second.length; ++j) {
                if (!usedToken[j]) {
                    if (isTokensEqual(first[i], second[j])) {
                        result.add(first[i]);
                        usedToken[j] = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public double calculateEqualValue(Sentence first, Sentence second) {

        if (StringFactory.isNullOrWhiteSpace(first.getSentence()) && StringFactory.isNullOrWhiteSpace(second.getSentence())) {
            return 1.0;
        }

        if (StringFactory.isNullOrWhiteSpace(first.getSentence()) || StringFactory.isNullOrWhiteSpace(second.getSentence())) {
            return 0.0;
        }

        ArrayList<String> equalsTokens = getEqualsTokens(first.getTokens(), second.getTokens());

        int equalsCount = equalsTokens.size();
        return (1.0 * equalsCount) / (first.getTokens().length + second.getTokens().length - equalsCount);
    }

    public boolean isEqual(Sentence first, Sentence second) {
        return thresholdSentence <= calculateEqualValue(first, second);
    }




}
