package net.purevirtual.scrapboard.advent2025;

import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public static long sumFile(String resourceName) {
        InputLines result = getInputLines(resourceName);
        boolean[][] map = new boolean[result.lines().size()][result.maxLineLength()];
        parseMap(result.lines(), map, result.maxLineLength());
        return partialSum(map, false);
    }

    private static InputLines getInputLines(String resourceName) {
        List<String> lines = new ArrayList<>();
        int maxLineLength = 0;
        try (var scanner = FileUtil.toScanner(resourceName)) {
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                lines.add(line);
                maxLineLength = Math.max(maxLineLength, line.length());
            }
        }
        return new InputLines(lines, maxLineLength);
    }

    private record InputLines(List<String> lines, int maxLineLength) {
    }

    private static int around(boolean[][] map, int x, int y) {
        return
                isRoll(map, x + 1, y)
                        + isRoll(map, x - 1, y)
                        + isRoll(map, x + 1, y + 1)
                        + isRoll(map, x, y + 1)
                        + isRoll(map, x - 1, y + 1)
                        + isRoll(map, x + 1, y - 1)
                        + isRoll(map, x, y - 1)
                        + isRoll(map, x - 1, y - 1);
    }

    private static int isRoll(boolean[][] map, int x, int y) {
        if (x < 0 || y < 0) return 0;
        if (y >= map.length) return 0;
        var row = map[y];
        if (x >= row.length) return 0;
        return row[x] ? 1 : 0;
    }

    public static long sumFileWithRepeats(String resourceName) {
        InputLines result = getInputLines(resourceName);
        boolean[][] map = new boolean[result.lines().size()][result.maxLineLength()];
        parseMap(result.lines(), map, result.maxLineLength());
        long sum = 0;
        while (true) {
            var partialSum = partialSum(map, true);
            if (partialSum == 0) break;
            sum = sum + partialSum;
        }
        return sum;
    }

    private static void parseMap(List<String> lines, boolean[][] map, int maxLineLength) {
        for (int y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            map[y] = new boolean[maxLineLength];
            for (int x = 0; x < line.length(); x++) {
                var cell = line.charAt(x);
                map[y][x] = cell == '@';
            }
        }
    }

    private static long partialSum(boolean[][] map, boolean withRemoval) {
        int sum = 0;
        for (int y = 0; y < map.length; y++) {
            var row = map[y];
            for (int x = 0; x < row.length; x++) {
                var cell = row[x];
                if (cell && around(map, x, y) < 4) {
                    sum++;
                    if (withRemoval) {
                        row[x] = false;
                    }
                }
            }
        }
        return sum;
    }
}

