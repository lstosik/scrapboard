package net.purevirtual.scrapboard.algs.map;

import junit.framework.TestCase;

/**
 *
 * @author Łukasz Stosik <lstosik@gmail.com>
 */
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

    /**
     * Test of get method, of class TernaryTrie.
     */
    public void testGet_Object() {
        System.out.println("get");
        Object key = null;
        TernaryTrie instance = new TernaryTrie();
        Object expResult = null;
        Object result = instance.get(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class TernaryTrie.
     */
    public void testGet_3args() {
        System.out.println("get");
        TernaryTrie instance = new TernaryTrie();
        Object expResult = null;
        Object result = instance.get(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class TernaryTrie.
     */
    public void testPut() {
        System.out.println("put");
        String key = "";
        Object value = null;
        TernaryTrie instance = new TernaryTrie();
        Object expResult = null;
        Object result = instance.put(key, value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
