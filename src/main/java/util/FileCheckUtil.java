package util;

import execption.FileTypeError;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public static Pattern imgPattern = Pattern.compile(".*(.jpg|.jpeg|.png)$");
    public static Pattern htmlPattern = Pattern.compile(".*(.html)$");

    public static Map<String, Pattern> patternMap = new HashMap<>();

    static {
        patternMap.put("pdf", pdfPattern);
        patternMap.put("word", wordPattern);
        patternMap.put("image",imgPattern);
        patternMap.put("html",htmlPattern);
    }


    /**
     * @param  ‘pdf' or 'word'
     * @param pathName  文件路径
     * @return
     */
    public static void checkFileType(String type, String pathName) {
        type = type.toLowerCase();
        Pattern pattern = patternMap.get(type);
        Matcher matcher = pattern.matcher(pathName);
        if (!matcher.matches()) {
            throw new FileTypeError("文件类型错误: " + pathName);
        }
    }

    public static void checkFileExist(String path) throws FileAlreadyExistsException {
        if (Files.exists(Paths.get(path))) {
            throw new FileAlreadyExistsException(path + " 文件已存在");
        }
    }

}
