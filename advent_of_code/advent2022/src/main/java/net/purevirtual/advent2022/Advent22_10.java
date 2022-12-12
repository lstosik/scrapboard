package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Advent22_10 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input10.txt");
        Scanner scanner = new Scanner(file);
        int register = 1;
        int completedCycles = 1;
        int sum=0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("noop")) {
                printCrt(completedCycles, register);
                if(shouldCountValue(completedCycles)) {
                    sum=sum+register*completedCycles;
                }
                completedCycles++;
            } else {
                String[] parts = line.split(" ");
                
                
                if(shouldCountValue(completedCycles)) {
                    sum=sum+register*completedCycles;
                }
                printCrt(completedCycles, register);
                completedCycles++;
                
                if(shouldCountValue(completedCycles)) {
                    sum=sum+register*completedCycles;
                }
                register += Integer.parseInt(parts[1]);
                printCrt(completedCycles, register);
                completedCycles++;
            }

        }
        System.out.println();
        System.out.println(sum);
    }

    static boolean shouldCountValue(int cycle) {
        return cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220;
    }
    
    static void printCrt(int cycle, int register) {
        if (register +1>= (cycle % 40)  && register -1 <= (cycle % 40)) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        if (cycle % 40 == 0) {
            System.out.println();
        }
    }
}
