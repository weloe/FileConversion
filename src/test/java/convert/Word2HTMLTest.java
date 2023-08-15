package convert;

import org.junit.Test;

import java.nio.file.FileAlreadyExistsException;

public class Word2HTMLTest {

    @Test
    public void testConvert() throws FileAlreadyExistsException {
        new Word2HTML().convert("src/main/resources/testWord.doc", "src/main/resources/Word2HTML.html");
    }
}