package agh.jo;

import agh.jo.knuth.patricia.PatriciaTree;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        PatriciaTree patriciaTree1 = Examples.getExamplePatriciaTree(0);
        PatriciaTree patriciaTree2 = Examples.getExamplePatriciaTree(1);
        PatriciaTree patriciaTree3 = Examples.getExamplePatriciaTree(2);
        PatriciaTree patriciaTree4 = Examples.getExamplePatriciaTree(3);
        PatriciaTree patriciaTree5 = Examples.getExamplePatriciaTree(4);
        PatriciaTree patriciaTree6 = Examples.getExamplePatriciaTree(5);
        PatriciaTree patriciaTree7 = Examples.getExamplePatriciaTree(6);
    }

}
