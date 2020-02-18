package agh.jo.knuth.patricia.file.ops;

import agh.jo.utils.intf.FunctionalInterfaceVoidReturn;
import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.MixMachine;
import agh.jo.knuth.patricia.PatriciaTree;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

public abstract class FileOpsStrategyAbstractTest {
    public static FileOpsStrategy getNewFileOpsStrategy(
            String filePath,
            String fileName,
            char charEOF,
            char charEOK,
            WordStrategy wordStrategy,
            Encoding encoding,
            int amountOfBits
    ) throws Exception {
        FileOps fileOps = new FileOps(null, filePath, fileName, charEOF, charEOK, wordStrategy);
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding, amountOfBits);
        fileOps = patriciaTree.getFileOps();
        FileOpsStrategy fileOpsStrategy = fileOps.getFileOpsStrategy();
        return fileOpsStrategy;
    }

    public static MixMachine extractMixMachine(FileOpsStrategy fileOpsStrategy) {
        return fileOpsStrategy.getOwner().getOwner().getMixMachine();
    }

    public static int[] getCharPosition(String[] expectedCharStringArray) {
        int[] charPositionIndexArray = new int[expectedCharStringArray.length];
        int previousCharBitLength = 0;
        charPositionIndexArray[0] = -1;
        charPositionIndexArray[1] = 0;
        for (int i = 2; i < expectedCharStringArray.length; i++) {
                previousCharBitLength = expectedCharStringArray[i-1].getBytes(Charset.forName("UTF-8")).length;
            charPositionIndexArray[i] = charPositionIndexArray[i-1] + previousCharBitLength;
        }
        return charPositionIndexArray;
    }

    public static void assertGetStringFromFileAtPositionRandomAccess(FileOpsStrategy fileOpsStrategy, String[] expectedCharArray, int[] charPositionIndexArray) {
        for (int i = 0; i < expectedCharArray.length; i++) {
            assertGetSingleStringFromFileAtPositionRandomAccess(fileOpsStrategy, expectedCharArray, charPositionIndexArray, i);
        }
    }

    public static void assertGetSingleStringFromFileAtPositionRandomAccess(FileOpsStrategy fileOpsStrategy, String[] expectedStringCharArray, int[] charPositionIndexArray, int index) {
        assertEquals(expectedStringCharArray[index], fileOpsStrategy.getStringFromFileAtPositionRandomAccess(charPositionIndexArray[index]));
    }

    public static void assertGetCharFromFileAtPosition(FileOpsStrategy fileOpsStrategy, String[] expectedCharArray, int[] charPositionIndexArray) throws Exception {
        for (int i = 0; i < expectedCharArray.length; i++) {
            assertGetSingleCharFromFileAtPosition(fileOpsStrategy, expectedCharArray, charPositionIndexArray, i);
        }
    }

    public static void assertGetSingleCharFromFileAtPosition(FileOpsStrategy fileOpsStrategy, String[] expectedCharArray, int[] charPositionIndexArray, int index) throws Exception {
        if(index != 0 && index != expectedCharArray.length-1) assertEquals(expectedCharArray[index].charAt(0), fileOpsStrategy.getCharFromFileAtPosition(charPositionIndexArray[index]));
        else {
            FunctionalInterfaceVoidReturn fi = () -> { fileOpsStrategy.getCharFromFileAtPosition(charPositionIndexArray[index]); };
            assertThrows(Exception.class, fi::run);
        }
    }

    public static void assertIsCharExistFromFileAtPosition(FileOpsStrategy fileOpsStrategy, int[] charPositionIndexArray) {
        for (int i = 0; i < charPositionIndexArray.length; i++) {
            assertIsSingleCharExistFromFileAtPosition(fileOpsStrategy, charPositionIndexArray, i);
        }
    }

    public static void assertIsSingleCharExistFromFileAtPosition(FileOpsStrategy fileOpsStrategy, int[] charPositionIndexArray, int index) {
        if(index != 0 && index != charPositionIndexArray.length-1) assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(charPositionIndexArray[index]));
        else assertFalse(fileOpsStrategy.isCharExistFromFileAtPosition(charPositionIndexArray[index]));
    }

    public void assertGetSingleNumberOfBitsFromFileAtPosition(FileOpsStrategy fileOpsStrategy, MixMachine mixMachine, String[] expectedCharStringArray, int firstValidCharIndex, int lastValidCharIndex) throws Exception {
        for (int i = firstValidCharIndex; i < lastValidCharIndex; i++) {
            assertGetSingleSuccessfulNumberOfBitsFromFileAtPosition(fileOpsStrategy, mixMachine, expectedCharStringArray, i, lastValidCharIndex);
        }
    }

    public void assertGetSingleSuccessfulNumberOfBitsFromFileAtPosition(FileOpsStrategy fileOpsStrategy, MixMachine mixMachine, String[] expectedCharStringArray, int expectedArrayIndex, int lastValidCharIndex) throws Exception {
        String expectedFileWholeContent = concatStringArray(expectedCharStringArray, expectedArrayIndex , lastValidCharIndex);
        int requestedAmountOfBits = getBitAmount(expectedCharStringArray, expectedArrayIndex, lastValidCharIndex, mixMachine);
        int actualPosition = getBytePosition(expectedCharStringArray, expectedArrayIndex);
        String actualFileWholeContent = fileOpsStrategy.getNumberOfCharsBasedOnNumberOfBitsFromFileAtPosition(requestedAmountOfBits, actualPosition);
        assertEquals(expectedFileWholeContent, actualFileWholeContent);
    }

    public int getBytePosition(String[] expectedCharStringArray, int expectedArrayIndex) {
        String skippedFileContent = concatStringArray(expectedCharStringArray, 1, expectedArrayIndex-1);
        byte[] bytes = skippedFileContent.getBytes(Charset.forName("UTF-8"));
        return bytes.length;
    }

    public static int getBitAmount(String[] expectedCharStringArrayOrFileStringArray, int startStringArrayIndex, int endStringArrayIndex, MixMachine mixMachine) throws Exception {
        String wholeFileContent = concatStringArray(expectedCharStringArrayOrFileStringArray, 0, expectedCharStringArrayOrFileStringArray.length-1);
        String fragmentOfFileContent = wholeFileContent.substring(startStringArrayIndex, endStringArrayIndex+1);
        if(mixMachine.getEncoding() == Encoding.JAVA) return fragmentOfFileContent.getBytes(Charset.forName("UTF-8")).length*MixMachine.JAVA_DEFAULT_AMOUNT_OF_BITS;
        if(mixMachine.getEncoding() == Encoding.MIX) return mixMachine.getBinaryString(fragmentOfFileContent).length();
        throw new Exception("Bad encoding. Mix machine's encoding is neither " + Encoding.JAVA + " nor " + Encoding.MIX + ". Your mix machine's encoding: " + mixMachine.getEncoding());
    }

    public static int getByteAmount(String[] fileStringArray, int startStringArrayIndex, int endStringArrayIndex, MixMachine mixMachine) throws Exception {
        if(mixMachine.getEncoding() == Encoding.JAVA) return getBitAmount(fileStringArray, startStringArrayIndex, endStringArrayIndex, mixMachine)/mixMachine.getAmountOfBits(); //MixMachine.JAVA_DEFAULT_AMOUNT_OF_BITS;
        if(mixMachine.getEncoding() == Encoding.MIX) return getBitAmount(fileStringArray, startStringArrayIndex, endStringArrayIndex, mixMachine)/mixMachine.getAmountOfBits(); //MixMachine.MIX_DEFAULT_AMOUNT_OF_BITS;
        throw new Exception("Bad encoding. Mix machine's encoding is neither " + Encoding.JAVA + " nor " + Encoding.MIX + ". Your mix machine's encoding: " + mixMachine.getEncoding());
    }

    public static String concatStringArray(String[] array, int startIndex, int endIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startIndex; i < endIndex+1 && i < array.length; i++) {
            if(array[i] != null) stringBuilder.append(array[i]);
            else stringBuilder.append('\0');
        }
        return stringBuilder.toString();
    }
}
