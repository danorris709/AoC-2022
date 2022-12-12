package com.envyful.aoc.twelve;

import com.github.javaparser.utils.Pair;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class PartTwo {

    public static int findShortestPossibleLowestStartingPath(String text) {
        String[] data = text.split("\n");
        int[][] heightmap = new int[data[0].toCharArray().length][data.length];
        List<Coordinate> starts = Lists.newArrayList();
        Coordinate end = null;
        int currentY = 0;

        for (String s : data) {
            char[] chars = s.toCharArray();
            int currentX = 0;

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'S' || chars[i] == 'a') {
                    starts.add(new Coordinate(currentX, currentY));
                    heightmap[currentX][currentY] = 0;
                    currentX++;
                } else if (chars[i] == 'E') {
                    end = new Coordinate(currentX, currentY);
                    heightmap[currentX][currentY] = 25;
                    currentX++;
                } else {
                    heightmap[currentX][currentY] = (int) chars[i] - 97;
                    currentX++;
                }
            }

            currentY++;
        }

        int shortestPath = 10000;

        for (Coordinate start : starts) {
            int length = calculateShortestPath(start, end, heightmap);
            if (length < shortestPath) {
                shortestPath = length;
            }
        }

        return shortestPath;
    }

    private static int calculateShortestPath(Coordinate start, Coordinate end, int[][] heightmap) {
        Set<Coordinate> travelled = Sets.newHashSet(start);
        Queue<Pair<Coordinate, Integer>> next = Queues.newArrayDeque(Lists.newArrayList(new Pair<>(start, 0)));

        Pair<Integer, Integer>[] directions = new Pair[] {

                new Pair(0, -1),
                new Pair(0, 1),

                new Pair(-1, 0),
                new Pair(1, 0),

        };

        while (!next.isEmpty()) {
            Pair<Coordinate, Integer> poll = next.poll();
            Coordinate current = poll.a;

            if (current.equals(end)) {
                return poll.b;
            }

            for (Pair<Integer, Integer> direction : directions) {
                if ((current.y + direction.b) < 0 ||
                        (current.x + direction.a) < 0 ||
                        (current.x + direction.a) >= heightmap.length ||
                        (current.y + direction.b) >= heightmap[0].length) {
                    continue;
                }

                int testY = current.y + direction.b;
                int testX = current.x + direction.a;
                int currentHeight = heightmap[current.x][current.y];
                int testHeight = heightmap[testX][testY];
                int delta = testHeight - currentHeight;
                Coordinate test = new Coordinate(testX, testY);

                if (delta > 1 || travelled.contains(test)) {
                    continue;
                }

                travelled.add(test);
                next.add(new Pair<>(test, poll.b + 1));
            }
        }

        return 10_000;
    }

    public static class Coordinate {

        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public double distance(Coordinate coordinate) {
            return Math.sqrt(Math.pow(((double) this.x) - coordinate.x, 2) + Math.pow(((double) this.y) - coordinate.y, 2));
        }

        @Override
        public String toString() {
            return x + ", " + y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate)o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
