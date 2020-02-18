package agh.jo.knuth.patricia;

import agh.jo.knuth.mix.machine.MixByte;
import agh.jo.knuth.mix.machine.MixEncoding;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class MixMachine {
    private Encoding encoding;
    private int amountOfBits;
    private MixEncoding mixEncoding;
    public static final int MIX_DEFAULT_AMOUNT_OF_BITS = 5;
    public static final int JAVA_DEFAULT_AMOUNT_OF_BITS = 8;

    private MixMachine() {}

    public MixMachine(Encoding encoding) throws Exception {
        setEncoding(encoding);
        setAmountOfBits();
    }

    public MixMachine(Encoding encoding, int amountOfBits) throws Exception {
        setEncoding(encoding);
        setAmountOfBits(amountOfBits);
    }

    public MixMachine(Encoding encoding, int amountOfBits, char charEOF, char charEOK) throws Exception {
        setMixEncoding(charEOF, charEOK, encoding);
        setEncoding(encoding);
        setAmountOfBits(amountOfBits);
    }

    public MixMachine(Encoding encoding, char charEOF, char charEOK) throws Exception {
        setMixEncoding(charEOF, charEOK, encoding);
        setEncoding(encoding);
        setAmountOfBits();
    }

    private void setMixEncoding(char charEOF, char charEOK, Encoding encoding) {
        if(encoding == Encoding.MIX) this.mixEncoding = new MixEncoding(charEOF, charEOK);
    }

    private void setEncoding(Encoding encoding) throws Exception {
        if(encoding == Encoding.MIX && this.getMixEncoding() == null)
            throw new Exception("If you intend to create PatriciaTree with Mix Machine Encoding" +
                    " it is required to pass End Of File character and End Of Key character in constructor.");
        else if(encoding != Encoding.JAVA && encoding != Encoding.MIX)
            throw new Exception("Bad encoding: " + encoding);
        else this.encoding = encoding;
    }

    private void setAmountOfBits(int amountOfBits) {
        this.amountOfBits = amountOfBits;
    }

    private void setAmountOfBits() throws Exception {
        if(this.getEncoding() == Encoding.MIX) this.amountOfBits = MixMachine.MIX_DEFAULT_AMOUNT_OF_BITS;
        // FileOperations reads byte one by one (8 bits at a time), then creates String(bytes, "UTF-8"); which is enough for most characters in ASCII
        else if(this.getEncoding() == Encoding.JAVA) this.amountOfBits = MixMachine.JAVA_DEFAULT_AMOUNT_OF_BITS;
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    public String getBinaryString(String charChain) throws Exception {
        StringBuilder binaryStringOfCharChain = new StringBuilder();
        for (int i = 0; i < charChain.length(); i++) {
            binaryStringOfCharChain.append(getBinaryString(getCharCode(charChain.charAt(i))));
        }
        return binaryStringOfCharChain.toString();
    }

    protected String getBinaryString(int characterCode) throws Exception {
        if(this.getEncoding() == Encoding.MIX) return new MixByte(this.amountOfBits, characterCode).getBytesToMixBinaryString();
        // FileOperations reads char one by one (1-4 bytes (8 bits each) at a time), then creates String(bytes, "UTF-8");
        else if(this.getEncoding() == Encoding.JAVA) {
            int binaryString = Integer.parseInt(Integer.toBinaryString(characterCode));
            int amountOfBytes = (int) Math.ceil((float) String.valueOf(binaryString).length()/this.amountOfBits);
            return String.format("%0" + amountOfBytes*this.amountOfBits + "d", binaryString);
        }
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    protected byte[] getCharBytes(int characterCode) throws Exception {
        if(this.getEncoding() == Encoding.MIX) return new MixByte(this.amountOfBits, characterCode).getBytes();
        else if(this.getEncoding() == Encoding.JAVA) {
            return getCharIntToJavaBytes(characterCode);
        }
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    public static int javaBytesToInt(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8).charAt(0);
    }

    protected byte[] getCharIntToJavaBytes(int characterCode) {
        char character = (char) characterCode;
        String characterInString = new StringBuilder().append(character).toString();
        byte[] utf8bytes = characterInString.getBytes(StandardCharsets.UTF_8);
        return utf8bytes;
    }

    protected int getCharCode(char character) throws Exception {
        if(this.getEncoding() == Encoding.MIX) return this.getMixEncoding().charToInt(character);
        else if(this.getEncoding() == Encoding.JAVA) return (int) character;
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    @Override
    public String toString() {
        return "MixMachine{" +
                "\n\tencoding=" + encoding +
                ",\n\tamountOfBits=" + amountOfBits +
                ",\n\tmixEncoding=" + mixEncoding +
                "\n}";
    }
}
