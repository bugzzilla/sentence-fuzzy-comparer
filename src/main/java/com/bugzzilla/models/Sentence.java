package com.bugzzilla.models;

import com.bugzzilla.tools.SentenceWrapper;

import java.util.Arrays;
import java.util.List;

public class Sentence {

    private String originalSentence = "";
    private String sentence = "";
    private String[] tokens = null;

    public Sentence(String originalSentence) {
        this.originalSentence = originalSentence;
        this.sentence = SentenceWrapper.normalize(originalSentence);
        this.tokens = SentenceWrapper.getTokens(sentence);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "originalSentence='" + originalSentence + '\'' +
                ", sentence='" + sentence + '\'' +
                ", tokens=" + Arrays.toString(tokens) +
                '}';
    }

    public String getSentence() {
        return sentence;
    }

    public String[] getTokens() {
        return tokens;
    }
}
