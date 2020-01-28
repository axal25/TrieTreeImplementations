package agh.jo.knuth.patricia;

import agh.jo.knuth.mix.machine.MixByte;
import agh.jo.knuth.mix.machine.MixEncoding;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MixMachine {
    private Encoding encoding;
    private int amountOfBits;

    public MixMachine(Encoding encoding, int amountOfBits) {
        setEncoding(encoding);
        setAmountOfBits(amountOfBits);
    }

    public MixMachine(Encoding encoding) throws Exception {
        setEncoding(encoding);
        setAmountOfBits();
    }

    private void setAmountOfBits() throws Exception {
        if(this.getEncoding() == Encoding.MIX) this.amountOfBits = 5;
        // FileOperations reads byte one by one (8 bits at a time), then creates String(bytes, "UTF-8"); which is enough for most characters in ASCII
        else if(this.getEncoding() == Encoding.JAVA) this.amountOfBits = 8;
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    protected String getBinaryString(String charChain) throws Exception {
        StringBuilder binaryStringOfCharChain = new StringBuilder();
        for (int i = 0; i < charChain.length(); i++) {
            binaryStringOfCharChain.append(getBinaryString(getCharCode(charChain.charAt(i))));
        }
        return binaryStringOfCharChain.toString();
    }

    protected String getBinaryString(int characterCode) throws Exception {
        if(this.getEncoding() == Encoding.MIX) return new MixByte(this.amountOfBits, characterCode).getBytesToMixBinaryString();
        // FileOperations reads byte one by one (8 bits at a time), then creates String(bytes, "UTF-8"); which is enough for most characters in ASCII
        else if(this.getEncoding() == Encoding.JAVA) return String.format("%0" + this.amountOfBits + "d", Integer.parseInt(Integer.toBinaryString(characterCode)));
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    protected byte[] getCharBytes(int characterCode) throws Exception {
        if(this.getEncoding() == Encoding.MIX) return new MixByte(this.amountOfBits, characterCode).getBytes();
        else if(this.getEncoding() == Encoding.JAVA) {
            byte[] charByteRepresentation = new byte[1];
            charByteRepresentation[0] = (byte) characterCode;
            return charByteRepresentation;
        }
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    protected int getCharCode(char character) throws Exception {
        if(this.getEncoding() == Encoding.MIX) return MixEncoding.charToInt(character);
        else if(this.getEncoding() == Encoding.JAVA) return (int) character;
        else throw new Exception("Bad encoding: " + this.getEncoding());
    }

    @Override
    public String toString() {
        return "MixMachine{" +
                "encoding=" + encoding +
                ", amountOfBits=" + amountOfBits +
                '}';
    }
}
