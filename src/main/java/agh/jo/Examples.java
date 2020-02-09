package agh.jo;

import agh.jo.cnf.converter.CNFConverter;
import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.PatriciaNode;
import agh.jo.knuth.patricia.PatriciaTree;
import agh.jo.knuth.patricia.file.ops.WordStrategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Examples {
    public final static boolean isConvertCnfSourceFileToPatriciaSourceFile = false;
    public final static String[] cnfFilePath = {
            null,
            null,
            null,
            null,
            "/src/main/resources/CNF/dimacs",
            "/src/main/resources/CNF/dimacs",
            "/src/main/resources/CNF/dimacs",
    };
    public final static String[] cnfFileName = {
            null,
            null,
            null,
            null,
            "Analiza1-gss-20-s100.cnf",
            "Analiza1-openstacks-sequencedstrips-nonadl-nonnegated-os-sequencedstrips-p30_3.025-NOTKNOWN.cnf",
            "ShorterCNFExample_MoreNodesThanKnuthExample.cnf",

    };
    public final static String[] patriciaFilePath = {
            "/src/main/resources/knuth/patricia",
            "/src/main/resources/knuth/patricia",
            "/src/main/resources/knuth/patricia",
            "/src/main/resources/knuth/patricia",
            "/src/main/resources/CNF/processed",
            "/src/main/resources/CNF/processed",
            "/src/main/resources/CNF/processed",
    };
    public final static String[] patriciaFileName = {
            "KnuthsPatriciaExample1.txt",
            "KnuthsPatriciaExample2.txt",
            "KnuthsPatriciaExample3.txt",
            "KnuthsPatriciaExample4.txt",
            "Analiza1-gss-20-s100_sorted.txt",
            "Analiza1-openstacks-sequencedstrips-nonadl-nonnegated-os-sequencedstrips-p30_3.025-NOTKNOWN_sorted.txt",
            "ShorterCNFExample_MoreNodesThanKnuthExample_sorted.txt",
    };
    public final static char[] charEOCNF = {'\0', '\0', '\0', '\0', '|', '\n', '\n'};
    public final static char[] charInputCNFdelimiterReplacement = {'\0', '\0', '\0', '\0', '_', ' ', ' '};
    public final static char[] charOutputEOF = {'\0', '\0', '\0', '\0', ';', ';', ';'};
    public final static char[] charEOF = {';', 'Π', 'Π', 'Π', charOutputEOF[4], charOutputEOF[5], charOutputEOF[6]};
    public final static char[] charEOK = {' ', 'Φ', 'Φ', 'Φ', charEOCNF[4], charEOCNF[5], charEOCNF[6]};
    public final static WordStrategy[] wordStrategy = {
            WordStrategy.SINGLE,
            WordStrategy.SINGLE,
            WordStrategy.SINGLE,
            WordStrategy.SINGLE,
            WordStrategy.SINGLE,
            WordStrategy.SINGLE,
            WordStrategy.SINGLE
    };
    public final static Encoding[] encoding = {
            Encoding.JAVA,
            Encoding.JAVA,
            Encoding.JAVA,
            Encoding.JAVA,
            Encoding.JAVA,
            Encoding.JAVA,
            Encoding.JAVA
    };
    public final static String[][] keys = {
            {"THIS ", "IS ", "THE ", "HOUSE ", "THAT ", "JACK ", "BUILT;"},
            {"THISΦ", "ISΦ", "THEΦ", "HOUSEΦ", "THATΦ", "JACKΦ", "BUILTΦΠ"},
            {"THISΦ", "ISΦΦ", "THEΦ", "HOUSEΦ", "THATΦ", "JACKΦ", "BUILTΦΦΦΦΠ"},
            {"THIS Φ", "IS ΦΦ", "THE Φ", "HOUSE Φ", "THAT Φ", "JACK Φ", "BUILT ;ΦΦΦΦΠ"},
            {null, null, null, null, null, null, null},
            null,
            null,
            null,
    };
    public final static String[][] prefixes = {
            {"THIS", "IS", "THE", "HOUSE", "THAT", "JACK", "BUILT"},
            {"THIS", "IS", "THE", "HOUSE", "THAT", "JACK", "BUILTΦ"},
            {"THIS", "ISΦ", "THE", "HOUSE", "THAT", "JACK", "BUILTΦΦΦΦ"},
            {"THIS ", "IS Φ", "THE ", "HOUSE ", "THAT ", "JACK ", "BUILT ;ΦΦΦΦ"},
            {null, null, null, null, null, null, null},
            null,
            null,
            null,
    };
    public final static String[][] multipleNodePrefixes = {
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"},
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"},
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"},
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"},
            {null, null, null, null, null, null, null},
            null,
            null,
            null,
    };

    public static PatriciaTree getExamplePatriciaTree(int exampleIndex) {
        convertCnfSourceFileToPatriciaSourceFile(exampleIndex);
        System.out.println(">>> converted cnf source file to patricia tree source file <<<");
        PatriciaTree patriciaTree = createExamplePatriciaTree(exampleIndex);
        System.out.println(">>> created patricia tree from source file <<<");
        return patriciaTree;
    }

    public static void convertCnfSourceFileToPatriciaSourceFile(int exampleIndex) {
        if(isConvertCnfSourceFileToPatriciaSourceFile) {
            if(isExampleCnfDataFilled(exampleIndex)) {
                int i = exampleIndex;
                CNFConverter cnfConverter = new CNFConverter(
                        cnfFilePath[i],
                        cnfFileName[i],
                        patriciaFilePath[i],
                        patriciaFileName[i],
                        charEOCNF[i],
                        charInputCNFdelimiterReplacement[i],
                        charOutputEOF[i]
                );
                try {
                    cnfConverter.convert();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new NullPointerException("Caught exception: " + e.toString());
                }
            }
        }
    }

    public static PatriciaTree createExamplePatriciaTree(int exampleIndex) {
        int i = exampleIndex;
        PatriciaTree patriciaTree = getNewInitiatedTree(patriciaFilePath[i], patriciaFileName[i], charEOF[i], charEOK[i], wordStrategy[i], encoding[i], true);
        if(patriciaTree == null) throw new NullPointerException("patriciaTree: " + patriciaTree);

        String[] keysOrPrefixesOrMultipleNodePrefixesArray = null;
        if(multipleNodePrefixes[i]!=null && (
                keysOrPrefixesOrMultipleNodePrefixesArray==null ||
                keysOrPrefixesOrMultipleNodePrefixesArray.length < multipleNodePrefixes[i].length
        )) keysOrPrefixesOrMultipleNodePrefixesArray = multipleNodePrefixes[i];
        if(prefixes[i]!=null && (
                        keysOrPrefixesOrMultipleNodePrefixesArray==null ||
                        keysOrPrefixesOrMultipleNodePrefixesArray.length < prefixes[i].length
        )) keysOrPrefixesOrMultipleNodePrefixesArray = prefixes[i];
        if(keys[i]!=null && (
                keysOrPrefixesOrMultipleNodePrefixesArray==null ||
                        keysOrPrefixesOrMultipleNodePrefixesArray.length < keys[i].length
        )) keysOrPrefixesOrMultipleNodePrefixesArray = keys[i];

        for (int j = 0; keysOrPrefixesOrMultipleNodePrefixesArray!=null && j < keysOrPrefixesOrMultipleNodePrefixesArray.length; j++) {
            if(j!=0) {
                System.out.println();
                insertNextKeyIntoTree(patriciaTree, true);
            }
            if(keys[i]!=null && !isNullOrEmpty(keys[i][j])) {
                checkKeyIsContaining(patriciaTree, keys[i][j], true);
                checkKeyFindNodeMatching(patriciaTree, keys[i][j], true);
            }
            if(prefixes[i]!=null && !isNullOrEmpty(prefixes[i][j])) {
                checkKeyIsContaining(patriciaTree, prefixes[i][j], false);
                checkPrefixIsContaining(patriciaTree, prefixes[i][j], true);
                checkPrefixFindNodesMatching(patriciaTree, prefixes[i][j], true);
            }
            if(multipleNodePrefixes[i]!=null && !isNullOrEmpty(multipleNodePrefixes[i][j])) {
                checkPrefixIsContaining(patriciaTree, multipleNodePrefixes[i][j], true);
                checkPrefixFindNodesMatching(patriciaTree, multipleNodePrefixes[i][j], true);
            }
        }

        try {
            patriciaTree.insertAllKeysIntoTree();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("Caught exception: " + e.toString());
        }

        System.out.println();
        insertNextKeyIntoTree(patriciaTree, false);
        System.out.println();

        /**
        System.out.println("patriciaTree: " + patriciaTree);
        System.out.println();
         **/

        if(patriciaTree == null) throw new NullPointerException("patriciaTree: " + patriciaTree);
        return patriciaTree;
    }

    public static PatriciaTree getNewInitiatedTree(String filePath, String fileName, char charEOF, char charEOK, WordStrategy wordStrategy, Encoding encoding, boolean expectedOutcome) {
        PatriciaTree patriciaTree = null;
        try {
            patriciaTree = new PatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
            assertTrue(expectedOutcome);
            final String msg = "PatriciaTree initiated with header node for first key";
            System.out.println(msg);
            return patriciaTree;
        } catch (Exception e) {
            assertFalse(expectedOutcome);
            final String msg = "PatriciaTree could not be initiated";
            System.out.println(msg);
            return null;
        }
    }

    public static void insertNextKeyIntoTree(PatriciaTree patriciaTree, boolean expectedOutcome) {
        try {
            patriciaTree.insertNextKeyIntoTree();
            assertTrue(expectedOutcome);
            final String msg = "Inserted new node into PatriciaTree";
            System.out.println(msg);
        } catch (Exception e) {
            assertFalse(expectedOutcome);
            final String msg = "Could not insert new node into PatriciaTree";
            System.out.println(msg);
        }
    }

    public static void checkPrefixIsContaining(PatriciaTree patriciaTree, String searchWord, boolean expectedOutcome) {
        boolean isContainingPrefix = false;
        PatriciaNode[] nodesContainingPrefix = null;
        try {
            isContainingPrefix = patriciaTree.isContainingPrefix(searchWord);
            assertTrue(expectedOutcome);
            if(isContainingPrefix) {
                assertTrue(expectedOutcome);
                final String msg = "\"" + searchWord + "\" is a prefix contained inside PatriciaTree";
                System.out.println(msg);
            } else {
                assertFalse(expectedOutcome);
                final String msg = "\"" + searchWord + "\" is NOT a prefix contained inside PatriciaTree";
                System.out.println(msg);
            }
        } catch (Exception e) {
            assertFalse(expectedOutcome);
            final String msg = "Search word \"" + searchWord + "\" caused some kind of exception.";
            System.out.println(msg);
        }
    }

    public static void checkPrefixFindNodesMatching(PatriciaTree patriciaTree, String searchWord, boolean expectedOutcome) {
        PatriciaNode[] nodesContainingPrefix = null;
        try {
            nodesContainingPrefix = patriciaTree.findNodesMatchingPrefix(searchWord);
            if(nodesContainingPrefix == null) assertFalse(expectedOutcome);
            else assertTrue(expectedOutcome);
            for (PatriciaNode node:nodesContainingPrefix) {
                final String msg = "\"" + searchWord + "\" is a prefix contained inside PatriciaNode with id: " + node.getId();
                System.out.println(msg);
            }
        } catch (Exception e) {
            assertFalse(expectedOutcome);
            final String msg = "Search word \"" + searchWord + "\" caused some kind of exception.";
            System.out.println(msg);
        }
    }

    public static void checkKeyIsContaining(PatriciaTree patriciaTree, String searchWord, boolean expectedOutcome) {
        boolean isContainingKey = false;
        PatriciaNode[] nodesContainingKey = null;
        try {
            isContainingKey = patriciaTree.isContainingKey(searchWord);
            if(isContainingKey) {
                assertTrue(expectedOutcome);
                final String msg = "\"" + searchWord + "\" is a key contained inside PatriciaTree";
                System.out.println(msg);
            } else {
                assertFalse(expectedOutcome);
                final String msg = "\"" + searchWord + "\" is NOT a key contained inside PatriciaTree";
                System.out.println(msg);
            }
        } catch (Exception e) {
            assertFalse(expectedOutcome);
            final String msg = "Search word \"" + searchWord + "\" caused some kind of exception.";
            System.out.println(msg);
        }
    }

    public static void checkKeyFindNodeMatching(PatriciaTree patriciaTree, String searchWord, boolean expectedOutcome) {
        PatriciaNode nodeContainingKey = null;
        try {
            nodeContainingKey = patriciaTree.findNodeMatchingKey(searchWord);
            if(nodeContainingKey == null) assertFalse(expectedOutcome);
            else assertTrue(expectedOutcome);
            final String msg = "\"" + searchWord + "\" is a prefix contained inside PatriciaNode with id: " + nodeContainingKey.getId();
            System.out.println(msg);
        } catch (Exception e) {
            assertFalse(expectedOutcome);
            final String msg = "Search word \"" + searchWord + "\" caused some kind of exception.";
            System.out.println(msg);
        }
    }

    public static boolean isExampleCnfDataFilled(int exampleIndex) {
        int i = exampleIndex;
        return (
                !isNullOrEmpty(cnfFilePath[i]) &&
                        !isNullOrEmpty(cnfFileName[i]) &&
                        !isNullOrEmpty(patriciaFilePath[i]) &&
                        !isNullOrEmpty(patriciaFileName[i]) &&
                        isSet(charEOCNF[i]) &&
                        isSet(charInputCNFdelimiterReplacement[i]) &&
                        isSet(charOutputEOF[i])
        );
    }

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public static boolean isSet(char input) {
        return input == '\0';
    }
}
