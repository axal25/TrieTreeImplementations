package agh.jo;

import agh.jo.knuth.patricia.Encoding;
import agh.jo.knuth.patricia.PatriciaNode;
import agh.jo.knuth.patricia.PatriciaTree;
import agh.jo.knuth.patricia.file.ops.WordStrategy;

/**
 * Hello world!
 *
 */
public class App {
    public final static String filePath = "/src/main/resources/knuth/patricia";
    public final static String[] fileName = {"KnuthsPatriciaExample1.txt", "KnuthsPatriciaExample2.txt", "KnuthsPatriciaExample3.txt"};
    public final static char[] charEOF = {';', 'Π', 'Π'};
    public final static char[] charEOK = {' ', 'Φ', 'Φ'};
    public final static WordStrategy wordStrategy = WordStrategy.SINGLE;
    public final static Encoding encoding = Encoding.JAVA;

    public static void main( String[] args ) {
//        PatriciaTree patriciaTree1 = getExamplePatriciaTree1();
//        PatriciaTree patriciaTree2 = getExamplePatriciaTree2();
        PatriciaTree patriciaTree3 = getExamplePatriciaTree3();
    }

    public static PatriciaTree getExamplePatriciaTree1() {
        PatriciaTree patriciaTree = getNewInitiatedTree(filePath, fileName[0], charEOF[0], charEOK[0], wordStrategy, encoding);
        checkKey(patriciaTree, "THIS ");
        checkKey(patriciaTree, "THIS");
        checkPrefix(patriciaTree, "THIS");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "IS ");
        checkKey(patriciaTree, "IS");
        checkPrefix(patriciaTree, "IS");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "THE ");
        checkKey(patriciaTree, "THE");
        checkPrefix(patriciaTree, "THE");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "HOUSE ");
        checkKey(patriciaTree, "HOUSE");
        checkPrefix(patriciaTree, "HOUSE");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "THAT ");
        checkKey(patriciaTree, "THAT");
        checkPrefix(patriciaTree, "THAT");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "JACK ");
        checkKey(patriciaTree, "JACK");
        checkPrefix(patriciaTree, "JACK");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "BUILT;");
        checkKey(patriciaTree, "BUILT");
        checkPrefix(patriciaTree, "BUILT");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        System.out.println();

        System.out.println("patriciaTree: " + patriciaTree);
        System.out.println();

        if(patriciaTree == null) throw new NullPointerException("patriciaTree: " + patriciaTree);
        return patriciaTree;
    }

    public static PatriciaTree getExamplePatriciaTree2() {
        PatriciaTree patriciaTree = getNewInitiatedTree(filePath, fileName[1], charEOF[1], charEOK[1], wordStrategy, encoding);
        checkKey(patriciaTree, "THISΦ");
        checkKey(patriciaTree, "THIS");
        checkPrefix(patriciaTree, "THIS");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "ISΦ");
        checkKey(patriciaTree, "IS");
        checkPrefix(patriciaTree, "IS");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "THEΦ");
        checkKey(patriciaTree, "THE");
        checkPrefix(patriciaTree, "THE");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "HOUSEΦ");
        checkKey(patriciaTree, "HOUSE");
        checkPrefix(patriciaTree, "HOUSE");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "THATΦ");
        checkKey(patriciaTree, "THAT");
        checkPrefix(patriciaTree, "THAT");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "JACKΦ");
        checkKey(patriciaTree, "JACK");
        checkPrefix(patriciaTree, "JACK");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "BUILTΦΠ");
        checkKey(patriciaTree, "BUILTΦ");
        checkPrefix(patriciaTree, "BUILTΦ");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        System.out.println();

        System.out.println("patriciaTree: " + patriciaTree);
        System.out.println();

        if(patriciaTree == null) throw new NullPointerException("patriciaTree: " + patriciaTree);
        return patriciaTree;
    }

    public static PatriciaTree getExamplePatriciaTree3() {
        PatriciaTree patriciaTree = getNewInitiatedTree(filePath, fileName[2], charEOF[2], charEOK[2], wordStrategy, encoding);
        checkKey(patriciaTree, "THISΦ");
        checkKey(patriciaTree, "THIS");
        checkPrefix(patriciaTree, "THIS");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "ISΦΦ");
        checkKey(patriciaTree, "ISΦ");
        checkPrefix(patriciaTree, "IS");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "THEΦ");
        checkKey(patriciaTree, "THE");
        checkPrefix(patriciaTree, "THE");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "HOUSEΦ");
        checkKey(patriciaTree, "HOUSE");
        checkPrefix(patriciaTree, "HOUSE");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "THATΦ");
        checkKey(patriciaTree, "THAT");
        checkPrefix(patriciaTree, "THAT");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "JACKΦ");
        checkKey(patriciaTree, "JACK");
        checkPrefix(patriciaTree, "JACK");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkKey(patriciaTree, "BUILTΦΦΦΦΠ");
        checkKey(patriciaTree, "BUILTΦΦΦΦ");
        checkPrefix(patriciaTree, "BUILT");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        System.out.println();

        System.out.println("patriciaTree: " + patriciaTree);
        System.out.println();

        if(patriciaTree == null) throw new NullPointerException("patriciaTree: " + patriciaTree);
        return patriciaTree;
    }

    public static PatriciaTree getNewInitiatedTree(String filePath, String fileName, char charEOF, char charEOK, WordStrategy wordStrategy, Encoding encoding) {
        try {
            PatriciaTree patriciaTree = new PatriciaTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
            System.out.println("PatriciaTree initiated with header node for first key");
            return patriciaTree;
        } catch (Exception e) {
            System.out.println("PatriciaTree could not be initiated");
            return null;
        }
    }

    public static void insertNextKeyIntoTree(PatriciaTree patriciaTree) {
        try {
            patriciaTree.insertNextKeyIntoTree();
            System.out.println("Inserted new node into PatriciaTree");
        } catch (Exception e) {
            System.out.println("Could not insert new node into PatriciaTree");
        }
    }

    public static void checkPrefix(PatriciaTree patriciaTree, String searchWord) {
        try {
            if(patriciaTree.isContainingPrefix(searchWord)) {
                System.out.println("\"" + searchWord + "\" is a prefix contained inside PatriciaTree");
                PatriciaNode[] nodesContainingPrefix = patriciaTree.findNodesMatchingPrefix(searchWord);
                for (PatriciaNode node:nodesContainingPrefix) {
                    System.out.println("\"" + searchWord + "\" is a prefix contained inside PatriciaNode with id: " + node.getId());
                }
            } else {
                System.out.println("\"" + searchWord + "\" is NOT a prefix contained inside PatriciaTree");
            }
        } catch (Exception e) {
            System.out.println("Search word \"" + searchWord + "\" caused some kind of exception.");
        }
    }

    public static void checkKey(PatriciaTree patriciaTree, String searchWord) {
        try {
            if(patriciaTree.isContainingKey(searchWord)) {
                System.out.println("\"" + searchWord + "\" is a key contained inside PatriciaTree");
            } else {
                System.out.println("\"" + searchWord + "\" is NOT a key contained inside PatriciaTree");
            }
        } catch (Exception e) {
            System.out.println("Search word \"" + searchWord + "\" caused some kind of exception.");
            e.printStackTrace();
        }
    }
}
