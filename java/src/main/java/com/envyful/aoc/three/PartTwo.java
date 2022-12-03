package com.envyful.aoc.three;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class PartTwo {

    public int getBadgeSum(String text) {
        int sum = 0;
        int counter = 0;
        List<Set<Character>> characters = Lists.newArrayList();
        List<Character> badges = Lists.newArrayList();

        for (String s : text.split("\n")) {
            counter = counter % 3;

            if (counter == 0 && !characters.isEmpty()) {
                Set<Character> characters1 = characters.get(0);
                Set<Character> characters2 = characters.get(1);
                Set<Character> characters3 = characters.get(2);

                for (Character character : characters1) {
                    if (characters2.contains(character) && characters3.contains(character)) {
                        badges.add(character);
                        break;
                    }
                }

                characters.clear();
            }

            Set<Character> found = Sets.newHashSet();

            for (char c : s.toCharArray()) {
                found.add(c);
            }

            characters.add(found);
            counter++;
        }

        Set<Character> characters1 = characters.get(0);
        Set<Character> characters2 = characters.get(1);
        Set<Character> characters3 = characters.get(2);

        for (Character character : characters1) {
            if (characters2.contains(character) && characters3.contains(character)) {
                badges.add(character);
                break;
            }
        }

        for (Character badge : badges) {
            sum += ((int) Character.toLowerCase(badge)) - 96;

            if (Character.isUpperCase(badge)) {
                sum += 26;
            }
        }

        return sum;
    }
}
