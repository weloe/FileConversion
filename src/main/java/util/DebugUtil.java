package util;

public class DebugUtil {

    int level = 1;

    public static void log(String msg,Object ... objects){
        System.out.printf(msg + "\n", objects);
    }

}
