package com.envyful.aoc.two;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class PartTwo {

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
                    accumulation += 0;
                    accumulation += 3;
                } else if (values[1].equalsIgnoreCase("Y")) {
                    accumulation += 3;
                    accumulation += 1;
                } else if (values[1].equalsIgnoreCase("Z")) {
                    accumulation += 6;
                    accumulation += 2;
                }
            } else if (values[0].equalsIgnoreCase("B")) {
                if (values[1].equals("X")) {
                    accumulation += 0;
                    accumulation += 1;
                } else if (values[1].equalsIgnoreCase("Y")) {
                    accumulation += 3;
                    accumulation += 2;
                } else if (values[1].equalsIgnoreCase("Z")) {
                    accumulation += 6;
                    accumulation += 3;
                }
            } else if (values[0].equalsIgnoreCase("C")) {
                if (values[1].equals("X")) {
                    accumulation += 0;
                    accumulation += 2;
                } else if (values[1].equalsIgnoreCase("Y")) {
                    accumulation += 3;
                    accumulation += 3;
                } else if (values[1].equalsIgnoreCase("Z")) {
                    accumulation += 6;
                    accumulation += 1;
                }
            }
        }

        return accumulation;
    }

}
