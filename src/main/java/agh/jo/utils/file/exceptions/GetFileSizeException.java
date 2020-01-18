package agh.jo.utils.file.exceptions;

public class GetFileSizeException extends Exception {
    public GetFileSizeException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + GetFileSizeException.class.getName() + ": " + "\n\r" +
                "Exception cause(custom): " + cause,
                new Throwable(cause)
        );
    }
}
