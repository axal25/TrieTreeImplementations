package agh.jo.utils.file.exceptions;

public class BufferedReaderCloseException extends Exception {
    public BufferedReaderCloseException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + BufferedReaderCloseException.class.getName() + ": " + "\n\r" +
                "Exception cause(custom): " + cause,
                new Throwable(cause)
        );
    }
}
