package convert;

import constant.ConvertMethod;
import util.ConvertUtil;
import util.FileUtil;

public class Md2Word  implements FileConversion {
    private String suffix = ".docx";


    @Override
    public boolean isSupport(String s) {
        return ConvertMethod.MD2WORD.equals(s);
    }

    @Override
    public String convert(String pathName, String outPath) throws Exception {
        // 1. 读取 Markdown 文件
        String markdown = FileUtil.readFile(pathName);

        // 2. 将 Markdown 转换为 HTML
        String html = ConvertUtil.markdownToHtml(markdown);

        // 3. 将 HTML 转换为 Word 文档
        ConvertUtil.htmlToWord(html, outPath);

        return outPath;
    }


    @Override
    public String getSuffix() {
        return this.suffix;
    }
}
