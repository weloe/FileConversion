package execption;

/**
 * @author weloe
 */
public class FileTypeError extends RuntimeException {
    public FileTypeError(String message) {
        super(message);
    }

    public FileTypeError() {
        super();
    }
}
