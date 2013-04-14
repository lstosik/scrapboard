package net.purevirtual.googlejam;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 * Hello world!
 *
 */
public class App {
    List<Set<String>> generated = new ArrayList<Set<String>>(500);
    List<Set<String>> generatedSum = new ArrayList<Set<String>>(500);
    private void generate(int n) {
        System.out.println("generate:"+n);
        Set result = new HashSet();
        
        generated.add(result);
        if (n==1) {
            result.add("1");
            result.add("2");
            result.add("3");
            
            
            Set resultSum = new HashSet();
            resultSum.add("1");
            resultSum.add("2");
            resultSum.add("3");
            generatedSum.add(resultSum);
        } else {
            for(String a: generatedSum.get(n-2)) {
                if ("".equals(a)) {
                    continue;
                }
                for(String b: generatedSum.get(n-2)) {
                    if (a.length()*2+b.length()>n) {
                        continue;
                    }
                    makeAndCheckCandidate(a,b, result, n);
                }
                makeAndCheckCandidate(a,"0", result, n);
                makeAndCheckCandidate(a,"", result, n);
            }
            Set tmp = new HashSet(result);
            tmp.addAll(generatedSum.get(n-2));
            generatedSum.add(tmp);
        }
        System.out.println("generated:"+result.size());
        generated.add(result);
    }

        
    public static void main(String[] args) throws FileNotFoundException, IOException {
        App app = new App();
        for(int i=1;i<51;i++) {
            app.generate(i);
        }
        //PrintWriter writer = new PrintWriter(new FileWriter("output"));
        Set<String> sol = app.generatedSum.get(49);
        Set<BigInteger> solutions = new HashSet<BigInteger>();
        for(String s: sol) {
            solutions.add(new BigInteger(s));
        }
        Scanner console = new Scanner(System.in);
        console.next();
        //for(String s: app.generatedSum.get(49)) {
        //    System.out.println(s);
        //}
        //writer.close();
        //System.exit(0);
        PrintWriter writer = new PrintWriter(new FileWriter("output"));
        Scanner scanner = new Scanner(new FileReader("input"));
        int tests = scanner.nextInt();
        scanner.nextLine();
        for(int t=0;t<tests;t++) {
            BigInteger a = scanner.nextBigInteger();
            BigInteger b = scanner.nextBigInteger();
            //System.out.println("a"+a);
            a = bigIntSqRootCeil(a);
            //System.out.println("a"+a);
            //System.out.println("b"+b);
            b = bigIntSqRootFloor(b);
            //System.out.println("b"+b);
            scanner.nextLine();
            BigInteger count = BigInteger.ZERO;
            for (BigInteger i : solutions) {
                if (i.compareTo(b) <= 0 && i.compareTo(a) >= 0) {
                    if (isPalindrome(i) && isPalindrome(i.multiply(i))) {
                        //System.out.println("i" + i + " a " + i.multiply(i));
                        count = count.add(BigInteger.ONE);
                    }
                }
            }
            
            String msg = "Case #"+(t+1)+": "+count;
            System.out.println(msg);
            writer.println(msg);
        }
        writer.close();
    }
    
    public static boolean isPalindrome(BigInteger num) {
        char[] word = num.toString().toCharArray();
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }
    
    public static boolean isPalindrome(String num) {
        char[] word = num.toCharArray();
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }
    
    //http://stackoverflow.com/a/11962756/158037
    public static BigInteger bigIntSqRootFloor(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x.equals( BigInteger.ZERO) || x.equals( BigInteger.ONE)) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
                y.compareTo(x.divide(y)) > 0;
                y = ((x.divide(y)).add(y)).divide(two));
        return y;
    } // end bigIntSqRootFloor

    public static BigInteger bigIntSqRootCeil(BigInteger x)
            throws IllegalArgumentException {
        if (x.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x.equals( BigInteger.ZERO) || x.equals( BigInteger.ONE)) {
            return x;
        } // end if
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
                y.compareTo(x.divide(y)) > 0;
                y = ((x.divide(y)).add(y)).divide(two));
        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        } else {
            return y.add(BigInteger.ONE);
        }
    } // end bigIntSqRootCeil

    private void makeAndCheckCandidate(String a, String b, Set result, int n) {
        int needPad = n - (a.length() * 2 + b.length());
        if (needPad % 2 == 0) {
            String candidate = a + StringUtils.rightPad("", needPad / 2, "0") + b + StringUtils.leftPad("", needPad / 2, "0") + a;
            //System.out.println("candidate:"+candidate);
            if (isPalindrome(candidate)) {
                BigInteger i = new BigInteger(candidate);
                i = i.multiply(i);
                if (isPalindrome(i)) {
                    result.add(candidate);
                }
            }
        }
    }
}
