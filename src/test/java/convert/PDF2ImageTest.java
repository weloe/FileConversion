package convert;

import convert.PDF2Image;
import junit.framework.TestCase;

public class PDF2ImageTest extends TestCase {

    public void testConvert() throws Exception {
        new PDF2Image().convert("D:\\test\\testFile.pdf","D:\\test\\testFile");
    }
}