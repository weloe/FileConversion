package constant;

import java.util.HashSet;

/**
 * @author weloe
 */
public class ConvertMethod {
    public final static String PDF2IMG = "pdf2image";
    public final static String PDF2WORD = "pdf2word";
    public final static String WORD2HTML = "word2html";
    public final static String WORD2IMAGE = "word2image";
    public final static String WORD2PDF = "word2pdf";
    public final static String MD2WORD = "md2word";

    public static final HashSet<String> SET = new HashSet<>();
    static {
        SET.add(ConvertMethod.PDF2WORD);
        SET.add(ConvertMethod.PDF2IMG);
        SET.add(ConvertMethod.WORD2HTML);
        SET.add(ConvertMethod.WORD2IMAGE);
        SET.add(ConvertMethod.WORD2PDF);
        SET.add(ConvertMethod.MD2WORD);
    }

}
