package net.purevirtual.scrapboard.algs.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class DFS {
    private List<String> preorder = new ArrayList<>();
    private List<String> postorder = new LinkedList<>();
    private Map<String, Integer> marked = new HashMap<>();
    int mark;
    Graph graph;
    public void trace(Graph graph) {
        
        trace(graph, graph.getVertices());
    }
    
    public void trace(Graph graph, Collection<String> order) {
        this.graph = graph;
        Set<String> explored = new HashSet<>();
        mark = 0;
        for(String first: order) {
            if (!explored.contains(first)) {
                dfs(graph, first, explored, 0);
                mark++;
            }
        }
    }

    private void dfs(Graph graph, String vertice, Set<String> visited, int depth) {
        
        if (visited.contains(vertice)) {
            System.out.println(StringUtils.leftPad("", 2 * depth, ' ') + "check(" + vertice + ")");
            return;
        } else {
            preorder.add(vertice);
            System.out.println(StringUtils.leftPad("", 2 * depth, ' ') + "dfs  (" + vertice + ")");
        }
        visited.add(vertice);
        marked.put(vertice, mark);
        for (String adjacent : graph.getAdjacent(vertice)) {
            dfs(graph, adjacent, visited, depth + 1);
        }
        System.out.println(StringUtils.leftPad("", 2 * depth, ' ') + "done (" + vertice + ")");
        postorder.add(vertice);
    }

    public List<String> getPreorder() {
        return preorder;
    }

    public List<String> getPostorder() {
        return postorder;
    }
    
    public List<Integer> getMarks() {
        List<Integer> result = new ArrayList<>();
        for(String vertice:graph.getVertices()) {
            result.add(marked.get(vertice));
        }
        return result;
    }
    
}
