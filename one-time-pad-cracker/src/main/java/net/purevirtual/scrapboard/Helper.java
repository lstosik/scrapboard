package net.purevirtual.scrapboard;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public final class Helper {

    private static final Logger LOG = Logger.getLogger(Helper.class.getName());
    private static final FileFilter TEXT = new SuffixFileFilter(".txt");
    private static final FileFilter BINARY = new SuffixFileFilter(".bin");

    private Helper() {
    }

    public static List<Sample> readSamples(String directory) throws IOException  {
        List<Sample> result = new ArrayList<>();
        LOG.info("Reading samples from path="+new File(directory).getAbsolutePath());
        File[] list = new File(directory).listFiles(TEXT);

        for (File f : list) {
            String content = FileUtils.readFileToString(f);
            short[] rawContent = new short[content.length()/2];
            for(int i=0;i<rawContent.length;i++) {
                
                rawContent[i]= (short) Integer.parseInt(content.substring(i*2, i*2+2),16);
                if(i==0) {
                    System.out.println(content.substring(i*2, i*2+2)+" = "+rawContent[i]);
                }
            }
            result.add(new Sample(rawContent, f.getName()));
        }
        
        /*
        list = new File(directory).listFiles(BINARY);
        for (File f : list) {
            byte[] rawContent = FileUtils.readFileToByteArray(f);
            result.add(new Sample(rawContent));
        }
        */
        return result;
        
    }
    
    public static short[] decode(String input) {
        short[] result = new short[input.length() / 2];
        for (int i = 0; i < result.length; i++) {

            result[i] = (short) Integer.parseInt(input.substring(i * 2, i * 2 + 2), 16);

        }
        return result;
    }
}
