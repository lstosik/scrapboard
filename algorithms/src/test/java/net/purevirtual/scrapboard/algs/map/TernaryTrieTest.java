package net.purevirtual.scrapboard.algs.map;

import junit.framework.TestCase;

public class TernaryTrieTest extends TestCase {

    public TernaryTrieTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testTries2() {
        System.out.println("testTries2");
        String[] keys = "441 212 213 352 244 344 432".split(" ");
        TernaryTrie<String> instance = new TernaryTrie<>();
        for (String s : keys) {
            instance.put(s, "yes, we can");
            System.out.println("put(" + s + ")");
        }

        for (String s : keys) {
            System.out.println("" + instance.getDepth(s));
        }
    }
}
