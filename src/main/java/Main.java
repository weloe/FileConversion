
import convert.FileConversion;
import convert.PDF2Image;
import convert.PDF2Word;
import util.DebugUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<FileConversion> list = new ArrayList<>();

    static {
        list.add(new PDF2Word());
        list.add(new PDF2Image());
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        // 判断是否还有输入
        while (scan.hasNext()) {

            System.out.println("请输入转换方法，文件路径 输出路径");
            System.out.println("例如 pdf2word D:\\test\\testpdf.pdf null");
            System.out.println("输出路径如果输入'null'则为文件同目录下同名文件");

            String convertMethod = scan.next();
            String pathName = scan.next();
            String outPath = scan.next();

            DebugUtil.log("转换方法: %s  文件: %s",convertMethod,pathName);

            String dirName;
            String fileName;

            if(outPath.equals("null")){
                dirName = pathName.substring(0, pathName.lastIndexOf("\\"));

                fileName = pathName.substring(pathName.lastIndexOf("\\")+1,pathName.lastIndexOf("."));

            }else {
                dirName = outPath.substring(0, outPath.lastIndexOf("\\"));

                fileName = outPath.substring(outPath.lastIndexOf("\\")+1,outPath.lastIndexOf("."));
            }

            for (FileConversion fileConversion : list) {
                if (fileConversion.isSupport(convertMethod)) {
                    try {
                        outPath = fileConversion.convert(pathName, dirName +"\\"+ fileName);
                    } catch (Exception e) {
                        DebugUtil.log(e.getMessage());
                    }
                }
            }

            DebugUtil.log("转换成功！文件路径: %s", outPath);

        }

        scan.close();


    }


}

