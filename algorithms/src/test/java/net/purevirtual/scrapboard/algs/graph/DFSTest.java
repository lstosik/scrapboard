package net.purevirtual.scrapboard.algs.graph;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class DFSTest extends TestCase {
    
    public DFSTest(String testName) {
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
     * Test of getPreorder method, of class DFS.
     */
    public void testGetPreorder() throws IOException {
        System.out.println("getPreorder");
        DFS instance = new DFS();
        InputStream is = GraphTest.class.getResourceAsStream("graph1.txt");
        Graph result = Graph.read(is);
        instance.trace(result);
        assertEquals("A E B G D C H F", StringUtils.join(instance.getPreorder(), ' '));
    }



    /**
     * Test of getPostorder method, of class DFS.
     */
    public void testGetPostorder() throws IOException {
        System.out.println("getPostorder");
        DFS instance = new DFS();
        InputStream is = GraphTest.class.getResourceAsStream("graph5.txt");
        Graph result = Graph.read(is);
        instance.trace(result);
        String reverse = StringUtils.reverse(StringUtils.join(instance.getPostorder(), ' '));
        System.out.println(reverse);
        assertEquals("F G H E A B C D", reverse);
        
        
        instance = new DFS();
        is = GraphTest.class.getResourceAsStream("graph6.txt");
        result = Graph.read(is);
        Graph reversedGraph = result.reverse();
        instance.trace(reversedGraph);
        List<String> order = instance.getPostorder();
        Collections.reverse(order);
        
        instance = new DFS();
        instance.trace(result, order);
        
        assertEquals("1 1 2 2 2 1 1 0 2 2", StringUtils.join(instance.getMarks(), ' '));
    }
}
