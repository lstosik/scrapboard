package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Advent22_05 {

    public static void main(String[] args) throws FileNotFoundException {
        Deque<Character>[] stacks = new Deque[10];
        stacks[0]= new ArrayDeque();
        stacks[1]= deque("DBJV");
        stacks[2]= deque("PVBWRDF");
        stacks[3]= deque("RGFLDCWQ");
        stacks[4]= deque("WJPMLNDB");
        stacks[5]= deque("HNBPCSQ");
        stacks[6]= deque("RDBSNG");
        stacks[7]= deque("ZBPMQFSH");
        stacks[8]= deque("WLF");
        stacks[9]= deque("SVFMR");
        System.out.println();
        for (int j=1;j<=9;j++) {
            System.out.print(stacks[j].peek());
        }
        System.out.println();
        File file = new File("input5.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line  = scanner.nextLine();
            //move 1 from 4 to 1
            String[] split = line.split(" ");
            System.err.println("split "+line+" to "+Arrays.toString(split));
            int count=Integer.parseInt(split[1]);
            int from = Integer.parseInt(split[3]);
            int to = Integer.parseInt(split[5]);
            Deque<Character> hand = new ArrayDeque();
            for (int j=0;j<count;j++) {
                hand.push(stacks[from].pop());
            }
            for (int j=0;j<count;j++) {
                stacks[to].push(hand.pop());
            }
        }
        for (int j=1;j<=9;j++) {
            System.out.println(j+" = "+stacks[j].peek());
        }
        System.out.println();
        for (int j=1;j<=9;j++) {
            System.out.print(stacks[j].peek());
        }
        System.out.println();
    }
    
    private static Deque<Character> deque(String input) {
        List<Character> tmp = new ArrayList();
        for (Character c:input.toCharArray()) {
            tmp.add(c);
        }
        Collections.reverse(tmp);
        return new ArrayDeque(tmp);
    }
}
