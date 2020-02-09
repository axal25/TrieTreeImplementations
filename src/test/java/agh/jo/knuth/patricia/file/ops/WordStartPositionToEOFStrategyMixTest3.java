package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.mix.machine.MixEncoding;
import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WordStartPositionToEOFStrategyMixTest3 extends WordStartPositionToEOFStrategyMixAbstractTest {
    // THISΦISΦΦTHEΦHOUSEΦTHATΦJACKΦBUILTΦΦΦΦΠ This Is Not A Part Of Any Key Because Of EOF Character (Π)
    public final static WordStrategy wordStrategy = WordStartPositionToEOFStrategyAbstractTest.wordStrategy;
    public final static Encoding encoding = WordStartPositionToEOFStrategyMixAbstractTest.encoding;
    public final static String filePath = "/src/main/resources/knuth/patricia";
    public final static String fileName = "KnuthsPatriciaExample3.txt";
    public final static String[] expectedCharStringArray = {
            null, "T", "H", "I", "S", "Φ",
            "I", "S", "Φ", "Φ",
            "T", "H", "E", "Φ",
            "H", "O", "U", "S", "E", "Φ",
            "T", "H", "A", "T", "Φ",
            "J", "A", "C", "K", "Φ",
            "B", "U", "I", "L", "T", "Φ", "Φ", "Φ", "Φ", "Π",
            // This Is Not A Part Of Any Key Because Of EOF Character (Π)
            " ", "T", "h", "i", "s", " ",
            "I", "s", " ",
            "N", "o", "t", " ",
            "A", " ",
            "P", "a", "r", "t", " ",
            "O", "f", " ",
            "A", "n", "y", " ",
            "K", "e", "y", " ",
            "B", "e", "c", "a", "u", "s", "e", " ",
            "O", "f", " ",
            "E", "O", "F", " ",
            "C", "h", "a", "r", "a", "c", "t", "e", "r", " ",
            "Π", null
    };
    public final static char charEOF = ((char) MixEncoding.GreekCapitalLetter_CharCode_Pi);
    public final static char charEOK = ((char) MixEncoding.GreekCapitalLetter_CharCode_Phi);
    public final static int[] charPositionIndexArray = getCharPosition(expectedCharStringArray);
    public final static int amountOfBits = WordStartPositionToEOFStrategyMixTest1.amountOfBits;

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
