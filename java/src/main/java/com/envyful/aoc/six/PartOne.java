package com.envyful.aoc.six;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class PartOne {

    public static int getCodeStart(String text) {
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i < 4) {
                continue;
            }

            Set<Character> key = Sets.newHashSet();
            List<Character> builder = Lists.newArrayList();

            for (int a = 0; a < 4; a++) {
                key.add(chars[i - a]);
                builder.add(chars[i - a]);
            }

            if (key.size() == 4) {
                return i + 1;
            }
        }

        return -1;
    }
}
