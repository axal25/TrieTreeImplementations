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
