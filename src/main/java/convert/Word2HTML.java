package convert;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Word2HTML implements FileConversion{
    private String suffix = ".html";

    @Override
    public boolean isSupport(String s) {
        return "word2html".equals(s);
    }

    @Override
    public String convert(String pathName, String dirAndFileName) {
        String outPath = dirAndFileName + suffix;
        if(Files.exists(Paths.get(outPath))){
            throw new RuntimeException(outPath+" 文件已存在");
        }

        Document doc = new Document();
        doc.loadFromFile(pathName);
        doc.saveToFile(outPath, FileFormat.Html);
        doc.dispose();
        return outPath;
    }
}
