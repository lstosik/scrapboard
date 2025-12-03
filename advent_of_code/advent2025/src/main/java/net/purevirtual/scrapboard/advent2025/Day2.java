package net.purevirtual.scrapboard.advent2025;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Day2 {

    public static long sumFile(String resourceName)  {
        String line = FileUtil.readToString(resourceName);
        String[] ranges = line.split(",");
        long sum = 0;
        for (var range : ranges) {
            sum = sum + sumRange(range);
        }
        return sum;
    }

    public static long sumRange(String range) {
        String[] parts = range.split("-");
        String from = parts[0];
        String to = parts[1];
        long current;
        if (from.length() % 2 != 0) {
            //from = "1" + "0".repeat(from.length() / 2);
            current = BigInteger.TEN.pow(from.length() / 2).longValueExact();
        } else {
            current = Long.parseLong(from.substring(0, from.length() / 2));
        }
        long minFrom = Long.parseLong(from);
        long maxTo = Long.parseLong(to);
        long sum = 0;
        while (true) {
            long fullCurrent = Long.parseLong(String.valueOf(current).repeat(2));
            if (fullCurrent > maxTo) {
                break;
            }
            if(fullCurrent>=minFrom) {
                sum = sum + fullCurrent;
            }
            current++;
        }
        return sum;
    }

    public static long sumFileV2(String resourceName) {
        String line = FileUtil.readToString(resourceName);
        String[] ranges = line.split(",");
        long sum = 0;
        for (var range : ranges) {
            sum = sum + sumRangeV2(range);
        }
        return sum;
    }

    public static long sumRangeV2(String range) {
        String[] parts = range.split("-");
        String from = parts[0];
        String to = parts[1];
        long minFrom = Long.parseLong(from);
        long maxTo = Long.parseLong(to);
        long sum = 0;
        for(long current=minFrom; current <= maxTo; current++) {
            if(isPalindrome(String.valueOf(current))) {
                sum = sum + current;
            }
        }
        return sum;
    }

    public static boolean isPalindrome(String input) {
        for(int i =1;i<=input.length()/2;i++) {
            if (isPalindrome(input, i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPalindrome(String input, int partSize) {
        if (input.length() % partSize != 0) {
            return false;
        }
        Set<String> parts = new HashSet<>();
        for(int i =0;i<(input.length() / partSize);i++) {
            parts.add(input.substring(partSize*i,partSize*(i+1)));
        }
        return parts.size() == 1;
    }

}

