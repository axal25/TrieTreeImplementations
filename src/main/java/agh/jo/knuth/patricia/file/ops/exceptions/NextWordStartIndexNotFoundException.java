package agh.jo.knuth.patricia.file.ops.exceptions;

import agh.jo.utils.file.exceptions.FileOpenException;

public class NextWordStartIndexNotFoundException extends Exception {
    public NextWordStartIndexNotFoundException(String callingClassName, String callingFunctionName, String cause) {
        super(callingClassName + " >>> " + callingFunctionName + " >>> " + FileOpenException.class.getName() + ": " + "\n\r" +
                        "Exception cause(custom): " + cause,
                new Throwable(cause)
        );
    }
}
