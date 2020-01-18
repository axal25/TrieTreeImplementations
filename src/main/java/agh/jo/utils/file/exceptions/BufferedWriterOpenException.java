package agh.jo.utils.file.exceptions;

public class BufferedWriterOpenException extends Exception {
    public BufferedWriterOpenException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + BufferedWriterOpenException.class.getName() + ": " + "\n\r" +
                "Exception cause(custom): " + cause,
            new Throwable(cause)
        );
    }
}
