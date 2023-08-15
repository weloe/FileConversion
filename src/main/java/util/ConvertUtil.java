package util;

import convert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weloe
 */
public class ConvertUtil {
    private static List<FileConversion> list = new ArrayList<>();

    static {
        list.add(new PDF2Word());
        list.add(new PDF2Image());

        list.add(new Word2HTML());
        list.add(new Word2Image());
        list.add(new Word2PDF());
    }


    public static String convert(String convertMethod, String pathName, String outPath) throws Exception {

        if ("null".equals(outPath)) {
            outPath = pathName;
            outPath = outPath.substring(0,outPath.lastIndexOf("."));
        }

        FileConversion fileConversion = getConverter(convertMethod);
        if (fileConversion == null) {
            throw new RuntimeException("不支持该转化方法: " + convertMethod);
        }
        if (outPath.lastIndexOf(".") != -1) {
            // 有后缀
            FileCheckUtil.checkFileType(convertMethod.substring(convertMethod.lastIndexOf("2") + 1),outPath);
        } else {
            // 没有后缀加上后缀
            outPath = outPath + fileConversion.getSuffix();
        }
        // 判断输出路径是否有文件
        FileCheckUtil.checkFileExist(outPath);

        outPath = fileConversion.convert(pathName, outPath);
        return outPath;
    }

    private static FileConversion getConverter(String convertMethod) {
        // 调用方法
        for (FileConversion fileConversion : list) {
            if (fileConversion.isSupport(convertMethod)) {
                return fileConversion;
            }
        }
        return null;
    }
}
