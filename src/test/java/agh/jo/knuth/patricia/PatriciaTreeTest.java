package agh.jo.knuth.patricia;

import agh.jo.func.intf.FunctionalInterfaceVoidReturn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatriciaTreeTest {

    @Test
    @Order(1)
    @DisplayName("getStringFromFileAtPositionContinuousAccess")
    void getStringAtPositionContinuousAccess() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertEquals(null, patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(-1));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(0));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(1));
        assertEquals("I", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(2));
        assertEquals("S", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(3));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(4));
        assertEquals("I", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(5));
        assertEquals("S", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(6));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(7));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(8));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(9));
        assertEquals("E", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(10));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(11));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(12));
        assertEquals("O", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(13));
        assertEquals("U", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(14));
        assertEquals("S", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(15));
        assertEquals("E", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(16));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(17));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(18));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(19));
        assertEquals("A", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(20));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(21));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(22));
        assertEquals("J", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(23));
        assertEquals("A", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(24));
        assertEquals("C", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(25));
        assertEquals("K", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(26));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(27));
        assertEquals("B", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(28));
        assertEquals("U", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(29));
        assertEquals("I", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(30));
        assertEquals("L", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(31));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(32));
        assertEquals(";", patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(33));
        assertEquals(null, patriciaTree.getFileOperations().getStringFromFileAtPositionContinuousAccess(34));
    }

    @Test
    @Order(2)
    @DisplayName("getStringFromFileAtPositionRandomAccess")
    void getStringAtPositionRandomAccess() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertEquals(null, patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(-1));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(0));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(1));
        assertEquals("I", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(2));
        assertEquals("S", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(3));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(4));
        assertEquals("I", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(5));
        assertEquals("S", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(6));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(7));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(8));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(9));
        assertEquals("E", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(10));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(11));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(12));
        assertEquals("O", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(13));
        assertEquals("U", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(14));
        assertEquals("S", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(15));
        assertEquals("E", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(16));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(17));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(18));
        assertEquals("H", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(19));
        assertEquals("A", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(20));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(21));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(22));
        assertEquals("J", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(23));
        assertEquals("A", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(24));
        assertEquals("C", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(25));
        assertEquals("K", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(26));
        assertEquals(" ", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(27));
        assertEquals("B", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(28));
        assertEquals("U", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(29));
        assertEquals("I", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(30));
        assertEquals("L", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(31));
        assertEquals("T", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(32));
        assertEquals(";", patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(33));
        assertEquals(null, patriciaTree.getFileOperations().getStringFromFileAtPositionRandomAccess(34));
    }

    @Test
    @Order(3)
    @DisplayName("getCharFromFileAtPosition")
    void getCharAtPosition() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        FunctionalInterfaceVoidReturn fi = () -> { patriciaTree.getFileOperations().getCharFromFileAtPosition(-1); };
        assertThrows(Exception.class, fi::run);
        assertEquals('T', patriciaTree.getFileOperations().getCharFromFileAtPosition(0));
        assertEquals('H', patriciaTree.getFileOperations().getCharFromFileAtPosition(1));
        assertEquals('I', patriciaTree.getFileOperations().getCharFromFileAtPosition(2));
        assertEquals('S', patriciaTree.getFileOperations().getCharFromFileAtPosition(3));
        assertEquals(' ', patriciaTree.getFileOperations().getCharFromFileAtPosition(4));
        assertEquals('I', patriciaTree.getFileOperations().getCharFromFileAtPosition(5));
        assertEquals('S', patriciaTree.getFileOperations().getCharFromFileAtPosition(6));
        assertEquals(' ', patriciaTree.getFileOperations().getCharFromFileAtPosition(7));
        assertEquals('T', patriciaTree.getFileOperations().getCharFromFileAtPosition(8));
        assertEquals('H', patriciaTree.getFileOperations().getCharFromFileAtPosition(9));
        assertEquals('E', patriciaTree.getFileOperations().getCharFromFileAtPosition(10));
        assertEquals(' ', patriciaTree.getFileOperations().getCharFromFileAtPosition(11));
        assertEquals('H', patriciaTree.getFileOperations().getCharFromFileAtPosition(12));
        assertEquals('O', patriciaTree.getFileOperations().getCharFromFileAtPosition(13));
        assertEquals('U', patriciaTree.getFileOperations().getCharFromFileAtPosition(14));
        assertEquals('S', patriciaTree.getFileOperations().getCharFromFileAtPosition(15));
        assertEquals('E', patriciaTree.getFileOperations().getCharFromFileAtPosition(16));
        assertEquals(' ', patriciaTree.getFileOperations().getCharFromFileAtPosition(17));
        assertEquals('T', patriciaTree.getFileOperations().getCharFromFileAtPosition(18));
        assertEquals('H', patriciaTree.getFileOperations().getCharFromFileAtPosition(19));
        assertEquals('A', patriciaTree.getFileOperations().getCharFromFileAtPosition(20));
        assertEquals('T', patriciaTree.getFileOperations().getCharFromFileAtPosition(21));
        assertEquals(' ', patriciaTree.getFileOperations().getCharFromFileAtPosition(22));
        assertEquals('J', patriciaTree.getFileOperations().getCharFromFileAtPosition(23));
        assertEquals('A', patriciaTree.getFileOperations().getCharFromFileAtPosition(24));
        assertEquals('C', patriciaTree.getFileOperations().getCharFromFileAtPosition(25));
        assertEquals('K', patriciaTree.getFileOperations().getCharFromFileAtPosition(26));
        assertEquals(' ', patriciaTree.getFileOperations().getCharFromFileAtPosition(27));
        assertEquals('B', patriciaTree.getFileOperations().getCharFromFileAtPosition(28));
        assertEquals('U', patriciaTree.getFileOperations().getCharFromFileAtPosition(29));
        assertEquals('I', patriciaTree.getFileOperations().getCharFromFileAtPosition(30));
        assertEquals('L', patriciaTree.getFileOperations().getCharFromFileAtPosition(31));
        assertEquals('T', patriciaTree.getFileOperations().getCharFromFileAtPosition(32));
        assertEquals(';', patriciaTree.getFileOperations().getCharFromFileAtPosition(33));
        fi = () -> { patriciaTree.getFileOperations().getCharFromFileAtPosition(34); };
        assertThrows(Exception.class, fi::run);
    }

    @Test
    @Order(4)
    @DisplayName("isCharExistFromFileAtPosition")
    void isCharExistFromFileAtPosition() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertFalse(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(-1));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(0));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(1));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(2));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(3));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(4));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(5));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(6));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(7));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(8));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(9));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(10));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(11));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(12));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(13));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(14));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(15));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(16));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(17));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(18));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(19));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(20));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(21));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(22));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(23));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(24));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(25));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(26));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(27));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(28));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(29));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(30));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(31));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(32));
        assertTrue(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(33));
        assertFalse(patriciaTree.getFileOperations().isCharExistFromFileAtPosition(34));
    }

    @Test
    @Order(5)
    @DisplayName("getCharCode(char character)")
    void getCharCode() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertEquals(23, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(0)));
        assertEquals(8, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(1)));
        assertEquals(9, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(2)));
        assertEquals(22, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(3)));
        assertEquals(0, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(4)));
        assertEquals(9, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(5)));
        assertEquals(22, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(6)));
        assertEquals(0, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(7)));
        assertEquals(23, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(8)));
        assertEquals(8, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(9)));
        assertEquals(5, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(10)));
        assertEquals(0, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(11)));
        assertEquals(8, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(12)));
        assertEquals(16, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(13)));
        assertEquals(24, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(14)));
        assertEquals(22, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(15)));
        assertEquals(5, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(16)));
        assertEquals(0, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(17)));
        assertEquals(23, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(18)));
        assertEquals(8, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(19)));
        assertEquals(1, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(20)));
        assertEquals(23, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(21)));
        assertEquals(0, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(22)));
        assertEquals(11, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(23)));
        assertEquals(1, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(24)));
        assertEquals(3, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(25)));
        assertEquals(12, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(26)));
        assertEquals(0, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(27)));
        assertEquals(2, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(28)));
        assertEquals(24, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(29)));
        assertEquals(9, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(30)));
        assertEquals(13, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(31)));
        assertEquals(23, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(32)));
        assertEquals(31, patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(33)));
    }

    @Test
    @Order(6)
    @DisplayName("getCharBytes(int characterCode)")
    void getCharBytes() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertEquals(23, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(0)))[0]);
        assertEquals(8, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(1)))[0]);
        assertEquals(9, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(2)))[0]);
        assertEquals(22, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(3)))[0]);
        assertEquals(0, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(4)))[0]);
        assertEquals(9, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(5)))[0]);
        assertEquals(22, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(6)))[0]);
        assertEquals(0, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(7)))[0]);
        assertEquals(23, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(8)))[0]);
        assertEquals(8, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(9)))[0]);
        assertEquals(5, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(10)))[0]);
        assertEquals(0, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(11)))[0]);
        assertEquals(8, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(12)))[0]);
        assertEquals(16, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(13)))[0]);
        assertEquals(24, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(14)))[0]);
        assertEquals(22, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(15)))[0]);
        assertEquals(5, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(16)))[0]);
        assertEquals(0, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(17)))[0]);
        assertEquals(23, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(18)))[0]);
        assertEquals(8, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(19)))[0]);
        assertEquals(1, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(20)))[0]);
        assertEquals(23, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(21)))[0]);
        assertEquals(0, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(22)))[0]);
        assertEquals(11, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(23)))[0]);
        assertEquals(1, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(24)))[0]);
        assertEquals(3, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(25)))[0]);
        assertEquals(12, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(26)))[0]);
        assertEquals(0, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(27)))[0]);
        assertEquals(2, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(28)))[0]);
        assertEquals(24, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(29)))[0]);
        assertEquals(9, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(30)))[0]);
        assertEquals(13, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(31)))[0]);
        assertEquals(23, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(32)))[0]);
        assertEquals(31, patriciaTree.getMixMachine().getCharBytes(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(33)))[0]);
    }

    @Test
    @Order(7)
    @DisplayName("getBinaryString(int characterCode)")
    void getBinaryString() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertEquals("10111", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(0))));
        assertEquals("01000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(1))));
        assertEquals("01001", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(2))));
        assertEquals("10110", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(3))));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(4))));
        assertEquals("01001", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(5))));
        assertEquals("10110", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(6))));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(7))));
        assertEquals("10111", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(8))));
        assertEquals("01000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(9))));
        assertEquals("00101", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(10))));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(11))));
        assertEquals("01000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(12))));
        assertEquals("10000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(13))));
        assertEquals("11000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(14))));
        assertEquals("10110", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(15))));
        assertEquals("00101", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(16))));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(17))));
        assertEquals("10111", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(18))));
        assertEquals("01000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(19))));
        assertEquals("00001", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(20))));
        assertEquals("10111", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(21))));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(22))));
        assertEquals("01011", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(23))));
        assertEquals("00001", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(24))));
        assertEquals("00011", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(25))));
        assertEquals("01100", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(26))));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(27))));
        assertEquals("00010", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(28))));
        assertEquals("11000", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(29))));
        assertEquals("01001", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(30))));
        assertEquals("01101", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(31))));
        assertEquals("10111", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(32))));
        assertEquals("11111", patriciaTree.getMixMachine().getBinaryString(patriciaTree.getMixMachine().getCharCode(patriciaTree.getFileOperations().getCharFromFileAtPosition(33))));
    }

    @Test
    @Order(8)
    @DisplayName("getBinaryString(String charChain)")
    void getBinaryStringOfCharacterChain() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);

        // THIS
        // 10111 01000 01001 10110
        StringBuilder stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(0))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(1))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(2))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(3));
        assertEquals("10111010000100110110", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        //   (SPACE)
        // 00000
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(4));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        // IS
        // 01001 10110
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(5))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(6));
        assertEquals("0100110110", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        //   (SPACE)
        // 00000
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(7));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        // THE
        // 10111 01000 00101
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(8))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(9))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(10));
        assertEquals("101110100000101", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        //   (SPACE)
        // 00000
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(11));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        // HOUSE
        // 01000 10000 11000 10110 00101
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(12))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(13))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(14))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(15))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(16));
        assertEquals("0100010000110001011000101", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        //   (SPACE)
        // 00000
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(17));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        // THAT
        // 10111 01000 00001 10111
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(18))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(19))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(20))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(21));
        assertEquals("10111010000000110111", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        //   (SPACE)
        // 00000
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(22));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        // JACK
        // 01011 00001 00011 01100
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(23))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(24))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(25))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(26));
        assertEquals("01011000010001101100", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        //   (SPACE)
        // 00000
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(27));
        assertEquals("00000", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        // BUILT
        // 00010 11000 01001 01101 10111
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(28))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(29))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(30))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(31))
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(32));
        assertEquals("0001011000010010110110111", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));

        // ;
        // 11111
        stringBuilder = new StringBuilder()
                .append(patriciaTree.getFileOperations().getCharFromFileAtPosition(33));
        assertEquals("11111", patriciaTree.getMixMachine().getBinaryString(stringBuilder.toString()));
    }

    @Test
    @Order(9)
    @DisplayName("getNumberOfBitsFromFileAtPosition(int amountOfBit, int position)")
    void getNumberOfBitsFromFileAtPosition() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertEquals("1011101000010011011000000", patriciaTree.getNumberOfBitsFromFileAtPosition(5*5, 0));
        assertEquals("1011101000010011011000", patriciaTree.getNumberOfBitsFromFileAtPosition(5*5-3, 0));
        assertEquals("010011011000000", patriciaTree.getNumberOfBitsFromFileAtPosition(3*5, 5));
        assertEquals("10111010000010100000", patriciaTree.getNumberOfBitsFromFileAtPosition(4*5, 8));
        assertEquals("010001000011000101100010100000", patriciaTree.getNumberOfBitsFromFileAtPosition(6*5, 12));
        assertEquals("1011101000000011011100000", patriciaTree.getNumberOfBitsFromFileAtPosition(5*5, 18));
        assertEquals("0101100001000110110000000", patriciaTree.getNumberOfBitsFromFileAtPosition(5*5, 23));
        assertEquals("000101100001001011011011111111", patriciaTree.getNumberOfBitsFromFileAtPosition(6*5, 28));
    }

    @Test
    @Order(10)
    @DisplayName("PatriciaTree constructor - Knuths Example")
    void PatriciaTree() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertNotNull(patriciaTree);
        assertEquals(fileName, patriciaTree.getFileOperations().getFileName());
        assertEquals(System.getProperty("user.dir") + filePath, patriciaTree.getFileOperations().getFilePath());
        assertEquals(encoding, patriciaTree.getMixMachine().getEncoding());
        assertEquals(1, patriciaTree.getCurrentNodeId());
        assertNotNull(patriciaTree.getHeader());
        assertEquals(0, patriciaTree.getHeader().getId());
        assertEquals(0, patriciaTree.getHeader().getSkip());
        assertEquals(0, patriciaTree.getHeader().getKey());
        assertNull(patriciaTree.getHeader().getRightLink());
        assertFalse(patriciaTree.getHeader().getIsRightAncestor());
        assertNotNull(patriciaTree.getHeader().getLeftLink());
        assertEquals(patriciaTree.getHeader(), patriciaTree.getHeader().getLeftLink());
        assertTrue(patriciaTree.getHeader().getIsLeftAncestor());

        assertEquals(patriciaTree.getHeader(), patriciaTree.getHeader().getLeftLink());
        assertTrue(patriciaTree.getHeader().getIsLeftAncestor());
        String headerRepresentation = "" +
                "PatriciaNode{\n" +
                "\tid = 0,\n" +
                "\tkey = 0,\n" +
                "\tskip = 0,\n" +
                "\tleftLink.id = 0,\n" +
                "\trightLink = null\n" +
                "}";
        assertEquals(headerRepresentation, patriciaTree.getHeader().getLeftLink().toString());

        String patriciaTreeRepresentation = "" +
                "PatriciaTree{\n" +
                "\tfileOperations = FileOperations{\n" +
                "\t\tfilePath='/home/jackdaeel/IdeaProjects/TrieTreeImplementations/src/main/resources/knuth/patricia',\n" +
                "\t\tfileName='KnuthsPatriciaExample.txt'\n" +
                "\t}, \n" +
                "\tcurrentNodeId = 1, \n" +
                "\tmixMachine = MixMachine{encoding=MIX, amountOfBits=5}, \n" +
                "\talgorithmPLogic = AlgorithmP{\n" +
                "\t\towner=PatriciaTree{ !null },\n" +
                "\t\tcurrentNode.getId()=0,\n" +
                "\t\tpreviousNode.getId()=0,\n" +
                "\t\tlatestInsertedNode.getId()=0,\n" +
                "\t\tcurrentSearchWordBitIndex=0,\n" +
                "\t\tcurrentNodeBitIndex=0,\n" +
                "\t\tlongestMatchingBitStreak=-1,\n" +
                "\t\tkeyValueKeyToInsertFromFile=-1,\n" +
                "\t\tbinarySearchWordString='null',\n" +
                "\t\tbinaryStringKeyToInsertFromFile='null'\n" +
                "\t}, \n" +
                "\theader = PatriciaNode{\n" +
                "\t\tid = 0,\n" +
                "\t\tkey = 0,\n" +
                "\t\tskip = 0,\n" +
                "\t\tleftLink.id = 0,\n" +
                "\t\trightLink = null\n" +
                "\t}\n" +
                "}";
        assertEquals(patriciaTreeRepresentation, patriciaTree.toString());
    }

    @Test
    @Order(11)
    @DisplayName("isContaining")
    void isContaining() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        int amountOfCharsInsideFile = 34;
        StringBuilder wholeFileContents = new StringBuilder();
        for (int i = 0; i < amountOfCharsInsideFile; i++) {
            wholeFileContents.append(patriciaTree.getFileOperations().getCharFromFileAtPosition(i));
        }
        for (int i = 0; i < wholeFileContents.length(); i++) {
            assertTrue(patriciaTree.isContaining(wholeFileContents.substring(0, i)));
        }
    }

    @Test
    @Order(12)
    @DisplayName("findNewKeyStringFromFile")
    void findNewKeyStringFromFile() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);

        int prevKeyStartIndex = 0;
        int newKeyStartIndex = 0;
        String newKeyString = "THIS IS THE HOUSE THAT JACK BUILT;";
        assertEquals(0, newKeyStartIndex);
        assertEquals("THIS IS THE HOUSE THAT JACK BUILT;", newKeyString);

        prevKeyStartIndex = newKeyStartIndex;
        newKeyStartIndex = patriciaTree.getFileOperations().findNextWordStartIndex(newKeyStartIndex);
        assertEquals(prevKeyStartIndex + 5, newKeyStartIndex);
        newKeyString = patriciaTree.getFileOperations().findNextWordStringFromFile(newKeyStartIndex);
        assertEquals("IS THE HOUSE THAT JACK BUILT;", newKeyString);

        prevKeyStartIndex = newKeyStartIndex;
        newKeyStartIndex = patriciaTree.getFileOperations().findNextWordStartIndex(newKeyStartIndex);
        assertEquals(prevKeyStartIndex + 3, newKeyStartIndex);
        newKeyString = patriciaTree.getFileOperations().findNextWordStringFromFile(newKeyStartIndex);
        assertEquals("THE HOUSE THAT JACK BUILT;", newKeyString);

        prevKeyStartIndex = newKeyStartIndex;
        newKeyStartIndex = patriciaTree.getFileOperations().findNextWordStartIndex(newKeyStartIndex);
        assertEquals(prevKeyStartIndex + 4, newKeyStartIndex);
        newKeyString = patriciaTree.getFileOperations().findNextWordStringFromFile(newKeyStartIndex);
        assertEquals("HOUSE THAT JACK BUILT;", newKeyString);

        prevKeyStartIndex = newKeyStartIndex;
        newKeyStartIndex = patriciaTree.getFileOperations().findNextWordStartIndex(newKeyStartIndex);
        assertEquals(prevKeyStartIndex + 6, newKeyStartIndex);
        newKeyString = patriciaTree.getFileOperations().findNextWordStringFromFile(newKeyStartIndex);
        assertEquals("THAT JACK BUILT;", newKeyString);

        prevKeyStartIndex = newKeyStartIndex;
        newKeyStartIndex = patriciaTree.getFileOperations().findNextWordStartIndex(newKeyStartIndex);
        assertEquals(prevKeyStartIndex + 5, newKeyStartIndex);
        newKeyString = patriciaTree.getFileOperations().findNextWordStringFromFile(newKeyStartIndex);
        assertEquals("JACK BUILT;", newKeyString);

        prevKeyStartIndex = newKeyStartIndex;
        newKeyStartIndex = patriciaTree.getFileOperations().findNextWordStartIndex(newKeyStartIndex);
        assertEquals(prevKeyStartIndex + 5, newKeyStartIndex);
        newKeyString = patriciaTree.getFileOperations().findNextWordStringFromFile(newKeyStartIndex);
        assertEquals("BUILT;", newKeyString);
    }

    // Needed for description of first (after initiation)
    @Test
    @Order(15)
    @DisplayName("Constructor >> insert - Knuths Example")
    void PatriciaTree2ndNode() throws Exception {
        String filePath = "/src/main/resources/knuth/patricia";
        String fileName = "KnuthsPatriciaExample.txt";
        Encoding encoding = Encoding.MIX;
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, encoding);
        assertNotNull(patriciaTree);
        assertEquals(fileName, patriciaTree.getFileOperations().getFileName());
        assertEquals(System.getProperty("user.dir") + filePath, patriciaTree.getFileOperations().getFilePath());
        assertEquals(encoding, patriciaTree.getMixMachine().getEncoding());
        assertEquals(1, patriciaTree.getCurrentNodeId());
        assertNotNull(patriciaTree.getHeader());
        assertEquals(0, patriciaTree.getHeader().getId());
        assertEquals(0, patriciaTree.getHeader().getSkip());
        assertEquals(0, patriciaTree.getHeader().getKey());
        assertNull(patriciaTree.getHeader().getRightLink());
        assertFalse(patriciaTree.getHeader().getIsRightAncestor());
        assertNotNull(patriciaTree.getHeader().getLeftLink());
        assertEquals(patriciaTree.getHeader(), patriciaTree.getHeader().getLeftLink());
        assertTrue(patriciaTree.getHeader().getIsLeftAncestor());

        assertEquals(patriciaTree.getHeader(), patriciaTree.getHeader().getLeftLink());
        assertTrue(patriciaTree.getHeader().getIsLeftAncestor());
        String headerRepresentation = "" +
                "PatriciaNode{\n" +
                "\tid = 0,\n" +
                "\tkey = 0,\n" +
                "\tskip = 0,\n" +
                "\tleftLink.id = 0,\n" +
                "\trightLink = null\n" +
                "}";
        assertEquals(headerRepresentation, patriciaTree.getHeader().getLeftLink().toString());

        String patriciaTreeRepresentation = "" +
                "PatriciaTree{\n" +
                "\tfileOperations = FileOperations{\n" +
                "\t\tfilePath='/home/jackdaeel/IdeaProjects/TrieTreeImplementations/src/main/resources/knuth/patricia',\n" +
                "\t\tfileName='KnuthsPatriciaExample.txt'\n" +
                "\t}, \n" +
                "\tcurrentNodeId = 1, \n" +
                "\tmixMachine = MixMachine{encoding=MIX, amountOfBits=5}, \n" +
                "\talgorithmPLogic = AlgorithmP{\n" +
                "\t\towner=PatriciaTree{ !null },\n" +
                "\t\tcurrentNode.getId()=0,\n" +
                "\t\tpreviousNode.getId()=0,\n" +
                "\t\tlatestInsertedNode.getId()=0,\n" +
                "\t\tcurrentSearchWordBitIndex=0,\n" +
                "\t\tcurrentNodeBitIndex=0,\n" +
                "\t\tlongestMatchingBitStreak=-1,\n" +
                "\t\tkeyValueKeyToInsertFromFile=-1,\n" +
                "\t\tbinarySearchWordString='null',\n" +
                "\t\tbinaryStringKeyToInsertFromFile='null'\n" +
                "\t}, \n" +
                "\theader = PatriciaNode{\n" +
                "\t\tid = 0,\n" +
                "\t\tkey = 0,\n" +
                "\t\tskip = 0,\n" +
                "\t\tleftLink.id = 0,\n" +
                "\t\trightLink = null\n" +
                "\t}\n" +
                "}";
//        assertEquals(patriciaTreeRepresentation, patriciaTree.toString());
        System.out.println("\nisContaining - \"THIS\"");
        assertTrue(patriciaTree.isContaining("THIS"));
        System.out.println("\nisContaining - \"THIS \"");
        assertTrue(patriciaTree.isContaining("THIS "));
        System.out.println("\nisContaining - \"IS\"");
        assertFalse(patriciaTree.isContaining("IS"));
        System.out.println("\nisContaining - \"IS \"");
        assertFalse(patriciaTree.isContaining("IS "));
        System.out.println("\nisContaining - \"THIS \"");
        assertTrue(patriciaTree.isContaining("THIS "));
        System.out.println("\nisContaining - \"IS \"");
        assertFalse(patriciaTree.isContaining("IS "));

        System.out.println("\n\n\ninsertNextKeyIntoTree - \"IS\"");
        patriciaTree.insertNextKeyIntoTree();
        System.out.println();
        assertNotNull(patriciaTree.getHeader().getLeftLink());
        assertFalse(patriciaTree.getHeader().getIsLeftAncestor());
        assertNotEquals(patriciaTree.getHeader(), patriciaTree.getHeader().getLeftLink());
        String node2Representation = "" +
                "PatriciaNode{\n" +
                "\tid = 1,\n" +
                "\tkey = 5,\n" +
                "\tskip = 0,\n" +
                "\tleftLink.id = 1,\n" +
                "\trightLink.id = 0\n" +
                "}";
//        assertEquals(node2Representation, patriciaTree.getHeader().getLeftLink().toString());
        patriciaTreeRepresentation = "" +
                "PatriciaTree{\n" +
                "\tfileOperations = FileOperations{\n" +
                "\t\tfilePath='/home/jackdaeel/IdeaProjects/TrieTreeImplementations/src/main/resources/knuth/patricia',\n" +
                "\t\tfileName='KnuthsPatriciaExample.txt'\n" +
                "\t}, \n" +
                "\tcurrentNodeId = 2, \n" +
                "\tmixMachine = MixMachine{encoding=MIX, amountOfBits=5}, \n" +
                "\talgorithmPLogic = AlgorithmP{\n" +
                "\t\towner=PatriciaTree{ !null },\n" +
                "\t\tcurrentNode.getId()=0,\n" +
                "\t\tpreviousNode.getId()=0,\n" +
                "\t\tlatestInsertedNode.getId()=1,\n" +
                "\t\tcurrentSearchWordBitIndex=0,\n" +
                "\t\tcurrentNodeBitIndex=0,\n" +
                "\t\tlongestMatchingBitStreak=0,\n" +
                "\t\tkeyValueKeyToInsertFromFile=5,\n" +
                "\t\tbinarySearchWordString='',\n" +
                "\t\tbinaryStringKeyToInsertFromFile='0100110110000001011101000001010000001000100001100010110001010000010111010000000110111000000101100001000110110000000000101100001001011011011111111'\n" +
                "\t}, \n" +
                "\theader = PatriciaNode{\n" +
                "\t\tid = 0,\n" +
                "\t\tkey = 0,\n" +
                "\t\tskip = 0,\n" +
                "\t\tleftLink = PatriciaNode{\n" +
                "\t\t\tid = 1,\n" +
                "\t\t\tkey = 5,\n" +
                "\t\t\tskip = 0,\n" +
                "\t\t\tleftLink.id = 1,\n" +
                "\t\t\trightLink.id = 0\n" +
                "\t\t},\n" +
                "\t\trightLink = null\n" +
                "\t}\n" +
                "}";
//        assertEquals(patriciaTreeRepresentation, patriciaTree.toString());
        System.out.println("\npatriciaTree.toString(): " + patriciaTree.toString());
        System.out.println("\nisContaining - \"THIS \"");
        assertTrue(patriciaTree.isContaining("THIS "));
        System.out.println("\nisContaining - \"IS \"");
        assertTrue(patriciaTree.isContaining("IS "));

        System.out.println("\n\nisContaining - \"THE \"");
        assertFalse(patriciaTree.isContaining("THE "));
        System.out.println("\n\n\ninsertNextKeyIntoTree - \"IS\"");

        System.out.println("\n\npatriciaTree.toString(): " + patriciaTree.toString());

        patriciaTree.insertNextKeyIntoTree();
        System.out.println("\n\nisContaining - \"THIS \"");
        assertTrue(patriciaTree.isContaining("THIS "));

        System.out.println("\nisContaining - \"THE \"");
        assertTrue(patriciaTree.isContaining("THE "));
        System.out.println("\nisContaining - \"IS \"");
        assertTrue(patriciaTree.isContaining("IS "));

        //header skip na -1? pozycje startowa na -1? bo Knuth ustawia j := 0, przy indeksowaniu pozycji w pliku od 1, my używamy 0
        //(this.currentSearchWordBitIndex + 1) i zmiana pozycji startowej na 0 (z -1)?
        //(this.currentSearchWordBitIndex + 1) i zmiana pozycji startowej na 0?
        //(this.currentSearchWordBitIndex + 1) i zmiana pozycji startowej na 0?
        //(this.currentSearchWordBitIndex + 1) i zmiana pozycji startowej na 0?
        patriciaTree.insertNextKeyIntoTree();
        patriciaTree.insertNextKeyIntoTree();
        patriciaTree.insertNextKeyIntoTree();
        System.out.println("\n\nisContaining - \"THIS \"");
        assertTrue(patriciaTree.isContaining("THIS "));
        System.out.println("\n\nisContaining - \"IS \"");
        assertTrue(patriciaTree.isContaining("IS "));
        System.out.println("\n\nisContaining - \"THE \"");
        assertTrue(patriciaTree.isContaining("THE "));
        System.out.println("\n\nisContaining - \"HOUSE \"");
        assertTrue(patriciaTree.isContaining("HOUSE "));
        System.out.println("\n\nisContaining - \"THAT \"");
        assertTrue(patriciaTree.isContaining("THAT "));
        System.out.println("\n\nisContaining - \"JACK \"");
        assertTrue(patriciaTree.isContaining("JACK "));
        System.out.println("\n\nisContaining - \"BUILT;\"");
        assertTrue(patriciaTree.isContaining("BUILT;"));
    }
}
