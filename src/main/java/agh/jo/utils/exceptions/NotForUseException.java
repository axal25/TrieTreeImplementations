package agh.jo.utils.exceptions;

import agh.jo.utils.file.exceptions.FileOpenException;

public class NotForUseException extends Exception {
    public NotForUseException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + FileOpenException.class.getName() + ": " + "\n\r" +
                        "Exception cause(custom): " + cause,
                new Throwable(cause)
        );
    }
}
