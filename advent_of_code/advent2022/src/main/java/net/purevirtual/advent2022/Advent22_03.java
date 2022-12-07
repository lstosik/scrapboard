package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Advent22_03 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input3.txt");
        Scanner scanner = new Scanner(file);
        long sum =0;
        long sum2 =0;
        int count=0;
        while (scanner.hasNext()) {
            String line1  = scanner.next();
            String line2  = scanner.next();
            String line3  = scanner.next();
            final Set<Integer> set = getChars(line1);
            set.retainAll(getChars(line2));
            set.retainAll(getChars(line3));
            sum2=sum2+set.iterator().next();
        }
        System.out.println(sum2);
    }
    
    private static int value(char input) {
        if(input>='A'&& input<='Z') {
                        return input-'A'+27;
                    } else {
                        return input-'a'+1;
                    }
    }
    
    private static Set<Integer> getChars(String input) {
        return input.chars()
                .mapToObj(c->value((char) c))
                .collect(Collectors.toSet());

    }
}
