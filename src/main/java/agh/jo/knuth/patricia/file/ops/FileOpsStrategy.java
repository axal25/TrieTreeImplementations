package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.file.ops.exceptions.NextWordStartIndexNotFoundException;
import agh.jo.utils.file.RandomAccessFileContainer;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;

@Getter
public abstract class FileOpsStrategy {
    private FileOps owner;
    private String filePath;
    @Setter
    private String fileName;
    @Setter
    private char charEOF; // End Of File character
    @Setter
    private char charEOK; // End Of Key character // start of another Key

    private FileOpsStrategy() {}
    protected FileOpsStrategy(FileOps owner, String filePath, String fileName, char charEOF, char charEOK) {
        this.owner = owner;
        setFilePath(filePath);
        setFileName(fileName);
        setCharEOF(charEOF);
        setCharEOK(charEOK);
    }

    private void setFilePath(String filePath) {
        this.filePath = System.getProperty("user.dir") + filePath;
    }

    public abstract String getWordStringFromFileStartingAtPosition(int newKeyStartIndex) throws Exception;

    public abstract String getNumberOfCharsBasedOnNumberOfBitsFromFileAtPosition(int requestedAmountOfBits, int position) throws Exception;

    public int findNextWordStartIndex(int latestInsertedNodeKeyPosition) throws NextWordStartIndexNotFoundException {
        final String functionName = "String findNewKeyStartIndex(int latestInsertedNodeKeyPosition)";
        boolean isEOKEncountered = false;
        String singleCharacter = null;
        int newKeyStartIndex = latestInsertedNodeKeyPosition;
        RandomAccessFileContainer randomAccessFileContainer = new RandomAccessFileContainer(this.filePath, this.fileName);
        while(true) {
            if(singleCharacter == null) singleCharacter = readOneCharAtPositionToString(randomAccessFileContainer, latestInsertedNodeKeyPosition);
            else singleCharacter = readNextCharToString(randomAccessFileContainer);
            if(singleCharacter == null) throw new NextWordStartIndexNotFoundException(
                    this.getClass().getName(),
                    functionName,
                    "Encountered EOF Exception at position: " + newKeyStartIndex + ". " +
                            "There are no more keys after this point."
            );
            if(singleCharacter.charAt(0) == this.getCharEOF()) throw new NextWordStartIndexNotFoundException(
                    this.getClass().getName(),
                    functionName,
                    "Encountered EOF character ('" + this.getCharEOF() + "') at position: " + newKeyStartIndex + ". " +
                    "There are no more keys after this point."
            );
            if(singleCharacter.charAt(0) == this.getCharEOK()) isEOKEncountered = true;
            if(isEOKEncountered && !isSingleCharacterEOKorEOF(singleCharacter)) break;
            newKeyStartIndex = newKeyStartIndex + singleCharacter.getBytes(Charset.forName("UTF-8")).length;
        }
        randomAccessFileContainer.close();
        return newKeyStartIndex;
    }

    public boolean isSingleCharacterEOKorEOF(String singleCharacter) {
        return singleCharacter.charAt(0) == this.charEOK || singleCharacter.charAt(0) == this.charEOF;
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
        RandomAccessFileContainer randomAccessFileContainer = new RandomAccessFileContainer(this.filePath, this.fileName);
        singleCharacter = readOneCharAtPositionToString(randomAccessFileContainer, position);
        randomAccessFileContainer.close();
        return singleCharacter;
    }

    protected String readOneCharAtPositionToString(RandomAccessFileContainer randomAccessFileContainer, int position) {
        randomAccessFileContainer.seek(position);
        return randomAccessFileContainer.getNextCharToString();
    }

    protected String readNextCharToString(RandomAccessFileContainer randomAccessFileContainer) {
        return randomAccessFileContainer.getNextCharToString();
    }

    @Override
    public String toString() {
        return "FileOpsStrategy{\n" +
                "\t\t\tfilePath='" + filePath + "" +
                ",\n\t\t\tfileName='" + fileName + "'" +
                ",\n\t\t\tcharEOF='" + charEOF + "'" +
                ",\n\t\t\tcharEOK='" + charEOK + "'" +
                "\n\t\t}";
    }
}
