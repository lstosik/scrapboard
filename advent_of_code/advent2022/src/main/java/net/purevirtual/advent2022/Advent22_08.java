package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Advent22_08 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input8.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        int height = lines.size();
        int width = lines.get(0).length();
        System.out.println("lines =" + height + ", length = " + width);
        boolean[][] visible = new boolean[height][width];
        int totalCount = 0;
        int maxScenic = 0;
        for (int y = 0; y < height; y++) {
            int maxSoFar = -1;
            for (int x = 0; x < width; x++) {
                int tree = lines.get(y).charAt(x) - '0';
                if (tree > maxSoFar) {
                    maxSoFar = tree;
                    if (!visible[y][x]) {
                        totalCount++;
                    }
                    visible[y][x] = true;
                }
                int scenic = scenic(lines, y, x);
                if (scenic > maxScenic) {
                    maxScenic = scenic;
                    System.err.println("new record "+maxScenic+" for "+y+" "+x);
                }
            }
            maxSoFar = -1;
            for (int x = width - 1; x >= 0; x--) {
                int tree = lines.get(y).charAt(x) - '0';
                if (tree > maxSoFar) {
                    maxSoFar = tree;
                    if (!visible[y][x]) {
                        totalCount++;
                    }
                    visible[y][x] = true;
                }
            }
        }
        for (int x = 0; x < width; x++) {
            int maxSoFar = -1;
            for (int y = 0; y < height; y++) {
                int tree = lines.get(y).charAt(x) - '0';
                if (tree > maxSoFar) {
                    maxSoFar = tree;
                    if (!visible[y][x]) {
                        totalCount++;
                    }
                    visible[y][x] = true;
                }
            }
            maxSoFar = -1;
            for (int y = height - 1; y >= 0; y--) {
                int tree = lines.get(y).charAt(x) - '0';
                if (tree > maxSoFar) {
                    maxSoFar = tree;
                    if (!visible[y][x]) {
                        totalCount++;
                    }
                    visible[y][x] = true;
                }
            }
        }
        System.out.println(totalCount);
        System.out.println(maxScenic);
    }

    private static int scenic(List<String> lines, int y, int x) {
        int height = lines.size();
        int width = lines.get(0).length();
        int tree = lines.get(y).charAt(x) - '0';
        int product = 1;
        int count = 0;
        if(y==0||x==0||y==height-1||x==width-1) {
            return 0;
        }
        for (int y1 = y - 1; y1 >= 0; y1--) {
            int other = lines.get(y1).charAt(x) - '0';
            count++;
            if (other >= tree) {
                break;
            }
        }
        product *= count;
        
        //System.err.println("found down "+count+" for "+y+" "+x);
        count = 0;
        for (int y1 = y + 1; y1 < height; y1++) {
            int other = lines.get(y1).charAt(x) - '0';
            //System.err.println("compare "+other +" vs starting "+tree);
            count++;
            if (other >= tree) {
                break;
            }
        }
        product *= count;
        //System.err.println("found up "+count+" for "+y+" "+x);
        count = 0;
        for (int x1 = x - 1; x1 >= 0; x1--) {
            int other = lines.get(y).charAt(x1) - '0';
            count++;
            if (other >= tree) {
                break;
            }
        }
        //System.err.println("found left "+count+" for "+y+" "+x);
        product *= count;
        count = 0;
        for (int x1 = x + 1; x1 < width; x1++) {
            int other = lines.get(y).charAt(x1) - '0';
            count++;
            if (other >= tree) {
                break;
            }
        }
        product *= count;
        //System.err.println("found right "+count+" for "+y+" "+x);
        //System.err.println();
        return product;
    }

}
