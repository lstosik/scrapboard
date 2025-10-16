package net.purevirtual.scrapboard.algs.graph;

import java.io.InputStream;
import junit.framework.TestCase;

public class GraphTest extends TestCase {
    
    public GraphTest(String testName) {
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
     * Test of read method, of class Graph.
     */
    public void testRead() throws Exception {
        System.out.println("read");
        InputStream is = GraphTest.class.getResourceAsStream("graph1.txt");
        Graph expResult = null;
        Graph result = Graph.read(is);
        new DFS().trace(result);
        //assertEquals(expResult, result);
        //// TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
