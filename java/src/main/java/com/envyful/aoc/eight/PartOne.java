package com.envyful.aoc.eight;

import java.util.AbstractMap;
import java.util.Map;

public class PartOne {

    public static int calculateTreeCoverage(String text) {
        String[] split = text.split("\n");
        int[][] trees = new int[split.length][split[0].toCharArray().length];

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            char[] chars = s.toCharArray();

            for (int j = 0; j < chars.length; j++) {
                trees[i][j] = Integer.parseInt(String.valueOf(chars[j]));
                System.out.print(trees[i][j]);
            }

            System.out.println();
        }

        int visible = (split.length * 2) + (2 * split[0].toCharArray().length) - 4;

        for (int x = 1; x < (trees.length - 1); x++) {
            int[] row = trees[x];

            for (int y = 1; y < (row.length - 1); y++) {
                int current = row[y];


                if (checkDirection(trees, current, y, x, new AbstractMap.SimpleEntry<>(1, 0)) ||
                        checkDirection(trees, current, y, x, new AbstractMap.SimpleEntry<>(-1, 0)) ||
                        checkDirection(trees, current, y, x, new AbstractMap.SimpleEntry<>(0, 1)) ||
                        checkDirection(trees, current, y, x, new AbstractMap.SimpleEntry<>(0, -1))) {
                    visible++;
                }
            }
        }

        return visible;
    }

    private static boolean checkDirection(int[][] board, int current, int currentY, int currentX, Map.Entry<Integer, Integer> delta) {
        while ((currentX + delta.getKey()) < board.length && (currentY + delta.getValue()) < board[0].length
                && (currentX + delta.getKey()) >= 0 && (currentY + delta.getValue()) >= 0) {
            currentY += delta.getValue();
            currentX += delta.getKey();

            if (board[currentX][currentY] >= current) {
                return false;
            }
        }

        return true;
    }
}
