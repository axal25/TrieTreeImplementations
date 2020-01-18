package agh.jo.utils.file.exceptions;

public class FileWriterOpenException extends Exception {
    public FileWriterOpenException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + FileWriterOpenException.class.getName() + ": " + "\n\r" +
                "Exception cause(custom): " + cause,
            new Throwable(cause)
        );
    }
}
