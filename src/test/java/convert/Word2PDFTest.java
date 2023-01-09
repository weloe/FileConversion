package convert;

import junit.framework.TestCase;

public class Word2PDFTest extends TestCase {

    public void testConvert() throws Exception {
        new Word2PDF().convert("D:\\test\\testFile.docx","D:\\test\\testFile");
    }
}