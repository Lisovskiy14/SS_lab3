package project.exception;

public class PageFaultException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Page Fault has occurred!";
    public PageFaultException() {
        super(DEFAULT_MESSAGE);
    }
}
