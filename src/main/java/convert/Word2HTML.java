package convert;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import constant.ConvertMethod;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author weloe
 */
public class Word2HTML implements FileConversion {
    private String suffix = ".html";

    @Override
    public boolean isSupport(String s) {
        return ConvertMethod.WORD2HTML.equals(s);
    }

    @Override
    public String convert(String pathName, String outDirAndFileName) throws FileAlreadyExistsException {
        String outPath = outDirAndFileName + getSuffix();
        if (Files.exists(Paths.get(outPath))) {
            throw new FileAlreadyExistsException(outPath + " 文件已存在");
        }

        Document doc = new Document();
        doc.loadFromFile(pathName);
        doc.saveToFile(outPath, FileFormat.Html);
        doc.dispose();
        return outPath;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }
}
