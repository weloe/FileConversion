package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void main(String[] args) {

        getFiles("C:\\Users\\psyco\\Desktop\\代写\\运营日报").forEach(System.out::println);
    }
    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    public static List<File> getFiles(String folderPath) {
        List<File> mdFilePaths = new ArrayList<>();
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".md")) {
                        mdFilePaths.add(file);
                    }
                }
            }
        }
        return mdFilePaths;
    }
}
