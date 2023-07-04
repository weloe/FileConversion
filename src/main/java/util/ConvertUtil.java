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

        String dirName;
        String fileName;

        if ("null".equals(outPath)) {
            outPath = pathName;
        }

        dirName = outPath.substring(0, outPath.lastIndexOf(File.separator));

        int last;
        if (outPath.lastIndexOf(".") != -1) {
            last = outPath.lastIndexOf(".");
        } else {
            last = outPath.length();
        }
        fileName = outPath.substring(outPath.lastIndexOf(File.separator) + 1, last);

        // 调用方法
        for (FileConversion fileConversion : list) {
            if (fileConversion.isSupport(convertMethod)) {
                outPath = fileConversion.convert(pathName, dirName, fileName);
            }
        }

        return outPath;
    }
}
