package com.envyful.aoc.three;

import com.google.common.collect.Sets;

import java.util.Set;

public class PartOne {

    public int getCombinedSum(String text) {
        int sum = 0;

        for (String s : text.split("\n")) {
            int half = s.length() / 2;
            Set<Character> firstHalf = Sets.newHashSet();
            Set<Character> secondHalf = Sets.newHashSet();

            for (char c : s.substring(0, half).toCharArray()) {
                firstHalf.add(c);
            }

            for (char c : s.substring(half).toCharArray()) {
                secondHalf.add(c);
            }

            for (Character character : firstHalf) {
                for (Character character1 : secondHalf) {
                    if (character1 == character) {
                        sum += ((int) Character.toLowerCase(character)) - 96;

                        if (Character.isUpperCase(character)) {
                            sum += 26;
                        }
                    }
                }
            }
        }

        return sum;
    }
}
