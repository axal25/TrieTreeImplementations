package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class WordSingleStrategyJavaTest1 extends WordSingleStrategyMixAbstractTest {
    public final static WordStrategy wordStrategy = WordSingleStrategyAbstractTest.wordStrategy;
    public final static Encoding encoding = WordSingleStrategyJavaAbstractTest.encoding;
    public final static String filePath = WordStartPositionToEOFStrategyJavaTest1.filePath;
    public final static String fileName = WordStartPositionToEOFStrategyJavaTest1.fileName;
    public final static String[] expectedCharStringArray = WordStartPositionToEOFStrategyJavaTest1.expectedCharStringArray;
    public final static int[] charPositionIndexArray = WordStartPositionToEOFStrategyJavaTest1.charPositionIndexArray;
    public final static char charEOF = WordStartPositionToEOFStrategyJavaTest1.charEOF;
    public final static char charEOK = WordStartPositionToEOFStrategyJavaTest1.charEOK;

    @Test
    @Order(1)
    @DisplayName("getStringFromFileAtPositionRandomAccess")
    void getStringAtPositionRandomAccessMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        assertGetStringFromFileAtPositionRandomAccess(fileOpsStrategy, expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(2)
    @DisplayName("getCharFromFileAtPosition")
    void getCharAtPositionMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        assertGetCharFromFileAtPosition(fileOpsStrategy, expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(3)
    @DisplayName("isCharExistFromFileAtPosition")
    void isCharExistFromFileAtPositionMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        assertIsCharExistFromFileAtPosition(fileOpsStrategy, charPositionIndexArray);
    }

    @Test
    @Order(4)
    @DisplayName("getNumberOfBitsFromFileAtPosition")
    void getNumberOfBitsFromFileAtPositionMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        MixMachine mixMachine = getMixMachine(encoding, charEOF, charEOK);
        assertGetNumberOfBitsFromFileAtPosition(fileOpsStrategy, mixMachine, expectedCharStringArray);
    }
}
