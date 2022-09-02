package SpaceRun.adventure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Utils {
    
    public static Set<String> loadFileListInSet(File file) {
        Set<String> set = new HashSet<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Errore file non trovato");
            Runtime.getRuntime().exit(0);
        }
        try {
            while (reader.ready()) {
                set.add(reader.readLine().trim().toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Errore recupero dal file");
            Runtime.getRuntime().exit(0);
        }
        return set;
    }

    public static List<String> parseString(String string, Set<String> stopwords) {
        List<String> tokens = new ArrayList<>();
        String[] split = string.toLowerCase().split("\\s+");
        for (String t : split) {
            if (!stopwords.contains(t)) {
                tokens.add(t);
            }
        }
        return tokens;
    }    
}