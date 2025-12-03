package net.purevirtual.scrapboard.advent2025;

public class Day1 {
    public static long sumFile(String resourceName) {
        try (var scanner = FileUtil.toScanner(resourceName)) {
            int counter = 0;
            int dial = 50;
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                var number = Integer.parseInt(line.substring(1));
                if (line.startsWith("L")) {
                    number = -1 * number;
                }
                dial = (dial + number + 100) % 100;
                if (dial == 0) {
                    counter++;
                }

            }
            return counter;
        }
    }

    public static long sumFileV2(String resourceName) {
        try (var scanner = FileUtil.toScanner(resourceName)) {
            int counter = 0;
            int dial = 50;
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                var number = Integer.parseInt(line.substring(1));
                if (line.startsWith("L")) {
                    number = -1 * number;
                }
                while (number != 0) {
                    if (number < 0) {
                        dial--;
                        number++;
                        if (dial == 0) {
                            counter++;
                        } else if (dial < 0) {
                            dial = 99;
                        }
                    }
                    if (number > 0) {
                        dial++;
                        number--;
                        if (dial > 99) {
                            dial = 0;
                            counter++;
                        }
                    }
                }
            }
            return counter;
        }

    }
}

