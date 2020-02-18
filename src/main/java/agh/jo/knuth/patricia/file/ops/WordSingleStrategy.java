package agh.jo.knuth.patricia.file.ops;

import agh.jo.utils.file.RandomAccessFileContainer;

public class WordSingleStrategy extends FileOpsStrategy {
    protected WordSingleStrategy(FileOps owner, String filePath, String fileName, char charEOF, char charEOK) {
        super(owner, filePath, fileName, charEOF, charEOK);
    }

    public String getWordStringFromFileStartingAtPosition(int newKeyStartIndex) throws Exception {
        final String functionName = "String getWordStringFromFileStartingAtPosition(int newKeyStartIndex)";
        boolean isEOKEncountered = false;
        boolean isEOFEncountered = false;
        String singleCharacter = null;
        StringBuilder wholeKeyString = new StringBuilder();
        RandomAccessFileContainer randomAccessFileContainer = new RandomAccessFileContainer(this.getFilePath(), this.getFileName());
        while(!isEOFEncountered) {
            if(singleCharacter == null) singleCharacter = readOneCharAtPositionToString(randomAccessFileContainer, newKeyStartIndex);
            else singleCharacter = readNextCharToString(randomAccessFileContainer);
            if(singleCharacter == null) break;
            if(isEOKEncountered && !isEOFEncountered && !isSingleCharacterEOKorEOF(singleCharacter)) break;
            if(singleCharacter.charAt(0) == this.getCharEOF()) isEOFEncountered = true;
            if(singleCharacter.charAt(0) == this.getCharEOK()) isEOKEncountered = true;
            wholeKeyString.append(singleCharacter);
        }
        randomAccessFileContainer.close();
        return wholeKeyString.toString();
    }

    public String getNumberOfCharsBasedOnNumberOfBitsFromFileAtPosition(int requestedAmountOfBits, int position) throws Exception {
        final String functionName = "String getNumberOfCharsFromFileAtPosition(int requestedAmountOfChars, int position)";
        StringBuilder numberOfCharsFromFileAtPosition = new StringBuilder();
        boolean isExceptionEOFEncountered = false;
        boolean isCharEOFEncountered = false;
        boolean isCharEOKEncountered = false;
        int amountOfBitsRead = 0;
        String singleCharacter = null;
        RandomAccessFileContainer randomAccessFileContainer = new RandomAccessFileContainer(this.getFilePath(), this.getFileName());
        while(!isExceptionEOFEncountered && !isCharEOFEncountered && amountOfBitsRead < requestedAmountOfBits) {
            if(singleCharacter == null) singleCharacter = readOneCharAtPositionToString(randomAccessFileContainer, position);
            else singleCharacter = readNextCharToString(randomAccessFileContainer);

            if(singleCharacter == null) isExceptionEOFEncountered = true;
            if(isCharEOKEncountered && singleCharacter.charAt(0) != this.getCharEOF() && singleCharacter.charAt(0) != this.getCharEOK())  break;

            numberOfCharsFromFileAtPosition.append(singleCharacter);
            amountOfBitsRead = amountOfBitsRead + this.getOwner().getOwner().getMixMachine().getBinaryString(singleCharacter).length();
            if(singleCharacter.charAt(0) == this.getCharEOF()) isCharEOFEncountered = true;
            if(singleCharacter.charAt(0) == this.getCharEOK()) isCharEOKEncountered = true;
        }
        randomAccessFileContainer.close();
        return numberOfCharsFromFileAtPosition.toString();
    }
}
