package service.writer;

public class WriterException extends Exception {
    public WriterException() {
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(Throwable cause) {
        super(cause);
    }

    public WriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
