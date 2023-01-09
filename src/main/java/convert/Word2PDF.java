package convert;


import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author weloe
 */
public class Word2PDF implements FileConversion{

    private String suffix = ".pdf";

    @Override
    public boolean isSupport(String s) {
        return "word2pdf".equals(s);
    }

    @Override
    public String convert(String pathName, String dirAndFileName) throws Exception {
        String outPath = dirAndFileName + suffix;
        if(Files.exists(Paths.get(outPath))){
            throw new RuntimeException(outPath+" 文件已存在");
        }
        //加载word
        Document document = new Document();
        document.loadFromFile(pathName, FileFormat.Docx);
        //保存结果文件
        document.saveToFile(outPath, FileFormat.PDF);
        document.close();
        return outPath;
    }
}
