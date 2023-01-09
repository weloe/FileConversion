package util;

import junit.framework.TestCase;

public class FileCheckUtilTest extends TestCase {

    public void testCheckSuffix() {
        String convertMethod = "pdf2word";
        FileCheckUtil.checkFileType(convertMethod.substring(0,3),"dsadad.pdf");
    }
}