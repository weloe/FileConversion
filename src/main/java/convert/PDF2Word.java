package convert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import util.DebugUtil;

import java.io.*;
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
        return "pdf2word".equals(s);
    }

    /**
     *
     * @param pathName
     * @throws IOException
     */
    @Override
    public String convert(String pathName,String dirAndFileName) throws Exception {
        String outPath = dirAndFileName + suffix;
        if(Files.exists(Paths.get(outPath))){
            throw new RuntimeException(outPath+" 文件已存在");
        }

        pdf2word(pathName, outPath);

        return outPath;
    }


    private void pdf2word(String pathName, String outPath) throws IOException {
        PDDocument doc = PDDocument.load(new File(pathName));
        int pagenumber = doc.getNumberOfPages();
        // 创建文件
        createFile(Paths.get(outPath));

        FileOutputStream fos = new FileOutputStream(outPath);
        Writer writer = new OutputStreamWriter(fos, "UTF-8");
        PDFTextStripper stripper = new PDFTextStripper();


        stripper.setSortByPosition(true);//排序

        stripper.setStartPage(1);//设置转换的开始页
        stripper.setEndPage(pagenumber);//设置转换的结束页
        stripper.writeText(doc, writer);
        writer.close();
        doc.close();
    }

}
