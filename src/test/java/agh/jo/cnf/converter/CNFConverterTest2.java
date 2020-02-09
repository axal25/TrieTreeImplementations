package agh.jo.cnf.converter;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CNFConverterTest2 extends CNFConverterAbstractTest {
    public final static String inFilePath = "/src/main/resources/CNF/dimacs";
    public final static String inFileName = "Analiza1-openstacks-sequencedstrips-nonadl-nonnegated-os-sequencedstrips-p30_3.025-NOTKNOWN.cnf";
    public final static String outFilePath = "/src/main/resources/CNF/processed";
    public final static String outFileName = "Analiza1-openstacks-sequencedstrips-nonadl-nonnegated-os-sequencedstrips-p30_3.025-NOTKNOWN_sorted.txt";
    public final static char EOCNF = '\n';
    public final static char inputCNFdelimiterReplacement = ' ';
    public final static char outputEOF = ';';

    @Test
    @Order(1)
    @DisplayName("CNF Converter - constructor 1")
    void CNFConverter1() {
        getNewCNFConverterAndAssert(inFilePath, inFileName, outFilePath, outFileName, EOCNF, inputCNFdelimiterReplacement, outputEOF);
    }

    @Test
    @Order(2)
    @DisplayName("CNF Reader - isFirstCharLetter")
    void CNFReader1() {
        CNFConverter cnfConverter = getNewCNFConverterAndAssert(inFilePath, inFileName, outFilePath, outFileName, EOCNF, inputCNFdelimiterReplacement, outputEOF);
        assertGetCnfReaderIsFirstCharLetter(cnfConverter);
    }

    @Test
    @Order(3)
    @DisplayName("CNF Converter - readCNF()")
    void CNFConverter3() throws Exception {
        CNFConverter cnfConverter = getNewCNFConverterAndAssert(inFilePath, inFileName, outFilePath, outFileName, EOCNF, inputCNFdelimiterReplacement, outputEOF);
        assertGetCnfReaderReadCNF(cnfConverter);
    }

    @Test
    @Order(4)
    @DisplayName("CNF Converter - convert()")
    void CNFConverter4() throws Exception {
        CNFConverter cnfConverter = getNewCNFConverterAndAssert(inFilePath, inFileName, outFilePath, outFileName, EOCNF, inputCNFdelimiterReplacement, outputEOF);
        testConvert(cnfConverter);
    }

    public static CNFConverter getNewCNFConverterAndAssert() {
        return getNewCNFConverterAndAssert(inFilePath, inFileName, outFilePath, outFileName, EOCNF, inputCNFdelimiterReplacement, outputEOF);
    }
}
