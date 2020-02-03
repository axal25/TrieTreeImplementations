package agh.jo.knuth.patricia;

import agh.jo.func.intf.FunctionalInterfaceVoidReturn;
import agh.jo.knuth.patricia.file.ops.FileOpsStrategy;
import agh.jo.knuth.patricia.file.ops.WordStrategy;

import java.nio.charset.Charset;

import static agh.jo.knuth.patricia.file.ops.FileOpsStrategyAbstractTest.concatStringArray;
import static agh.jo.knuth.patricia.file.ops.FileOpsStrategyAbstractTest.getByteAmount;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class PatriciaTreeWordStratSPTEOFTest {
    public final static WordStrategy wordStrategy = WordStrategy.START_POSITION_TO_EOF;

    public static void assertGetCharCodeOfGetCharFromFileAtPositionEquals(
            int[] charCodeArray,
            String[] fileStringArray,
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            int startPosition,
            int endPosition,
            boolean isDoGetCharBytes
    ) throws Exception {
        if(charCodeArray.length != (endPosition - startPosition +1)) throw new Exception("Char code array length does not match amount of positions." +
                " charCodeArray.length: " + charCodeArray.length + " (endPosition - startPosition +1): " + (endPosition - startPosition +1));
        for (int i = startPosition; i < endPosition; i++) {
            assertGetSingleCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray[i-startPosition], mixMachine, fileOpsStrategy, getBytePosition(fileStringArray, i), isDoGetCharBytes);
        }
    }

    public static int getBytePosition(String[] fileStringArray, int startCharPositionIndex) {
        if(startCharPositionIndex == 0) return 0;
        String wholeFileContent = concatStringArray(fileStringArray, 0, fileStringArray.length-1);
        String skippedFileContent = wholeFileContent.substring(0, startCharPositionIndex);
        return skippedFileContent.getBytes(Charset.forName("UTF-8")).length;
    }

    public static void assertGetSingleCharCodeOfGetCharFromFileAtPositionEquals(
            int expectedCharCode,
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            int position,
            boolean isDoGetCharBytes
    ) throws Exception {
        int actualCharCode = mixMachine.getCharCode(fileOpsStrategy.getCharFromFileAtPosition(position));
        assertEquals(expectedCharCode, actualCharCode);
        if(isDoGetCharBytes) actualCharCode = MixMachine.javaBytesToInt( mixMachine.getCharBytes(actualCharCode) );
        assertEquals(expectedCharCode, actualCharCode);
    }

    public static void assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(
            String[] binaryStringArray,
            String[] fileStringArray, MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            int startPosition,
            int endPosition
    ) throws Exception {
        if(binaryStringArray.length != (endPosition - startPosition +1)) throw new Exception("String code array length does not match amount of positions." +
                " binaryStringArray.length: " + binaryStringArray.length + " (endPosition - startPosition +1): " + (endPosition - startPosition +1));
        for (int i = startPosition; i < endPosition; i++) {
            assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(binaryStringArray[i-startPosition], mixMachine, fileOpsStrategy, getBytePosition(fileStringArray, i));
        }
    }

    public static void assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(
            String expectedBinaryString,
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            int position
    ) throws Exception {
        String actualBinaryString = mixMachine.getBinaryString(mixMachine.getCharCode(fileOpsStrategy.getCharFromFileAtPosition(position)));
        assertEquals(expectedBinaryString, actualBinaryString);
    }

    public void assertConcatGetByteString(
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            String[] binaryStringArray,
            String[] fileStringArray, int[] startPositionIndexArray,
            int[] endPositionIndexArray
    ) throws Exception {
        if(startPositionIndexArray.length != endPositionIndexArray.length)
            throw new Exception("Start and End position / index arrays are not the same length." +
                    " startPositionIndexArray.length: " + startPositionIndexArray.length +
                    " endPositionIndexArray.length: " + endPositionIndexArray.length);
        for (int i = 0; i < startPositionIndexArray.length; i++) {
            assertConcatGetByteString(mixMachine, fileOpsStrategy, binaryStringArray, fileStringArray, startPositionIndexArray[i], endPositionIndexArray[i]);
        }
    }

    public void assertConcatGetByteString(
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            String[] binaryStringArray,
            String[] fileStringArray,
            int startPositionIndex,
            int endPositionIndex
    ) throws Exception {
        StringBuilder actualChars = new StringBuilder();
        StringBuilder expectedByteString = new StringBuilder();
        for (int i = startPositionIndex; i < endPositionIndex+1; i++) {
            actualChars.append(fileOpsStrategy.getCharFromFileAtPosition(getBytePosition(fileStringArray, i)));
            expectedByteString.append(binaryStringArray[i]);
        }
        System.err.println("actualChars: \"" + actualChars + "\"");
        String actualByteString = mixMachine.getBinaryString(actualChars.toString());
        assertEquals(expectedByteString.toString(), actualByteString);
    }

    public void assertGetNumberOfBitsFromFileAtPosition(
            PatriciaTree patriciaTree,
            String[] binaryStringArray,
            int[] startPositionIndexArray,
            int[] endPositionIndexArray,
            int expectedAmountOfBits,
            String[] fileStringArray, Encoding encoding) throws Exception {
        if(startPositionIndexArray.length != endPositionIndexArray.length)
            throw new Exception("Start and End position / index arrays are not the same length." +
                    " startPositionIndexArray.length: " + startPositionIndexArray.length +
                    " endPositionIndexArray.length: " + endPositionIndexArray.length);
        for (int i = 0; i < startPositionIndexArray.length; i++) {
            assertGetNumberOfBitsFromFileAtPosition(patriciaTree, encoding, binaryStringArray, fileStringArray, startPositionIndexArray[i], endPositionIndexArray[i], expectedAmountOfBits);
        }
    }

    public void assertGetNumberOfBitsFromFileAtPosition(
            PatriciaTree patriciaTree,
            Encoding encoding, String[] binaryStringArray,
            String[] fileStringArray,
            int startCharPositionIndex,
            int endCharPositionIndex,
            int expectedAmountOfBits) throws Exception {
        String expectedByteString = concatStringArray(binaryStringArray, startCharPositionIndex, endCharPositionIndex);

        int actualMixMachineAmountOfBits = patriciaTree.getMixMachine().getAmountOfBits();
        assertEquals(expectedAmountOfBits, actualMixMachineAmountOfBits);

        int actualFragmentAmountOfBits = actualMixMachineAmountOfBits *
                getByteAmount(fileStringArray, startCharPositionIndex, endCharPositionIndex, patriciaTree.getMixMachine());
        int startBytePositionIndex = getBytePosition(fileStringArray, startCharPositionIndex);
        String actualFragmentByteString = patriciaTree.getNumberOfBitsFromFileAtPosition(actualFragmentAmountOfBits, startBytePositionIndex);
        assertEquals(expectedByteString, actualFragmentByteString);
    }

    public void assertIsContainingAllPrefixes(PatriciaTree patriciaTree, int[] startPositionIndexArray, String[] fileStringArray) throws Exception {
        for (int i = 0; i < startPositionIndexArray.length; i++) {
            assertIsContainingSinglePrefix(patriciaTree, startPositionIndexArray[i], fileStringArray);
        }
    }

    public void assertIsContainingSinglePrefix(PatriciaTree patriciaTree, int keyStartPosition, String[] fileStringArray) throws Exception {
        String wholeFileContents = concatStringArray(fileStringArray, 0, fileStringArray.length-1);
        for (int i = keyStartPosition+1; i < wholeFileContents.length(); i++) {
            String keyPrefix = wholeFileContents.substring(keyStartPosition, i);
            assertTrue(patriciaTree.isContainingPrefix(keyPrefix));
        }
    }

    public void assertFindNextWordStartIndexAndGetWordStringFromFileStartingAtPosition(FileOpsStrategy fileOpsStrategy, int[] startPositionIndexArray, String[] fileStringArray) throws Exception {
        if(startPositionIndexArray.length != fileStringArray.length)
            throw new Exception("Array's length of Start positions / indexes does not match Array's length of File strings." +
                    " startPositionIndexArray.length: " + startPositionIndexArray.length +
                    " fileStringArray.length: " + fileStringArray.length);
        for (int i = 0; i < startPositionIndexArray.length; i++) {
            if(i!=0) assertFindNextWordStartIndex(
                    fileOpsStrategy,
                    getBytePosition(fileStringArray, startPositionIndexArray[i-1]),
                    getBytePosition(fileStringArray, startPositionIndexArray[i])
            );
            String expectedKeyStartIndex = concatStringArray(fileStringArray, i, fileStringArray.length-1);
            assertGetWordStringFromFileStartingAtPosition(fileOpsStrategy, getBytePosition(fileStringArray, startPositionIndexArray[i]), expectedKeyStartIndex);
        }
    }

    public void assertFindNextWordStartIndex(FileOpsStrategy fileOpsStrategy, int prevKeyStartIndex, int expectedKeyStartIndex) throws Exception {
        int newKeyStartIndex = fileOpsStrategy.findNextWordStartIndex(prevKeyStartIndex);
        assertEquals(expectedKeyStartIndex, newKeyStartIndex);
    }

    public void assertGetWordStringFromFileStartingAtPosition(FileOpsStrategy fileOpsStrategy, int newKeyStartIndex, String expectedKeyString) throws Exception {
        String actualKeyString = fileOpsStrategy.getWordStringFromFileStartingAtPosition(newKeyStartIndex);
        assertEquals(expectedKeyString, actualKeyString);
    }

    public PatriciaTree getNewInitiatedPatriciaTreeAndAssert(
            String filePath,
            String fileName,
            char charEOF,
            char charEOK,
            WordStrategy wordStrategy,
            Encoding encoding,
            String[] fileStringArray
    ) throws Exception {
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        assertNotNull(patriciaTree);
        assertNotNull(patriciaTree.getMixMachine());
        assertNotNull(patriciaTree.getFileOps().getFileOpsStrategy());
        assertEquals(fileName, patriciaTree.getFileOps().getFileOpsStrategy().getFileName());
        assertEquals(System.getProperty("user.dir") + filePath, patriciaTree.getFileOps().getFileOpsStrategy().getFilePath());
        assertEquals(wordStrategy, patriciaTree.getFileOps().getWordStrategy());
        assertEquals(encoding, patriciaTree.getMixMachine().getEncoding());
        assertEquals(1, patriciaTree.getNextNodeIdToInsert());
        assertNotNull(patriciaTree.getHeader());
        assertEquals(0, patriciaTree.getHeader().getId());
        assertEquals(0, patriciaTree.getHeader().getSkip());
        assertEquals(0, patriciaTree.getHeader().getKey());
        assertNull(patriciaTree.getHeader().getRightLink());
        assertFalse(patriciaTree.getHeader().getIsRightAncestor());
        assertNotNull(patriciaTree.getHeader().getLeftLink());
        assertEquals(patriciaTree.getHeader(), patriciaTree.getHeader().getLeftLink());
        assertTrue(patriciaTree.getHeader().getIsLeftAncestor());
        assertNull(patriciaTree.getHeader().getRightLink());
        assertFalse(patriciaTree.getHeader().getIsRightAncestor());
        assertIsContainingPrefix(patriciaTree, fileStringArray, 1);
        return patriciaTree;
    }

    public void assertIsContainingPrefix(PatriciaTree patriciaTree, String[] fileStringArray, int amountOfNodes) throws Exception {
        for (int i = 0; i < fileStringArray.length; i++) {
            boolean isContaining = patriciaTree.isContainingPrefix(fileStringArray[i]);
            if(i < amountOfNodes) {
                assertTrue(isContaining);
            }
            else assertFalse(isContaining);
        }
    }

    public PatriciaTree getPatriciaTreeWithXNodesAndAssert(
            String filePath,
            String fileName,
            char charEOF,
            char charEOK,
            WordStrategy wordStrategy,
            Encoding encoding,
            String[] fileStringArray,
            int amountOfNodes
    ) throws Exception {
        if(amountOfNodes < 0) throw new Exception("Patricia tree can't have less nodes than 1. It always has at least one (header) node. amountOfNodes: " + amountOfNodes);
        if(amountOfNodes > fileStringArray.length) throw new Exception("This patricia tree can't have more nodes than there are words in file. fileStringArray.length: " + fileStringArray.length);
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray);
        for (int i = 1; i < amountOfNodes; i++) {
            patriciaTree.insertNextKeyIntoTree();
            assertIsContainingPrefix(patriciaTree, fileStringArray, i+1);
        }
        return patriciaTree;
    }

    public void assertFindNodesMatchingAllPermutationsOfSinglePrefix(PatriciaTree patriciaTree, String[] fileStringArray) throws Exception {
        for (int i = 0; i < fileStringArray.length; i++) {
            assertFindNodesMatchingSinglePrefix(patriciaTree, fileStringArray[i], concatStringArray(fileStringArray, i,fileStringArray.length-1), i);
        }
    }

    public void assertFindNodesMatchingSinglePrefix(PatriciaTree patriciaTree, String searchWord, String expectedWordString, int expectedNodeId) throws Exception {
        PatriciaNode[] patriciaNodes = patriciaTree.findNodesMatchingPrefix(searchWord);
        assertNotNull(patriciaNodes);
        assertEquals(1, patriciaNodes.length);
        for (int i = 0; i < patriciaNodes.length; i++) {
            assertEquals(expectedNodeId, patriciaNodes[i].getId());
            assertEquals(expectedWordString,  patriciaTree.getFileOps().getFileOpsStrategy().getWordStringFromFileStartingAtPosition(patriciaNodes[i].getKey()));
        }
    }

    public void assertFindMultipleNodesMatching(PatriciaTree patriciaTree, String searchWord, int[] expectedNodeIds, String[] fileStringArray) throws Exception {
        PatriciaNode[] foundNodes = patriciaTree.findNodesMatchingPrefix(searchWord);
        assertEquals(expectedNodeIds.length, foundNodes.length);
        for (int i = 0; i < expectedNodeIds.length; i++) {
            assertEquals(expectedNodeIds[i], foundNodes[i].getId());
        }
        for (int i = 0; i < foundNodes.length; i++) {
            String expectedKeyWord = concatStringArray(fileStringArray, expectedNodeIds[i],fileStringArray.length-1);
            String actualKeyWord = patriciaTree.getFileOps().getFileOpsStrategy().getWordStringFromFileStartingAtPosition(foundNodes[i].getKey());
            assertEquals(expectedKeyWord, actualKeyWord);
        }
    }

    public PatriciaTree patriciaTreeNodesToStringAssert(
            String filePath,
            String fileName,
            char charEOF,
            char charEOK,
            WordStrategy wordStrategy,
            Encoding encoding,
            String[] fileStringArray,
            int amountOfNodes,
            String[] headerRepresentations
    ) throws Exception {
        PatriciaTree patriciaTree =
                getPatriciaTreeWithXNodesAndAssert(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, fileStringArray, amountOfNodes);
        assertEquals(headerRepresentations[amountOfNodes-1], patriciaTree.getHeader().toString());
        return patriciaTree;
    }

    public void assertGetNumberOfBitsFromFileAtPositionAfterEOF(PatriciaTree patriciaTree, int[] endPositionIndexArray, String[] fileStringArray) throws Exception {
        int node7thKeyEndPositionChar = endPositionIndexArray[endPositionIndexArray.length-1];
        int node7thKeyEndPositionByte = getBytePosition(fileStringArray, node7thKeyEndPositionChar);
        String readBytes = null;
        int amountOfBitsPerByte = -1;
        if(patriciaTree.getMixMachine().getEncoding() == Encoding.MIX) amountOfBitsPerByte = 5;
        if(patriciaTree.getMixMachine().getEncoding() == Encoding.JAVA) amountOfBitsPerByte = 8;
        readBytes = patriciaTree.getFileOps().getFileOpsStrategy().getNumberOfCharsBasedOnNumberOfBitsFromFileAtPosition(1000*amountOfBitsPerByte, node7thKeyEndPositionByte);
        assertEquals(1, readBytes.length());
        assertEquals(patriciaTree.getFileOps().getFileOpsStrategy().getCharEOF(), readBytes.charAt(0));
    }
}
