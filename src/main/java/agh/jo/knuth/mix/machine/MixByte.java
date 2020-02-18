package agh.jo.knuth.mix.machine;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class MixByte {
    private int amountOfBits;
    private byte bytes[];
    static final public int MAX_UTF8_CHAR_BIT_LENGTH = 32;
    static final public int JAVA_BITS_AMOUNT_PER_BYTE = 8;
    // FileOperations reads byte one by one (8 bits at a time), then creates String(bytes, "UTF-8"); which is enough for most characters in ASCII

    public MixByte(int amountOfBits, int intValue) throws Exception {
        setAmountOfBits(amountOfBits);
        setBytes(intValue);
    }

    private void setAmountOfBits(int amountOfBits) {
        this.amountOfBits = amountOfBits;
    }

    private void setBytes(int intValue) throws Exception {
        this.bytes = calculateBytes(intValue);
    }

    private byte[] calculateBytes(int intValue) throws Exception {
        String stringJavaBytes = intToJavaBitsInString(intValue);
        String mixBitsInString = javaBitsToMixBitsInString(stringJavaBytes);
        String[] flushedRightMixBytesInStrings = flushRightMixBitsInString(mixBitsInString);
        return flushedRightMixBytesInStringToByteArray(flushedRightMixBytesInStrings);
    }

    protected int calculateAmountOfJavaBytes() {
        return (int) Math.ceil((float) this.amountOfBits/JAVA_BITS_AMOUNT_PER_BYTE);
    }

    protected String intToJavaBitsInString(int intValue) {
        return String.format("%0" + MixByte.MAX_UTF8_CHAR_BIT_LENGTH + "d", Integer.parseInt(Integer.toBinaryString(intValue)));
    }

    protected String javaBitsToMixBitsInString(String stringJavaBits) throws Exception {
        for (int i = 0; i < stringJavaBits.length() - this.amountOfBits; i++) {
            if(stringJavaBits.charAt(i) == '1') throw new Exception("This MixByte has only " + this.amountOfBits + " bits. You are trying to convert longer byte: " + stringJavaBits);
        }
        StringBuilder mixBitsInString = new StringBuilder();
        for (int i = stringJavaBits.length() - this.amountOfBits; i < stringJavaBits.length(); i++) {
            if(stringJavaBits.charAt(i) == '0') mixBitsInString.append("0");
            else if(stringJavaBits.charAt(i) == '1') mixBitsInString.append("1");
            else throw new Exception("Invalid JavaBits inside a string (at least one is not 0 or 1): " + stringJavaBits);
        }
        return mixBitsInString.toString();
    }

    protected String[] flushRightMixBitsInString(String mixBitsInString) {
        String[] flushedRightMixBitsInStrings = new String[ calculateAmountOfJavaBytes() ];
        int substringStart = 0;
        int substringEnd = 7;
        for (int i = 0; i < flushedRightMixBitsInStrings.length; i++) {
            if(i==0) {
                int unusedBitsAmount = 8 - (mixBitsInString.length() % 8);
                substringEnd = 8 - unusedBitsAmount;
                StringBuilder unusedBits = new StringBuilder();
                for (int j = 0; j < unusedBitsAmount; j++) unusedBits.append("0");
                flushedRightMixBitsInStrings[i] = unusedBits.append(mixBitsInString.substring(substringStart, substringEnd)).toString();
            }
            else {
                flushedRightMixBitsInStrings[i] = new StringBuilder().append(mixBitsInString.substring(substringStart, substringEnd)).toString();
            }
            substringStart = substringEnd;
            substringEnd = substringStart + 8;
        }
        return flushedRightMixBitsInStrings;
    }

    protected byte[] flushedRightMixBytesInStringToByteArray(String[] flushedRightMixBytesInString) throws Exception {
        byte[] bytes = new byte[ flushedRightMixBytesInString.length ];
        for (int i = 0; i < flushedRightMixBytesInString.length; i++) {
            bytes[i] = Byte.parseByte(flushedRightMixBytesInString[i], 2);
        }
        return bytes;
    }

    protected String byteToBinaryString(byte aByte) {
        return String.format("%0" + 8 + "d", Integer.parseInt(Integer.toBinaryString(aByte)));
    }

    protected String getByteToBinaryString(int bytesIndex) {
        return byteToBinaryString(this.bytes[bytesIndex]);
    }

    protected String getBytesToBinaryString() {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            binaryString.append(getByteToBinaryString(i));
        }
        return binaryString.toString();
    }

    public String getBytesToMixBinaryString() {
        String binaryString = getBytesToBinaryString();
        return binaryString.substring(binaryString.length() - this.amountOfBits);
    }

    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder()
                .append("MixByte{")
                .append("amountOfBits=").append(amountOfBits)
                .append(", bytes=").append(Arrays.toString(bytes))
                .append("}").append(" = ")
                .append(getBytesToMixBinaryString());
        return representation.toString();
    }
}
