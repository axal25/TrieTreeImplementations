package agh.jo;

import agh.jo.knuth.patricia.PatriciaTree;
import agh.jo.ui.PatriciaTreeVisualization;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Hello world!
 *
 */
public class App {
    public final static int AMOUNT_OF_ARGUMENTS_MIN = 2;
    public final static int EXAMPLE_NUMBER_INDEX_MIN = 0;
    public final static int EXAMPLE_NUMBER_INDEX_MAX = Examples.patriciaFileName.length-1;
    public final static boolean DEFAULT_IS_DO_CONVERT_CNF_SOURCE_FILE_TO_PATRICIA_SOURCE_FILE = false;
    // to run:
    // mvn clean package
    // java -jar target/TrieTreeImplementations_complete_standalone.jar Visual 0 false
    // java -jar target/TrieTreeImplementations_complete_standalone.jar Text 9 true


    public static void main( String[] args ) {
        parseRunArgs(args);
    }

    public static void parseRunArgs(String[] args) {
        if(args == null || args.length < AMOUNT_OF_ARGUMENTS_MIN)
            throw new UnsupportedOperationException("You must pass: \n" +
                    "1. \"text\" (only text processing) or \"visual\" (visual representation) for mode you wish to run this program \n" +
                    "2. number as an number of example you wish to run" +
                    " through String[] args in App.main(String[] args)\n" +
                    "3. (OPTIONALLY) \"true\" or \"false\" as flag to either convert CNF file or not");

        AppMode appMode = null;
        if(args[0].toLowerCase().compareTo("visual")==0) appMode = AppMode.VISUAL;
        else if(args[0].toLowerCase().compareTo("text")==0) appMode = AppMode.TEXT;
        else throw new UnsupportedOperationException("Your more you wish to run was different both from \"visual\" and \"text\"");

        int exampleNumber = Integer.parseInt(args[1]);
        if(exampleNumber < EXAMPLE_NUMBER_INDEX_MIN || exampleNumber > EXAMPLE_NUMBER_INDEX_MAX)
            throw new UnsupportedOperationException("Your example number is either lower than " + EXAMPLE_NUMBER_INDEX_MIN +
                    " or higher than " + EXAMPLE_NUMBER_INDEX_MAX);

        boolean isDoConvert = DEFAULT_IS_DO_CONVERT_CNF_SOURCE_FILE_TO_PATRICIA_SOURCE_FILE;
        if(args.length > AMOUNT_OF_ARGUMENTS_MIN) {
            isDoConvert = Boolean.parseBoolean(args[2].toLowerCase());
        }

        PatriciaTree patriciaTree = Examples.getExamplePatriciaTree(exampleNumber, isDoConvert);
        if( appMode == AppMode.VISUAL ) {
            if(exampleNumber == 5 || exampleNumber == 6)
                throw new UnsupportedOperationException("Visual application mode can't handle this many nodes in its current implementation");
            PatriciaTreeVisualization.patriciaTree = patriciaTree;
            PatriciaTreeVisualization.main(null);
        }
    }
}
