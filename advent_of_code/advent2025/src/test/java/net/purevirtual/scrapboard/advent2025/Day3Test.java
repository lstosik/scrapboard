package net.purevirtual.scrapboard.advent2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    void countLine() {
        assertEquals(357, Day3.sumFile("/day3_set1", 2));
    }

    @Test
    void countLine2() {
        assertEquals(17412, Day3.sumFile("/day3_set2", 2));
    }

    @Test
    void countLine2a() {
        assertEquals(3121910778619L, Day3.sumFile("/day3_set1", 12));
    }


    @Test
    void countLine2b() {
        assertEquals(172681562473501L, Day3.sumFile("/day3_set2", 12));
    }
}