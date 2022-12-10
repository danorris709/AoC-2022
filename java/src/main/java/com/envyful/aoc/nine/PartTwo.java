package com.envyful.aoc.nine;

import com.envyful.aoc.Main;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class PartTwo {

    public static int countTailMovement(String text) {
        List<Position> positions = IntStream.range(0, 10).mapToObj(i -> new Position(0, 0)).toList();
        Set<Position> travelledPositions = Sets.newHashSet(positions.get(positions.size() - 1).copy());

        for (String s : text.split("\n")) {
            String[] elements = s.split(" ");
            String direction = elements[0];
            int amount = Integer.parseInt(String.valueOf(elements[1]));

            for (int i = 0; i < amount; i++) {
                Position head = positions.get(0);

                switch (direction.toLowerCase(Locale.ROOT)) {
                    case "r" -> head.setX(head.getX() + 1);
                    case "l" -> head.setX(head.getX() - 1);
                    case "u" -> head.setY(head.getY() + 1);
                    case "d" -> head.setY(head.getY() - 1);
                }

                for (int pos = 1; pos < positions.size(); pos++) {
                    Position tail = positions.get(pos);
                    head = positions.get(pos - 1);
                    int deltaX = head.getX() - tail.getX();
                    int deltaY = head.getY() - tail.getY();

                    if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1) {
                        tail.setX(tail.getX() + (Math.abs(deltaX) > 1 ? (deltaX / Math.abs(deltaX)) : deltaX));
                        tail.setY(tail.getY() + (Math.abs(deltaY) > 1 ? (deltaY / Math.abs(deltaY)) : deltaY));
                    }
                }

                travelledPositions.add(positions.get(positions.size() - 1).copy());
            }
        }

        return travelledPositions.size();
    }


    public static class Position {

        private static int COUNTER = 0;

        private int id = COUNTER++;
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getId() {
            return this.id;
        }

        public int getX() {
            return this.x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position)o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Position copy() {
            return new Position(this.x, this.y);
        }

        public double distanceFromZero() {
            return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        }
    }
}
