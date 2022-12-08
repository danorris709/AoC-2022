package com.envyful.aoc.eight;

import java.util.AbstractMap;
import java.util.Map;

public class PartTwo {

    public static int getBestViewingDistance(String text) {
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

        int best = -1;

        for (int x = 1; x < (trees.length); x++) {
            int[] row = trees[x];

            for (int y = 1; y < (row.length); y++) {
                int current = row[y];


                int viewingDistance = getViewingDistance(trees, current, y, x, new AbstractMap.SimpleEntry<>(1, 0)) *
                        getViewingDistance(trees, current, y, x, new AbstractMap.SimpleEntry<>(-1, 0)) *
                        getViewingDistance(trees, current, y, x, new AbstractMap.SimpleEntry<>(0, 1)) *
                        getViewingDistance(trees, current, y, x, new AbstractMap.SimpleEntry<>(0, -1));

                if (viewingDistance > best) {
                    best = viewingDistance;
                }
            }
        }

        return best;
    }

    private static int getViewingDistance(int[][] board, int current, int currentY, int currentX, Map.Entry<Integer, Integer> delta) {
        int viewingDistance = 0;
        while ((currentX + delta.getKey()) < board.length && (currentY + delta.getValue()) < board[0].length
                && (currentX + delta.getKey()) >= 0 && (currentY + delta.getValue()) >= 0) {
            currentY += delta.getValue();
            currentX += delta.getKey();

            if (board[currentX][currentY] < current) {
                viewingDistance++;
            }


            if (board[currentX][currentY] >= current) {
                viewingDistance++;
                break;
            }
        }

        return viewingDistance;
    }
}
