package com.envyful.aoc.eleven;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongFunction;

public class PartTwo {

    public static long calculateMonkeyBuisness(String text) {
        List<Monkey> monkeys = Lists.newArrayList();
        Monkey.Builder builder = new Monkey.Builder();
        int trueMonkey = -1;
        int falseMonkey = -1;
        int divisibleBy = -1;
        int commonDenom = 1;

        for (String s : text.split("\n")) {
            if (s.isBlank()) {
                int finalDivisibleBy = divisibleBy;
                int finalTrueMonkey = trueMonkey;
                int finalFalseMonkey = falseMonkey;
                monkeys.add(builder.handler((existing, monkey, value) -> {
                    if (value.get() % finalDivisibleBy == 0) {
                        fromId(existing, monkey).getItems().remove(value);
                        fromId(existing, finalTrueMonkey).getItems().add(value);
                    } else {
                        fromId(existing, monkey).getItems().remove(value);
                        fromId(existing, finalFalseMonkey).getItems().add(value);
                    }
                }).build());
                builder = new Monkey.Builder();
                trueMonkey = -1;
                falseMonkey = -1;
                divisibleBy = -1;
                continue;
            }

            if (s.startsWith("Monkey")) {
                builder.id(Integer.parseInt(s.split(" ")[1].replace(":", "")));
            } else if (s.contains("Starting items:")) {
                String[] items = s.replace("Starting items: ", "").split(", ");
                for (String item : items) {
                    builder.item(Integer.parseInt(item.strip()));
                }
            } else if (s.contains("Operation: ")) {
                String operation = s.replace("Operation: new = ", "");
                boolean multi = operation.contains("*");

                try {
                    int amount = Integer.parseInt(operation.split(" " + (multi ? "\\*" : "\\+") + " ")[1].strip());
                    builder.operation(value -> multi ? value * amount : value + amount);
                } catch (NumberFormatException e) {
                    builder.operation(value -> multi ? value * value : value + value);
                }
            } else if (s.contains("Test: ")) {
                divisibleBy = Integer.parseInt(s.replace("Test: divisible by ", "").strip());
                commonDenom *= divisibleBy;
            } else {
                if (trueMonkey == -1) {
                    trueMonkey = Integer.parseInt(s.replace("If true: throw to monkey ", "").strip());
                } else {
                    falseMonkey = Integer.parseInt(s.replace("If false: throw to monkey ", "").strip());
                }
            }
        }

        int finalDivisibleBy = divisibleBy;
        int finalTrueMonkey = trueMonkey;
        int finalFalseMonkey = falseMonkey;
        monkeys.add(builder.handler((existing, monkey, value) -> {
            if (value.get() % finalDivisibleBy == 0) {
                fromId(existing, monkey).getItems().remove(value);
                fromId(existing, finalTrueMonkey).getItems().add(value);
            } else {
                fromId(existing, monkey).getItems().remove(value);
                fromId(existing, finalFalseMonkey).getItems().add(value);
            }
        }).build());

        for (int i = 0; i < 10_000; i++) {
            for (Monkey monkey : monkeys) {
                for (AtomicLong item : Lists.newArrayList(monkey.getItems())) {
                    item.set(monkey.getOperation().apply(item.get() % commonDenom) % commonDenom);
                    monkey.getHandler().handle(monkeys, monkey.getId(), item);
                    monkey.inspections++;
                }
            }
        }

        monkeys.sort(Comparator.comparing(monkey -> monkey.inspections));

        return (((long) monkeys.get(monkeys.size() - 1).inspections) * monkeys.get(monkeys.size() - 2).inspections);
    }

    private static Monkey fromId(List<Monkey> monkeys, int id) {
        for (Monkey monkey : monkeys) {
            if (monkey.id == id) {
                return monkey;
            }
        }

        return null;
    }

    public static class Monkey {

        private int id;
        private List<AtomicLong> items;
        private LongFunction<Long> operation;
        private TriConsumer<List<Monkey>, Integer, AtomicLong> handler;
        private long inspections = 0;

        public Monkey(int id, List<AtomicLong> items, LongFunction<Long> operation, TriConsumer<List<Monkey>, Integer, AtomicLong> handler) {
            this.id = id;
            this.items = items;
            this.operation = operation;
            this.handler = handler;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<AtomicLong> getItems() {
            return this.items;
        }

        public LongFunction<Long> getOperation() {
            return this.operation;
        }

        public TriConsumer<List<Monkey>, Integer, AtomicLong> getHandler() {
            return this.handler;
        }

        public static class Builder {

            private int id;
            private List<AtomicLong> items = Lists.newArrayList();
            private LongFunction<Long> operation;
            private TriConsumer<List<Monkey>, Integer, AtomicLong> handler;

            public Builder() {
            }

            public Monkey.Builder id(int id) {
                this.id = id;
                return this;
            }

            public Monkey.Builder item(int item) {
                this.items.add(new AtomicLong(item));
                return this;
            }

            public Monkey.Builder operation(LongFunction<Long> operation) {
                this.operation = operation;
                return this;
            }

            public Monkey.Builder handler(TriConsumer<List<Monkey>, Integer, AtomicLong> handler) {
                this.handler = handler;
                return this;
            }

            public Monkey build() {
                return new Monkey(this.id, this.items, this.operation, this.handler);
            }
        }
    }

    public interface TriConsumer<A, B, C> {

        void handle(A a, B b, C c);

    }
}
