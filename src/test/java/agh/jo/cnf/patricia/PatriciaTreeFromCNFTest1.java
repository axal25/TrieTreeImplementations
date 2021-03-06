package agh.jo.cnf.patricia;

import agh.jo.cnf.converter.CNFConverter;
import agh.jo.cnf.converter.CNFConverterTest1;
import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.PatriciaTree;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatriciaTreeFromCNFTest1 extends PatriciaTreeFromCNFAbstractTest {
    public final static String filePath = CNFConverterTest1.outFilePath;
    public final static String fileName = CNFConverterTest1.outFileName;
    public final static char charEOF = CNFConverterTest1.outputEOF;
    public final static char charEOK = CNFConverterTest1.EOCNF;
    public final static Encoding encoding = Encoding.JAVA;
    public final static WordStrategy wordStrategy = WordStrategy.SINGLE;
    public final static CNFConverter cnfConverter = CNFConverterTest1.getNewCNFConverterAndAssert();

    @Test
    @Order(1)
    @DisplayName("Patricia Tree - constructor 1")
    void PatriciaTree1() throws Exception {
        getNewInitiatedPatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
    }

    @Test
    @Order(2)
    @DisplayName("Patricia Tree - is Finding All Keys")
    void PatriciaTree2() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        isFindingAllKeys(patriciaTree, cnfConverter);
    }

    @Test
    @Order(3)
    @DisplayName("Patricia Tree - insertAllKeysIntoTree")
    void PatriciaTree3() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        patriciaTree = getPatriciaTreeWithAllKeys(patriciaTree);
        System.out.println("patriciaTree.getHeader().toString(): " + patriciaTree.getHeader().toString());
    }

    @Test
    @Order(4)
    @DisplayName("Patricia Tree - isContainingPrefix All")
    void PatriciaTree4() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        patriciaTree = getPatriciaTreeWithAllKeys(patriciaTree);
        isContainingAllPrefixes(patriciaTree, cnfConverter);
    }

    @Test
    @Order(5)
    @DisplayName("Patricia Tree - isContainingKey All")
    void PatriciaTree5() throws Exception {
        PatriciaTree patriciaTree = getNewInitiatedPatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        patriciaTree = getPatriciaTreeWithAllKeys(patriciaTree);
        isContainingAllKeys(patriciaTree, cnfConverter);
    }
}
