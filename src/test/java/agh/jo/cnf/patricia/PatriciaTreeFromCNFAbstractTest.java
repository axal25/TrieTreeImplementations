package agh.jo.cnf.patricia;

import agh.jo.cnf.converter.CNFConverter;
import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.PatriciaTree;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import agh.jo.knuth.patricia.file.ops.exceptions.NextWordStartIndexNotFound;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class PatriciaTreeFromCNFAbstractTest {

    public PatriciaTree getNewInitiatedPatriciaTree(
            String filePath,
            String fileName,
            char charEOF,
            char charEOK,
            WordStrategy wordStrategy,
            Encoding encoding
    ) throws Exception {
        PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        return patriciaTree;
    }

    public static void isFindingAllKeys(PatriciaTree patriciaTree, CNFConverter cnfConverter) throws Exception {
        int position = 0;
        int nextPosition = position;
        int cnfCounter = 0;
        String cnf = null;
        while((cnf = cnfConverter.getCnfReader().readCNF()) != null && !cnf.isEmpty()) {
            cnfCounter++;
            position = nextPosition;
            System.out.println(cnfCounter + ". \"" + cnf + "\"");
            Long[] longArray = cnfConverter.parseCNF(cnf);
            Arrays.sort(longArray);
            String sortedCnf = cnfConverter.literalArrayToString(longArray);
            sortedCnf = new StringBuilder().append(sortedCnf).append(cnfConverter.getCnfWriter().getEOCNF()).toString();
            String key = patriciaTree.getFileOps().getFileOpsStrategy().getWordStringFromFileStartingAtPosition(position);
            try {
                nextPosition = patriciaTree.getFileOps().getFileOpsStrategy().findNextWordStartIndex(position);
                System.out.println(cnfCounter + ". \"" + sortedCnf + "\"");
                assertEquals(sortedCnf, key);
            } catch (NextWordStartIndexNotFound e) {
                sortedCnf = new StringBuilder().append(sortedCnf).append(cnfConverter.getCnfWriter().getOutputEOF()).toString();
                System.out.println(cnfCounter + ". \"" + sortedCnf + "\"");
                assertEquals(sortedCnf, key);
            }
        }
    }

    public PatriciaTree getPatriciaTreeWithAllKeys(PatriciaTree patriciaTree) throws Exception {
        patriciaTree.insertAllKeysIntoTree();
        return patriciaTree;
    }

    public static void isContainingAllPrefixes(PatriciaTree patriciaTreeWithAllKeys, CNFConverter cnfConverter) throws Exception {
        String cnf = null;
        int cnfCounter = 0;
        while((cnf = cnfConverter.getCnfReader().readCNF()) != null && !cnf.isEmpty()) {
            cnfCounter++;
            System.out.println(cnfCounter + ". \"" + cnf + "\"");
            Long[] longArray = cnfConverter.parseCNF(cnf);
            Arrays.sort(longArray);
            String sortedCnf = cnfConverter.literalArrayToString(longArray);
            System.out.println(cnfCounter + ". \"" + sortedCnf + "\"");
            assertTrue(patriciaTreeWithAllKeys.isContainingPrefix(cnf));
            assertEquals(1, patriciaTreeWithAllKeys.findNodesMatchingPrefix(cnf).length);
        }
    }

    public static void isContainingAllKeys(PatriciaTree patriciaTreeWithAllKeys, CNFConverter cnfConverter) throws Exception {
        String cnf = null;
        int cnfCounter = 0;
        while((cnf = cnfConverter.getCnfReader().readCNF()) != null && !cnf.isEmpty()) {
            cnfCounter++;
            System.out.println(cnfCounter + ". \"" + cnf + "\"");
            Long[] longArray = cnfConverter.parseCNF(cnf);
            Arrays.sort(longArray);
            String sortedCnf = cnfConverter.literalArrayToString(longArray);
            System.out.println(cnfCounter + ". \"" + sortedCnf + "\"");
            assertTrue(patriciaTreeWithAllKeys.isContainingKey(cnf));
        }
    }
}
