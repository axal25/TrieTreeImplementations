package agh.jo.knuth.patricia.file.ops;

import agh.jo.utils.file.RandomAccessReadContainer;
import java.io.EOFException;
import java.io.RandomAccessFile;

public class SingleWordStrategy extends FileOpsStrategy {
    protected SingleWordStrategy(String filePath, String fileName) {
        super(filePath, fileName);
    }

    public String getWordStringFromFileStartingAtPosition(int newKeyStartIndex) {
        final String functionName = "String findNewKeyStringFromFile(int newKeyStartIndex)";
        StringBuilder wholeKeyString = new StringBuilder();
//        boolean isSpaceCharacterEncountered = false;
        String singleCharacter = null;
        RandomAccessReadContainer randomAccessReadContainer = new RandomAccessReadContainer(this.getFilePath(), this.getFileName());
        RandomAccessFile randomAccessFile = randomAccessReadContainer.getRandomAccessFile();
        try {
            // moves file pointer to position (byte index) specified
            randomAccessFile.seek(randomAccessFile.getFilePointer() + newKeyStartIndex);
            byte[] bytes = new byte[1];
            // read 1 byte (8 bits)
            bytes[0] = randomAccessFile.readByte();
            // create string from 1 byte (8 bits) [UTF-8]
            singleCharacter = new String( bytes, "UTF-8" );
            boolean isSpaceCharacterEncountered = false;
            while(!isSpaceCharacterEncountered || singleCharacter.charAt(0) == ' ') {
//            while(true) {
                wholeKeyString.append(singleCharacter);
                if(singleCharacter.charAt(0) == ' ') isSpaceCharacterEncountered = true;
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
}
