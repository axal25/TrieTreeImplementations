package agh.jo.knuth.mix.machine;

import agh.jo.func.intf.FunctionalInterfaceVoidReturn;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MixEncodingTest {

    @Test
    @Order(1)
    @DisplayName("MixEncoding constructor 1")
    void MixEncoding1() throws Exception {
        MixEncoding mixEncoding = new MixEncoding(';', ' ');
        assertAsciiMixEncoding(mixEncoding);
    }

    public static void assertAsciiMixEncoding(MixEncoding mixEncoding) throws Exception {
        assertNotNull(mixEncoding);

        assertEquals(';', mixEncoding.getCharEOF());
        assertEquals(' ', mixEncoding.getCharEOK());

        assertEquals(30, MixEncoding.statCharCodes.get('0'));
        assertEquals(30, mixEncoding.charToInt(' '));
        assertEquals(MixEncoding.statCharCodes.get('0'), mixEncoding.charToInt(' '));

        assertEquals(31, MixEncoding.statCharCodes.get('1'));
        assertEquals(31, mixEncoding.charToInt(';'));
        assertEquals(MixEncoding.statCharCodes.get('1'), mixEncoding.charToInt(';'));

        assertEquals(0, MixEncoding.statCharCodes.get(' '));
        assertEquals(0, mixEncoding.charToInt('0'));
        assertEquals(MixEncoding.statCharCodes.get(' '), mixEncoding.charToInt('0'));

        assertEquals(53, MixEncoding.statCharCodes.get(';'));
        assertEquals(53, mixEncoding.charToInt('1'));
        assertEquals(MixEncoding.statCharCodes.get(';'), mixEncoding.charToInt('1'));
    }

    @Test
    @Order(2)
    @DisplayName("MixEncoding constructor 2")
    void MixEncoding2() throws Exception {
        MixEncoding mixEncoding = new MixEncoding(';', ' ');
        assertGreekMixEncoding(mixEncoding);
    }

    public static void assertGreekMixEncoding(MixEncoding mixEncoding) throws Exception {
        char theta, Phi, Pi;
        int thetaCode, PhiCode, PiCode, expected_thetaMixCode, expectedPhiMixCode, expectedPiMixCode;
        thetaCode = 952;
        PhiCode = 934;
        PiCode = 928;
        theta = (char) thetaCode;
        Pi = (char) PiCode;
        Phi = (char) PhiCode;
        expected_thetaMixCode = 10;
        expectedPhiMixCode = 20;
        expectedPiMixCode = 21;

        System.out.println("theta: " + theta + " == (int) " + thetaCode + " -> expectedMix.theta: " + expected_thetaMixCode + " vs. actualMixEncoding.theta: " + mixEncoding.charToInt(theta));
        System.out.println("Phi: " + Phi + " == (int) " + PhiCode + " -> expectedMix.Phi: " + expectedPhiMixCode + " vs. actualMixEncoding.Phi: " + mixEncoding.charToInt(Phi));
        System.out.println("Pi: " + Pi + " == (int) " + PiCode + " -> expectedMix.Pi: " + expectedPiMixCode + " vs. actualMixEncoding.Pi: " + mixEncoding.charToInt(Pi));

        assertEquals(expected_thetaMixCode, mixEncoding.charToInt(theta));
        assertEquals(expectedPhiMixCode, mixEncoding.charToInt(Phi));
        assertEquals(expectedPiMixCode, mixEncoding.charToInt(Pi));

        assertEquals(expected_thetaMixCode, mixEncoding.charToInt(theta));
        assertEquals(expectedPhiMixCode, mixEncoding.charToInt(Phi));
        assertEquals(expectedPiMixCode, mixEncoding.charToInt(Pi));
    }
}
