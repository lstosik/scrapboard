package net.purevirtual.scrapboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.HexDump;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IOException, DecoderException {
        List<Sample> list = Helper.readSamples("src/main/resources/samples1");
        for (int j = 0; j < list.size(); j++) {
            System.out.printf("sample sample %d\n", list.get(j).getRaw()[0]);
        }
        Sample longest = Collections.max(list, new Comparator<Sample>() {

            @Override
            public int compare(Sample arg0, Sample arg1) {
                return arg0.getRaw().length - arg1.getRaw().length;
            }
        });
        short[] guesses = new short[longest.getRaw().length];
        for (int i = 0; i < guesses.length; i++) {
            for (short g = 0; g <= 255; g++) {
                if (isValidGuess(g, list, i)) {
                    System.out.printf("valid %d %c\n", g, g);
                    guesses[i] = g;
                    System.out.println("Sample results:");
                    /*
                     * for (Sample s : list) { int decoded = s.getRaw()[i] ^ g;
                     * System.out.printf("%d %c\n",decoded,decoded);
                     *
                     * }
                     */
                    break;
                }
            }
        }
        List<Sample> list2 = new ArrayList<>();
        short[] key = new short[]{1, 2};
        short[] a1 = new short[]{'a', ' '};
        short[] a2 = new short[2];
        short[] b1 = new short[]{' ', 'a'};
        short[] b2 = new short[2];
        b2[0] = (short) (b1[0] ^ key[0]);
        a2[0] = (short) (a1[0] ^ key[0]);
        b2[1] = (short) (b1[1] ^ key[1]);
        a2[1] = (short) (a1[1] ^ key[1]);
        list2.add(new Sample(b2, "x"));
        list2.add(new Sample(a2, "y"));
        for (Sample s : list) {
            System.out.println("decoded " + s.getFileName() + ":");
            for (int i = 0; i < s.getRaw().length; i++) {
                System.out.printf("%c", s.getRaw()[i] ^ guesses[i]);
            }
        }
        for (int i = 0; i < 300; i++) {

            for (int j = 0; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    short[] kb = list.get(k).getRaw();
                    short[] jb = list.get(j).getRaw();
                    if (i >= kb.length || i >= jb.length) {
                        continue;
                    }
                    short xor = (short) (kb[i] ^ jb[i]);
                    //System.out.printf("%d\n", xor);
                    if ('A' <= xor && xor <= 'Z') {

                        System.out.printf("%d(%d) is space and %d(%d) is %c or vice versa\n", j, i, k, i, xor);
                        short g1 = (short) (' ' ^ kb[i]);
                        short g2 = (short) (' ' ^ jb[i]);
                        int o1 = countLetters(g1, list, i);
                        int o2 = countLetters(g2, list, i);
                        System.out.printf("odds: %d %d\n", o1, o2);
                        if (o1 > o2) {
                            guesses[i] = g1;
                        } else if (o2 > o1) {
                            guesses[i] = g2;
                        }
                    }
                    if ('a' <= xor && xor <= 'z') {

                        System.out.printf("%d(%d) is space and %d(%d) is %c or vice versa\n", j, i, k, i, xor);
                        short g1 = (short) (' ' ^ kb[i]);
                        short g2 = (short) (' ' ^ jb[i]);
                        System.out.printf("odds: %d %d\n", countLetters(g1, list, i), countLetters(g2, list, i));
                    }
                }
            }
        }
        for (Sample s : list) {
            System.out.println("decoded " + s.getFileName() + ":");
            for (int i = 0; i < s.getRaw().length; i++) {
                System.out.printf("%c", s.getRaw()[i] ^ guesses[i]);
            }

            System.out.println("");
            for (int i = 0; i < s.getRaw().length; i++) {
                //System.out.printf("%d", s.getRaw()[i] ^ guesses[i]);
            }
            System.out.println("");
        }
        String x1 = "vampires at night";
        String x2 = "vampires at morning";
        byte[] br = Hex.decodeHex("123456aaaaa".toCharArray());
        for(int i=0;i<br.length;i++) {
            br[i] = (byte) (br[i]^x1.charAt(i)^x2.charAt(i));
        }
        System.out.println(Hex.encodeHexString(br));
    }

    private boolean isValidGuess(short guess, List<Sample> list, int pos) {

        for (Sample s : list) {
            if (pos >= s.getRaw().length) {
                continue;
            }
            int decoded = s.getRaw()[pos] ^ guess;
            boolean ischaracter = decoded >= 'a' && decoded <= 'z';
            //ischaracter = ischaracter || (decoded >= 'A' && decoded <='Z');
            //ischaracter = ischaracter || (decoded >= '0' && decoded <='9');
            if (!ischaracter && decoded != ' ' && decoded!='.' && decoded!=',') {
                //System.out.printf("not a char: %d %c\n", decoded, decoded);
                return false;
            }
        }
        return true;
    }

    private int countLetters(short guess, List<Sample> list, int pos) {
        int result = 0;
        for (Sample s : list) {
            if (pos >= s.getRaw().length) {
                continue;
            }
            int decoded = s.getRaw()[pos] ^ guess;
            boolean ischaracter = decoded >= 'a' && decoded <= 'z';
            ischaracter = ischaracter || (decoded >= 'A' && decoded <= 'Z');
            //ischaracter = ischaracter || (decoded >= '0' && decoded <='9');
            if (ischaracter) {
                result++;

            }
        }
        return result;
    }
    
    
    /*
     * public void testApp1() throws IOException {
     * StringUtils.isAlphanumeric(null) System.out.println("test1"); for(int
     * a='a';a<='z';a++) { System.out.println(""+a+ " "+ (a^' ')); } for(int
     * a='A';a<='Z';a++) { System.out.println(""+a+ " "+ (a^' ')); } for(int
     * a='0';a<='9';a++) { System.out.println(""+a+ " "+ (a^' ')); }
     * System.out.println(""+ (' '^' '));
     *
     *
     * }
     */
}
