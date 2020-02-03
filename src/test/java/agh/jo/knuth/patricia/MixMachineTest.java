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
        MixMachine mixMachine1 = new MixMachine(Encoding.JAVA, 8);
        MixMachine mixMachine2 = new MixMachine(Encoding.JAVA);

        assertEquals(Encoding.JAVA, mixMachine1.getEncoding());
        assertEquals(8, mixMachine1.getAmountOfBits());
        assertNull(mixMachine1.getMixEncoding());

        assertEquals(Encoding.JAVA, mixMachine2.getEncoding());
        assertEquals(8, mixMachine2.getAmountOfBits());
        assertNull(mixMachine2.getMixEncoding());

        assertEquals(mixMachine1.toString(), mixMachine2.toString());
    }
}
