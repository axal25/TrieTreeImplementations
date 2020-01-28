package agh.jo.knuth.patricia;

import agh.jo.knuth.patricia.file.ops.FileOpsStrategyTest;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatriciaTreeKnuthExampleJavaEncodingWordStratSingleTest extends PatriciaTreeWordStratSingleTest {
    public final static String filePath = "/src/main/resources/knuth/patricia";
    public final static String fileName = "KnuthsPatriciaExample.txt";
    public final static Encoding encoding = Encoding.JAVA;
    public final static WordStrategy wordStrategy = PatriciaTreeWordStratSingleTest.wordStrategy;
    public final static String[] fileStringArray = {"THIS ", "IS ", "THE ", "HOUSE ", "THAT ", "JACK ", "BUILT;"};

    public final static int[] charCodeArray = {
            //  T,  H,  I,  S, ' ',
            84, 72, 73, 83, 32,
            //  I,  S, ' ',
            73, 83, 32,
            //  T,  H,  E,  ' '
            84, 72, 69, 32,
            //  H,  O,  U,  S,  E, ' ',
            72, 79, 85, 83, 69, 32,
            //  T,  H,  A,  T, ' ',
            84, 72, 65, 84, 32,
            //  J,  A,  C,  K, ' ',
            74, 65, 67, 75, 32,
            //  B,  U,  I,  L,  T,  ';'
            66, 85, 73, 76, 84, 59
    };

    public final static String[] binaryStringArray = {
            //  T,          H,          I,          S,          ' ',
            //  84,         72,         73,         83,         32,
            "01010100", "01001000", "01001001", "01010011", "00100000",

            //  I,          S,          ' ',
            //  73,         83,         32,
            "01001001", "01010011", "00100000",

            //  T,          H,          E,          ' ',
            //  84,         72,         69,         32,
            "01010100", "01001000", "01000101", "00100000",

            //  H,          O,          U,          S,          E,          ' ',
            //  72,         79,         85,         83,         69,         32,
            "01001000", "01001111", "01010101", "01010011", "01000101", "00100000",

            //  T,          H,          A,          T,          ' ',
            //  84,         72,         65,         84,         32,
            "01010100", "01001000", "01000001", "01010100", "00100000",

            //  J,          A,          C,          K,          ' ',
            //  74,         65,         67,         75,         32,
            "01001010", "01000001", "01000011", "01001011", "00100000",

            //  B,          U,          I,          L,          T,          ';',
            //  66,         85,         73,         76,         84,         59,
            "01000010", "01010101", "01001001", "01001100", "01010100", "00111011",
    };
    public final static int[] startPositionIndexArray = {0, 5, 8, 12, 18, 23, 28};
    public final static int[] endPositionIndexArray = {4, 7, 11, 17, 22, 27, 33};
    public final static int expectedAmountOfBits = 8;
    public final static String[] headerRepresentations = PatriciaTreeKnuthExampleJavaEncodingWordStratSPTEOFTest.headerRepresentations;

    @Test
    @Order(1)
    @DisplayName("PatriciaTree constructor - only header node")
    void PatriciaTree() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
    }

    @Test
    @Order(2)
    @DisplayName("getStringFromFileAtPositionRandomAccess")
    void getStringAtPositionRandomAccess() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        FileOpsStrategyTest.assertGetStringFromFileAtPositionRandomAccess(patriciaTree.getFileOps().getFileOpsStrategy());
    }

    @Test
    @Order(3)
    @DisplayName("getCharFromFileAtPosition")
    void getCharAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        FileOpsStrategyTest.assertGetCharFromFileAtPosition(patriciaTree.getFileOps().getFileOpsStrategy());
    }

    @Test
    @Order(4)
    @DisplayName("isCharExistFromFileAtPosition")
    void isCharExistFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        FileOpsStrategyTest.assertIsCharExistFromFileAtPosition(patriciaTree.getFileOps().getFileOpsStrategy());
    }

    @Test
    @Order(5)
    @DisplayName("getCharCode(char character)")
    void getCharCode() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        assertGetCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1, false);
    }

    @Test
    @Order(6)
    @DisplayName("getCharBytes(int characterCode)")
    void getCharBytes() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        assertGetCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1, true);
    }

    @Test
    @Order(7)
    @DisplayName("getBinaryString(int characterCode)")
    void getBinaryString() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(binaryStringArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1);
    }

    @Test
    @Order(8)
    @DisplayName("getBinaryString(String charChain)")
    void getBinaryStringOfCharacterChain() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        assertConcatGetByteString(patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), binaryStringArray, startPositionIndexArray, endPositionIndexArray);
    }

    @Test
    @Order(9)
    @DisplayName("getNumberOfBitsFromFileAtPosition(int amountOfBit, int position)")
    void getNumberOfBitsFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        assertGetNumberOfBitsFromFileAtPosition(patriciaTree, binaryStringArray, startPositionIndexArray, endPositionIndexArray, expectedAmountOfBits);
    }

    @Test
    @Order(10)
    @DisplayName("isContaining")
    void isContaining() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        assertIsContainingCharOneByOne(patriciaTree, charCodeArray.length);
    }

    @Test
    @Order(11)
    @DisplayName("findNextWordStartIndex")
    void findNewKeyStringFromFile() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        assertFindNextWordStartIndexAndGetWordStringFromFileStartingAtPosition(patriciaTree.getFileOps().getFileOpsStrategy(), startPositionIndexArray, fileStringArray);
    }

    // Needed for description of first (after initiation)
    @Test
    @Order(12)
    @DisplayName("Constructor >> insert - 2 nodes - is Containing")
    void PatriciaTree2ndNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, 2, wordStrategy);
    }

    @Test
    @Order(13)
    @DisplayName("Constructor >> insert - 3 nodes - is Containing")
    void PatriciaTree3rdNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, 3, wordStrategy);
    }

    @Test
    @Order(14)
    @DisplayName("Constructor >> insert - 4 nodes - is Containing")
    void PatriciaTree4thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, 4, wordStrategy);
    }

    @Test
    @Order(15)
    @DisplayName("Constructor >> insert - 5 nodes - is Containing")
    void PatriciaTree5thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, 5, wordStrategy);
    }

    @Test
    @Order(16)
    @DisplayName("Constructor >> insert - 6 nodes - is Containing")
    void PatriciaTree6thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, 6, wordStrategy);
    }

    @Test
    @Order(17)
    @DisplayName("Constructor >> insert - 7 nodes - is Containing")
    void PatriciaTree7thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, 7, wordStrategy);
    }

    @Test
    @Order(18)
    @DisplayName("Constructor >> insert - 7 nodes - find Nodes Matching")
    void PatriciaTree7thNodeSearch() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, 7, wordStrategy);
        assertFindNodesMatching(patriciaTree, fileStringArray);
        String searchWord = "TH";
        int[] expectedNodeIds = {0, 2, 4};
        assertFindMultipleNodesMatching(patriciaTree, searchWord, expectedNodeIds, fileStringArray);
    }

    @Test
    @Order(19)
    @DisplayName("Constructor >> insert - 1 node - to String")
    void PatriciaTree1stNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, encoding, fileStringArray, 1, wordStrategy, headerRepresentations);
    }

    @Test
    @Order(20)
    @DisplayName("Constructor >> insert - 2 nodes - to String")
    void PatriciaTree2ndNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, encoding, fileStringArray, 2, wordStrategy, headerRepresentations);
    }

    @Test
    @Order(21)
    @DisplayName("Constructor >> insert - 3 nodes - to String")
    void PatriciaTree3rdNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, encoding, fileStringArray, 3, wordStrategy, headerRepresentations);
    }

    @Test
    @Order(22)
    @DisplayName("Constructor >> insert - 4 nodes - to String")
    void PatriciaTree4thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, encoding, fileStringArray, 4, wordStrategy, headerRepresentations);
    }

    @Test
    @Order(23)
    @DisplayName("Constructor >> insert - 5 nodes - to String")
    void PatriciaTree5thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, encoding, fileStringArray, 5, wordStrategy, headerRepresentations);
    }

    @Test
    @Order(24)
    @DisplayName("Constructor >> insert - 6 nodes - to String")
    void PatriciaTree6thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, encoding, fileStringArray, 6, wordStrategy, headerRepresentations);
    }

    @Test
    @Order(25)
    @DisplayName("Constructor >> insert - 7 nodes - to String")
    void PatriciaTree7thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, encoding, fileStringArray, 7, wordStrategy, headerRepresentations);
    }
}
