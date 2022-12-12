package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Advent22_11 {

    public static void main(String[] args) throws FileNotFoundException {
        List<Monkey> monkeys = new ArrayList<>();
 
        //example monkeys
        /*
        monkeys.add(new Monkey(List.of(79, 98), a->mul(a,19),23,2,3));
        monkeys.add(new Monkey(List.of(54, 65, 75, 74), a->add(a,6),19,2,0));
        monkeys.add(new Monkey(List.of(79, 60, 97), a->mul(a,a),13,1,3));
        monkeys.add(new Monkey(List.of(74), a->add(a,3),17,0,1));
        */
        
        monkeys.add(new Monkey(List.of(71, 56, 50, 73), a->mul(a,11),13,1,7));
        monkeys.add(new Monkey(List.of(70, 89, 82), a->add(a,1),7,3,6));
        monkeys.add(new Monkey(List.of(52, 95), a->mul(a,a),3,5,4));
        monkeys.add(new Monkey(List.of(94, 64, 69, 87, 70), a->add(a,2),19,2,6));
        monkeys.add(new Monkey(List.of(98, 72, 98, 53, 97, 51), a->add(a,6),5,0,5));
        monkeys.add(new Monkey(List.of(79), a->add(a,7),2,7,0));
        monkeys.add(new Monkey(List.of(77, 55, 63, 93, 66, 90, 88, 71), a->mul(a,7),11,2,4));
        monkeys.add(new Monkey(List.of(54, 97, 87, 70, 59, 82, 59), a->add(a,8),17,1,3));
        long divisor = 1;
        for(Monkey monkey:monkeys) {
            divisor = divisor*monkey.test;
        }
        System.err.println("divisor"+divisor);
        for(int round=1;round<=10000;round ++) {
            for(Monkey monkey:monkeys) {
                monkey.items.forEach(item-> {
                    monkey.inspections++;
                    //long newItem = monkey.operation.apply(item) /3;
                    long newItem = monkey.operation.apply(item);
                    if(newItem % monkey.test==0) {
                        monkeys.get(monkey.trueThrow).items.add(newItem);
                    } else {
                        monkeys.get(monkey.falseThrow).items.add(newItem);
                    }
                });
                monkey.items.clear();
            }
        }
        monkeys.sort(Comparator.comparing(Monkey::getInspections));
        System.out.println(monkeys);
        BigInteger best= BigInteger.valueOf(monkeys.get(monkeys.size()-1).inspections);
        BigInteger second= BigInteger.valueOf(monkeys.get(monkeys.size()-2).inspections);
        System.out.println(best.multiply(second));
    }

   
    
    static class Monkey {
        List<Long> items;
        Function<Long, Long> operation;
        long test;
        int trueThrow;
        int falseThrow;
        long inspections = 0;

        public Monkey(List<Integer> items, Function<Long, Long> operation, int test, int trueThrow, int falseThrow) {
            this.items = new ArrayList<>();
            items.forEach(item->this.items.add(item.longValue()));
            this.operation = operation;
            this.test = test;
            this.trueThrow = trueThrow;
            this.falseThrow = falseThrow;
        }

        public long getInspections() {
            return inspections;
        }

        @Override
        public String toString() {
            return "Monkey{" + "inspections=" + inspections + '}';
        }
        
        
        
        
    }
    
    static long specialCube(long input) {
        return mul(input, input);
    }
    
    static long mul(long input1, long input2) {
        BigInteger tmp1 = BigInteger.valueOf(input1);
        BigInteger tmp2 = BigInteger.valueOf(input2);
        BigInteger tmp = tmp1.multiply(tmp2).remainder(BigInteger.valueOf(9699690*23));
        return tmp.longValueExact();
    }
    
    static long add(long input1, long input2) {
        BigInteger tmp1 = BigInteger.valueOf(input1);
        BigInteger tmp2 = BigInteger.valueOf(input2);
        BigInteger tmp = tmp1.add(tmp2).remainder(BigInteger.valueOf(9699690*23));
        return tmp.longValueExact();
    }
   
}

