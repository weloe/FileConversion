package convert;

import junit.framework.TestCase;

public class Word2HTMLTest extends TestCase {

    public void testConvert() {
        new Word2HTML().convert("D:\\test\\testFile.docx","D:\\test\\testFile");
    }
}