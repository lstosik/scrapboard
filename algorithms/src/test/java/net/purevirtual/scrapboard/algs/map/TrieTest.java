package net.purevirtual.scrapboard.algs.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import junit.framework.TestCase;

public class TrieTest extends TestCase {
	
	public TrieTest(String testName) {
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


	public void testTries1() {
		System.out.println("testTries2");
        String[] keys = "11 313 2323 311 133 23 22".split(" ");
		      Trie<String> instance = new Trie<String>();
        for (String s : keys) {
            instance.put(s, "yes, we can");
            System.out.println("put(" + s + ")");
        }
        for (String s : keys) {
            String value = instance.get(s);
            System.out.println("get(" + s + ")=" + value);
        }
        System.out.println("testTries2:" + instance.countNodes());
	}
}
