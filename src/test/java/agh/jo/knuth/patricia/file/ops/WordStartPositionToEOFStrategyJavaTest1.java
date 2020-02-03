package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WordStartPositionToEOFStrategyJavaTest1 extends WordStartPositionToEOFStrategyJavaAbstractTest {
    // THIS IS THE HOUSE THAT JACK BUILT;
    public final static WordStrategy wordStrategy = WordStartPositionToEOFStrategyMixTest1.wordStrategy;
    public final static Encoding encoding = WordStartPositionToEOFStrategyMixTest1.encoding;
    public final static String filePath = WordStartPositionToEOFStrategyMixTest1.filePath;
    public final static String fileName = WordStartPositionToEOFStrategyMixTest1.fileName;
    public final static String[] expectedCharStringArray = WordStartPositionToEOFStrategyMixTest1.expectedCharStringArray;
    public final static int[] charPositionIndexArray = WordStartPositionToEOFStrategyMixTest1.charPositionIndexArray;
    public final static char charEOF = WordStartPositionToEOFStrategyMixTest1.charEOF;
    public final static char charEOK = WordStartPositionToEOFStrategyMixTest1.charEOK;

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
