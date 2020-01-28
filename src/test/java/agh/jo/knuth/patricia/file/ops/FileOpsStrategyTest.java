package agh.jo.knuth.patricia.file.ops;

import agh.jo.func.intf.FunctionalInterfaceVoidReturn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileOpsStrategyTest {
    private final static String filePath = "/src/main/resources/knuth/patricia";
    private final static String fileName = "KnuthsPatriciaExample.txt";

    private FileOpsStrategy getFileOps() throws Exception {
        FileOps fileOps = new FileOps(filePath, fileName);
        FileOpsStrategy fileOpsStrategy = fileOps.getFileOpsStrategy();
        return fileOpsStrategy;
    }

    @Test
    @Order(1)
    @DisplayName("getStringFromFileAtPositionRandomAccess")
    void getStringAtPositionRandomAccess() throws Exception {
        FileOpsStrategy fileOpsStrategy = getFileOps();
        assertGetStringFromFileAtPositionRandomAccess(fileOpsStrategy);
    }

    public static void assertGetStringFromFileAtPositionRandomAccess(FileOpsStrategy fileOpsStrategy) {
        assertEquals(null, fileOpsStrategy.getStringFromFileAtPositionRandomAccess(-1));
        assertEquals("T", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(0));
        assertEquals("H", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(1));
        assertEquals("I", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(2));
        assertEquals("S", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(3));
        assertEquals(" ", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(4));
        assertEquals("I", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(5));
        assertEquals("S", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(6));
        assertEquals(" ", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(7));
        assertEquals("T", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(8));
        assertEquals("H", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(9));
        assertEquals("E", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(10));
        assertEquals(" ", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(11));
        assertEquals("H", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(12));
        assertEquals("O", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(13));
        assertEquals("U", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(14));
        assertEquals("S", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(15));
        assertEquals("E", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(16));
        assertEquals(" ", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(17));
        assertEquals("T", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(18));
        assertEquals("H", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(19));
        assertEquals("A", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(20));
        assertEquals("T", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(21));
        assertEquals(" ", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(22));
        assertEquals("J", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(23));
        assertEquals("A", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(24));
        assertEquals("C", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(25));
        assertEquals("K", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(26));
        assertEquals(" ", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(27));
        assertEquals("B", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(28));
        assertEquals("U", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(29));
        assertEquals("I", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(30));
        assertEquals("L", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(31));
        assertEquals("T", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(32));
        assertEquals(";", fileOpsStrategy.getStringFromFileAtPositionRandomAccess(33));
        assertEquals(null, fileOpsStrategy.getStringFromFileAtPositionRandomAccess(34));
    }

    @Test
    @Order(2)
    @DisplayName("getCharFromFileAtPosition")
    void getCharAtPosition() throws Exception {
        FileOpsStrategy fileOpsStrategy = getFileOps();
        assertGetCharFromFileAtPosition(fileOpsStrategy);
    }

    public static void assertGetCharFromFileAtPosition(FileOpsStrategy fileOpsStrategy) throws Exception {
        FunctionalInterfaceVoidReturn fi = () -> { fileOpsStrategy.getCharFromFileAtPosition(-1); };
        assertThrows(Exception.class, fi::run);
        assertEquals('T', fileOpsStrategy.getCharFromFileAtPosition(0));
        assertEquals('H', fileOpsStrategy.getCharFromFileAtPosition(1));
        assertEquals('I', fileOpsStrategy.getCharFromFileAtPosition(2));
        assertEquals('S', fileOpsStrategy.getCharFromFileAtPosition(3));
        assertEquals(' ', fileOpsStrategy.getCharFromFileAtPosition(4));
        assertEquals('I', fileOpsStrategy.getCharFromFileAtPosition(5));
        assertEquals('S', fileOpsStrategy.getCharFromFileAtPosition(6));
        assertEquals(' ', fileOpsStrategy.getCharFromFileAtPosition(7));
        assertEquals('T', fileOpsStrategy.getCharFromFileAtPosition(8));
        assertEquals('H', fileOpsStrategy.getCharFromFileAtPosition(9));
        assertEquals('E', fileOpsStrategy.getCharFromFileAtPosition(10));
        assertEquals(' ', fileOpsStrategy.getCharFromFileAtPosition(11));
        assertEquals('H', fileOpsStrategy.getCharFromFileAtPosition(12));
        assertEquals('O', fileOpsStrategy.getCharFromFileAtPosition(13));
        assertEquals('U', fileOpsStrategy.getCharFromFileAtPosition(14));
        assertEquals('S', fileOpsStrategy.getCharFromFileAtPosition(15));
        assertEquals('E', fileOpsStrategy.getCharFromFileAtPosition(16));
        assertEquals(' ', fileOpsStrategy.getCharFromFileAtPosition(17));
        assertEquals('T', fileOpsStrategy.getCharFromFileAtPosition(18));
        assertEquals('H', fileOpsStrategy.getCharFromFileAtPosition(19));
        assertEquals('A', fileOpsStrategy.getCharFromFileAtPosition(20));
        assertEquals('T', fileOpsStrategy.getCharFromFileAtPosition(21));
        assertEquals(' ', fileOpsStrategy.getCharFromFileAtPosition(22));
        assertEquals('J', fileOpsStrategy.getCharFromFileAtPosition(23));
        assertEquals('A', fileOpsStrategy.getCharFromFileAtPosition(24));
        assertEquals('C', fileOpsStrategy.getCharFromFileAtPosition(25));
        assertEquals('K', fileOpsStrategy.getCharFromFileAtPosition(26));
        assertEquals(' ', fileOpsStrategy.getCharFromFileAtPosition(27));
        assertEquals('B', fileOpsStrategy.getCharFromFileAtPosition(28));
        assertEquals('U', fileOpsStrategy.getCharFromFileAtPosition(29));
        assertEquals('I', fileOpsStrategy.getCharFromFileAtPosition(30));
        assertEquals('L', fileOpsStrategy.getCharFromFileAtPosition(31));
        assertEquals('T', fileOpsStrategy.getCharFromFileAtPosition(32));
        assertEquals(';', fileOpsStrategy.getCharFromFileAtPosition(33));
        fi = () -> { fileOpsStrategy.getCharFromFileAtPosition(34); };
        assertThrows(Exception.class, fi::run);
    }

    @Test
    @Order(3)
    @DisplayName("isCharExistFromFileAtPosition")
    void isCharExistFromFileAtPosition() throws Exception {
        FileOpsStrategy fileOpsStrategy = getFileOps();
        assertIsCharExistFromFileAtPosition(fileOpsStrategy);
    }

    public static void assertIsCharExistFromFileAtPosition(FileOpsStrategy fileOpsStrategy) {
        assertFalse(fileOpsStrategy.isCharExistFromFileAtPosition(-1));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(0));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(1));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(2));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(3));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(4));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(5));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(6));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(7));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(8));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(9));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(10));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(11));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(12));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(13));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(14));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(15));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(16));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(17));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(18));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(19));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(20));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(21));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(22));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(23));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(24));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(25));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(26));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(27));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(28));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(29));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(30));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(31));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(32));
        assertTrue(fileOpsStrategy.isCharExistFromFileAtPosition(33));
        assertFalse(fileOpsStrategy.isCharExistFromFileAtPosition(34));
    }
}
