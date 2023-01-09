package convert;

import java.io.IOException;

/**
 * @author weloe
 */
public interface FileConversion {

    boolean isSupport(String s);

    String convert(String pathName,String dirAndFileName) throws Exception;

}
