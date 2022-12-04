package com.envyful.aoc.four;

import com.google.common.collect.Maps;

import java.util.AbstractMap;
import java.util.Map;

public class PartOne {

    public static int calculateFullyOverlapping(String text) {
        int covered = 0;

        for (String s : text.split("\n")) {
            Map<Integer, Boolean> sectionsA = Maps.newHashMap();
            Map<Integer, Boolean> sectionsB = Maps.newHashMap();
            String[] elves = s.split(",");
            Map.Entry<Integer, Integer> elfOne = parseElf(elves[0]);
            Map.Entry<Integer, Integer> elfTwo = parseElf(elves[1]);

            for (int i = elfOne.getKey(); i <= elfOne.getValue(); i++) {
                sectionsA.put(i, true);
            }

            for (int i = elfTwo.getKey(); i <= elfTwo.getValue(); i++) {
                sectionsB.put(i, true);
            }

            if (isFullyCovered(sectionsA, elfTwo)) {
                covered++;
            } else if (isFullyCovered(sectionsB, elfOne)) {
                covered++;
            }
        }

        return covered;
    }

    private static Map.Entry<Integer, Integer> parseElf(String elf) {
        String[] split = elf.split("-");
        return new AbstractMap.SimpleEntry<>(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    private static boolean isFullyCovered(Map<Integer, Boolean> values, Map.Entry<Integer, Integer> elf) {
        for (int i = elf.getKey(); i <= elf.getValue(); i++) {
            if (!values.getOrDefault(i, false)) {
                return false;
            }
        }

        return true;
    }
}
