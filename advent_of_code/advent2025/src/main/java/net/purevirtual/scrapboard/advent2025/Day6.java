package net.purevirtual.scrapboard.advent2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    public static long sumFile3(String resourceName) {
        try (var scanner = FileUtil.toScanner(resourceName)) {
            List<Long> line1 = readArgumentLine(scanner);
            List<Long> line2 = readArgumentLine(scanner);
            List<Long> line3 = readArgumentLine(scanner);
            List<String> operators = new ArrayList<>();
            while (scanner.hasNext("[*+]")) {
                operators.add(scanner.next("[*+]"));
            }

            long sum = 0;
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i).equals("+")) {
                    sum += line1.get(i) + line2.get(i) + line3.get(i);
                } else {
                    sum += line1.get(i) * line2.get(i) * line3.get(i);
                }
            }
            return sum;
        }
    }

    private static List<Long> readArgumentLine(Scanner scanner) {
        String[] columns = scanner.nextLine().trim().split("\\s+");
        return Arrays.stream(columns).map(Long::parseLong).toList();
    }

    public static long sumFile4(String resourceName) {
        try (var scanner = FileUtil.toScanner(resourceName)) {
            List<Long> line1 = readArgumentLine(scanner);
            List<Long> line2 = readArgumentLine(scanner);
            List<Long> line3 = readArgumentLine(scanner);
            List<Long> line4 = readArgumentLine(scanner);
            List<String> operators = new ArrayList<>();
            while (scanner.hasNext("[*+]")) {
                operators.add(scanner.next("[*+]"));
            }

            long sum = 0;
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i).equals("+")) {
                    sum += line1.get(i) + line2.get(i) + line3.get(i) + line4.get(i);
                } else {
                    sum += line1.get(i) * line2.get(i) * line3.get(i) * line4.get(i);
                }
            }
            return sum;
        }
    }

    public static long sumFileReorderDigits(int rows, String resourceName) {
        try (var scanner = FileUtil.toScanner(resourceName)) {
            List<String> lines = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                lines.add(scanner.nextLine());
            }
            String operators = scanner.nextLine();

            long sum = 0;
            int offset = 0;
            boolean complete = false;
            while (offset < operators.length() && !complete) {
                var operator = operators.charAt(offset);
                int abc = operators.indexOf('+', offset + 1);
                int def = operators.indexOf('*', offset + 1);
                int digitsToUse;
                if (abc == -1 && def == -1) {
                    digitsToUse = operators.length() - offset;
                    complete = true;
                } else if (abc != -1 && def != -1) {
                    digitsToUse = Math.min(abc, def) - offset - 1;
                } else if (abc != -1) {
                    digitsToUse = abc - offset - 1;
                } else {
                    digitsToUse = def - offset - 1;
                }
                System.err.println("digitsToUse: " + digitsToUse);
                if (operator == '+') {
                    long subsum = 0;
                    for (int row = 0; row < digitsToUse; row++) {
                        subsum = subsum + getNumber(lines, rows, offset, row);
                    }
                    System.err.println("subsum: " + subsum);
                    sum += subsum;
                } else {
                    long subproduct = 1;
                    for (int row = 0; row < digitsToUse; row++) {
                        subproduct = subproduct * getNumber(lines, rows, offset, row);
                    }
                    System.err.println("subproduct = " + subproduct);
                    sum += subproduct;
                }
                offset += digitsToUse + 1;
            }
            return sum;
        }
    }

    static long getNumber(List<String> lines, int rowCount, int baseIndex, int digitNumber) {
        int sum = 0;
        for (int j = 0; j < rowCount; j++) {
            String line = lines.get(j);
            if (baseIndex + digitNumber >= line.length()) {
                continue;
            }
            char digit = line.charAt(baseIndex + digitNumber);
            if (digit == ' ') {
                continue;
            }
            sum = digit - '0' + sum * 10;
        }
        System.err.println("Calculated number: " + sum);
        return sum;
    }
}

