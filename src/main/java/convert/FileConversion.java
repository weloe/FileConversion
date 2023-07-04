package convert;

import java.io.File;

/**
 * @author weloe
 */
public interface FileConversion {

    /**
     * 判断是否支持该方法
     * @param s
     * @return
     */
    boolean isSupport(String s);

    /**
     * @param pathName          源文件路径
     * @param outDirAndFileName 输出的文件路径和文件名
     * @return
     * @throws Exception
     */
    String convert(String pathName, String outDirAndFileName) throws Exception;


    /**
     * @param pathName 源文件路径
     * @param outDir   输出的文件目录
     * @param outName  输出的文件名
     * @return
     * @throws Exception
     */
    default String convert(String pathName, String outDir, String outName) throws Exception {
        return convert(pathName, outDir + File.separator + outName);
    }

    /**
     * 输出文件为本目录下的同名文件
     *
     * @param pathName
     * @return
     * @throws Exception
     */
    default String convert(String pathName) throws Exception {
        String dirName = pathName.substring(0, pathName.lastIndexOf(File.separator));
        String fileName = pathName.substring(pathName.lastIndexOf(File.separator) + 1, pathName.lastIndexOf("."));
        return convert(pathName, dirName + File.separator + fileName);
    }

    /**
     * 输出文件的后缀
     * @return
     */
    String getSuffix();

}
