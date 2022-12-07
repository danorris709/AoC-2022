package com.envyful.aoc.seven;

import com.envyful.aoc.Main;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PartTwo {

    public static int calculateRequiredDeletion(String text) {
        Map<String, List<Main.File>> directories = Maps.newHashMap();
        Map<String, AtomicInteger> directorySizes = Maps.newHashMap();
        List<String> currentDir = Lists.newArrayList();
        boolean listing = false;

        for (String s : text.split("\n")) {
            if (s.equalsIgnoreCase("$ cd /")) {
                continue;
            }

            if (listing && s.startsWith("$")) {
                listing = false;
            }

            if (s.equalsIgnoreCase("$ ls")) {
                listing = true;
                continue;
            }

            if (listing) {
                String[] split = s.split(" ");

                if (!s.startsWith("dir")) {
                    directories.computeIfAbsent("/" + String.join("/", currentDir), abc -> Lists.newArrayList()).add(new Main.File(split[1], split[0]));

                    for (int i = 0; i <= currentDir.size(); i++) {
                        directorySizes.computeIfAbsent("/" + String.join("/", currentDir.subList(0, i)), dir -> new AtomicInteger(0)).addAndGet(Integer.parseInt(split[0]));
                    }
                } else {
                    directories.computeIfAbsent((currentDir.size() >= 1 ? "/" : "") + String.join("/", currentDir) + "/" + split[1], abc -> Lists.newArrayList());
                }

                continue;
            }

            if (s.startsWith("$ cd ")) {
                String arg = s.replace("$ cd ", "");

                if (arg.equalsIgnoreCase("..")) {
                    currentDir.remove(currentDir.size() - 1);
                } else {
                    currentDir.add(arg);
                }
            }
        }

        List<Integer> bigEnough = Lists.newArrayList();
        int total = directorySizes.get("/").get();
        int free = 70000000 - total;
        int required = 30000000 - free;

        for (Map.Entry<String, AtomicInteger> entry : directorySizes.entrySet()) {
            if (entry.getValue().get() > required) {
                bigEnough.add(entry.getValue().get());
            }
        }

        bigEnough.sort(Comparator.comparing(Integer::intValue));

        return bigEnough.get(0);
    }

    public static class File {

        private String name;
        private String size;

        public File(String name, String size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return this.name;
        }

        public String getSize() {
            return this.size;
        }
    }
}
