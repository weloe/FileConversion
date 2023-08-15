package convert;

import org.junit.Test;

public class Word2PDFTest {

    @Test
    public void testConvert() throws Exception {
        new Word2PDF().convert("src/main/resources/testWord.doc", "src/main/resources/Word2PDF.pdf");
    }
}