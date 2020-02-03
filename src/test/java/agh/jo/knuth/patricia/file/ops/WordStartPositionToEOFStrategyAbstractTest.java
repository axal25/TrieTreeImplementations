package agh.jo.knuth.patricia.file.ops;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;

public abstract class WordStartPositionToEOFStrategyAbstractTest extends FileOpsStrategyAbstractTest {
    public static final WordStrategy wordStrategy = WordStrategy.START_POSITION_TO_EOF;

    public void assertGetNumberOfBitsFromFileAtPosition(FileOpsStrategy fileOpsStrategy, MixMachine mixMachine, String[] expectedCharStringArray) throws Exception {
        int firstValidCharIndex = 1;
        int lastValidCharIndex = -1;
        if(mixMachine.getEncoding() == Encoding.MIX) lastValidCharIndex = expectedCharStringArray.length-2;
        else if(mixMachine.getEncoding() == Encoding.JAVA) lastValidCharIndex = expectedCharStringArray.length-1;

        int firstEOFCharIndex = -1;
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
        }
        if(firstEOFCharIndex < lastValidCharIndex) lastValidCharIndex = firstEOFCharIndex;
        assertGetSingleNumberOfBitsFromFileAtPosition(fileOpsStrategy, mixMachine, expectedCharStringArray, firstValidCharIndex, lastValidCharIndex);
    }
}
