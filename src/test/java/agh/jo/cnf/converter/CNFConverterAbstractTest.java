package agh.jo.cnf.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class CNFConverterAbstractTest {

    public static CNFConverter getNewCNFConverterAndAssert(String inFilePath, String inFileName, String outFilePath, String outFileName, char EOCNF, char inputCNFdelimiterReplacement, char outputEOF) {
        CNFConverter cnfConverter = new CNFConverter(inFilePath, inFileName, outFilePath, outFileName, EOCNF, inputCNFdelimiterReplacement, outputEOF);
        assertNotNull(cnfConverter);
        assertNotNull(cnfConverter.getCnfReader());
        assertNotNull(cnfConverter.getCnfWriter());
        assertNotNull(cnfConverter.getCnfReader().getInFilePath());
        assertNotNull(cnfConverter.getCnfReader().getInFileName());
        assertNotNull(cnfConverter.getCnfWriter().getOutFilePath());
        assertNotNull(cnfConverter.getCnfWriter().getOutFileName());
        assertFalse(cnfConverter.getCnfReader().getInFilePath().isEmpty());
        assertFalse(cnfConverter.getCnfReader().getInFileName().isEmpty());
        assertFalse(cnfConverter.getCnfWriter().getOutFilePath().isEmpty());
        assertFalse(cnfConverter.getCnfWriter().getOutFileName().isEmpty());
        return cnfConverter;
    }

    public void assertGetCnfReaderIsFirstCharLetter(CNFConverter cnfConverter) {
        CNFReader cnfReader = cnfConverter.getCnfReader();
        for (int i = 0; i < 'A'; i++) {
            assertFalse(cnfReader.isFirstCharLetter(String.valueOf((char) i)));
        }
        for (int i = 0; i < 26; i++) {
            System.out.println("('A'+" + i + ")" + ('A' + i));
            assertTrue(cnfReader.isFirstCharLetter(String.valueOf((char) ('A' + i))));
        }
        for (int i = 'Z' + 1; i < 'a'; i++) {
            assertFalse(cnfReader.isFirstCharLetter(String.valueOf((char) i)));
        }
        for (int i = 0; i < 26; i++) {
            System.out.println("('a'+" + i + ")" + ('a' + i));
            assertTrue(cnfReader.isFirstCharLetter(String.valueOf((char) ('a' + i))));
        }
        for (int i = 'z' + 1; i < 150; i++) {
            assertFalse(cnfReader.isFirstCharLetter(String.valueOf((char) i)));
        }
    }

    public void assertGetCnfReaderReadCNF(CNFConverter cnfConverter) throws Exception {
        String line = cnfConverter.getCnfReader().readCNF();
        while(line!=null && !line.isEmpty()) {
            System.out.println("line: \"" + line + "\"");
            Pattern patternBad = Pattern.compile("([a-z]|[A-Z]).*");
            Pattern patternGood = Pattern.compile("([0-9]|(\\-)).*");
            Matcher matcherBad = patternBad.matcher(line);
            Matcher matcherGood = patternGood.matcher(line);
            assertFalse(matcherBad.matches());
            assertTrue(matcherGood.matches());
            line = cnfConverter.getCnfReader().readCNF();
        }
        cnfConverter.getCnfReader().close();
    }

    public void testConvert(CNFConverter cnfConverter) throws Exception {
        cnfConverter.convert();
        cnfConverter.getCnfReader().close();
    }
}
