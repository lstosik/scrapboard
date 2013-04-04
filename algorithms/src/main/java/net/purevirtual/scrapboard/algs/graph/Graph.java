package net.purevirtual.scrapboard.algs.graph;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;



/**
 *
 * @author ucho
 */
public class Graph {
    private LinkedHashMap<String, List<String>> state = new LinkedHashMap<>();
    public static Graph read(InputStream is) throws IOException {
        List<String> lines = IOUtils.readLines(is);
        //System.out.println("lines:"+lines.size());
        Graph graph = new Graph();
        for(String line: lines) {
            String[] split = line.split("[^0-9a-zA-Z]");
            graph.addVertice(split[0]);
            for(int i=1;i<split.length;i++) {
                graph.addEdge(split[0], split[i]);
            }
        }
        return graph;
    }

    public void addVertice(String string) {
        List<String> get = state.get(string);
        if(get == null) {
            state.put(string, new ArrayList<String>());
        }
    }

    public void addEdge(String string, String string0) {
        List<String> get = state.get(string);
        get.add(string0);
        //System.out.println("adding "+string+" -> "+ string0);
    }
    
    public Collection<String> getVertices() {
        return state.keySet();
    }

    Iterable<String> getAdjacent(String vertice) {
        return state.get(vertice);
    }

    Graph reverse() {
        Graph result = new Graph();
        for(String vertice: this.getVertices()) {
            result.addVertice(vertice);
        }
        for(String vertice: this.getVertices()) {
            for(String adjacent: this.getAdjacent(vertice)) {
                result.addEdge(adjacent, vertice);
            }
        }
        return result;
    }

    
}
