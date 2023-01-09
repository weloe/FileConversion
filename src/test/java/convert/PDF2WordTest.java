package convert;

import convert.PDF2Word;
import org.junit.Test;

public class PDF2WordTest {

    @Test
    public void convert() throws Exception {
        PDF2Word pdf2Word = new PDF2Word();
        pdf2Word.convert("D:\\test\\testFile.pdf","D:\\test\\testFile");
    }
}