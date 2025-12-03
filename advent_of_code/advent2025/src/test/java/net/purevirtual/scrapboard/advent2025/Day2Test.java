package net.purevirtual.scrapboard.advent2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day2Test {
    @Test
    void countLine() {
        assertEquals(1227775554L, Day2.sumFile("/day2_set1"));
    }

    @Test
    void countLine2() {
        assertEquals(21898734247L, Day2.sumFile("/day2_set2"));
    }

    @Test
    void countLineDebug() {
        assertEquals(33, Day2.sumRange("11-22"));
        assertEquals(99, Day2.sumRange("95-115"));
        assertEquals(1010, Day2.sumRange("998-1012"));
        assertEquals(1188511885L, Day2.sumRange("1188511880-1188511890"));
        assertEquals(222222L, Day2.sumRange("222220-222224"));
        assertEquals(0L, Day2.sumRange("1698522-1698528"));
        assertEquals(446446L, Day2.sumRange("446443-446449"));
        assertEquals(38593859L, Day2.sumRange("38593856-38593862"));
        assertEquals(0L, Day2.sumRange("565653-565659"));
        assertEquals(0L, Day2.sumRange("824824821-824824827"));
        assertEquals(0L, Day2.sumRange("2121212118-2121212124"));
    }
    @Test
    void countLine3() {
        assertEquals(4174379265L, Day2.sumFileV2("/day2_set1"));
    }
    @Test
    void countLine4() {
        assertEquals(28915664389L, Day2.sumFileV2("/day2_set2"));
    }

    @Test
    void countLineDebugV2() {
        assertTrue(Day2.isPalindrome("22"));
        assertEquals(33, Day2.sumRangeV2("11-22"));
        assertEquals(2121212121L, Day2.sumRangeV2("2121212118-2121212124"));
    }
}