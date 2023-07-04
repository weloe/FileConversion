package util;

/**
 * @author weloe
 */
public class DebugUtil {

    int level = 1;

    public static void logf(String msg, Object... objects) {
        System.out.printf(msg + "\n", objects);
    }

}
