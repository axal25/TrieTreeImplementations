package agh.jo.utils.file.exceptions;

public class BufferedWriterCloseException extends Exception {
    public BufferedWriterCloseException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + BufferedWriterCloseException.class.getName() + ": " + "\n\r" +
                "Exception cause(custom): " + cause,
            new Throwable(cause)
        );
    }
}
