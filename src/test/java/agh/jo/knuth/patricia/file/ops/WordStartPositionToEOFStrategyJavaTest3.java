package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WordStartPositionToEOFStrategyJavaTest3 extends WordStartPositionToEOFStrategyJavaAbstractTest {
    // THISΦISΦΦTHEΦHOUSEΦTHATΦJACKΦBUILTΦΦΦΦΠ This Is Not A Part Of Any Key Because Of EOF Character (Π)
    public final static String filePath = WordStartPositionToEOFStrategyMixTest3.filePath;
    public final static String fileName = WordStartPositionToEOFStrategyMixTest3.fileName;
    public final static WordStrategy wordStrategy = WordStartPositionToEOFStrategyJavaAbstractTest.wordStrategy;
    public final static Encoding encoding = WordStartPositionToEOFStrategyJavaAbstractTest.encoding;
    public final static String[] expectedCharStringArray = WordStartPositionToEOFStrategyMixTest3.expectedCharStringArray;
    public final static int[] charPositionIndexArray = WordStartPositionToEOFStrategyMixTest3.charPositionIndexArray;
    public final static char charEOF = WordStartPositionToEOFStrategyMixTest3.charEOF;
    public final static char charEOK = WordStartPositionToEOFStrategyMixTest3.charEOK;
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
