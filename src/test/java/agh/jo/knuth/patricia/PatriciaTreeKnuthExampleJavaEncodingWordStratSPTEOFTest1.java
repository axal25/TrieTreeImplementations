package agh.jo.knuth.patricia;

import agh.jo.func.intf.FunctionalInterfaceVoidReturn;
import agh.jo.knuth.patricia.file.ops.FileOpsStrategyAbstractTest;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatriciaTreeKnuthExampleJavaEncodingWordStratSPTEOFTest1 extends PatriciaTreeWordStratSPTEOFTest {
    public final static String filePath = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.filePath;
    public final static String fileName = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.fileName;
    public final static char charEOF = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.charEOF;
    public final static char charEOK = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.charEOK;
    public final static Encoding encoding = Encoding.JAVA;
    public final static WordStrategy wordStrategy = PatriciaTreeWordStratSPTEOFTest.wordStrategy;
    public final static String[] fileStringArray = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.fileStringArray;
    public final static String[] expectedCharStringArray = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.expectedCharStringArray;
    public final static int[] charPositionIndexArray = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.charPositionIndexArray;

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
    public final static int[] startPositionIndexArray = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.startPositionIndexArray;
    public final static int[] endPositionIndexArray = PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.endPositionIndexArray;
    public final static int expectedAmountOfBits = MixMachine.JAVA_DEFAULT_AMOUNT_OF_BITS;
    public final static String[] headerRepresentations = {
            PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest1.headerRepresentations[0],
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 5,\n" +
                    "\t\tskip = 3,\n" +
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
                    "\t\tskip = 3,\n" +
                    "\t\tleftLink.id = 1,\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 8,\n" +
                    "\t\t\tskip = 17,\n" +
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
                    "\t\tskip = 3,\n" +
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
                    "\t\t\tskip = 17,\n" +
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
                    "\t\tskip = 3,\n" +
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
                    "\t\t\tskip = 17,\n" +
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
                    "\t\tskip = 3,\n" +
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
                    "\t\t\tskip = 17,\n" +
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
                    "\t\tskip = 3,\n" +
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
                    "\t\t\tskip = 17,\n" +
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
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
    }

    @Test
    @Order(2)
    @DisplayName("getStringFromFileAtPositionRandomAccess")
    void getStringAtPositionRandomAccess() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        FileOpsStrategyAbstractTest.assertGetStringFromFileAtPositionRandomAccess(patriciaTree.getFileOps().getFileOpsStrategy(), expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(3)
    @DisplayName("getCharFromFileAtPosition")
    void getCharAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        FileOpsStrategyAbstractTest.assertGetCharFromFileAtPosition(patriciaTree.getFileOps().getFileOpsStrategy(), expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(4)
    @DisplayName("isCharExistFromFileAtPosition")
    void isCharExistFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        FileOpsStrategyAbstractTest.assertIsCharExistFromFileAtPosition(patriciaTree.getFileOps().getFileOpsStrategy(), charPositionIndexArray);
    }

    @Test
    @Order(5)
    @DisplayName("getCharCode(char character)")
    void getCharCode() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        assertGetCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray, fileStringArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1, false);
    }

    @Test
    @Order(6)
    @DisplayName("getCharBytes(int characterCode)")
    void getCharBytes() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        assertGetCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray, fileStringArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1, true);
    }

    @Test
    @Order(7)
    @DisplayName("getBinaryString(int characterCode)")
    void getBinaryString() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(binaryStringArray, fileStringArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1);
    }

    @Test
    @Order(8)
    @DisplayName("getBinaryString(String charChain)")
    void getBinaryStringOfCharacterChain() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        assertConcatGetByteString(patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), binaryStringArray, fileStringArray, startPositionIndexArray, endPositionIndexArray);
    }

    @Test
    @Order(9)
    @DisplayName("getNumberOfBitsFromFileAtPosition(int amountOfBits, int position)")
    void getNumberOfBitsFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        assertGetNumberOfBitsFromFileAtPosition(patriciaTree, binaryStringArray, startPositionIndexArray, endPositionIndexArray, expectedAmountOfBits, fileStringArray, encoding);
    }

    @Test
    @Order(10)
    @DisplayName("Constructor >> insert - 1 node - is Containing Prefix")
    void isContaining() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        assertIsContainingSinglePrefix(patriciaTree, startPositionIndexArray[0], fileStringArray);
    }

    @Test
    @Order(11)
    @DisplayName("findNextWordStartIndex")
    void findNewKeyStringFromFile() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        assertFindNextWordStartIndexAndGetWordStringFromFileStartingAtPosition(patriciaTree.getFileOps().getFileOpsStrategy(), startPositionIndexArray, fileStringArray);
    }

    // Needed for description of first (after initiation)
    @Test
    @Order(12)
    @DisplayName("Constructor >> insert - 2 nodes - is Containing Prefix")
    void PatriciaTree2ndNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 2);
    }

    @Test
    @Order(13)
    @DisplayName("Constructor >> insert - 3 nodes - is Containing Prefix")
    void PatriciaTree3rdNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 3);
    }

    @Test
    @Order(14)
    @DisplayName("Constructor >> insert - 4 nodes - is Containing Prefix")
    void PatriciaTree4thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 4);
    }

    @Test
    @Order(15)
    @DisplayName("Constructor >> insert - 5 nodes - is Containing Prefix")
    void PatriciaTree5thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 5);
    }

    @Test
    @Order(16)
    @DisplayName("Constructor >> insert - 6 nodes - is Containing Prefix")
    void PatriciaTree6thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 6);
    }

    @Test
    @Order(17)
    @DisplayName("Constructor >> insert - 7 nodes - is Containing Prefix")
    void PatriciaTree7thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 7);
        assertIsContainingAllPrefixes(patriciaTree, startPositionIndexArray, fileStringArray);
    }

    @Test
    @Order(18)
    @DisplayName("Constructor >> insert - 7 nodes - find Nodes Matching Prefix")
    void PatriciaTree7thNodeSearch() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 7);
        assertFindNodesMatchingAllPermutationsOfSinglePrefix(patriciaTree, fileStringArray);
        String searchWord = "TH";
        int[] expectedNodeIds = {0, 2, 4};
        assertFindMultipleNodesMatching(patriciaTree, searchWord, expectedNodeIds, fileStringArray);
    }

    @Test
    @Order(19)
    @DisplayName("Constructor >> insert - 1 node - to String")
    void PatriciaTree1stNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 1, headerRepresentations);
    }

    @Test
    @Order(20)
    @DisplayName("Constructor >> insert - 2 nodes - to String")
    void PatriciaTree2ndNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 2, headerRepresentations);
    }

    @Test
    @Order(21)
    @DisplayName("Constructor >> insert - 3 nodes - to String")
    void PatriciaTree3rdNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 3, headerRepresentations);
    }

    @Test
    @Order(22)
    @DisplayName("Constructor >> insert - 4 nodes - to String")
    void PatriciaTree4thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 4, headerRepresentations);
    }

    @Test
    @Order(23)
    @DisplayName("Constructor >> insert - 5 nodes - to String")
    void PatriciaTree5thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 5, headerRepresentations);
    }

    @Test
    @Order(24)
    @DisplayName("Constructor >> insert - 6 nodes - to String")
    void PatriciaTree6thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 6, headerRepresentations);
    }

    @Test
    @Order(25)
    @DisplayName("Constructor >> insert - 7 nodes - to String")
    void PatriciaTree7thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 7, headerRepresentations);
    }

    @Test
    @Order(26)
    @DisplayName("Constructor >> insert - 7 nodes - findNextWordStartIndex(node7thKeyPosition)")
    void PatriciaTree7thNodeFindNextWordStartIndex() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 7);
        FunctionalInterfaceVoidReturn fi = () -> {
            int node7thKeyPosition = patriciaTree.getKeyInsertLogic().getLatestInsertedNode().getKey();
            patriciaTree.getFileOps().getFileOpsStrategy().findNextWordStartIndex(node7thKeyPosition);
        };
        assertThrows(Exception.class, fi::run);
    }

    @Test
    @Order(27)
    @DisplayName("Constructor >> insert - 7 nodes + non existent insert")
    void PatriciaTree8thNonExistentNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 7);
        FunctionalInterfaceVoidReturn fi = () -> {
            patriciaTree.insertNextKeyIntoTree();
        };
        assertThrows(Exception.class, fi::run);
    }

    @Test
    @Order(28)
    @DisplayName("Constructor >> insert - 7 nodes - getNumberOfBitsFromFileAtPosition(1, node7thKeyPosition)")
    void PatriciaTree7thNodeGetNumberOfBitsFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, 7);
        assertGetNumberOfBitsFromFileAtPositionAfterEOF(patriciaTree, endPositionIndexArray, fileStringArray);
    }
}
