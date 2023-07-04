package util;

import junit.framework.TestCase;

public class ConvertUtilTest extends TestCase {

    public void testConvert() throws Exception {
        ConvertUtil.convert("pdf2word","src/main/resources/testPdf.pdf","src/main/resources/ConvertUtil");
    }
}