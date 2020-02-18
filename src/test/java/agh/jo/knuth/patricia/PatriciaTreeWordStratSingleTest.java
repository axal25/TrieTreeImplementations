package agh.jo.knuth.patricia;

import agh.jo.knuth.patricia.file.ops.FileOpsStrategy;
import agh.jo.knuth.patricia.file.ops.WordStrategy;

import static agh.jo.knuth.patricia.file.ops.FileOpsStrategyAbstractTest.concatStringArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class PatriciaTreeWordStratSingleTest extends PatriciaTreeWordStratSPTEOFTest {
    public final static WordStrategy wordStrategy = WordStrategy.SINGLE;

    @Override
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
            String expectedKeyStartIndex = fileStringArray[i];
            assertGetWordStringFromFileStartingAtPosition(fileOpsStrategy, getBytePosition(fileStringArray, startPositionIndexArray[i]), expectedKeyStartIndex);
        }
    }

    @Override
    public void assertFindNodesMatchingAllPermutationsOfSinglePrefix(PatriciaTree patriciaTree, String[] fileStringArray) throws Exception {
        for (int i = 0; i < fileStringArray.length; i++) {
            assertFindNodesMatchingSinglePrefix(patriciaTree, fileStringArray[i], fileStringArray[i], i);
        }
    }

    @Override
    public void assertFindMultipleNodesMatching(PatriciaTree patriciaTree, String searchWord, int[] expectedNodeIds, String[] fileStringArray) throws Exception {
        PatriciaNode[] foundNodes = patriciaTree.findNodesMatchingPrefix(searchWord);
        assertEquals(expectedNodeIds.length, foundNodes.length);
        for (int i = 0; i < expectedNodeIds.length; i++) {
            assertEquals(expectedNodeIds[i], foundNodes[i].getId());
        }
        for (int i = 0; i < foundNodes.length; i++) {
            String expectedKeyWord = fileStringArray[expectedNodeIds[i]];
            String actualKeyWord = patriciaTree.getFileOps().getFileOpsStrategy().getWordStringFromFileStartingAtPosition(foundNodes[i].getKey());
            assertEquals(expectedKeyWord, actualKeyWord);
        }
    }

    @Override
    public void assertIsContainingSinglePrefix(PatriciaTree patriciaTree, int keyStartPosition, String[] fileStringArray) throws Exception {
        String wholeFileContents = concatStringArray(fileStringArray, 0, fileStringArray.length-1);
        boolean isEncounteredEOForEOK = false;
        for (int i = keyStartPosition+1; i < wholeFileContents.length() && !isEncounteredEOForEOK; i++) {
            if(
                    wholeFileContents.charAt(i-1) == patriciaTree.getFileOps().getFileOpsStrategy().getCharEOF() ||
                            wholeFileContents.charAt(i-1) == patriciaTree.getFileOps().getFileOpsStrategy().getCharEOK()
            ) isEncounteredEOForEOK = true;
            String keyPrefix = wholeFileContents.substring(keyStartPosition, i);
            assertTrue(patriciaTree.isContainingPrefix(keyPrefix));
        }
    }
}
