package com.envyful.aoc.five;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class PartOne {

    public static String getTopCrates(String text) {
        List<String> lines = Lists.newArrayList();
        Map<Integer, List<String>> stacks = null;

        for (String s : text.split("\n")) {
            if (s.contains("[") && stacks == null) {
                lines.add(s);
                continue;
            }

            if (stacks == null && s.isEmpty()) {
                stacks = generateStacks(lines);
                System.out.println(stacks);
                continue;
            }

            if (!s.contains("move")) {
                continue;
            }

            String[] s1 = s.split(" ");
            int count = Integer.parseInt(s1[1]);
            int from = Integer.parseInt(s1[3]);
            int to = Integer.parseInt(s1[5]);

            for (int i = 0; i < count; i++) {
                List<String> fromStack = stacks.get(from);
                List<String> toStack = stacks.get(to);

                toStack.add(fromStack.remove(fromStack.size() - 1));
            }

            System.out.println(s);
            System.out.println(stacks);
        }

        StringBuilder builder = new StringBuilder();

        for (List<String> value : stacks.values()) {
            builder.append(value.get(value.size() - 1));
        }

        return builder.toString();
    }

    private static Map<Integer, List<String>> generateStacks(List<String> lines) {
        Map<Integer, List<String>> stacks = Maps.newHashMap();

        for (String line : lines) {
            char[] chars = line.toCharArray();

            for (int i = 0; i < line.toCharArray().length; i++) {
                char c = chars[i];

                if (c != '[') {
                    continue;
                }

                String character = chars[i + 1] + "";
                int pos = (i) / 4;
                stacks.computeIfAbsent(pos + 1, unused -> Lists.newArrayList()).add(character);
                i += 3;
            }
        }

        for (Integer integer : stacks.keySet()) {
            stacks.put(integer, Lists.reverse(stacks.get(integer)));
        }

        return stacks;
    }

}
