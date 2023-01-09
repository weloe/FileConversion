package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {


    /**
     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同
     *
     * @param picList 文件流数组
     * @param outPath 输出路径
     */
    public static void yPic(List<BufferedImage> picList, String outPath) {// 纵向处理图片
        if (picList == null || picList.size() <= 0) {
            DebugUtil.log("图片为空");
            return;
        }
        try {
            // 总高度
            int height = 0,
                    // 总宽度
                    width = 0,
                    // 临时的高度 , 或保存偏移高度
                    offsetHeight = 0,
                    // 临时的高度，主要保存每个高度
                    tmpHeight = 0,
                    // 图片的数量
                    picNum = picList.size();
            // 保存每个文件的高度
            int[] heightArray = new int[picNum];
            // 保存图片流
            BufferedImage buffer = null;
            // 保存所有的图片的RGB
            List<int[]> imgRgb = new ArrayList<int[]>();
            // 保存一张图片中的RGB数据
            int[] tmpImgRgb;
            for (int i = 0; i < picNum; i++) {
                buffer = picList.get(i);
                // 图片高度
                heightArray[i] = offsetHeight = buffer.getHeight();
                if (i == 0) {
                    // 图片宽度
                    width = buffer.getWidth();
                }
                // 获取总高度
                height += offsetHeight;
                // 从图片中读取RGB
                tmpImgRgb = new int[width * offsetHeight];
                tmpImgRgb = buffer.getRGB(0, 0, width, offsetHeight, tmpImgRgb, 0, width);
                imgRgb.add(tmpImgRgb);
            }
            // 设置偏移高度为0
            offsetHeight = 0;
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < picNum; i++) {
                tmpHeight = heightArray[i];
                if (i != 0) {
                    // 计算偏移高度
                    offsetHeight += tmpHeight;
                }
                // 写入流中
                imageResult.setRGB(0, offsetHeight, width, tmpHeight, imgRgb.get(i), 0, width);
            }
            File outFile = new File(outPath);
            // 写图片
            ImageIO.write(imageResult, "png", outFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
