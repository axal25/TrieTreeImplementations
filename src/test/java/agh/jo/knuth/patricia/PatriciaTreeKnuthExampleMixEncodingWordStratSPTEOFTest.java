package agh.jo.knuth.patricia;

import agh.jo.knuth.patricia.file.ops.FileOpsStrategyTest;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest extends PatriciaTreeWordStratSPTEOFTest {
    public final static String filePath = "/src/main/resources/knuth/patricia";
    public final static String fileName = "KnuthsPatriciaExample.txt";
    public final static Encoding encoding = Encoding.MIX;
    public final static WordStrategy wordStrategy = PatriciaTreeWordStratSPTEOFTest.wordStrategy;
    public final static String[] fileStringArray = {"THIS ", "IS ", "THE ", "HOUSE ", "THAT ", "JACK ", "BUILT;"};

    public final static int[] charCodeArray = {
            23, 8, 9, 22, 0, 9, 22, 0, 23, 8,
            5, 0, 8, 16, 24, 22, 5, 0, 23, 8,
            1, 23, 0, 11, 1, 3, 12, 0, 2, 24,
            9, 13, 23, 20
    };

    public final static String[] binaryStringArray = {
            "10111", "01000", "01001", "10110", "00000",
            "01001", "10110", "00000",
            "10111", "01000", "00101", "00000",
            "01000", "10000", "11000", "10110", "00101", "00000",
            "10111", "01000", "00001", "10111", "00000",
            "01011", "00001", "00011", "01100", "00000",
            "00010", "11000", "01001", "01101", "10111", "10100"
    };
    public final static int[] startPositionIndexArray = {0, 5, 8, 12, 18, 23, 28};
    public final static int[] endPositionIndexArray = {4, 7, 11, 17, 22, 27, 33};
    public final static int expectedAmountOfBits = 5;

    public final static String[] headerRepresentations = {
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink.id = 0,\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 5,\n" +
                    "\t\tskip = 0,\n" +
                    "\t\tleftLink.id = 1,\n" +
                    "\t\trightLink.id = 0\n" +
                    "\t},\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 5,\n" +
                    "\t\tskip = 0,\n" +
                    "\t\tleftLink.id = 1,\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 8,\n" +
                    "\t\t\tskip = 11,\n" +
                    "\t\t\tleftLink.id = 2,\n" +
                    "\t\t\trightLink.id = 0\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 5,\n" +
                    "\t\tskip = 0,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 3,\n" +
                    "\t\t\tkey = 12,\n" +
                    "\t\t\tskip = 4,\n" +
                    "\t\t\tleftLink.id = 3,\n" +
                    "\t\t\trightLink.id = 1\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 8,\n" +
                    "\t\t\tskip = 11,\n" +
                    "\t\t\tleftLink.id = 2,\n" +
                    "\t\t\trightLink.id = 0\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 5,\n" +
                    "\t\tskip = 0,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 3,\n" +
                    "\t\t\tkey = 12,\n" +
                    "\t\t\tskip = 4,\n" +
                    "\t\t\tleftLink.id = 3,\n" +
                    "\t\t\trightLink.id = 1\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 8,\n" +
                    "\t\t\tskip = 11,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 4,\n" +
                    "\t\t\t\tkey = 18,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tleftLink.id = 4,\n" +
                    "\t\t\t\trightLink.id = 2\n" +
                    "\t\t\t},\n" +
                    "\t\t\trightLink.id = 0\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 5,\n" +
                    "\t\tskip = 0,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 5,\n" +
                    "\t\t\tkey = 23,\n" +
                    "\t\t\tskip = 3,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 3,\n" +
                    "\t\t\t\tkey = 12,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tleftLink.id = 3,\n" +
                    "\t\t\t\trightLink.id = 1\n" +
                    "\t\t\t},\n" +
                    "\t\t\trightLink.id = 5\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 8,\n" +
                    "\t\t\tskip = 11,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 4,\n" +
                    "\t\t\t\tkey = 18,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tleftLink.id = 4,\n" +
                    "\t\t\t\trightLink.id = 2\n" +
                    "\t\t\t},\n" +
                    "\t\t\trightLink.id = 0\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 5,\n" +
                    "\t\tskip = 0,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 6,\n" +
                    "\t\t\tkey = 28,\n" +
                    "\t\t\tskip = 1,\n" +
                    "\t\t\tleftLink.id = 6,\n" +
                    "\t\t\trightLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 5,\n" +
                    "\t\t\t\tkey = 23,\n" +
                    "\t\t\t\tskip = 2,\n" +
                    "\t\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\t\tid = 3,\n" +
                    "\t\t\t\t\tkey = 12,\n" +
                    "\t\t\t\t\tskip = 1,\n" +
                    "\t\t\t\t\tleftLink.id = 3,\n" +
                    "\t\t\t\t\trightLink.id = 1\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\trightLink.id = 5\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 8,\n" +
                    "\t\t\tskip = 11,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 4,\n" +
                    "\t\t\t\tkey = 18,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tleftLink.id = 4,\n" +
                    "\t\t\t\trightLink.id = 2\n" +
                    "\t\t\t},\n" +
                    "\t\t\trightLink.id = 0\n" +
                    "\t\t}\n" +
                    "\t},\n" +
                    "\trightLink = null\n" +
                    "}"
    };

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
