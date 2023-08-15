package convert;

import org.junit.Test;

public class PDF2ImageTest {

    @Test
    public void testConvert() throws Exception {
        new PDF2Image().convert("src/main/resources/testPdf.pdf", "src/main/resources/PDF2Image.jpg");
    }
}