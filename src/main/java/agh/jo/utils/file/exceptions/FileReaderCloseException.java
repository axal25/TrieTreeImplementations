package agh.jo.utils.file.exceptions;

public class FileReaderCloseException extends Exception {
    public FileReaderCloseException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + FileReaderCloseException.class.getName() + ": " + "\n\r" +
                        "Exception cause(custom): " + cause,
                new Throwable(cause)
        );
    }
}
