package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Advent22_07 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input7.txt");
        Scanner scanner = new Scanner(file);
        Stack<String> stack = new Stack();
        stack.add("/");
        Mode mode = null;
        Map<String,Long> dirSizes = new HashMap<>();
        while(scanner.hasNextLine()) {
            String line  = scanner.nextLine();
            if (line.equals("$ cd /")) {
                stack = new Stack();
                stack.add("/");
            } else if (line.startsWith("$")) {
                if(line.equals("$ ls")) {
                    mode = Mode.LIST;
                } else if(line.equals("$ cd ..")) {
                    stack.pop();
                } else if(line.startsWith("$ cd ")) {
                    String dirName = line.substring("$ cd ".length());
                    stack.push(dirName);
                } else {
                    System.err.println("unsupported command: "+line);
                }
            } else {
                switch(mode) {
                    case LIST-> {
                        if(line.startsWith("dir ")) {
                            // ignore
                        } else {
                            final String[] parts = line.split(" ");
                            long size = Long.parseLong(parts[0]);
                            Stack<String> tmp = new Stack();
                            tmp.addAll(stack);
                            while(!tmp.isEmpty()) {
                                String currentPath = tmp.stream().reduce((a,b)->(a+"/"+b)).get();
                                dirSizes.compute(currentPath, (k, v) -> v == null ? size : v + size);
                                tmp.pop();
                            }
                            
                        }
                    }
                    default -> {
                        System.err.println("unsupported token: "+line);
                    }
                }
                
            }
        }
        System.err.println("topDir "+dirSizes);
        System.err.println("topDir "+dirSizes.entrySet().stream().filter(e->e.getValue()<=100000).map(e->e.getValue()).reduce((a,b)->a+b));
        
        long free= 70000000- dirSizes.get("/");
        long needToFree= 30000000-free;
        System.err.println("total =  "+dirSizes.get("/")+", free="+free+", need do free="+needToFree);
        //long sum = dirSizes.entrySet().stream().filter(x->x.getKey().contains("/")).map(e->e.getValue()).reduce((a,b)->a+b));
        System.err.println("topDir "+dirSizes.entrySet().stream().filter(e->e.getValue()>=needToFree).sorted((a,b)->a.getValue().compareTo(b.getValue())).findFirst());
        System.err.println("topDir "+dirSizes.entrySet().stream().filter(e->e.getValue()>=needToFree).sorted((a,b)->b.getValue().compareTo(a.getValue())).findFirst());
        
    }
    
    enum Mode {
        LIST;
    }
    
}
