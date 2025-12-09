package net.purevirtual.scrapboard.advent2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Day5 {

    public static long sumFile(String resourceName) {
        try (var scanner = FileUtil.toScanner(resourceName)) {
            List<Range> ranges = readRanges(scanner);
            int sum = 0;
            while (scanner.hasNextLine()) {
                var ingredient = scanner.nextLong();
                if (fresh(ranges, ingredient)) {
                    sum++;
                }
            }
            return sum;
        }
    }

    public static long sumRangeLength(String resourceName) {

        try (var scanner = FileUtil.toScanner(resourceName)) {
            List<Range> ranges = readRanges(scanner);
            for (var range : ranges) {
                System.err.println(range.from + " " + range.to + " " + range.length());
            }
            return ranges.stream().map(Range::length).reduce(Long::sum).orElseThrow();

        }
    }

    private static List<Range> readRanges(Scanner scanner) {
        List<Range> ranges = new ArrayList<>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.isBlank()) {
                break;
            }
            String[] parts = line.split("-");
            Range newRange = new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
            addOrMerge(ranges, newRange);
        }
        return ranges;
    }

    private static void addOrMerge(List<Range> ranges, Range newRange) {
        Optional<Range> overlap = ranges.stream().filter(x -> x.overlaps(newRange)).findFirst();
        if (overlap.isEmpty()) {
            ranges.add(newRange);
        } else {
            ranges.remove(overlap.get());
            addOrMerge(ranges, rangeUnion(newRange, overlap.get()));
        }
    }


    private static Range rangeUnion(Range a, Range b) {
        return new Range(Math.min(a.from(), b.from()), Math.max(a.to(), b.to()));
    }

    private static boolean fresh(List<Range> ranges, long ingredient) {
        for (var range : ranges) {
            if (range.from <= ingredient && ingredient <= range.to) {
                return true;
            }
        }
        return false;
    }


    private record Range(long from, long to) {
        public long length() {
            return to - from + 1;
        }

        public boolean overlaps(Range newRange) {
            return overlaps(newRange.from) || overlaps(newRange.to)
                    || newRange.overlaps(from) || newRange.overlaps(to);
        }

        public boolean overlaps(long input) {
            return from <= input && input <= to;
        }
    }


}

