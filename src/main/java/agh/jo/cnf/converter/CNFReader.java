package agh.jo.cnf.converter;

import agh.jo.utils.file.RandomAccessReadContainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CNFReader {
    private String inFilePath;
    private String inFileName;
    private RandomAccessReadContainer randomAccessReadContainer = null;
    public final int LINE_CHAR_LIMIT = 10000;
    public final char INPUT_DELIMITER = CNFConverter.INPUT_DELIMITER;

    protected CNFReader(String inFilePath, String inFileName) {
        setInFilePath(inFilePath);
        this.inFileName = inFileName;
    }

    private void setInFilePath(String inFilePath) {
        this.inFilePath = System.getProperty("user.dir") + inFilePath;
    }

    public String readCNF() throws Exception {
        String line = readLine();
        while(line!=null && !line.isEmpty() && isFirstCharLetter(line)) {
            line = readLine();
        }
        if(isFirstCharLetter(line)) line = "";
        return trimEOL(line);
    }

    protected String trimEOL(String input) {
        if(input!=null && !input.isEmpty()) {
            if(input.charAt(input.length()-1) == '\n' || input.charAt(input.length()-1) == '\r') return input.substring(0, input.length()-1);
            else if(input.length() > 1 && input.substring(input.length()-2).compareTo("\n\r") == 0) return input.substring(0, input.length()-2);
            else return input;
        } else return input;
    }

    protected boolean isStringEndingOnEOL(String input) {
        if(input.length() > 0 && (input.charAt(input.length()-1) == '\n' || input.charAt(input.length()-1) == '\r')) return true;
        else if(input.length() > 1 && input.substring(input.length()-2).compareTo("\n\r") == 0) return true;
        else return false;
    }

    protected boolean isFirstCharLetter(String line) {
        return line.length() > 0 && ((line.charAt(0) > 64 && line.charAt(0) < 91) || (96 < line.charAt(0) && line.charAt(0) < 123));
    }

    public String readLine() throws Exception {
        String currWord = readWord();
        return readLine(currWord, currWord.length());
    }

    private String readLine(String currWord, int charCount) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getEmptyIfNull(currWord));
        while(currWord!=null && !currWord.isEmpty() && !isStringEndingOnEOL(currWord) && charCount < LINE_CHAR_LIMIT) {
            currWord = readWord();
            stringBuilder.append(getEmptyIfNull(currWord));
            charCount = charCount + currWord.length();
        }
        return stringBuilder.toString();
    }

    public String readWord() throws Exception {
        if(randomAccessReadContainer == null) {
            randomAccessReadContainer = new RandomAccessReadContainer(this.inFilePath, this.inFileName);
            randomAccessReadContainer.seek(0);
        }
        int charCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        String currChar = readNextCharToString(randomAccessReadContainer);
        stringBuilder.append(getEmptyIfNull(currChar));
        charCounter = 1;
        while(currChar!=null && !currChar.isEmpty() && currChar.charAt(0) != INPUT_DELIMITER && !isStringEndingOnEOL(currChar) && charCounter < LINE_CHAR_LIMIT) {
            currChar = readNextCharToString(randomAccessReadContainer);
            stringBuilder.append(getEmptyIfNull(currChar));
            charCounter++;
        }
        return stringBuilder.toString();
    }

    private String getEmptyIfNull(String input) {
        if(input == null) return "";
        else return input;
    }

    public void close() {
        randomAccessReadContainer.close();
    }

    protected String readNextCharToString(RandomAccessReadContainer randomAccessReadContainer) throws Exception {
        String charInString = randomAccessReadContainer.getNextCharToString();
        if (charInString != null && charInString.length()!=1) throw new Exception("Character could not be retrieved. String retrieved at that location was not a single character - retrieved string: " + charInString);
        return charInString;
    }
}
