package net.purevirtual.scrapboard.advent2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    @Test
    void countLineBasicSet() {
        assertEquals(4277556, Day6.sumFile3("/day6_set1"));
    }

    @Test
    void countLineCustomSet() {
        assertEquals(5552221122013L, Day6.sumFile4("/day6_set2"));
    }

    @Test
    void countBasicSetWithRangeLength() {
        assertEquals(3263827, Day6.sumFileReorderDigits(3, "/day6_set1"));
    }

    @Test
    void countCustomSetWithRangeLength() {
        assertEquals(11371597126232L, Day6.sumFileReorderDigits(4, "/day6_set2"));
    }
}