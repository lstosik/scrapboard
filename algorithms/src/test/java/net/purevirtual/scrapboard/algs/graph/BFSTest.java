package net.purevirtual.scrapboard.algs.graph;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

public class BFSTest extends TestCase {
    
    public BFSTest(String testName) {
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
     * Test of trace method, of class BFS.
     */
    public void testTrace() throws IOException {
        System.out.println("trace");
        BFS instance = new BFS();
        InputStream is = GraphTest.class.getResourceAsStream("graph2.txt");
        Graph result = Graph.read(is);
        instance.trace(result);
    }

    /**
     * Test of getOrder method, of class BFS.
     */
    public void testGetOrder() throws IOException {
        System.out.println("getOrder");
        BFS instance = new BFS();
        InputStream is = GraphTest.class.getResourceAsStream("graph2.txt");
        Graph result = Graph.read(is);
        instance.trace(result);
        assertEquals("A B E C F G D H", StringUtils.join(instance.getOrder(), ' '));
        
        System.out.println("getOrder");
        instance = new BFS();
        is = GraphTest.class.getResourceAsStream("graph4.txt");
        result = Graph.read(is);
        instance.trace(result);
        assertEquals("A B E C D H G F", StringUtils.join(instance.getOrder(), ' '));
    }

    /**
     * Test of getMarks method, of class BFS.
     */
    public void testGetMarks() throws IOException {
        System.out.println("getMarks");
        BFS instance = new BFS();
        InputStream is = GraphTest.class.getResourceAsStream("graph3.txt");
        Graph result = Graph.read(is);
        instance.trace(result);
        assertEquals("0 0 1 2 2 0 1 2 2 2", StringUtils.join(instance.getMarks(), ' '));
    }
}
