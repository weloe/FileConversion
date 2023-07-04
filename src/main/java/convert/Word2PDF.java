package convert;


import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import constant.ConvertMethod;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author weloe
 */
public class Word2PDF implements FileConversion {

    private String suffix = ".pdf";

    @Override
    public boolean isSupport(String s) {
        return ConvertMethod.WORD2PDF.equals(s);
    }

    @Override
    public String convert(String pathName, String outDirAndFileName) throws Exception {
        String outPath = outDirAndFileName + getSuffix();
        if (Files.exists(Paths.get(outPath))) {
            throw new FileAlreadyExistsException(outPath + " 文件已存在");
        }
        //加载word
        Document document = new Document();
        document.loadFromFile(pathName, FileFormat.Docx);
        //保存结果文件
        document.saveToFile(outPath, FileFormat.PDF);
        document.close();
        return outPath;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }

}
