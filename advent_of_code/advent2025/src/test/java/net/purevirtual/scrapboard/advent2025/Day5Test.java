package net.purevirtual.scrapboard.advent2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    @Test
    void countLineBasicSet() {
        assertEquals(3, Day5.sumFile("/day5_set1"));
    }

    @Test
    void countLineCustomSet() {
        assertEquals(615, Day5.sumFile("/day5_set2"));
    }

    @Test
    void countBasicSetWithRangeLength() {
        assertEquals(14, Day5.sumRangeLength("/day5_set1"));
    }

    @Test
    void countCustomSetWithRangeLength() {
        assertEquals(353716783056994L, Day5.sumRangeLength("/day5_set2"));
    }
}