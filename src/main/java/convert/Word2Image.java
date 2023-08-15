package convert;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.ImageType;
import constant.ConvertMethod;
import org.apache.pdfbox.rendering.PDFRenderer;
import util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author weloe
 */
public class Word2Image implements FileConversion {
    private String suffix = ".jpg";

    @Override
    public boolean isSupport(String s) {
        return ConvertMethod.WORD2IMAGE.equals(s);
    }

    @Override
    public String convert(String pathName, String outPath) throws Exception {
        if (Files.exists(Paths.get(outPath))) {
            throw new FileAlreadyExistsException(outPath + " 文件已存在");
        }

        Document doc = new Document();
        //加载文件
        doc.loadFromFile(pathName);
        //上传文档页数，也是最后要生成的图片数
        Integer pageCount = doc.getPageCount();
        // 参数第一个和第三个都写死 第二个参数就是生成图片数
        BufferedImage[] image = doc.saveToImages(0, pageCount, ImageType.Bitmap);
        // 组合图片
        List<BufferedImage> imageList = Arrays.asList(image);
        ImageUtil.yPic(imageList, outPath);
        return outPath;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }
}
