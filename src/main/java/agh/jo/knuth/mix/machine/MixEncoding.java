package agh.jo.knuth.mix.machine;

import agh.jo.knuth.patricia.PatriciaNode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MixEncoding {
    public static final int GreekCapitalLetter_CharCode_Phi = 934;
    public static final int GreekSmallLetter_CharCode_theta = 952;
    public static final int GreekCapitalLetter_CharCode_Pi = 928;
    public static final Map<Character, Integer> statCharCodes;
    static {
        Map<Character, Integer> tmpMap = new HashMap<Character, Integer>();
        tmpMap.put(' ', 0);
        tmpMap.put('A', 1);
        tmpMap.put('B', 2);
        tmpMap.put('C', 3);
        tmpMap.put('D', 4);
        tmpMap.put('E', 5);
        tmpMap.put('F', 6);
        tmpMap.put('G', 7);
        tmpMap.put('H', 8);
        tmpMap.put('I', 9);

        tmpMap.put((char) GreekSmallLetter_CharCode_theta, 10);

        tmpMap.put('J', 11);
        tmpMap.put('K', 12);
        tmpMap.put('L', 13);
        tmpMap.put('M', 14);
        tmpMap.put('N', 15);
        tmpMap.put('O', 16);
        tmpMap.put('P', 17);
        tmpMap.put('Q', 18);
        tmpMap.put('R', 19);

        tmpMap.put((char) GreekCapitalLetter_CharCode_Phi, 20);
        tmpMap.put((char) GreekCapitalLetter_CharCode_Pi, 21);

        tmpMap.put('S', 22);
        tmpMap.put('T', 23);
        tmpMap.put('U', 24);
        tmpMap.put('V', 25);
        tmpMap.put('W', 26);
        tmpMap.put('X', 27);
        tmpMap.put('Y', 28);
        tmpMap.put('Z', 29);
        tmpMap.put('0', 30);
        tmpMap.put('1', 31);

        // 6 bits
        tmpMap.put('2', 32);
        tmpMap.put('3', 33);
        tmpMap.put('4', 34);
        tmpMap.put('5', 35);
        tmpMap.put('6', 36);
        tmpMap.put('7', 37);
        tmpMap.put('8', 38);
        tmpMap.put('9', 39);
        tmpMap.put('.', 40);
        tmpMap.put(',', 41);
        tmpMap.put('(', 42);
        tmpMap.put(')', 43);
        tmpMap.put('+', 44);
        tmpMap.put('-', 45);
        tmpMap.put('*', 46);
        tmpMap.put('/', 47);
        tmpMap.put('=', 48);
        tmpMap.put('$', 49);
        tmpMap.put('<', 50);
        tmpMap.put('>', 51);
        tmpMap.put('@', 52);
        tmpMap.put(';', 53);
        tmpMap.put(':', 54);
        tmpMap.put('\'', 55);
        statCharCodes = Collections.unmodifiableMap(tmpMap);
    }
    private Map<Character, Integer> charCodes;
    private char charEOF; // End Of File character
    private char charEOK; // End Of Key character // start of another Key

    public MixEncoding(char charEOF, char charEOK) {
        this.charEOF = charEOF;
        this.charEOK = charEOK;
        this.charCodes = new HashMap<Character, Integer>();
        statCharCodes.forEach(
                (key, value) -> { charCodes.put(key, value); }
        );
        putCharEOFInThePlaceOfChar1Value31();
        putCharEOKInThePlaceOfChar0Value30();
    }

    private void putCharEOFInThePlaceOfChar1Value31() {
        replaceOrSwapPlaces(charEOF, '1');
    }

    private void putCharEOKInThePlaceOfChar0Value30() {
        replaceOrSwapPlaces(charEOK, '0');
    }

    private void replaceOrSwapPlaces(char toInsertKeyChar, char toReplaceKeyChar) {
        int valueToReplaceKeyChar = this.charCodes.get(toReplaceKeyChar);
        this.charCodes.remove(toReplaceKeyChar);
        boolean previouslyContainedToInsertKeyChar = this.charCodes.containsKey(toInsertKeyChar);
        int valueToInsertKeyChar = this.charCodes.get(toInsertKeyChar)==null?-1:this.charCodes.get(toInsertKeyChar);
        this.charCodes.remove(toInsertKeyChar);
        this.charCodes.put(toInsertKeyChar, valueToReplaceKeyChar);
        if(previouslyContainedToInsertKeyChar) this.charCodes.put(toReplaceKeyChar, valueToInsertKeyChar);
    }

    public Integer charToInt(char inputChar) throws Exception {
        if(inputChar != (char) GreekSmallLetter_CharCode_theta) inputChar = Character.toUpperCase(inputChar);
        if( this.charCodes.containsKey(inputChar)) {
            return this.charCodes.get(inputChar);
        }
        else throw new Exception("Knuth's Mix Machine does not have such character in its representation. " +
                "Your character = '" +  inputChar + "' (UTF-16: " + ((int) inputChar) + ")");
    }

    @Override
    public String toString() {
        return "MixEncoding{" +
                "\n\tcharCodes=" + charCodes +
                ",\n\tcharEOF=" + charEOF +
                ",\n\tcharEOK=" + charEOK +
                "\n}";
    }
}
