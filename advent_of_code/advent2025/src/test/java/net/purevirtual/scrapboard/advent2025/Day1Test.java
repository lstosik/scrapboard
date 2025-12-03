package net.purevirtual.scrapboard.advent2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {
    @Test
    void countLine() {
        assertEquals(3L, Day1.sumFile("/day1_set1"));
    }

    @Test
    void countLine2() {
        assertEquals(1071L, Day1.sumFile("/day1_set2"));
    }

    @Test
    void countLine3() {
        assertEquals(6, Day1.sumFileV2("/day1_set1"));
    }

    @Test
    void countLine4() {
        assertEquals(6700, Day1.sumFileV2("/day1_set2"));
    }

}