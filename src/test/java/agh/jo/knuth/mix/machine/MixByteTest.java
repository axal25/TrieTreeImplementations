package agh.jo.knuth.mix.machine;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MixByteTest {

    @Test
    @Order(1)
    @DisplayName("MixByte constructor - bits: 5 / value: 12")
    void MixByte1() throws Exception {
        int amountOfBits = 5;
        int intValue = 12;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
    }

    @Test
    @Order(2)
    @DisplayName("MixByte constructor - bits: 5 / value: 31")
    void MixByte2() throws Exception {
        int amountOfBits = 5;
        int intValue = 31;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
    }

    @Test
    @Order(3)
    @DisplayName("MixByte constructor - bits: 6 / value: 32")
    void MixByte3() throws Exception {
        int amountOfBits = 6;
        int intValue = 32;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
    }

    @Test
    @Order(4)
    @DisplayName("MixByte constructor - bits: 9 / value: 300")
    void MixByt4() throws Exception {
        int amountOfBits = 9;
        int intValue = 300;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
    }

    @Test
    @Order(5)
    @DisplayName("calculateAmountOfBytes - bits: 5 / value: 12")
    void calculateAmountOfBytes1() throws Exception {
        int amountOfBits = 5;
        int intValue = 12;
        int expectedAmountOfBytes = 1;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        assertEquals(expectedAmountOfBytes, mixByte.calculateAmountOfBytes());
    }

    @Test
    @Order(6)
    @DisplayName("calculateAmountOfBytes - bits: 5 / value: 31")
    void calculateAmountOfBytes2() throws Exception {
        int amountOfBits = 5;
        int intValue = 31;
        int expectedAmountOfBytes = 1;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        assertEquals(expectedAmountOfBytes, mixByte.calculateAmountOfBytes());
    }

    @Test
    @Order(7)
    @DisplayName("calculateAmountOfBytes - bits: 6 / value: 32")
    void calculateAmountOfBytes3() throws Exception {
        int amountOfBits = 6;
        int intValue = 32;
        int expectedAmountOfBytes = 1;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        assertEquals(expectedAmountOfBytes, mixByte.calculateAmountOfBytes());
    }

    @Test
    @Order(8)
    @DisplayName("calculateAmountOfBytes - bits: 9 / value: 300")
    void calculateAmountOfBytes4() throws Exception {
        int amountOfBits = 9;
        int intValue = 300;
        int expectedAmountOfBytes = 2;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        assertEquals(expectedAmountOfBytes, mixByte.calculateAmountOfBytes());
    }

    @Test
    @Order(9)
    @DisplayName("intToJavaBitsInString - bits: 5 / value: 12")
    void intToJavaBitsInString1() throws Exception {
        int amountOfBits = 5;
        int intValue = 12;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String expectedBytes = "00000000000000000000000000001100";
        assertEquals(expectedBytes.substring(expectedBytes.length() - MixByte.MAX_UTF8_CHAR_BIT_LENGTH), mixByte.intToJavaBitsInString(intValue));
    }

    @Test
    @Order(10)
    @DisplayName("intToJavaBitsInString - bits: 5 / value: 31")
    void intToJavaBitsInString2() throws Exception {
        int amountOfBits = 5;
            int intValue = 31;
            MixByte mixByte = new MixByte(amountOfBits, intValue);
            assertNotNull(mixByte);
            assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String expectedBytes = "00000000000000000000000000011111";
        assertEquals(expectedBytes.substring(expectedBytes.length() - MixByte.MAX_UTF8_CHAR_BIT_LENGTH), mixByte.intToJavaBitsInString(intValue));
    }

    @Test
    @Order(11)
    @DisplayName("intToJavaBitsInString - bits: 6 / value: 32")
    void intToJavaBitsInString3() throws Exception {
        int amountOfBits = 6;
            int intValue = 32;
            MixByte mixByte = new MixByte(amountOfBits, intValue);
            assertNotNull(mixByte);
            assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String expectedBytes = "00000000000000000000000000100000";
        assertEquals(expectedBytes.substring(expectedBytes.length() - MixByte.MAX_UTF8_CHAR_BIT_LENGTH), mixByte.intToJavaBitsInString(intValue));
    }

    @Test
    @Order(12)
    @DisplayName("intToJavaBitsInString - bits: 9 / value: 300")
    void intToJavaBitsInString4() throws Exception {
        int amountOfBits = 9;
        int intValue = 300;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String expectedBytes = "00000000000000000000000100101100";
        assertEquals(expectedBytes.substring(expectedBytes.length() - MixByte.MAX_UTF8_CHAR_BIT_LENGTH), mixByte.intToJavaBitsInString(intValue));
    }

    @Test
    @Order(13)
    @DisplayName("javaBitsToMixBitsInString - bits: 5 / value: 12")
    void javaBitsToMixBitsInString1() throws Exception {
        int amountOfBits = 5;
            int intValue = 12;
            MixByte mixByte = new MixByte(amountOfBits, intValue);
            assertNotNull(mixByte);
            assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
            String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
            assertEquals("00000000000000000000000000001100", javaBitsInString);
            assertEquals("01100", mixByte.javaBitsToMixBitsInString(javaBitsInString));
    }

    @Test
    @Order(14)
    @DisplayName("javaBitsToMixBitsInString - bits: 5 / value: 31")
    void javaBitsToMixBitsInString2() throws Exception {
        int amountOfBits = 5;
        int intValue = 31;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000011111", javaBitsInString);
        assertEquals("11111", mixByte.javaBitsToMixBitsInString(javaBitsInString));
    }

    @Test
    @Order(15)
    @DisplayName("javaBitsToMixBitsInString - bits: 6 / value: 32")
    void javaBitsToMixBitsInString3() throws Exception {
        int amountOfBits = 6;
        int intValue = 32;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000100000", javaBitsInString);
        assertEquals("100000", mixByte.javaBitsToMixBitsInString(javaBitsInString));
    }

    @Test
    @Order(16)
    @DisplayName("javaBitsToMixBitsInString - bits: 9 / value: 300")
    void javaBitsToMixBitsInString4() throws Exception {
        int amountOfBits = 9;
        int intValue = 300;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000100101100", mixByte.intToJavaBitsInString(intValue));
        assertEquals("100101100", mixByte.javaBitsToMixBitsInString(javaBitsInString));
    }

    @Test
    @Order(17)
    @DisplayName("flushRightMixBitsInString - bits: 5 / value: 12")
    void flushRightMixBitsInString1() throws Exception {
        int amountOfBits = 5;
        int intValue = 12;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000001100", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("01100", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00001100", flushedRightMixBitsInStrings[0]);
    }

    @Test
    @Order(18)
    @DisplayName("flushRightMixBitsInString - bits: 5 / value: 31")
    void flushRightMixBitsInString2() throws Exception {
        int amountOfBits = 5;
        int intValue = 31;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000011111", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("11111", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00011111", flushedRightMixBitsInStrings[0]);
    }

    @Test
    @Order(19)
    @DisplayName("flushRightMixBitsInString - bits: 6 / value: 32")
    void flushRightMixBitsInString3() throws Exception {
        int amountOfBits = 6;
        int intValue = 32;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000100000", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("100000", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00100000", flushedRightMixBitsInStrings[0]);
    }

    @Test
    @Order(20)
    @DisplayName("flushRightMixBitsInString - bits: 9 / value: 300")
    void flushRightMixBitsInString4() throws Exception {
        int amountOfBits = 9;
        int intValue = 300;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
//            assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000100101100", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("100101100", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00000001", flushedRightMixBitsInStrings[0]);
        assertEquals("00101100", flushedRightMixBitsInStrings[1]);
    }

    @Test
    @Order(21)
    @DisplayName("flushedRightMixBytesInStringToByteArray - bits: 5 / value: 12")
    void flushedRightMixBytesInStringToByteArray1() throws Exception {
        int amountOfBits = 5;
        int intValue = 12;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000001100", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("01100", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00001100", flushedRightMixBitsInStrings[0]);
        byte[] bytes = mixByte.flushedRightMixBytesInStringToByteArray(flushedRightMixBitsInStrings);
        assertEquals("00001100", String.format("%0" + 8 + "d", Integer.parseInt(Integer.toBinaryString(bytes[0]))));
        assertEquals("00001100", mixByte.byteToBinaryString(bytes[0]));
        assertEquals("00001100", mixByte.getByteToBinaryString(0));
        assertEquals("00001100", mixByte.getBytesToBinaryString());
        String mixBinaryString = mixByte.getBytesToMixBinaryString();
        String expectedMixBinaryString = "01100";
        assertEquals(expectedMixBinaryString, mixBinaryString);
        assertEquals("MixByte{amountOfBits=" + amountOfBits + ", bytes=[" + intValue + "]} = " + expectedMixBinaryString, mixByte.toString());
        System.out.println(mixByte.toString());
    }

    @Test
    @Order(22)
    @DisplayName("flushedRightMixBytesInStringToByteArray - bits: 5 / value: 31")
    void flushedRightMixBytesInStringToByteArray2() throws Exception {
        int amountOfBits = 5;
        int intValue = 31;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000011111", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("11111", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00011111", flushedRightMixBitsInStrings[0]);
        byte[] bytes = mixByte.flushedRightMixBytesInStringToByteArray(flushedRightMixBitsInStrings);
        assertEquals("00011111", String.format("%0" + 8 + "d", Integer.parseInt(Integer.toBinaryString(bytes[0]))));
        assertEquals("00011111", mixByte.byteToBinaryString(bytes[0]));
        assertEquals("00011111", mixByte.getByteToBinaryString(0));
        assertEquals("00011111", mixByte.getBytesToBinaryString());
        String mixBinaryString = mixByte.getBytesToMixBinaryString();
        String expectedMixBinaryString = "11111";
        assertEquals(expectedMixBinaryString, mixBinaryString);
        assertEquals("MixByte{amountOfBits=" + amountOfBits + ", bytes=[" + intValue + "]} = " + expectedMixBinaryString, mixByte.toString());
        System.out.println(mixByte.toString());
    }

    @Test
    @Order(23)
    @DisplayName("flushedRightMixBytesInStringToByteArray - bits: 6 / value: 32")
    void flushedRightMixBytesInStringToByteArray3() throws Exception {
        int amountOfBits = 6;
        int intValue = 32;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000000100000", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("100000", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00100000", flushedRightMixBitsInStrings[0]);
        byte[] bytes = mixByte.flushedRightMixBytesInStringToByteArray(flushedRightMixBitsInStrings);
        assertEquals("00100000", String.format("%0" + 8 + "d", Integer.parseInt(Integer.toBinaryString(bytes[0]))));
        assertEquals("00100000", mixByte.byteToBinaryString(bytes[0]));
        assertEquals("00100000", mixByte.getByteToBinaryString(0));
        assertEquals("00100000", mixByte.getBytesToBinaryString());
        String mixBinaryString = mixByte.getBytesToMixBinaryString();
        String expectedMixBinaryString = "100000";
        assertEquals(expectedMixBinaryString, mixBinaryString);
        assertEquals("MixByte{amountOfBits=" + amountOfBits + ", bytes=[" + intValue + "]} = " + expectedMixBinaryString, mixByte.toString());
        System.out.println(mixByte.toString());
    }

    @Test
    @Order(24)
    @DisplayName("flushedRightMixBytesInStringToByteArray - bits: 9 / value: 300")
    void flushedRightMixBytesInStringToByteArray4() throws Exception {
        int amountOfBits = 9;
        int intValue = 300;
        MixByte mixByte = new MixByte(amountOfBits, intValue);
        assertNotNull(mixByte);
        assertEquals(amountOfBits, mixByte.getAmountOfBits());
        assertNotNull(mixByte.getBytes());
        String javaBitsInString = mixByte.intToJavaBitsInString(intValue);
        assertEquals("00000000000000000000000100101100", javaBitsInString);
        String mixBitsInString = mixByte.javaBitsToMixBitsInString(javaBitsInString);
        assertEquals("100101100", mixBitsInString);
        String[] flushedRightMixBitsInStrings = mixByte.flushRightMixBitsInString(mixBitsInString);
        assertEquals("00000001", flushedRightMixBitsInStrings[0]);
        assertEquals("00101100", flushedRightMixBitsInStrings[1]);
        byte[] bytes = mixByte.flushedRightMixBytesInStringToByteArray(flushedRightMixBitsInStrings);
        assertEquals("00000001", String.format("%0" + 8 + "d", Integer.parseInt(Integer.toBinaryString(bytes[0]))));
        assertEquals("00101100", String.format("%0" + 8 + "d", Integer.parseInt(Integer.toBinaryString(bytes[1]))));
        assertEquals("00000001", mixByte.byteToBinaryString(bytes[0]));
        assertEquals("00101100", mixByte.byteToBinaryString(bytes[1]));
        assertEquals("00000001", mixByte.getByteToBinaryString(0));
        assertEquals("00101100", mixByte.getByteToBinaryString(1));
        assertEquals("0000000100101100", mixByte.getBytesToBinaryString());
        String mixBinaryString = mixByte.getBytesToMixBinaryString();
        String expectedMixBinaryString = "100101100";
        assertEquals(expectedMixBinaryString, mixBinaryString);
        assertEquals("MixByte{amountOfBits=" + amountOfBits + ", bytes=[" + bytes[0] + ", " + bytes[1] + "]} = " + expectedMixBinaryString, mixByte.toString());
        System.out.println(mixByte.toString());
    }
}
