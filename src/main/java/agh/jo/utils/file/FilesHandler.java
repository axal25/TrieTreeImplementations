package agh.jo.utils.file;

import java.io.*;
import agh.jo.utils.file.exceptions.*;

public class FilesHandler {

    public static File tryNewFile(String fileName) {
        final String functionName = "tryNewFile(String fileName)";
        File fileToReturn = null;
        try {
            if( fileName == null || fileName.isEmpty() ) {
                String cause = "fileName = \"" + fileName + "\"";
                throw new FileOpenException(FilesHandler.class.getName(), functionName, cause);
            }
            fileToReturn = new File(fileName);
            if( fileToReturn == null ) {
                String cause = "new File(" + fileName + ") == null";
                throw new FileOpenException(FilesHandler.class.getName(), functionName, cause);
            }
        }
        catch( FileOpenException | NullPointerException e ) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesHandler.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
        }
        return fileToReturn;
    }

    public static File openExistingFile(String fileName) {
        final String functionName = "openExistingFile(String fileName)";
        File fileToReturn = tryNewFile( fileName );
        if( fileToReturn != null ) {
            try {
                if( !fileToReturn.exists() ) {
                    String cause = "fileToReturn.exists() == false";
                    throw new FileOpenException(FilesHandler.class.getName(), functionName, cause);
                }
                return fileToReturn;
            }
            catch( FileOpenException e ) {
                String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesHandler.class.getName(), functionName, e);
                System.out.println(exceptionMessage);
                return null;
            }
        }
        else {
            return null;
        }
    }

    public static File openExistingFile(String path, String fileName) {
        String completeFilePath = path + "/" + fileName;
        return openExistingFile( completeFilePath );
    }
}
