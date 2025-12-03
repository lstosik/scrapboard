package net.purevirtual.scrapboard.advent2025;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileUtil {
    public static Scanner toScanner(String resourceName) {
        InputStream input = FileUtil.class.getResourceAsStream(resourceName);
        if(input==null) {
            throw new IllegalArgumentException("No such resource: "+resourceName);
        }
        return new Scanner(input);
    }

    public static String readToString(String resourceName) {
        try (var input = Day2.class.getResourceAsStream(resourceName)) {
            if (input == null) {
                throw new IllegalArgumentException("No such resource: " + resourceName);
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex ) {
            throw new RuntimeException(ex);
        }
    }


}
