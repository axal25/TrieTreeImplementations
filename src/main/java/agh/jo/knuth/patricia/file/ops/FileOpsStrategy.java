package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.PatriciaTree;
import agh.jo.utils.file.ExceptionMessageGenerator;
import agh.jo.utils.file.RandomAccessReadContainer;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

@Getter
public abstract class FileOpsStrategy {
    private String filePath;
    @Setter
    private String fileName;

    private FileOpsStrategy() {}
    protected FileOpsStrategy(String filePath, String fileName) {
        setFilePath(filePath);
        setFileName(fileName);
    }

    private void setFilePath(String filePath) {
        this.filePath = System.getProperty("user.dir") + filePath;
    }

    public abstract String getWordStringFromFileStartingAtPosition(int newKeyStartIndex);

    public int findNextWordStartIndex(int latestInsertedNodeKeyPosition) {
        final String functionName = "String findNewKeyStartIndex(int latestInsertedNodeKeyPosition)";
        boolean isSpaceCharacterEncountered = false;
        int newKeyStartIndex = latestInsertedNodeKeyPosition;
        String singleCharacter = null;
        RandomAccessReadContainer randomAccessReadContainer = new RandomAccessReadContainer(this.filePath, this.fileName);
        RandomAccessFile randomAccessFile = randomAccessReadContainer.getRandomAccessFile();
        try {
            // moves file pointer to position (byte index) specified
            randomAccessFile.seek(randomAccessFile.getFilePointer() + latestInsertedNodeKeyPosition);
            byte[] bytes = new byte[1];
            // read 1 byte (8 bits)
            bytes[0] = randomAccessFile.readByte();
            // create string from 1 byte (8 bits) [UTF-8]
            singleCharacter = new String( bytes, "UTF-8" );
            while(!isSpaceCharacterEncountered || singleCharacter.charAt(0) == ' ') {
                newKeyStartIndex++;
                if(singleCharacter.charAt(0) == ' ') isSpaceCharacterEncountered = true;
                randomAccessFile.seek(randomAccessFile.getFilePointer());
                bytes[0] = randomAccessFile.readByte();
                singleCharacter = new String( bytes, "UTF-8" );
            }
        } catch (EOFException e) {
            System.out.println("Encountered End Of File (Exception) before 2nd ' ' (space) character. Error: " + e.toString());
        } catch (Exception e) {
            randomAccessReadContainer.close();
            throw e;
        } finally {
            randomAccessReadContainer.close();
            return newKeyStartIndex;
        }
    }

    public String getNumberOfCharsFromFileAtPosition(int requestedAmountOfChars, int position) {
        final String functionName = "String getNumberOfCharsFromFileAtPosition(int requestedAmountOfChars, int position)";
        StringBuilder wholeKeyString = new StringBuilder();
        int amountOfCharsRead = 0;
        String singleCharacter = null;
        RandomAccessReadContainer randomAccessReadContainer = new RandomAccessReadContainer(this.filePath, this.fileName);
        RandomAccessFile randomAccessFile = randomAccessReadContainer.getRandomAccessFile();
        try {
            // moves file pointer to position (byte index) specified
            randomAccessFile.seek(randomAccessFile.getFilePointer() + position);
            byte[] bytes = new byte[1];
            // read 1 byte (8 bits)
            bytes[0] = randomAccessFile.readByte();
            // create string from 1 byte (8 bits) [UTF-8]
            singleCharacter = new String( bytes, "UTF-8" );
            while(requestedAmountOfChars > amountOfCharsRead) {
                wholeKeyString.append(singleCharacter);
                amountOfCharsRead++;
                randomAccessFile.seek(randomAccessFile.getFilePointer());
                bytes[0] = randomAccessFile.readByte();
                singleCharacter = new String( bytes, "UTF-8" );
            }
        } catch (EOFException e) {
            System.out.println("Encountered End Of File (Exception) before 2nd ' ' (space) character.");
        } catch (Exception e) {
            randomAccessReadContainer.close();
            throw e;
        } finally {
            randomAccessReadContainer.close();
            return wholeKeyString.toString();
        }
    }

    public boolean isCharExistFromFileAtPosition(int position) {
        try {
            if(getCharFromFileAtPosition(position) != '\0') return true;
            else return false;
        } catch(Exception e) {
            return false;
        }
    }

    public char getCharFromFileAtPosition(int position) throws Exception {
        String stringAtPosition = getStringFromFileAtPositionRandomAccess(position);
        if (stringAtPosition != null && stringAtPosition.length()==1) return stringAtPosition.charAt(0);
        else throw new Exception("Character could not be retrieved. String retrieved at that location was not a single character - retrieved string: " + stringAtPosition);
    }

    protected String getStringFromFileAtPositionRandomAccess(int position) {
        final String functionName = "String getStringAtRandomPosition(int position)";
        String singleCharacter = null;
        RandomAccessReadContainer randomAccessReadContainer = new RandomAccessReadContainer(this.filePath, this.fileName);
        RandomAccessFile randomAccessFile = randomAccessReadContainer.getRandomAccessFile();

        try {
            // moves file pointer to position (byte index) specified
            randomAccessFile.seek(randomAccessFile.getFilePointer() + position);
            byte[] bytes = new byte[1];
            // read 1 byte (8 bits)
            bytes[0] = randomAccessFile.readByte();
            // create string from 1 byte (8 bits) [UTF-8]
            singleCharacter = new String( bytes, "UTF-8" );
        } catch (IOException e) {
            singleCharacter = null;
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
        }
        finally {
            randomAccessReadContainer.close();
            return singleCharacter;
        }
    }

    @Override
    public String toString() {
        return "FileOperations{\n" +
                "\t\t\tfilePath='" + filePath + '\'' +
                ",\n\t\t\tfileName='" + fileName + '\'' +
                "\n\t\t}";
    }
}
