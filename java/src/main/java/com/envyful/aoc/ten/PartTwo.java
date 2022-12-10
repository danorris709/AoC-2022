package com.envyful.aoc.ten;

import java.util.concurrent.atomic.AtomicInteger;

public class PartTwo {

    public static void createImage(String text) {
        AtomicInteger x = new AtomicInteger(1);
        int cycle = 0;
        int targetCycle = 0;
        Runnable toExecute = null;

        for (String s : text.split("\n")) {
            while (cycle < targetCycle) {
                if ((cycle % 40) >= x.get() - 1 && (cycle % 40) <= x.get() + 1) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }

                if ((cycle + 1) % 40 == 0) {
                    System.out.println();
                }

                cycle++;
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
    }

}
