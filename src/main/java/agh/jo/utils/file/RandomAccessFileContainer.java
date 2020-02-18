package agh.jo.utils.file;

import agh.jo.knuth.patricia.PatriciaTree;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.charset.Charset;

@Getter
@Setter
public class RandomAccessFileContainer {
    private int currentPosition = 0;
    private File file;
    private RandomAccessFile randomAccessFile;

    public RandomAccessFileContainer(String filePath, String fileName) {
        final String functionName = "RandomAccessFileContainer(String filePath, String fileName)";
        this.file = FilesHandler.openExistingFile(filePath, fileName);
        try {
            this.randomAccessFile = new RandomAccessFile(this.file, "rw");
        } catch (FileNotFoundException e) {
            this.randomAccessFile = null;
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            if( PatriciaTree.printNonCriticalExceptionInfo ) System.out.println(exceptionMessage);
        }
    }

    public void close() {
        final String functionName = "void close()";
        try {
            this.randomAccessFile.close();
        } catch (IOException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            if( PatriciaTree.printNonCriticalExceptionInfo ) System.out.println(exceptionMessage);
        }
    }

    public void seek(int position) {
        final String functionName = "seek(int position)";
        this.currentPosition = position;
        try {
            randomAccessFile.seek(position);
        } catch (IOException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            if( PatriciaTree.printNonCriticalExceptionInfo ) System.out.println(exceptionMessage);
        }
    }

    private int getAmountOfBytesNeededToEncode(int byteUintValue) throws Exception {
        if (byteUintValue == -1) return 1; // EOF
        else if(0 <= byteUintValue && byteUintValue <= 127) return 1; // there is always at least one byte
        else if(128 <= byteUintValue && byteUintValue <= 191) return -1; // this isn't correct form of first byte of char (2nd, 3rd or 4th byte has form of 10XXXXXX)
        else if(192 <= byteUintValue && byteUintValue <= 223) return 2; // 2 bytes
        else if(224 <= byteUintValue && byteUintValue <= 239) return 3; // 3 bytes
        else if(140 <= byteUintValue && byteUintValue <= 255) return 4; // 4 bytes
        else /**if(byteUintValue > 255)**/ throw new Exception("Impossible. There can't be UINT value bigger than 255 encoded on 1 byte. Your char (UINT): " + byteUintValue);
    }

    public String getNextCharToString() {
        final String functionName = "nextChar()";
        if(this.currentPosition < 0) return null;
        try {
            int currentByte = read();
            if(currentByte != -1) currentByte = Byte.toUnsignedInt((byte) currentByte);
            if (getAmountOfBytesNeededToEncode(currentByte) == -1)
                throw new UTFDataFormatException("Malformed UTF char at position: " + currentPosition);

            byte[] bytes = new byte[getAmountOfBytesNeededToEncode(currentByte)];
            bytes[0] = (byte) currentByte;

            for (int j = 1; j < bytes.length; j++) {
                bytes[j] = read();
            }

            return currentByte > -1 ? new String(bytes, Charset.forName("UTF-8")) : null; // EOF character is -1.
        } catch (Exception e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            if( PatriciaTree.printNonCriticalExceptionInfo ) System.out.println(exceptionMessage);
            return null;
        }
    }

    public byte read() {
        final String functionName = "read()";
        try {
            this.currentPosition++;
            return this.randomAccessFile.readByte();
        } catch (EOFException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            if( PatriciaTree.printNonCriticalExceptionInfo ) System.out.println(exceptionMessage);
            return (byte) -1;
        } catch (Exception e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(PatriciaTree.class.getName(), functionName, e);
            if( PatriciaTree.printNonCriticalExceptionInfo ) System.out.println(exceptionMessage);
            return (byte) 128;
        }
    }
}
