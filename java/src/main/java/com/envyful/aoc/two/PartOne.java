package com.envyful.aoc.two;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class PartOne {

    public static int calculateScore(String text) {
        Map<String, Integer> value = ImmutableMap.of("X", 1, "Y", 2, "Z", 3);
        int accumulation = 0;

        for (String s : text.split("\n")) {
            if (s.isBlank()) {
                continue;
            }

            String[] values = s.split(" ");

            if (values[0].equals("A")) {
                if (values[1].equals("X")) {
                    accumulation += 3;
                } else if (values[1].equalsIgnoreCase("Y")) {
                    accumulation += 6;
                } else if (values[1].equalsIgnoreCase("Z")) {
                    accumulation += 0;
                }
            } else if (values[0].equalsIgnoreCase("B")) {
                if (values[1].equals("X")) {
                    accumulation += 0;
                } else if (values[1].equalsIgnoreCase("Y")) {
                    accumulation += 3;
                } else if (values[1].equalsIgnoreCase("Z")) {
                    accumulation += 6;
                }
            } else if (values[0].equalsIgnoreCase("C")) {
                if (values[1].equals("X")) {
                    accumulation += 6;
                } else if (values[1].equalsIgnoreCase("Y")) {
                    accumulation += 0;
                } else if (values[1].equalsIgnoreCase("Z")) {
                    accumulation += 3;
                }
            }

            accumulation += value.getOrDefault(values[1], 0);
        }

        return accumulation;
    }

}
