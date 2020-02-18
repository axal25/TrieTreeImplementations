package agh.jo;

import agh.jo.cnf.converter.CNFConverter;
import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.PatriciaNode;
import agh.jo.knuth.patricia.PatriciaTree;
import agh.jo.knuth.patricia.file.ops.WordStrategy;
import agh.jo.knuth.patricia.file.ops.exceptions.NextWordStartIndexNotFoundException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Examples {
    public final static String[] cnfFilePath = {
            null, // 0
            null, // 1
            null, // 2
            null, // 3
            null, // 4
            "/src/main/resources/CNF/dimacs", // 5
            "/src/main/resources/CNF/dimacs", // 6
            "/src/main/resources/CNF/dimacs", // 7
            null, // 8
            null, // 9
    };
    public final static String[] cnfFileName = {
            null, // 0
            null, // 1
            null, // 2
            null, // 3
            null, // 4
            "Analiza1-gss-20-s100.cnf", // 5
            "Analiza1-openstacks-sequencedstrips-nonadl-nonnegated-os-sequencedstrips-p30_3.025-NOTKNOWN.cnf", // 6
            "ShorterCNFExample_MoreNodesThanKnuthExample.cnf", // 7
            null, // 8
            null, // 9
    };
    public final static String[] patriciaFilePath = {
            "/src/main/resources/knuth/patricia", // 0
            "/src/main/resources/knuth/patricia", // 1
            "/src/main/resources/knuth/patricia", // 2
            "/src/main/resources/knuth/patricia", // 3
            "/src/main/resources/knuth/patricia", // 4
            "/src/main/resources/CNF/processed", // 5
            "/src/main/resources/CNF/processed", // 6
            "/src/main/resources/CNF/processed", // 7
            "/src/main/resources/knuth/patricia", // 8
            "/src/main/resources/knuth/patricia", // 9
    };
    public final static String[] patriciaFileName = {
            "KnuthsPatriciaExample1.txt", // 0
            "KnuthsPatriciaExample1.txt", // 1
            "KnuthsPatriciaExample2.txt", // 2
            "KnuthsPatriciaExample3.txt", // 3
            "KnuthsPatriciaExample4.txt", // 4
            "Analiza1-gss-20-s100_sorted.txt", // 5
            "Analiza1-openstacks-sequencedstrips-nonadl-nonnegated-os-sequencedstrips-p30_3.025-NOTKNOWN_sorted.txt", // 6
            "ShorterCNFExample_MoreNodesThanKnuthExample_sorted.txt", // 7
            "DeleteTestResultExample1.txt", // 8
            "DeleteTestResultExample2.txt", // 9
    };
    public final static char[] charEOCNF = {
            '\0', // 0
            '\0', // 1
            '\0', // 2
            '\0', // 3
            '\0', // 4
            '|', // 5
            '\n', // 6
            '\n', // 7
            '\0', // 8
            '\0', // 9
    };
    public final static char[] charInputCNFdelimiterReplacement = {
            '\0', // 0
            '\0', // 1
            '\0', // 2
            '\0', // 3
            '\0', // 4
            '_', // 5
            ' ', // 6
            ' ', // 7
            '\0', // 8
            '\0', // 9
    };
    public final static char[] charOutputEOF = {
            '\0', // 0
            '\0', // 1
            '\0', // 2
            '\0', // 3
            '\0', // 4
            ';', // 5
            ';', // 6
            ';', // 7
            '\0', // 8
            '\0', // 9
    };
    public final static char[] charEOF = {
            ';', // 0
            ';', // 1
            'Π', // 2
            'Π', // 3
            'Π', // 4
            charOutputEOF[5], // 5
            charOutputEOF[6], // 6
            charOutputEOF[7], // 7
            ';', // 8
            ';', // 9
    };
    public final static char[] charEOK = {
            ' ', // 0
            ' ', // 1
            'Φ', // 2
            'Φ', // 3
            'Φ', // 4
            charEOCNF[5], // 5
            charEOCNF[6], // 6
            charEOCNF[7], // 7
            ' ', // 8
            ' ', // 9
    };
    public final static WordStrategy[] wordStrategy = {
            WordStrategy.START_POSITION_TO_EOF, // 0
            WordStrategy.SINGLE, // 1
            WordStrategy.SINGLE, // 2
            WordStrategy.SINGLE, // 3
            WordStrategy.SINGLE, // 4
            WordStrategy.SINGLE, // 5
            WordStrategy.SINGLE, // 6
            WordStrategy.SINGLE, // 7
            WordStrategy.SINGLE, // 8
            WordStrategy.SINGLE, // 9
    };
    public final static Encoding[] encoding = {
            Encoding.MIX, // 0
            Encoding.JAVA, // 1
            Encoding.JAVA, // 2
            Encoding.JAVA, // 3
            Encoding.JAVA, // 4
            Encoding.JAVA, // 5
            Encoding.JAVA, // 6
            Encoding.JAVA, // 7
            Encoding.JAVA, // 8
            Encoding.JAVA, // 9
    };
    public final static String[][] keys = {
            {"THIS IS THE HOUSE THAT JACK BUILT;", "IS THE HOUSE THAT JACK BUILT;", "THE HOUSE THAT JACK BUILT;", "HOUSE THAT JACK BUILT;", "THAT JACK BUILT;", "JACK BUILT;", "BUILT;"}, // 0
            {"THIS ", "IS ", "THE ", "HOUSE ", "THAT ", "JACK ", "BUILT;"}, // 1
            {"THISΦ", "ISΦ", "THEΦ", "HOUSEΦ", "THATΦ", "JACKΦ", "BUILTΦΠ"}, // 2
            {"THISΦ", "ISΦΦ", "THEΦ", "HOUSEΦ", "THATΦ", "JACKΦ", "BUILTΦΦΦΦΠ"}, // 3
            {"THIS Φ", "IS ΦΦ", "THE Φ", "HOUSE Φ", "THAT Φ", "JACK Φ", "BUILT ;ΦΦΦΦΠ"}, // 4
            {null, null, null, null, null, null, null}, // 5
            null, // 6
            null, // 7
            { // 8
                "THIS ", "IS ", "THE ", "TEST ", "FILE ", "NUMBER ", "1 ", "FOR ", "DELETION ", "ONE ", "OF ", "NODES. ",
                "LONGESTWORDINTREE ", "TODELETE. ", "MORE ", "NODES ", "AFTER ", "NODE ", "TO ", "DELETE;"
            },
            { // 9
                    "THIS ", "IS ", "THE ", "TEST ", "FILE ", "NUMBER ", "1 ", "FOR ", "DELETION ", "ONE ", "OF ", "NODES. ",
                    "LONGESTWORDINTREExTODELETE. ", "MORE ", "NODES ", "AFTER ", "NODE ", "TO ", "DELETE;"
            },
    };
    public final static String[][] prefixes = {
            {"THIS", "IS", "THE", "HOUSE", "THAT", "JACK", "BUILT"}, // 0
            {"THIS", "IS", "THE", "HOUSE", "THAT", "JACK", "BUILT"}, // 1
            {"THIS", "IS", "THE", "HOUSE", "THAT", "JACK", "BUILTΦ"}, // 2
            {"THIS", "ISΦ", "THE", "HOUSE", "THAT", "JACK", "BUILTΦΦΦΦ"}, // 3
            {"THIS ", "IS Φ", "THE ", "HOUSE ", "THAT ", "JACK ", "BUILT ;ΦΦΦΦ"}, // 4
            {null, null, null, null, null, null, null}, // 5
            null, // 6
            null, // 7
            { // 8
                    "THIS", "IS", "THE", "TEST", "FILE", "NUMBER", "1", "FOR", "DELETION", "ONE", "OF", "NODES.",
                    "LONGESTWORDINTREE", "TODELETE.", "MORE", "NODES", "AFTER", "NODE", "TO", "DELETE"
            },
            { // 9
                    "THIS", "IS", "THE", "TEST", "FILE", "NUMBER", "1", "FOR", "DELETION", "ONE", "OF", "NODES.",
                    "LONGESTWORDINTREExTODELETE.", "MORE", "NODES", "AFTER", "NODE", "TO", "DELETE"
            },
    };
    public final static String[][] multipleNodePrefixes = {
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"}, // 0
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"}, // 1
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"}, // 2
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"}, // 3
            {"TH", "TH", "TH", "TH", "TH", "TH", "TH"}, // 4
            {null, null, null, null, null, null, null}, // 5
            null, // 6
            null, // 7
            null, // 8
            null, // 9
    };

    public static PatriciaTree getExamplePatriciaTree(int exampleIndex, boolean isDoConvert) {
        convertCnfSourceFileToPatriciaSourceFile(exampleIndex, isDoConvert);
        System.out.println(">>> creating patricia tree from source file <<<");
        PatriciaTree patriciaTree = createExamplePatriciaTree(exampleIndex);
        System.out.println(">>> created patricia tree from source file <<<");
        return patriciaTree;
    }

    public static void convertCnfSourceFileToPatriciaSourceFile(int exampleIndex, boolean isDoConvert) {
        if(isDoConvert) {
            System.out.println("You want to convert CNF file to PatriciaTree source file.");
            if(isExampleCnfDataFilled(exampleIndex)) {
                System.out.println("Converting CNF file to PatriciaTree source file...");
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
                System.out.println("Converted CNF file to PatriciaTree source file...");
            } else System.out.println("Can't convert file because at least one parameter for this example is not filled out.");
        } else System.out.println("You do not wish to convert CNF file to PatriciaTree source file.");
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
        return input != '\0';
    }

    static void sourceCodeFragmentNeededOnlyForWrittenPartOfEngineeringThesis() {
        try {
            final String cnfFilePath = "/src/main/resources/CNF/dimacs";
            final String cnfFileName = "Analiza1-gss-20-s100.cnf";
            final char charEOCNF = '|';
            final char charInputCNFdelimiterReplacement = '_';
            final char charOutputEOF = ';';
            final String patriciaFilePath = "/src/main/resources/CNF/processed";
            final String patriciaFileName = "Analiza1-gss-20-s100_sorted.txt";
            final char charEOF = charOutputEOF;
            final char charEOK = charEOCNF;
            final WordStrategy wordStrategy = WordStrategy.SINGLE;
            final Encoding encoding = Encoding.JAVA;

            CNFConverter cnfConverter = new CNFConverter(
                    cnfFilePath,
                    cnfFileName,
                    patriciaFilePath,
                    patriciaFileName,
                    charEOCNF,
                    charInputCNFdelimiterReplacement,
                    charOutputEOF
            );
            cnfConverter.convert();
            PatriciaTree patriciaTree = new PatriciaTree(patriciaFilePath, patriciaFileName, charEOF, charEOK, wordStrategy, encoding);

            int position = 0;
            int nextPosition = position;
            int cnfCounter = 0;
            String cnf = null;
            while ((cnf = cnfConverter.getCnfReader().readCNF()) != null && !cnf.isEmpty()) {
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
                } catch (NextWordStartIndexNotFoundException e) {
                    sortedCnf = new StringBuilder().append(sortedCnf).append(cnfConverter.getCnfWriter().getOutputEOF()).toString();
                    System.out.println(cnfCounter + ". \"" + sortedCnf + "\"");
                    assertEquals(sortedCnf, key);
                }
            }
            System.out.println("tested");
        } catch(Exception e) { e.printStackTrace(); };
    }
}
