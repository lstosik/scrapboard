package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Advent22_06 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input6.txt");
        Scanner scanner = new Scanner(file);
        String line  = scanner.nextLine();
        for (int i =0;i<line.length()-13;i++) {
            Set<Character> quad = new HashSet<>();
            for(int j=0;j<14;j++) {
                quad.add(line.charAt(i+j));
            }
            //if(quad.size()==4) {
            if(quad.size()==14) {
                System.out.println(i+1+13);
                break;
            }
        }
        
    }
    
}
