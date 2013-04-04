package net.purevirtual.scrapboard.algs.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFS {
    private List<String> order = new ArrayList<>();
    private Map<String, Integer> marked = new HashMap<>();
    Graph graph;
    public void trace(Graph graph) {
        this.graph = graph;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int mark = 0;
        for (String first : graph.getVertices()) {
            if (marked.containsKey(first)) {
                continue;
            }
            queue.add(first);
            visited.add(first);
            marked.put(first, mark);
            while (!queue.isEmpty()) {
                String pooled = queue.poll();
                System.out.println("pooled " + pooled);
                order.add(pooled);
                for (String adjacent : graph.getAdjacent(pooled)) {
                    if (!visited.contains(adjacent)) {
                        marked.put(adjacent, mark);
                        visited.add(adjacent);
                        queue.add(adjacent);
                    }
                }
            }
            mark++;
        }
    }
    
    public List<String> getOrder() {
        return order;
    }
    
    public List<Integer> getMarks() {
        List<Integer> result = new ArrayList<>();
        for(String vertice:graph.getVertices()) {
            result.add(marked.get(vertice));
        }
        return result;
    }
}
