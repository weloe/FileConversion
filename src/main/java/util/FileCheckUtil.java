package util;

import execption.FileTypeError;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weloe
 */
public class FileCheckUtil {
    public static Pattern pdfPattern = Pattern.compile(".*(.pdf)$");
    public static Pattern wordPattern = Pattern.compile(".*(.doc|.docx)$");

    public static Map<String, Pattern> patternMap = new HashMap<>();

    static {
        patternMap.put("pdf", pdfPattern);
        patternMap.put("wor", wordPattern);
    }


    /**
     * @param pdfOrWord ‘pdf' or 'word'
     * @param pathName  文件路径
     * @return
     */
    public static void checkFileType(String pdfOrWord, String pathName) {
        Matcher matcher = patternMap.get(pdfOrWord).matcher(pathName);
        if (!matcher.matches()) {
            throw new FileTypeError("请检查文件类型");
        }
    }


}
