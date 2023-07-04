package convert;

import org.junit.Test;

public class PDF2WordTest {

    @Test
    public void testConvert() throws Exception {
        PDF2Word pdf2Word = new PDF2Word();
        pdf2Word.convert("src/main/resources/testPdf.pdf","src/main/resources/PDF2Word");
    }
}