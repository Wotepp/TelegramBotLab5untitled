package ru.university.processor;

import java.util.ArrayList;
import java.util.List;

public class TextProcessor {

    private static final String VOWELS = "аеёиоуыэюяaeiouyАЕЁИОУЫЭЮЯAEIOUY";

    public static String splitByVowels(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        List<String> syllables = new ArrayList<>();

        int start = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isVowel(ch)) {
                if (start < i) {
                    syllables.add(input.substring(start, i));
                }
                start = i;
            }
        }
        if (start < input.length()) {
            syllables.add(input.substring(start));
        }

        for (int i = 0; i < syllables.size(); i++) {
            if (i > 0) result.append("-");
            result.append(syllables.get(i));
        }

        return result.toString();
    }

    private static boolean isVowel(char ch) {
        return VOWELS.indexOf(ch) != -1;
    }
}