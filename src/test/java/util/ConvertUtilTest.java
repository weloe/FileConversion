package util;

import org.junit.Test;

public class ConvertUtilTest {

    @Test
    public void testConvert() throws Exception {
        // windows test
//         ConvertUtil.convert("pdf2word", "src/main/resources/testPdf.pdf", "D:\\GithubRepository\\FileConversion\\src\\main\\resources\\ConvertUtil");

        // linux test
        ConvertUtil.convert("pdf2word", "src/main/resources/testPdf.pdf", "src/main/resources/ConvertUtil");
    }
}