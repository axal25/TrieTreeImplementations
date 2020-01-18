package agh.jo.utils.file.exceptions;

public class FileWriterCloseException extends Exception {
    public FileWriterCloseException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + FileWriterCloseException.class.getName() + ": " + "\n\r" +
                "Exception cause(custom): " + cause,
            new Throwable(cause)
        );
    }
}
