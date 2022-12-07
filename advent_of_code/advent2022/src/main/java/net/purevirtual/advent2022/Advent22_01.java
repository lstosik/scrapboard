package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Advent22_01 {

    public  void notmain(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);
        long sum =0;
        while (scanner.hasNextLine()) {
            String line  = scanner.nextLine();
            if (line.isBlank()) {
                break;
            }
            final String[] split = line.split(" ");
            int part=0;
            String op = split[0];
            String my = split[1];
            if(my.equals("Z")) {
                part+=6;
                if(op.equals("A")) {
                    part+=2;
                }
                if(op.equals("B")) {
                    part+=3;
                }
                if(op.equals("C")) {
                    part+=1;
                }
            }
            if(my.equals("Y")) {
                part+=3;
                if(op.equals("A")) {
                    part+=1;
                }
                if(op.equals("B")) {
                    part+=2;
                }
                if(op.equals("C")) {
                    part+=3;
                }
            }
            if(my.equals("X")) {
                part+=0;
                if(op.equals("A")) {
                    part+=3;
                }
                if(op.equals("B")) {
                    part+=1;
                }
                if(op.equals("C")) {
                    part+=2;
                }
            }
            sum+=part;
        }
        System.out.println(sum);
    }
//        public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("input1.txt");
//        Scanner scanner = new Scanner(file);
//        long sum =0;
//        while (scanner.hasNextLine()) {
//            String line  = scanner.nextLine();
//            if (line.isBlank()) {
//                break;
//            }
//            final String[] split = line.split(" ");
//            int part=0;
//            String op = split[0];
//            String my = split[1];
//            if(my.equals("X")) {
//                part+=1;
//            }
//            if(my.equals("Y")) {
//                part+=2;
//            }
//            if(my.equals("Z")) {
//                part+=3;
//            }
//            if((op.equals("A")&&my.equals("Y")) ||
//                    (op.equals("B")&&my.equals("Z")) ||
//                    (op.equals("C")&&my.equals("X"))) {
//                part+=6;
//            } else if((op.equals("A")&&my.equals("X")) ||
//                    (op.equals("B")&&my.equals("Y")) ||
//                    (op.equals("C")&&my.equals("Z"))) {
//                part+=3;
//            } else {
//                part+=0;
//            }
//            sum+=part;
//        }
//        System.out.println(sum);
//    }
    
//    
//     public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("input1.txt");
//        Scanner scanner = new Scanner(file);
//        long sum =0;
//        long maxSum1 = 0;
//        long maxSum2 = 0;
//        long maxSum3 = 0;
//        while (scanner.hasNextLine()) {
//            String line  = scanner.nextLine();
//            if (line.isBlank()) {
//                sum=0;
//                continue;
//            }
//            sum = sum + Long.parseLong(line);
//            if (sum > maxSum1) {
//                maxSum3=maxSum2;
//                maxSum2=maxSum1;
//                maxSum1 = sum;
//            } else if (sum > maxSum2) {
//                maxSum3=maxSum2;
//                maxSum2=sum;
//            }else if (sum > maxSum3) {
//                maxSum3=sum;
//            }
//        }
//        System.out.println(maxSum1+maxSum2+maxSum3);
//    }
}
