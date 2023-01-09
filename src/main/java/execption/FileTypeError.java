package execption;

public class FileTypeError extends RuntimeException {
    public FileTypeError(String message) {
        super(message);
    }

    public FileTypeError() {
        super();
    }
}
