package agh.jo.knuth.mix.machine;

import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MixEncoding {
    private static final Map<Character, Integer> characterCodes;
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
//        tmpMap.put('\theta', 10);
        tmpMap.put('J', 11);
        tmpMap.put('K', 12);
        tmpMap.put('L', 13);
        tmpMap.put('M', 14);
        tmpMap.put('N', 15);
        tmpMap.put('O', 16);
        tmpMap.put('P', 17);
        tmpMap.put('Q', 18);
        tmpMap.put('R', 19);
//        tmpMap.put('\Phi', 20);
//        tmpMap.put('\Pi', 21);
        tmpMap.put('S', 22);
        tmpMap.put('T', 23);
        tmpMap.put('U', 24);
        tmpMap.put('V', 25);
        tmpMap.put('W', 26);
        tmpMap.put('X', 27);
        tmpMap.put('Y', 28);
        tmpMap.put('Z', 29);
        tmpMap.put('0', 30);
//        tmpMap.put('1', 31);
        tmpMap.put(';', 31); // end of file
//        tmpMap.put('2', 32);
//        tmpMap.put('3', 33);
//        tmpMap.put('4', 34);
//        tmpMap.put('5', 35);
//        tmpMap.put('6', 36);
//        tmpMap.put('7', 37);
//        tmpMap.put('8', 38);
//        tmpMap.put('9', 39);
//        tmpMap.put('.', 40);
//        tmpMap.put(',', 41);
//        tmpMap.put('(', 42);
//        tmpMap.put(')', 43);
//        tmpMap.put('+', 44);
//        tmpMap.put('-', 45);
//        tmpMap.put('*', 46);
//        tmpMap.put('/', 47);
//        tmpMap.put('=', 48);
//        tmpMap.put('$', 49);
//        tmpMap.put('<', 50);
//        tmpMap.put('>', 51);
//        tmpMap.put('@', 52);
//        tmpMap.put(';', 53);
//        tmpMap.put(':', 54);
//        tmpMap.put('\'', 55);
        characterCodes = Collections.unmodifiableMap(tmpMap);
    }

    public static Integer charToInt(char inputChar) throws Exception {
        inputChar = Character.toUpperCase(inputChar);
        if( MixEncoding.characterCodes.containsKey(inputChar)) {
            return MixEncoding.characterCodes.get(inputChar);
        }
        else throw new Exception("Knuth's Mix Machine does not have such character in its representation or " +
                "it's one of 3 character java is unable to represent using Character type (\\theta - 10, \\Phi - 20, \\Pi - 21). " +
                "Your character = '" +  inputChar + "'");
    }
}
