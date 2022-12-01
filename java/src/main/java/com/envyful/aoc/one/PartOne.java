package com.envyful.aoc.one;

public class PartOne {

    public static int getMaxElf(String text) {
        String[] split = text.split("\n");
        int currentElf = 0;
        int maxElf = -1;

        for (String s : split) {
            if (s.isBlank()) {
                if (currentElf > maxElf) {
                    maxElf = currentElf;
                }

                currentElf = 0;
                continue;
            }

            try {
                currentElf += Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return maxElf;
    }
}
