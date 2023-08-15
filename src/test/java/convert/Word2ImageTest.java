package convert;

import org.junit.Test;

public class Word2ImageTest {

    @Test
    public void testConvert() throws Exception {
        new Word2Image().convert("src/main/resources/testWord.doc", "src/main/resources/Word2Image.jpg");
    }
}