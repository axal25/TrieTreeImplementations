package agh.jo.knuth.patricia.file.ops;

import agh.jo.utils.file.RandomAccessReadContainer;
import java.io.EOFException;

public class WordStartPositionToEOFStrategy extends FileOpsStrategy {
    protected WordStartPositionToEOFStrategy(FileOps owner, String filePath, String fileName, char charEOF, char charEOK) {
        super(owner, filePath, fileName, charEOF, charEOK);
    }

    public String getWordStringFromFileStartingAtPosition(int newKeyStartIndex) throws EOFException {
        final String functionName = "String getWordStringFromFileStartingAtPosition(int newKeyStartIndex)";
        boolean isEOFEncountered = false;
        String singleCharacter = null;
        StringBuilder wholeKeyString = new StringBuilder();
        RandomAccessReadContainer randomAccessReadContainer = new RandomAccessReadContainer(this.getFilePath(), this.getFileName());
        while(!isEOFEncountered) {
            if(singleCharacter == null) singleCharacter = readOneCharAtPositionToString(randomAccessReadContainer, newKeyStartIndex);
            else singleCharacter = readNextCharToString(randomAccessReadContainer);
            if(singleCharacter == null) throw new EOFException(
                    "Encountered EOF Exception at position: " + randomAccessReadContainer.getCurrentPosition() + ". " +
                            "There must be EOF character before real End Of File."
            );
            if(singleCharacter.charAt(0) == this.getCharEOF()) isEOFEncountered = true;
            wholeKeyString.append(singleCharacter);
        }
        randomAccessReadContainer.close();
        return wholeKeyString.toString();
    }

    public String getNumberOfCharsBasedOnNumberOfBitsFromFileAtPosition(int requestedAmountOfBits, int position) throws Exception {
        final String functionName = "String getNumberOfCharsFromFileAtPosition(int requestedAmountOfChars, int position)";
        StringBuilder numberOfCharsFromFileAtPosition = new StringBuilder();
        boolean isExceptionEOFEncountered = false;
        boolean isCharEOFEncountered = false;
        int amountOfBitsRead = 0;
        String singleCharacter = null;
        RandomAccessReadContainer randomAccessReadContainer = new RandomAccessReadContainer(this.getFilePath(), this.getFileName());
        while(!isExceptionEOFEncountered && !isCharEOFEncountered && amountOfBitsRead < requestedAmountOfBits) {
            if(singleCharacter == null) singleCharacter = readOneCharAtPositionToString(randomAccessReadContainer, position);
            else singleCharacter = readNextCharToString(randomAccessReadContainer);
            if(singleCharacter == null) isExceptionEOFEncountered = true;
            if(singleCharacter.charAt(0) == this.getCharEOF()) isCharEOFEncountered = true;
            numberOfCharsFromFileAtPosition.append(singleCharacter);
            amountOfBitsRead = amountOfBitsRead + this.getOwner().getOwner().getMixMachine().getBinaryString(singleCharacter).length();
        }
        randomAccessReadContainer.close();
        return numberOfCharsFromFileAtPosition.toString();
    }
}
