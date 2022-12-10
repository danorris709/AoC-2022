package com.envyful.aoc.ten;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class PartOne {

    public static int getSampleSum(String text) {
        AtomicInteger x = new AtomicInteger(1);
        int cycle = 0;
        int targetCycle = 0;
        Runnable toExecute = null;
        int sum = 0;
        Set<Integer> counted = Sets.newHashSet();

        for (String s : text.split("\n")) {
            while (cycle < targetCycle) {
                cycle++;

                if ((cycle - 20) % 40 == 0 && !counted.contains(cycle)) {
                    sum += cycle * x.get();
                    counted.add(cycle);
                }
            }

            if (toExecute != null) {
                toExecute.run();
            }

            if (s.equalsIgnoreCase("noop")) {
                targetCycle = cycle + 1;
                toExecute = null;
            } else if (s.startsWith("addx")) {
                targetCycle = cycle + 2;
                int increment = Integer.parseInt(s.split(" ")[1]);
                toExecute = () -> x.getAndAdd(increment);
            }
        }

        return sum;
    }

}
