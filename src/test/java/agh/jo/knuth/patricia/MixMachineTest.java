package agh.jo.knuth.patricia;

import agh.jo.func.intf.FunctionalInterfaceVoidReturn;
import agh.jo.knuth.mix.machine.MixEncoding;
import agh.jo.knuth.mix.machine.MixEncodingTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MixMachineTest {

    @Test
    @Order(1)
    @DisplayName("MixEncoding constructor 1")
    void MixMachine1() throws Exception {
        MixMachine mixMachine = new MixMachine(Encoding.MIX, 5, ';', ' ');
        MixEncodingTest.assertAsciiMixEncoding(mixMachine.getMixEncoding());
        MixEncodingTest.assertGreekMixEncoding(mixMachine.getMixEncoding());
    }

    @Test
    @Order(2)
    @DisplayName("MixEncoding constructor 2")
    void MixMachine2() throws Exception {
        MixMachine mixMachine = new MixMachine(Encoding.MIX, ';', ' ');
        MixEncodingTest.assertAsciiMixEncoding(mixMachine.getMixEncoding());
        MixEncodingTest.assertGreekMixEncoding(mixMachine.getMixEncoding());
    }

    @Test
    @Order(3)
    @DisplayName("MixEncoding constructor 3")
    void MixMachine3() throws Exception {
        FunctionalInterfaceVoidReturn functionalInterfaceVoidReturn1 = () -> {
            MixMachine mixMachine = new MixMachine(Encoding.MIX, 5);
        };
        FunctionalInterfaceVoidReturn functionalInterfaceVoidReturn2 = () -> {
            MixMachine mixMachine = new MixMachine(Encoding.MIX);
        };
        assertThrows(Exception.class, functionalInterfaceVoidReturn1::run);
        assertThrows(Exception.class, functionalInterfaceVoidReturn2::run);
    }

    @Test
    @Order(4)
    @DisplayName("MixEncoding constructor 4")
    void MixMachine4() throws Exception {
        Encoding expectedEncoding = Encoding.JAVA;
        int expectedAmountOfBits = 8;
        MixMachine mixMachine1 = new MixMachine(expectedEncoding, expectedAmountOfBits);
        MixMachine mixMachine2 = new MixMachine(expectedEncoding);

        assertEquals(expectedEncoding, mixMachine1.getEncoding());
        assertEquals(expectedAmountOfBits, mixMachine1.getAmountOfBits());
        assertNull(mixMachine1.getMixEncoding());
        String expectedBinaryString = getBinaryString("a".getBytes(), expectedAmountOfBits);
        assertEquals(expectedBinaryString, getBinaryString(mixMachine1.getCharBytes('a'), expectedAmountOfBits));
        assertEquals(expectedBinaryString, mixMachine1.getBinaryString('a'));

        assertEquals(expectedEncoding, mixMachine2.getEncoding());
        assertEquals(expectedAmountOfBits, mixMachine2.getAmountOfBits());
        assertNull(mixMachine2.getMixEncoding());
        assertEquals(expectedBinaryString, getBinaryString(mixMachine2.getCharBytes('a'), expectedAmountOfBits));
        assertEquals(expectedBinaryString, mixMachine2.getBinaryString('a'));

        assertEquals(mixMachine1.toString(), mixMachine2.toString());
    }

    @Test
    @Order(4)
    @DisplayName("MixEncoding constructor 5")
    void MixMachine5() throws Exception {
        Encoding expectedEncoding = Encoding.MIX;
        int expectedAmountOfBits1 = 6;
        int expectedAmountOfBits2 = MixMachine.MIX_DEFAULT_AMOUNT_OF_BITS;
        char expectedEOFchar = ';';
        char expectedEOKchar = ' ';
        MixMachine mixMachine1 = new MixMachine(expectedEncoding, expectedAmountOfBits1, expectedEOFchar, expectedEOKchar);
        MixMachine mixMachine2 = new MixMachine(expectedEncoding, expectedEOFchar, expectedEOKchar);

        assertEquals(expectedEOFchar, mixMachine1.getMixEncoding().getCharEOF());
        assertEquals(expectedEOKchar, mixMachine1.getMixEncoding().getCharEOK());
        assertEquals(expectedEncoding, mixMachine1.getEncoding());
        assertEquals(expectedAmountOfBits1, mixMachine1.getAmountOfBits());
        assertNotNull(mixMachine1.getMixEncoding());
        int expectedMixCharCode = 1; // 'A'
        String expectedBinaryString1 = getBinaryString(new StringBuilder().append((char) expectedMixCharCode).toString().getBytes(), expectedAmountOfBits1);
        assertEquals(
                expectedBinaryString1,
                getBinaryString(mixMachine1.getCharBytes(expectedMixCharCode), expectedAmountOfBits1)
        );
        assertEquals(
                expectedBinaryString1,
                mixMachine1.getBinaryString(expectedMixCharCode)
        );

        assertEquals(expectedEOFchar, mixMachine2.getMixEncoding().getCharEOF());
        assertEquals(expectedEOKchar, mixMachine2.getMixEncoding().getCharEOK());
        assertEquals(expectedEncoding, mixMachine2.getEncoding());
        assertEquals(expectedAmountOfBits2, mixMachine2.getAmountOfBits());
        assertNotNull(mixMachine2.getMixEncoding());
        String expectedBinaryString2 = getBinaryString(new StringBuilder().append((char) expectedMixCharCode).toString().getBytes(), expectedAmountOfBits2);
        assertEquals(
                expectedBinaryString2,
                getBinaryString(mixMachine2.getCharBytes(expectedMixCharCode), expectedAmountOfBits2)
        );
        assertEquals(
                expectedBinaryString2,
                mixMachine2.getBinaryString(expectedMixCharCode)
        );

        assertNotEquals(mixMachine1.toString(), mixMachine2.toString());
    }

    public static String getBinaryString(byte[] bytes, int amountOfBits) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte singleByte:bytes) {
            String binaryString = Integer.toBinaryString((int) singleByte);
            int binaryInt = Integer.parseInt(binaryString);
            binaryString = String.format("%0" + amountOfBits + "d", binaryInt);
            stringBuilder.append(binaryString);
        }
        return stringBuilder.toString();
    }
}
