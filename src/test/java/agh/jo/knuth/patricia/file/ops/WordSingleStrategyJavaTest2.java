package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WordSingleStrategyJavaTest2 extends WordSingleStrategyMixAbstractTest {
    public final static WordStrategy wordStrategy = WordSingleStrategyAbstractTest.wordStrategy;
    public final static Encoding encoding = WordSingleStrategyJavaAbstractTest.encoding;
    public final static String filePath = WordStartPositionToEOFStrategyJavaTest2.filePath;
    public final static String fileName = WordStartPositionToEOFStrategyJavaTest2.fileName;
    public final static String[] expectedCharStringArray = WordStartPositionToEOFStrategyJavaTest2.expectedCharStringArray;
    public final static int[] charPositionIndexArray = WordStartPositionToEOFStrategyJavaTest2.charPositionIndexArray;
    public final static char charEOF = WordStartPositionToEOFStrategyJavaTest2.charEOF;
    public final static char charEOK = WordStartPositionToEOFStrategyJavaTest2.charEOK;
    public final static int amountOfBits = MixMachine.JAVA_DEFAULT_AMOUNT_OF_BITS;

    @Test
    @Order(1)
    @DisplayName("getStringFromFileAtPositionRandomAccess")
    void getStringAtPositionRandomAccessMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getNewFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, amountOfBits);
        assertGetStringFromFileAtPositionRandomAccess(fileOpsStrategy, expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(2)
    @DisplayName("getCharFromFileAtPosition")
    void getCharAtPositionMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getNewFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, amountOfBits);
        assertGetCharFromFileAtPosition(fileOpsStrategy, expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(3)
    @DisplayName("isCharExistFromFileAtPosition")
    void isCharExistFromFileAtPositionMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getNewFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, amountOfBits);
        assertIsCharExistFromFileAtPosition(fileOpsStrategy, charPositionIndexArray);
    }

    @Test
    @Order(4)
    @DisplayName("getNumberOfBitsFromFileAtPosition")
    void getNumberOfBitsFromFileAtPositionMIX() throws Exception {
        FileOpsStrategy fileOpsStrategy = getNewFileOpsStrategy(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, amountOfBits);
        MixMachine mixMachine = extractMixMachine(fileOpsStrategy);
        assertGetNumberOfBitsFromFileAtPosition(fileOpsStrategy, mixMachine, expectedCharStringArray);
    }
}
