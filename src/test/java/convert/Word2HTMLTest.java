package convert;

import junit.framework.TestCase;

import java.nio.file.FileAlreadyExistsException;

public class Word2HTMLTest extends TestCase {

    public void testConvert() throws FileAlreadyExistsException {
        new Word2HTML().convert("D:\\test\\testFile.docx","D:\\test\\testFile");
    }
}