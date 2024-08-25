package util;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.XHTMLValidationType;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import convert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weloe
 */
public class ConvertUtil {
    private static final List<FileConversion> list = new ArrayList<>();

    static {
        list.add(new PDF2Word());
        list.add(new PDF2Image());

        list.add(new Word2HTML());
        list.add(new Word2Image());
        list.add(new Word2PDF());

        list.add(new Md2Word());
    }

    /**
     * 转换整个文件夹里的文件
     * @param convertMethod
     * @param inputFolder
     * @param outFolder
     * @throws Exception
     */
    public static void convertFiles(String convertMethod, String inputFolder, String outFolder) throws Exception {

        for (File file : FileUtil.getFiles(inputFolder)) {
            System.out.println(file.getName());
            String outPath = outFolder + file.getName().split("\\.")[0];
            System.out.println(outPath);
            convert(convertMethod,file.getAbsolutePath(), outPath);
        }

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






    public static void htmlToWord(String html, String outPath) throws IOException {
        // 创建临时 HTML 文件
        File tempHtmlFile = File.createTempFile("temp", ".html");
        tempHtmlFile.deleteOnExit(); // 确保程序结束后删除临时文件

        // 将 HTML 字符串写入临时文件
        try (FileWriter writer = new FileWriter(tempHtmlFile)) {
            writer.write(html);
        }


        // 创建 Word 文档
        Document document = new Document();

        // 从临时 HTML 文件加载内容
        document.loadFromFile(tempHtmlFile.getAbsolutePath(), FileFormat.Html, XHTMLValidationType.None);


        // 保存 Word 文档
        document.saveToFile(outPath, FileFormat.Docx_2013);

        System.out.println("HTML 字符串已转换为 Word 文档: " + outPath);
    }

    public static String markdownToHtml(String markdown) {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(markdown);
        return renderer.render(document);
    }

}
