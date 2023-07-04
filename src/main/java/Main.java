import util.DebugUtil;
import util.FileCheckUtil;
import util.ConvertUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author weloe
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("============================");
        System.out.println("请输入转换方法 文件路径 输出路径");
        System.out.println("例如: ");
        System.out.println("pdf2word D:\\test\\testpdf.pdf null");
        System.out.println("输出路径如果输入'null'则为文件同目录下同名不同后缀文件");

        // 判断是否还有输入
        while (scan.hasNext()) {

            String convertMethod = scan.next();
            String pathName = scan.next();
            String outPath = scan.next();

            if (!Files.exists(Paths.get(pathName))) {
                DebugUtil.logf("该文件 %s 不存在", pathName);
                continue;
            }

            DebugUtil.logf("转换方法: %s  文件: %s", convertMethod, pathName);

            // 检查文件类型
            try {
                FileCheckUtil.checkFileType(convertMethod.substring(0, 3), pathName);
            } catch (Exception e) {
                DebugUtil.logf("请检查需要转换的文件的类型");
                continue;
            }

            // 执行转换
            try {
                ConvertUtil.convert(convertMethod, pathName, outPath);
            } catch (Exception e) {
                DebugUtil.logf(e.getMessage());
            }
            DebugUtil.logf("转换成功！文件路径: %s", outPath);
        }

        scan.close();


    }


}

