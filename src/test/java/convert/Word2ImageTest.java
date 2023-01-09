package convert;

import junit.framework.TestCase;

public class Word2ImageTest extends TestCase {

    public void testConvert() throws Exception {
        new Word2Image().convert("D:\\test\\testFile.doc","D:\\test\\testFile");
    }
}