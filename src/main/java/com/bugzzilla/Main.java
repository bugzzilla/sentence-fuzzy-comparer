package com.bugzzilla;

import com.bugzzilla.models.Sentence;
import com.bugzzilla.tools.FuzzyComparer;
import com.bugzzilla.tools.StringFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Sentence> sentences = new ArrayList<Sentence>();
        FuzzyComparer comparer = new FuzzyComparer(0.25, 0.45, 3, 2);
        Sentence sourceSentence = new Sentence("Нечёткое сравнение строк: пойми меня, если сможешь");

        sentences.add(new Sentence("Нечёткое сравнение строк: пойми меня, если сможешь"));
        sentences.add(new Sentence("Нечеткое сравнение строк: наивысшая эффективность"));
        sentences.add(new Sentence("Алгоритмы нечеткого сравнения строк. Практическое применение"));
        sentences.add(new Sentence("Алгоритм сравнения текстовых строк?"));
        sentences.add(new Sentence("РАБОТА СО СТРОКАМИ В JAVA"));
        sentences.add(new Sentence("Нечёткий поиск в тексте и словаре"));
        sentences.add(new Sentence("Обработка строк в Java. Часть I: String, StringBuffer, StringBuilder"));
        sentences.add(new Sentence("Алгоритмы поиска в строке"));
        sentences.add(new Sentence("поиск строки в файле"));


        for (Sentence sentence: sentences) {
            double calculateEqualValue = comparer.calculateEqualValue(sourceSentence, sentence);
            System.out.println(calculateEqualValue + ":" + comparer.isEqual(sourceSentence, sentence) + ":" + sentence.getSentence());
        }
    }
}
