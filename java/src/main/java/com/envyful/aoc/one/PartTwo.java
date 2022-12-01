package com.envyful.aoc.one;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

public class PartTwo {

    public static int sumTopThreeElves(String text) {
        String[] split = text.split("\n");
        int currentElf = 0;
        List<Integer> elves = Lists.newArrayList();

        for (String s : split) {
            if (s.isBlank()) {
                elves.add(currentElf);
                currentElf = 0;
                continue;
            }

            try {
                currentElf += Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        elves.add(currentElf);
        elves.sort(Comparator.comparing(Integer::intValue));

        int sum = 0;

        for (int i = 1; i <= 3; i++) {
            sum += elves.get((elves.size() - i));
        }

        return sum;
    }
}
