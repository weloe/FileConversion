package convert;

import constant.ConvertMethod;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import util.DebugUtil;
import util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weloe
 */
public class PDF2Image implements FileConversion {
    private String suffix = ".jpg";
    public static final int DEFAULT_DPI = 150;


    @Override
    public boolean isSupport(String s) {
        return ConvertMethod.PDF2IMG.equals(s);
    }

    @Override
    public String convert(String pathName, String outPath) throws FileAlreadyExistsException {
        if (Files.exists(Paths.get(outPath))) {
            throw new FileAlreadyExistsException(outPath + " 文件已存在");
        }

        pdf2multiImage(pathName, outPath, DEFAULT_DPI);

        return outPath;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * pdf转图片
     * 多页PDF会每页转换为一张图片，下面会有多页组合成一页的方法
     *
     * @param pdfFile pdf文件路径
     * @param outPath 图片输出路径
     * @param dpi     相当于图片的分辨率，值越大越清晰，但是转换时间变长
     */
    public void pdf2multiImage(String pdfFile, String outPath, int dpi) {
        if (dpi <= 0) {
            // 如果没有设置DPI，默认设置为150
            dpi = DEFAULT_DPI;
        }
        try (PDDocument pdf = PDDocument.load(new FileInputStream(pdfFile))) {
            int actSize = pdf.getNumberOfPages();
            List<BufferedImage> picList = new ArrayList<>();
            for (int i = 0; i < actSize; i++) {
                BufferedImage image = new PDFRenderer(pdf).renderImageWithDPI(i, dpi, ImageType.RGB);
                picList.add(image);
            }
            // 组合图片
            ImageUtil.yPic(picList, outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
