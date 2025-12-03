package net.purevirtual.scrapboard.advent2025;

import java.util.HashMap;
import java.util.Map;

public class Day3 {

    public static long sumFile(String resourceName, int canSelect) {
        try (var scanner = FileUtil.toScanner(resourceName)) {
            long sum = 0;
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                sum = sum + maxDigits(line, canSelect);
            }
            return sum;
        }
    }

    public static long maxDigits(String input, int canSelect) {
        return maxDigits(input, canSelect, new HashMap<>(input.length() * canSelect));
    }
    public static long maxDigits(String input, int canSelect, Map<String, Long> memo) {
        if(canSelect<1) {
            return 0;
        }
        String key = input + "_" + canSelect;
        Long cached = memo.get(key);
        if(cached!= null) {
            return cached;
        }
        if (canSelect >= input.length()) {
            return Long.parseLong(input);
        }
        int current = Character.digit(input.charAt(input.length()-1), 10);
        long withCurrent = current + 10*maxDigits(input.substring(0, input.length()-1), canSelect-1, memo);
        long withoutCurrent = maxDigits(input.substring(0, input.length()-1), canSelect, memo);
        long result = Math.max(withoutCurrent, withCurrent);
        memo.put(key,result);
        return result;
    }
}

