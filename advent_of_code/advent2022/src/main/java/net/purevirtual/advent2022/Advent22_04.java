package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Advent22_04 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input4.txt");
        Scanner scanner = new Scanner(file);
        int count=0;
        int count2=0;
        while (scanner.hasNext()) {
            String line  = scanner.next();
            String[] pairs = line.split(",");
            int a1=Integer.parseInt(pairs[0].split("-")[0]);
            int a2=Integer.parseInt(pairs[0].split("-")[1]);
            int b1=Integer.parseInt(pairs[1].split("-")[0]);
            int b2=Integer.parseInt(pairs[1].split("-")[1]);
            if((a1<=b1&& a2>=b2)
                    ||(b1<=a1&& b2>=a2)) {
                count++;
            }
            if((a1<=b1&& b1<=a2)
                    ||(a1<=b2&& b2<=a2)
                    ||(b1<=a1&& a1<=b2)
                    ||(b1<=a2&& a2<=b2)
                    ) {
                count2++;
            }
        }
        System.out.println(count);
        System.out.println(count2);
    }
}
