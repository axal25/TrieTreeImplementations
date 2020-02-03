package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;

import java.util.LinkedList;

public abstract class WordSingleStrategyAbstractTest extends FileOpsStrategyAbstractTest {
    public static final WordStrategy wordStrategy = WordStrategy.SINGLE;


    public void assertGetNumberOfBitsFromFileAtPosition(FileOpsStrategy fileOpsStrategy, MixMachine mixMachine, String[] expectedCharStringArray) throws Exception {
        int firstValidCharIndex = 1;
        int lastValidCharIndex = -1;
        lastValidCharIndex = expectedCharStringArray.length-2;

        int firstEOFCharIndex = -1;
        LinkedList<Integer> firstEOKCharIndex = new LinkedList<>();
        boolean isEncounteredEOF = false;
        for (int i = firstValidCharIndex; i < expectedCharStringArray.length && !isEncounteredEOF; i++) {
            if (
                    expectedCharStringArray[i] != null
                            && expectedCharStringArray[i].charAt(0) == fileOpsStrategy.getCharEOF()
                            && !isEncounteredEOF
            ) {
                firstEOFCharIndex = i;
                isEncounteredEOF = true;
            }
            if (
                    expectedCharStringArray[i-1] != null
                            && expectedCharStringArray[i-1].charAt(0) == fileOpsStrategy.getCharEOK()
                            && expectedCharStringArray[i] != null
                            && expectedCharStringArray[i].charAt(0) != fileOpsStrategy.getCharEOK()
                            && expectedCharStringArray[i].charAt(0) != fileOpsStrategy.getCharEOF()
            ) {
                firstEOKCharIndex.add(i-1);
            }
        }
        int firstValidCharIndexOfThisKey = -1;
        int lastValidCharIndexOfThisKey = -1;
        for (int i = 0; i < firstEOKCharIndex.size(); i++) {
            if(i!=0) firstValidCharIndexOfThisKey = firstEOKCharIndex.get(i-1) + 1;
            else firstValidCharIndexOfThisKey = firstValidCharIndex;
            lastValidCharIndexOfThisKey = Integer.MAX_VALUE;

            if(firstEOFCharIndex < lastValidCharIndexOfThisKey) lastValidCharIndexOfThisKey = firstEOFCharIndex;
            if(firstEOKCharIndex.get(i) < lastValidCharIndexOfThisKey) lastValidCharIndexOfThisKey = firstEOKCharIndex.get(i);
            assertGetSingleNumberOfBitsFromFileAtPosition(fileOpsStrategy, mixMachine, expectedCharStringArray, firstValidCharIndexOfThisKey, lastValidCharIndexOfThisKey);
        }
        lastValidCharIndexOfThisKey = lastValidCharIndex;
        if(firstEOFCharIndex < lastValidCharIndexOfThisKey) lastValidCharIndexOfThisKey = firstEOFCharIndex;
        firstValidCharIndexOfThisKey = firstEOKCharIndex.get(firstEOKCharIndex.size()-1) + 1;
        assertGetSingleNumberOfBitsFromFileAtPosition(fileOpsStrategy, mixMachine, expectedCharStringArray, firstValidCharIndexOfThisKey, lastValidCharIndexOfThisKey);
    }
}
