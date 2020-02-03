package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WordStartPositionToEOFStrategyMixTest1 extends WordStartPositionToEOFStrategyMixAbstractTest {
    // THIS IS THE HOUSE THAT JACK BUILT;
    public final static WordStrategy wordStrategy = WordStartPositionToEOFStrategyAbstractTest.wordStrategy;
    public final static Encoding encoding = WordStartPositionToEOFStrategyMixAbstractTest.encoding;
    public final static String filePath = "/src/main/resources/knuth/patricia";
    public final static String fileName = "KnuthsPatriciaExample1.txt";
    public final static String[] expectedCharStringArray = {
            null, "T", "H", "I", "S", " ",
            "I", "S", " ",
            "T", "H", "E", " ",
            "H", "O", "U", "S", "E", " ",
            "T", "H", "A", "T", " ",
            "J", "A", "C", "K", " ",
            "B", "U", "I", "L", "T", ";", null
    };
    public final static char charEOF = ';';
    public final static char charEOK = ' ';
    public final static int[] charPositionIndexArray = getCharPosition(expectedCharStringArray);

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
