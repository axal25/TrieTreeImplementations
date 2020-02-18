package agh.jo.knuth.patricia;

import agh.jo.utils.intf.FunctionalInterfaceVoidReturn;
import agh.jo.knuth.mix.machine.MixEncoding;
import agh.jo.knuth.patricia.file.ops.FileOpsStrategyAbstractTest;
import agh.jo.knuth.patricia.file.ops.WordStartPositionToEOFStrategyMixTest4;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatriciaTreeKnuthExampleMixEncodingWordStratSPTEOFTest4 extends PatriciaTreeWordStratSPTEOFTest {
    public final static String filePath = "/src/main/resources/knuth/patricia";
    public final static String fileName = "KnuthsPatriciaExample4.txt";
    public final static char charEOF = (char) MixEncoding.GreekCapitalLetter_CharCode_Pi;
    public final static char charEOK = (char) MixEncoding.GreekCapitalLetter_CharCode_Phi;
    public final static Encoding encoding = Encoding.MIX;
    public final static WordStrategy wordStrategy = PatriciaTreeWordStratSPTEOFTest.wordStrategy;
    // THIS ΦIS ΦΦTHE ΦHOUSE ΦTHAT ΦJACK ΦBUILT ;ΦΦΦΦΠ This Is Not A Part Of Any Key Because Of EOF Character Π
    public final static String[] fileStringArray = {"THIS Φ", "IS ΦΦ", "THE Φ", "HOUSE Φ", "THAT Φ", "JACK Φ", "BUILT ;ΦΦΦΦΠ"};
    public final static String[] expectedCharStringArray = WordStartPositionToEOFStrategyMixTest4.expectedCharStringArray;
    public final static int[] charPositionIndexArray = WordStartPositionToEOFStrategyMixTest4.charPositionIndexArray;

    public final static int[] charCodeArray = {
            23, 8, 9, 22, 0, 30,
            9, 22, 0, 30, 30,
            23, 8, 5, 0, 30,
            8, 16, 24, 22, 5, 0, 30,
            23, 8, 1, 23, 0, 30,
            11, 1, 3, 12, 0, 30,
            2, 24, 9, 13, 23, 0, 53, 30, 30, 30, 30, 31
    };

    public final static String[] binaryStringArray = {
            "010111", "001000", "001001", "010110", "000000", "011110",
            "001001", "010110", "000000", "011110", "011110",
            "010111", "001000", "000101", "000000", "011110",
            "001000", "010000", "011000", "010110", "000101", "000000", "011110",
            "010111", "001000", "000001", "010111", "000000", "011110",
            "001011", "000001", "000011", "001100", "000000", "011110",
            "000010", "011000", "001001", "001101", "010111", "000000", "110101", "011110", "011110", "011110", "011110", "011111"
    };
    public final static int[] startPositionIndexArray = {0, 5+1, 8+1+2, 12+1+3, 18+1+4, 23+1+5, 28+1+6};
    public final static int[] endPositionIndexArray = {4+1, 7+1+2, 11+1+3, 17+1+4, 22+1+5, 27+1+6, 33+1+4+7+1};
    public final static int expectedAmountOfBits = 6;

    public final static String[] headerRepresentations = {
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tisLeftAncestor = true,\n" +
                    "\tisRightAncestor = false,\n" +
                    "\tleftLink.id = 0,\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tisLeftAncestor = false,\n" +
                    "\tisRightAncestor = false,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 7,\n" +
                    "\t\tskip = 1,\n" +
                    "\t\tisLeftAncestor = true,\n" +
                    "\t\tisRightAncestor = true,\n" +
                    "\t\tleftLink.id = 1,\n" +
                    "\t\trightLink.id = 0\n" +
                    "\t},\n" +
                    "\trightLink = null\n" +
                    "}",
            "PatriciaNode{\n" +
                    "\tid = 0,\n" +
                    "\tkey = 0,\n" +
                    "\tskip = 0,\n" +
                    "\tisLeftAncestor = false,\n" +
                    "\tisRightAncestor = false,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 7,\n" +
                    "\t\tskip = 1,\n" +
                    "\t\tisLeftAncestor = true,\n" +
                    "\t\tisRightAncestor = false,\n" +
                    "\t\tleftLink.id = 1,\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 14,\n" +
                    "\t\t\tskip = 13,\n" +
                    "\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
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
                    "\tisLeftAncestor = false,\n" +
                    "\tisRightAncestor = false,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 7,\n" +
                    "\t\tskip = 1,\n" +
                    "\t\tisLeftAncestor = false,\n" +
                    "\t\tisRightAncestor = false,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 3,\n" +
                    "\t\t\tkey = 20,\n" +
                    "\t\t\tskip = 4,\n" +
                    "\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
                    "\t\t\tleftLink.id = 3,\n" +
                    "\t\t\trightLink.id = 1\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 14,\n" +
                    "\t\t\tskip = 13,\n" +
                    "\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
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
                    "\tisLeftAncestor = false,\n" +
                    "\tisRightAncestor = false,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 7,\n" +
                    "\t\tskip = 1,\n" +
                    "\t\tisLeftAncestor = false,\n" +
                    "\t\tisRightAncestor = false,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 3,\n" +
                    "\t\t\tkey = 20,\n" +
                    "\t\t\tskip = 4,\n" +
                    "\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
                    "\t\t\tleftLink.id = 3,\n" +
                    "\t\t\trightLink.id = 1\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 14,\n" +
                    "\t\t\tskip = 13,\n" +
                    "\t\t\tisLeftAncestor = false,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 4,\n" +
                    "\t\t\t\tkey = 28,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\t\tisRightAncestor = true,\n" +
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
                    "\tisLeftAncestor = false,\n" +
                    "\tisRightAncestor = false,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 7,\n" +
                    "\t\tskip = 1,\n" +
                    "\t\tisLeftAncestor = false,\n" +
                    "\t\tisRightAncestor = false,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 5,\n" +
                    "\t\t\tkey = 35,\n" +
                    "\t\t\tskip = 3,\n" +
                    "\t\t\tisLeftAncestor = false,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 3,\n" +
                    "\t\t\t\tkey = 20,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\t\tisRightAncestor = true,\n" +
                    "\t\t\t\tleftLink.id = 3,\n" +
                    "\t\t\t\trightLink.id = 1\n" +
                    "\t\t\t},\n" +
                    "\t\t\trightLink.id = 5\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 14,\n" +
                    "\t\t\tskip = 13,\n" +
                    "\t\t\tisLeftAncestor = false,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 4,\n" +
                    "\t\t\t\tkey = 28,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\t\tisRightAncestor = true,\n" +
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
                    "\tisLeftAncestor = false,\n" +
                    "\tisRightAncestor = false,\n" +
                    "\tleftLink = PatriciaNode{\n" +
                    "\t\tid = 1,\n" +
                    "\t\tkey = 7,\n" +
                    "\t\tskip = 1,\n" +
                    "\t\tisLeftAncestor = false,\n" +
                    "\t\tisRightAncestor = false,\n" +
                    "\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\tid = 6,\n" +
                    "\t\t\tkey = 42,\n" +
                    "\t\t\tskip = 1,\n" +
                    "\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\tisRightAncestor = false,\n" +
                    "\t\t\tleftLink.id = 6,\n" +
                    "\t\t\trightLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 5,\n" +
                    "\t\t\t\tkey = 35,\n" +
                    "\t\t\t\tskip = 2,\n" +
                    "\t\t\t\tisLeftAncestor = false,\n" +
                    "\t\t\t\tisRightAncestor = true,\n" +
                    "\t\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\t\tid = 3,\n" +
                    "\t\t\t\t\tkey = 20,\n" +
                    "\t\t\t\t\tskip = 1,\n" +
                    "\t\t\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\t\t\tisRightAncestor = true,\n" +
                    "\t\t\t\t\tleftLink.id = 3,\n" +
                    "\t\t\t\t\trightLink.id = 1\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\trightLink.id = 5\n" +
                    "\t\t\t}\n" +
                    "\t\t},\n" +
                    "\t\trightLink = PatriciaNode{\n" +
                    "\t\t\tid = 2,\n" +
                    "\t\t\tkey = 14,\n" +
                    "\t\t\tskip = 13,\n" +
                    "\t\t\tisLeftAncestor = false,\n" +
                    "\t\t\tisRightAncestor = true,\n" +
                    "\t\t\tleftLink = PatriciaNode{\n" +
                    "\t\t\t\tid = 4,\n" +
                    "\t\t\t\tkey = 28,\n" +
                    "\t\t\t\tskip = 1,\n" +
                    "\t\t\t\tisLeftAncestor = true,\n" +
                    "\t\t\t\tisRightAncestor = true,\n" +
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
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
    }

    @Test
    @Order(2)
    @DisplayName("getStringFromFileAtPositionRandomAccess")
    void getStringAtPositionRandomAccess() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        FileOpsStrategyAbstractTest.assertGetStringFromFileAtPositionRandomAccess(patriciaTree.getFileOps().getFileOpsStrategy(), expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(3)
    @DisplayName("getCharFromFileAtPosition")
    void getCharAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        FileOpsStrategyAbstractTest.assertGetCharFromFileAtPosition(patriciaTree.getFileOps().getFileOpsStrategy(), expectedCharStringArray, charPositionIndexArray);
    }

    @Test
    @Order(4)
    @DisplayName("isCharExistFromFileAtPosition")
    void isCharExistFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        FileOpsStrategyAbstractTest.assertIsCharExistFromFileAtPosition(patriciaTree.getFileOps().getFileOpsStrategy(), charPositionIndexArray);
    }

    @Test
    @Order(5)
    @DisplayName("getCharCode(char character)")
    void getCharCode() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        assertGetCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray, fileStringArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1, false);
    }

    @Test
    @Order(6)
    @DisplayName("getCharBytes(int characterCode)")
    void getCharBytes() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        assertGetCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray, fileStringArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1, true);
    }

    @Test
    @Order(7)
    @DisplayName("getBinaryString(int characterCode)")
    void getBinaryString() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(binaryStringArray, fileStringArray, patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), 0, charCodeArray.length-1);
    }

    @Test
    @Order(8)
    @DisplayName("getBinaryString(String charChain)")
    void getBinaryStringOfCharacterChain() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        assertConcatGetByteString(patriciaTree.getMixMachine(), patriciaTree.getFileOps().getFileOpsStrategy(), binaryStringArray, fileStringArray, startPositionIndexArray, endPositionIndexArray);
    }

    @Test
    @Order(9)
    @DisplayName("getNumberOfBitsFromFileAtPosition(int amountOfBits, int position)")
    void getNumberOfBitsFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        assertGetNumberOfBitsFromFileAtPosition(patriciaTree, binaryStringArray, startPositionIndexArray, endPositionIndexArray, expectedAmountOfBits, fileStringArray, encoding);
    }

    @Test
    @Order(10)
    @DisplayName("Constructor >> insert - 1 node - is Containing Prefix")
    void isContaining() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        assertIsContainingSinglePrefix(patriciaTree, startPositionIndexArray[0], fileStringArray);
    }

    @Test
    @Order(11)
    @DisplayName("findNextWordStartIndex")
    void findNewKeyStringFromFile() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray);
        assertFindNextWordStartIndexAndGetWordStringFromFileStartingAtPosition(patriciaTree.getFileOps().getFileOpsStrategy(), startPositionIndexArray, fileStringArray);
    }

    // Needed for description of first (after initiation)
    @Test
    @Order(12)
    @DisplayName("Constructor >> insert - 2 nodes - is Containing Prefix")
    void PatriciaTree2ndNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 2);
    }

    @Test
    @Order(13)
    @DisplayName("Constructor >> insert - 3 nodes - is Containing Prefix")
    void PatriciaTree3rdNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 3);
    }

    @Test
    @Order(14)
    @DisplayName("Constructor >> insert - 4 nodes - is Containing Prefix")
    void PatriciaTree4thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 4);
    }

    @Test
    @Order(15)
    @DisplayName("Constructor >> insert - 5 nodes - is Containing Prefix")
    void PatriciaTree5thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 5);
    }

    @Test
    @Order(16)
    @DisplayName("Constructor >> insert - 6 nodes - is Containing Prefix")
    void PatriciaTree6thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 6);
    }

    @Test
    @Order(17)
    @DisplayName("Constructor >> insert - 7 nodes - is Containing Prefix")
    void PatriciaTree7thNode() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 7);
        assertIsContainingAllPrefixes(patriciaTree, startPositionIndexArray, fileStringArray);
    }

    @Test
    @Order(18)
    @DisplayName("Constructor >> insert - 7 nodes - find Nodes Matching Prefix")
    void PatriciaTree7thNodeSearch() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 7);
        assertFindNodesMatchingAllPermutationsOfSinglePrefix(patriciaTree, fileStringArray);
        String searchWord = "TH";
        int[] expectedNodeIds = {0, 2, 4};
        assertFindMultipleNodesMatching(patriciaTree, searchWord, expectedNodeIds, fileStringArray);
    }

    @Test
    @Order(19)
    @DisplayName("Constructor >> insert - 1 node - to String")
    void PatriciaTree1stNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 1, headerRepresentations);
    }

    @Test
    @Order(20)
    @DisplayName("Constructor >> insert - 2 nodes - to String")
    void PatriciaTree2ndNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 2, headerRepresentations);
    }

    @Test
    @Order(21)
    @DisplayName("Constructor >> insert - 3 nodes - to String")
    void PatriciaTree3rdNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 3, headerRepresentations);
    }

    @Test
    @Order(22)
    @DisplayName("Constructor >> insert - 4 nodes - to String")
    void PatriciaTree4thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 4, headerRepresentations);
    }

    @Test
    @Order(23)
    @DisplayName("Constructor >> insert - 5 nodes - to String")
    void PatriciaTree5thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 5, headerRepresentations);
    }

    @Test
    @Order(24)
    @DisplayName("Constructor >> insert - 6 nodes - to String")
    void PatriciaTree6thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 6, headerRepresentations);
    }

    @Test
    @Order(25)
    @DisplayName("Constructor >> insert - 7 nodes - to String")
    void PatriciaTree7thNodeRepresentation() throws Exception {
        PatriciaTree patriciaTree = patriciaTreeNodesToStringAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 7, headerRepresentations);
    }

    @Test
    @Order(26)
    @DisplayName("Constructor >> insert - 7 nodes - findNextWordStartIndex(node7thKeyPosition)")
    void PatriciaTree7thNodeFindNextWordStartIndex() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 7);
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
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 7);
        FunctionalInterfaceVoidReturn fi = () -> {
            patriciaTree.insertNextKeyIntoTree();
        };
        assertThrows(Exception.class, fi::run);
    }

    @Test
    @Order(28)
    @DisplayName("Constructor >> insert - 7 nodes - getNumberOfBitsFromFileAtPosition(1, node7thKeyPosition)")
    void PatriciaTree7thNodeGetNumberOfBitsFromFileAtPosition() throws Exception {
        PatriciaTree patriciaTree = getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, expectedAmountOfBits, fileStringArray, 7);
        assertGetNumberOfBitsFromFileAtPositionAfterEOF(patriciaTree, endPositionIndexArray, fileStringArray);
    }
}
