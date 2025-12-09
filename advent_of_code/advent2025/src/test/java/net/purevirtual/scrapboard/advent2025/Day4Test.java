package net.purevirtual.scrapboard.advent2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void countLineBasicSet() {
        assertEquals(13, Day4.sumFile("/day4_set1"));
    }

    @Test
    void countLineCustomSet() {
        assertEquals(1569, Day4.sumFile("/day4_set2"));
    }

    @Test
    void countBasicSetWithRepeats() {
        assertEquals(43, Day4.sumFileWithRepeats("/day4_set1"));
    }

    @Test
    void countCustomSetWithRepeats() {
        assertEquals(9280, Day4.sumFileWithRepeats("/day4_set2"));
    }

}