package agh.jo.utils.file;

import java.io.*;
import agh.jo.utils.file.exceptions.*;

public class FilesOps {

    public static File tryNewFile(String fileName) {
        final String functionName = "tryNewFile(String fileName)";
        File fileToReturn = null;
        try {
            if( fileName == null || fileName.isEmpty() ) {
                String cause = "fileName = \"" + fileName + "\"";
                throw new FileOpenException(FilesOps.class.getName(), functionName, cause);
            }
            fileToReturn = new File(fileName);
            if( fileToReturn == null ) {
                String cause = "new File(" + fileName + ") == null";
                throw new FileOpenException(FilesOps.class.getName(), functionName, cause);
            }
        }
        catch( FileOpenException | NullPointerException e ) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
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
                    throw new FileOpenException(FilesOps.class.getName(), functionName, cause);
                }
                return fileToReturn;
            }
            catch( FileOpenException e ) {
                String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
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

    public static File openNewFile(String fileName) {
        final String functionName = "openNewFile(String fileName)";
        File fileToReturn = tryNewFile( fileName );
        if( fileToReturn != null ) {
            try {
                if( getFilePath(fileName) == null ) {
                    String cause = "parameter String fileName = " + fileName + "\n\r" + "\t\t\t\t\tIts parent directory doesn't exist";
                    throw new FileOpenException(FilesOps.class.getName(), functionName, cause);
                }
                if( fileToReturn.exists() ) {
                    String cause = "fileToReturn.exists() == true";
                    throw new FileOpenException(FilesOps.class.getName(), functionName, cause);
                }
                return fileToReturn;
            }
            catch( FileOpenException e ) {
                String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
                System.out.println(exceptionMessage);
                return null;
            }
        }
        else {
            return null;
        }
    }

    public static File openNewFile(String path, String fileName) {
        String completeFilePath = path + "/" + fileName;
        return openNewFile( completeFilePath );
    }

    public static FileReader openFileReader(File file) {
        final String functionName = "openFileReader(File file)";
        FileReader fileReaderToReturn = null;
        try {
            fileReaderToReturn = new FileReader( file );
            if( fileReaderToReturn == null ) {
                String cause = "fileReaderToReturn == null";
                throw new FileReaderOpenException(FilesOps.class.getName(), functionName, cause);
            }
            return fileReaderToReturn;
        } catch (FileNotFoundException | FileReaderOpenException | NullPointerException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static FileReader closeFileReader(FileReader fileReader) {
        final String functionName = "closeFileReader(FileReader fileReader)";
        try {
            if( fileReader == null ) {
                throw new FileReaderCloseException(FilesOps.class.getName(), functionName, "function parameter FileReader fileReader == null");
            }
            fileReader.close();
            return fileReader;
        } catch (IOException | FileReaderCloseException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static BufferedReader openBufferedReader(FileReader fileReader) {
        final String functionName = "openBufferedReader(File file)";
        if( fileReader == null ) {
            try {
                throw new BufferedReaderOpenException(FilesOps.class.getName(), functionName, "function parameter FileReader fileReader == null");
            } catch (BufferedReaderOpenException e) {
                String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
                System.out.println(exceptionMessage);
                return null;
            }
        }
        else {
            return new BufferedReader( fileReader );
        }
    }

    public static BufferedReader closeBufferedReader(BufferedReader bufferedReader) {
        final String functionName = "closeBufferedReader(BufferedReader bufferedReader)";
        try {
            if( bufferedReader == null ) {
                throw new BufferedReaderCloseException(FilesOps.class.getName(), functionName, "function parameter BufferedReader bufferedReader == null");
            }
            bufferedReader.close();
            return bufferedReader;
        } catch (BufferedReaderCloseException | IOException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static double getFileSizeBytes(File file) {
        final String functionName = "getFileSize(File file)";
        try {
            if (file != null) {
                if (file.exists() && file.isFile()) {
                    return new Long( file.length() ).doubleValue();
                } else {
                    throw new GetFileSizeException(FilesOps.class.getName(), functionName, "function parameter File file.exists() == false");
                }
            } else {
                throw new GetFileSizeException(FilesOps.class.getName(), functionName, "function parameter File file == null");
            }
        } catch(GetFileSizeException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return -1;
        }
    }

    public static double getFileSizeBytes(String fileName) {
        return getFileSizeBytes(   openExistingFile( fileName )   );
    }

    public static double getStringSizeBytes(String string) {
        if( string == null ) return -1;
        return string.getBytes().length;
    }

    public static double sizeBytes2KiloB(double fileSizeInBytes) {
        if( fileSizeInBytes == -1 ) return -1;
        return fileSizeInBytes / (1024);
    }

    public static double sizeBytes2MegaB(double fileSizeInBytes) {
        if( fileSizeInBytes == -1 ) return -1;
        return sizeBytes2KiloB( fileSizeInBytes ) / (1024);
    }

    public static double sizeBytes2GigaB(double fileSizeInBytes) {
        if( fileSizeInBytes == -1 ) return -1;
        return sizeBytes2MegaB( fileSizeInBytes ) / (1024);
    }

    public static boolean deleteFile(String fileName) {
        File fileToDelete = openExistingFile( fileName );
        if( fileToDelete == null ) {
            return false;
        }
        else {
            fileToDelete.delete();
            return true;
        }
    }

    public static String getFilePath(String fullFilePath) {
        final String functionName = "getFilePath(String fullFilePath)";
        int indexOfLastSlash = fullFilePath.lastIndexOf('/');
        if( indexOfLastSlash == -1 ) {
            return "";
        }
        String filePath = fullFilePath.substring(0, indexOfLastSlash);
        try {
            if( isDirectory( filePath ) ) {
                return filePath;
            }
            else {
                throw new FileOpenException(
                        FilesOps.class.getName(),
                        functionName,
                        "function parameter fullFilePath = " + fullFilePath + "\n\r" +
                                "\t\t\t\t\tIts parent directory doesn't exist"
                );
            }
        }
        catch( FileOpenException e ) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static boolean isDirectory(String fullFilePath) {
        File directory = new File( fullFilePath );
        if( directory.exists() && directory.isDirectory() ) {
            return true;
        }
        else {
            return false;
        }
    }

    public static FileWriter openFileWriter(File file, boolean doAppend) {
        final String functionName = "openFileWriter(File file, boolean doAppend)";
        FileWriter fileWriterToReturn = null;
        try {
            fileWriterToReturn = new FileWriter( file, doAppend );
            if( fileWriterToReturn == null ) {
                String cause = "fileWriterToReturn == null";
                throw new FileWriterOpenException(FilesOps.class.getName(), functionName, cause);
            }
            return fileWriterToReturn;
        } catch (IOException | FileWriterOpenException | NullPointerException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static FileWriter closeFileWriter(FileWriter fileWriter) {
        final String functionName = "closeFileWriter(FileWriter fileWriter)";
        try {
            if( fileWriter == null ) {
                throw new FileWriterCloseException(FilesOps.class.getName(), functionName, "function parameter FileWriter fileWriter == null");
            }
            fileWriter.close();
            return fileWriter;
        } catch (IOException | FileWriterCloseException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static BufferedWriter openBufferedWriter(FileWriter fileWriter) {
        final String functionName = "openBufferedWriter(FileWriter fileWriter)";
        if( fileWriter == null ) {
            try {
                throw new BufferedWriterOpenException(FilesOps.class.getName(), functionName, "function parameter FileWriter fileWriter == null");
            } catch (BufferedWriterOpenException e) {
                String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
                System.out.println(exceptionMessage);
                return null;
            }
        }
        else {
            return new BufferedWriter( fileWriter );
        }
    }

    public static BufferedWriter closeBufferedWriter(BufferedWriter bufferedWriter) {
        final String functionName = "closeBufferedWriter(BufferedWriter bufferedWriter)";
        try {
            if( bufferedWriter == null ) {
                throw new BufferedWriterCloseException(FilesOps.class.getName(), functionName, "function parameter BufferedWriter bufferedWriter == null");
            }
            bufferedWriter.close();
            return bufferedWriter;
        } catch (BufferedWriterCloseException | IOException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static File renameFile( File currentFile, File newFile ) {
        final String functionName = "renameFile( File currentFile, File newFile )";
        try {
            if( openExistingFile( currentFile.getAbsolutePath() ) == null )
                throw new RenameFileException(FilesOps.class.getName(), functionName, "parameter File currentFile doesn't exist");
            if( openNewFile( newFile.getAbsolutePath()) == null )
                throw new RenameFileException(FilesOps.class.getName(), functionName, "parameter File newFile already exists");
            boolean didRenameSucceed = currentFile.renameTo( newFile );
            if( didRenameSucceed ) {
                return newFile;
            }
            else {
                throw new RenameFileException(FilesOps.class.getName(), functionName, "returned value by \"currentFile.renameTo( newFile )\" == FALSE");
            }
        }
        catch( RenameFileException e ) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(FilesOps.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
    }

    public static String renameFile( String currentFullFilePath, String newFullFilePath ) {
        File currentFile = openExistingFile( currentFullFilePath );
        if( currentFile == null ) return null;
        File newFile = openNewFile( newFullFilePath );
        if( newFile == null ) return null;
        newFile = renameFile( currentFile, newFile );
        if( newFile == null ) {
            return null;
        }
        else {
            return newFile.getAbsolutePath();
        }
    }

    public static File createFile( String newFullFilePath ) {
        File newFile = openNewFile( newFullFilePath );
        FileWriter fileWriter = openFileWriter( newFile, false );
        fileWriter = closeFileWriter( fileWriter );
        return openExistingFile( newFullFilePath );
    }
}
