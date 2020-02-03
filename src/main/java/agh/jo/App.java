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
    public final static String fileName = "KnuthsPatriciaExample3.txt";
    public final static char charEOF = 'Π';
    public final static char charEOK = 'Φ';
    public final static WordStrategy wordStrategy = WordStrategy.SINGLE;
    public final static Encoding encoding = Encoding.JAVA;

    public static void main( String[] args ) {
        PatriciaTree patriciaTree = getNewInitiatedTree(filePath, fileName, charEOF, charEOK, wordStrategy, encoding);
        checkPrefix(patriciaTree, "THIS");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkPrefix(patriciaTree, "IS");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkPrefix(patriciaTree, "THE");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkPrefix(patriciaTree, "HOUSE");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkPrefix(patriciaTree, "THAT");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkPrefix(patriciaTree, "JACK");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
        checkPrefix(patriciaTree, "BUILT");
        checkPrefix(patriciaTree, "TH");

        System.out.println();
        insertNextKeyIntoTree(patriciaTree);
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
}
