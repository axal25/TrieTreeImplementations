package agh.jo.knuth.patricia;

import agh.jo.knuth.patricia.file.ops.FileOpsStrategy;
import agh.jo.knuth.patricia.file.ops.WordStrategy;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class PatriciaTreeWordStratSPTEOFTest {
    public final static WordStrategy wordStrategy = WordStrategy.START_POSITION_TO_EOF;

    public static void assertGetCharCodeOfGetCharFromFileAtPositionEquals(
            int[] charCodeArray,
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            int startPosition,
            int endPosition,
            boolean isDoGetCharBytes
    ) throws Exception {
        if(charCodeArray.length != (endPosition - startPosition +1)) throw new Exception("Char code array length does not match amount of positions." +
                " charCodeArray.length: " + charCodeArray.length + " (endPosition - startPosition +1): " + (endPosition - startPosition +1));
        for (int i = startPosition; i < endPosition; i++) {
            assertGetCharCodeOfGetCharFromFileAtPositionEquals(charCodeArray[i-startPosition], mixMachine, fileOpsStrategy, i, isDoGetCharBytes);
        }
    }

    public static void assertGetCharCodeOfGetCharFromFileAtPositionEquals(
            int expectedCharCode,
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            int position,
            boolean isDoGetCharBytes
    ) throws Exception {
        int actualCharCode = mixMachine.getCharCode(fileOpsStrategy.getCharFromFileAtPosition(position));
        if(isDoGetCharBytes) actualCharCode = mixMachine.getCharBytes(actualCharCode)[0];
        assertEquals(expectedCharCode, actualCharCode);
    }

    public static void assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(
            String[] binaryStringArray,
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            int startPosition,
            int endPosition
    ) throws Exception {
        if(binaryStringArray.length != (endPosition - startPosition +1)) throw new Exception("String code array length does not match amount of positions." +
                " binaryStringArray.length: " + binaryStringArray.length + " (endPosition - startPosition +1): " + (endPosition - startPosition +1));
        for (int i = startPosition; i < endPosition; i++) {
            assertGetBinaryStringOfGetCharCodeOfGetCharFromFileAtPositionEquals(binaryStringArray[i-startPosition], mixMachine, fileOpsStrategy, i);
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
            int[] startPositionIndexArray,
            int[] endPositionIndexArray
    ) throws Exception {
        if(startPositionIndexArray.length != endPositionIndexArray.length)
            throw new Exception("Start and End position / index arrays are not the same length." +
                    " startPositionIndexArray.length: " + startPositionIndexArray.length +
                    " endPositionIndexArray.length: " + endPositionIndexArray.length);
        for (int i = 0; i < startPositionIndexArray.length; i++) {
            assertConcatGetByteString(mixMachine, fileOpsStrategy, binaryStringArray, startPositionIndexArray[i], endPositionIndexArray[i]);
        }
    }

    public void assertConcatGetByteString(
            MixMachine mixMachine,
            FileOpsStrategy fileOpsStrategy,
            String[] binaryStringArray,
            int startPositionIndex,
            int endPositionIndex
    ) throws Exception {
        StringBuilder actualChars = new StringBuilder();
        StringBuilder expectedByteString = new StringBuilder();
        for (int i = startPositionIndex; i < endPositionIndex+1; i++) {
            actualChars.append(fileOpsStrategy.getCharFromFileAtPosition(i));
            expectedByteString.append(binaryStringArray[i]);
        }
        String actualByteString = mixMachine.getBinaryString(actualChars.toString());
        assertEquals(expectedByteString.toString(), actualByteString);
    }

    public void assertGetNumberOfBitsFromFileAtPosition(
            PatriciaTree patriciaTree,
            String[] binaryStringArray,
            int[] startPositionIndexArray,
            int[] endPositionIndexArray,
            int expectedAmountOfBits
    ) throws Exception {
        if(startPositionIndexArray.length != endPositionIndexArray.length)
            throw new Exception("Start and End position / index arrays are not the same length." +
                    " startPositionIndexArray.length: " + startPositionIndexArray.length +
                    " endPositionIndexArray.length: " + endPositionIndexArray.length);
        for (int i = 0; i < startPositionIndexArray.length; i++) {
            assertGetNumberOfBitsFromFileAtPosition(patriciaTree, binaryStringArray, startPositionIndexArray[i], endPositionIndexArray[i], expectedAmountOfBits);
        }
    }

    public void assertGetNumberOfBitsFromFileAtPosition(
            PatriciaTree patriciaTree,
            String[] binaryStringArray,
            int startPositionIndex,
            int endPositionIndex,
            int expectedAmountOfBits
    ) throws Exception {
        StringBuilder expectedByteString = new StringBuilder();
        for (int i = startPositionIndex; i < endPositionIndex+1; i++) {
            expectedByteString.append(binaryStringArray[i]);
        }
        int actualMixMachineAmountOfBits = patriciaTree.getMixMachine().getAmountOfBits();
        assertEquals(expectedAmountOfBits, actualMixMachineAmountOfBits);
        int actualFragmentAmountOfBits = actualMixMachineAmountOfBits*(endPositionIndex-startPositionIndex+1);
        String actualFragmentByteString = patriciaTree.getNumberOfBitsFromFileAtPosition(actualFragmentAmountOfBits, startPositionIndex);
        assertEquals(expectedByteString.toString(), actualFragmentByteString);
    }

    public void assertIsContainingCharOneByOne(PatriciaTree patriciaTree, int amountOfCharsInsideFile) throws Exception {
        StringBuilder wholeFileContents = new StringBuilder();
        for (int i = 0; i < amountOfCharsInsideFile; i++) {
            wholeFileContents.append(patriciaTree.getFileOps().getFileOpsStrategy().getCharFromFileAtPosition(i));
        }
        for (int i = 0; i < wholeFileContents.length(); i++) {
            assertTrue(patriciaTree.isContaining(wholeFileContents.substring(0, i)));
        }
    }

    public void assertFindNextWordStartIndexAndGetWordStringFromFileStartingAtPosition(FileOpsStrategy fileOpsStrategy, int[] startPositionIndexArray, String[] fileStringArray) throws Exception {
        if(startPositionIndexArray.length != fileStringArray.length)
            throw new Exception("Array's length of Start positions / indexes does not match Array's length of File strings." +
                    " startPositionIndexArray.length: " + startPositionIndexArray.length +
                    " fileStringArray.length: " + fileStringArray.length);
        for (int i = 0; i < startPositionIndexArray.length; i++) {
            if(i!=0) assertFindNextWordStartIndex(fileOpsStrategy, startPositionIndexArray[i-1], startPositionIndexArray[i]);
            String expectedKeyStartIndex = concatStringArray(fileStringArray, i, fileStringArray.length-1);
            assertGetWordStringFromFileStartingAtPosition(fileOpsStrategy, startPositionIndexArray[i], expectedKeyStartIndex);
        }
    }

    public String concatStringArray(String[] array, int startIndex, int endIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startIndex; i < endIndex+1 && i < array.length; i++) {
            stringBuilder.append(array[i]);
        }
        return stringBuilder.toString();
    }

    public void assertFindNextWordStartIndex(FileOpsStrategy fileOpsStrategy, int prevKeyStartIndex, int expectedKeyStartIndex) {
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
            Encoding encoding,
            String[] fileStringArray,
            WordStrategy wordStrategy) throws Exception {
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding, wordStrategy);
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
        assertIsContaining(patriciaTree, fileStringArray, 1);
        return patriciaTree;
    }

    public void assertIsContaining(PatriciaTree patriciaTree, String[] fileStringArray, int amountOfNodes) throws Exception {
        for (int i = 0; i < fileStringArray.length; i++) {
            boolean isContaining = patriciaTree.isContaining(fileStringArray[i]);
            if(i < amountOfNodes) assertTrue(isContaining);
            else assertFalse(isContaining);
        }
    }

    public PatriciaTree getPatriciaTreeWithXNodesAndAssert(
            String filePath,
            String fileName,
            Encoding encoding,
            String[] fileStringArray,
            int amountOfNodes, WordStrategy wordStrategy) throws Exception {
        if(amountOfNodes < 0) throw new Exception("Patricia tree can't have less nodes than 1. It always has at least one (header) node. amountOfNodes: " + amountOfNodes);
        if(amountOfNodes > fileStringArray.length) throw new Exception("This patricia tree can't have more nodes than there are words in file. fileStringArray.length: " + fileStringArray.length);
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTreeAndAssert(filePath, fileName, encoding, fileStringArray, wordStrategy);
        for (int i = 1; i < amountOfNodes; i++) {
            patriciaTree.insertNextKeyIntoTree();
            assertIsContaining(patriciaTree, fileStringArray, i+1);
        }
        return patriciaTree;
    }

    public void assertFindNodesMatching(PatriciaTree patriciaTree, String[] fileStringArray) throws Exception {
        for (int i = 0; i < fileStringArray.length; i++) {
            assertFindNodesMatching(patriciaTree, fileStringArray[i], concatStringArray(fileStringArray, i,fileStringArray.length-1), i);
        }
    }

    public void assertFindNodesMatching(PatriciaTree patriciaTree, String searchWord, String expectedWordString, int expectedNodeId) throws Exception {
        PatriciaNode[] patriciaNodes = patriciaTree.findNodesMatching(searchWord);
        assertEquals(1, patriciaNodes.length);
        for (int i = 0; i < patriciaNodes.length; i++) {
            assertEquals(expectedNodeId, patriciaNodes[i].getId());
            assertEquals(expectedWordString,  patriciaTree.getFileOps().getFileOpsStrategy().getWordStringFromFileStartingAtPosition(patriciaNodes[i].getKey()));
        }
    }

    public void assertFindMultipleNodesMatching(PatriciaTree patriciaTree, String searchWord, int[] expectedNodeIds, String[] fileStringArray) throws Exception {
        PatriciaNode[] foundNodes = patriciaTree.findNodesMatching(searchWord);
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

    public PatriciaTree patriciaTreeNodesToStringAssert(String filePath,
                                                         String fileName,
                                                         Encoding encoding,
                                                         String[] fileStringArray,
                                                         int amountOfNodes, WordStrategy wordStrategy,
                                                         String[] headerRepresentations
    ) throws Exception {
        PatriciaTree patriciaTree =
                getPatriciaTreeWithXNodesAndAssert(filePath, fileName, encoding, fileStringArray, amountOfNodes, wordStrategy);
        assertEquals(headerRepresentations[amountOfNodes-1], patriciaTree.getHeader().toString());
        return patriciaTree;
    }
}
