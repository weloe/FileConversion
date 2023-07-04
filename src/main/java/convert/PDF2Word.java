package convert;

import constant.ConvertMethod;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import util.DebugUtil;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.Files.createFile;

/**
 * @author weloe
 */
public class PDF2Word implements FileConversion {

    private String suffix = ".doc";

    @Override
    public boolean isSupport(String s) {
        return ConvertMethod.PDF2WORD.equals(s);
    }

    /**
     * @param pathName
     * @throws IOException
     */
    @Override
    public String convert(String pathName, String outDirAndFileName) throws Exception {
        String outPath = outDirAndFileName + getSuffix();
        if (Files.exists(Paths.get(outPath))) {
            throw new FileAlreadyExistsException(outPath + " 文件已存在");
        }

        pdf2word(pathName, outPath);

        return outPath;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }


    private void pdf2word(String pathName, String outPath) throws IOException {
        PDDocument doc = PDDocument.load(new File(pathName));
        int pageNumber = doc.getNumberOfPages();
        // 创建文件
        createFile(Paths.get(outPath));

        FileOutputStream fos = new FileOutputStream(outPath);
        Writer writer = new OutputStreamWriter(fos, "UTF-8");
        PDFTextStripper stripper = new PDFTextStripper();


        stripper.setSortByPosition(true);

        stripper.setStartPage(1);//设置转换的开始页
        stripper.setEndPage(pageNumber);//设置转换的结束页
        stripper.writeText(doc, writer);
        writer.close();
        doc.close();
    }

}
