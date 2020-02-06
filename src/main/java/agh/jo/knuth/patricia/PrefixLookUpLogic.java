package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PrefixLookUpLogic {
    private PatriciaTree owner;
    private PatriciaNode currentNode, previousNode;
    private int currentSearchWordBitIndex, longestMatchingBitStreak;
    private String binarySearchWordString;

    /** Setters **/

    private PrefixLookUpLogic() {}
    protected PrefixLookUpLogic(PatriciaTree owner) {
        this.owner = owner;
        setPreviousNode(getOwner().getHeader());
        setCurrentNode(getOwner().getHeader());
        setCurrentSearchWordBitIndex(0);
        setLongestMatchingBitStreak(-1);
        setBinarySearchWordString(null);
    }

    /** Normal methods **/
    /** Public methods **/

    /** Algorithm P - isContainingPrefix - Look-up Prefix **/

    protected boolean isContainingPrefix(String binarySearchWordString) throws Exception {
        setBinarySearchWordString( binarySearchWordString );
        setPreviousNode(getOwner().getHeader());
        setCurrentNode(getOwner().getHeader());
        setCurrentSearchWordBitIndex(0);
        setLongestMatchingBitStreak(0);
        if( PatriciaTree.printProcessingInfo ) System.out.println("isContainingPrefix() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString() + " >>> moveLeft()");
        return moveLeft();
    }

    /** Protected (internal) methods **/
    /** isContainingPrefix - Look-up Prefix **/

    // Ruch w lewo
    protected boolean moveLeft() throws Exception {
        setPreviousNode(getCurrentNode());
        setCurrentNode(getPreviousNode().getLeftLink());
        if( PatriciaTree.printProcessingInfo ) System.out.print("moveLeft() >>> getPreviousNode(): " + getPreviousNode());
        if(getPreviousNode().getIsLeftAncestor()) {
            if( PatriciaTree.printProcessingInfo ) System.out.println(" moveLeft() >>> getPreviousNode().getIsLeftAncestor() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return compareKeyToSearchWord();
        } else {
            if( PatriciaTree.printProcessingInfo ) System.out.println(" moveLeft() >>> !getPreviousNode().getIsLeftAncestor() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return skipBits();
        }
    }

    // Ominiecie bitow
    protected boolean skipBits() throws Exception {
        if( PatriciaTree.printProcessingInfo ) System.out.println("skipBits() >>> getCurrentSearchWordBitIndex(): " + getCurrentSearchWordBitIndex() + " := " + (getCurrentSearchWordBitIndex() + getCurrentNode().getSkip()));
        setCurrentSearchWordBitIndex(getCurrentSearchWordBitIndex() + getCurrentNode().getSkip());
        if(getCurrentSearchWordBitIndex() >= getBinarySearchWordString().length()) {
            if( PatriciaTree.printProcessingInfo ) System.out.println("skipBits() >>> getCurrentSearchWordBitIndex() >= getBinarySearchWordString().length() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return compareKeyToSearchWord();
        } else {
            if( PatriciaTree.printProcessingInfo ) System.out.println("skipBits() >>> getCurrentSearchWordBitIndex() < getBinarySearchWordString().length() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return bitCheck();
        }
    }

    // Sprawdzenie bitu
    protected boolean bitCheck() throws Exception {
        if( PatriciaTree.printProcessingInfo ) System.out.println("bitCheck() >>> getBinarySearchWordString(): " + getBinarySearchWordString() +
                ", getCurrentSearchWordBitIndex(): " + getCurrentSearchWordBitIndex() +
                ", getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()): " + getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()));
        if(getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '1') {
            if( PatriciaTree.printProcessingInfo ) System.out.println("bitCheck() >>> getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '1'" +
                    " >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return moveRight();
        }
        else if(getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '0') {
            if( PatriciaTree.printProcessingInfo ) System.out.println("bitCheck() >>> getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '0'" +
                    " >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return moveLeft();
        } else throw new Exception("Word you are trying to look-up has in its binary representation invalid character " +
                "(neither 1 or 0) at position: " + getCurrentSearchWordBitIndex() +
                " character: " + getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()));
    }

    // Ruch w prawo
    protected boolean moveRight() throws Exception {
        setPreviousNode(getCurrentNode());
        setCurrentNode(getPreviousNode().getRightLink());
        if( PatriciaTree.printProcessingInfo ) System.out.print("moveRight() >>> getPreviousNode(): " + getPreviousNode());
        if(getPreviousNode().getIsRightAncestor()) {
            if( PatriciaTree.printProcessingInfo ) System.out.println("moveRight() >>> getPreviousNode().getIsRightAncestor() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return compareKeyToSearchWord();
        }
        else {
            if( PatriciaTree.printProcessingInfo ) System.out.println("moveRight() >>> !getPreviousNode().getIsRightAncestor() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
            return skipBits();
        }
    }

    // Porownanie klucza do argumentu przeszukiwania
    protected boolean compareKeyToSearchWord() throws Exception {
        String currentNodeNumberOfBitsFromFile= this.owner.getNumberOfBitsFromFileAtPosition(
                getBinarySearchWordString().length(),
                getCurrentNode().getKey()
        );
        if (PatriciaTree.printProcessingInfo)
            System.out.println("compareKeyToSearchWord() >>> ((AlgorithmP) this).toString(): " + ((PrefixLookUpLogic) this).toString());
        if (PatriciaTree.printProcessingInfo)
            System.out.println("compareKeyToSearchWord() >>> currentNodeNumberOfBitsFromFile: " + currentNodeNumberOfBitsFromFile);
        if (PatriciaTree.printProcessingInfo)
            System.out.println("compareKeyToSearchWord() >>> getBinarySearchWordString(): " + getBinarySearchWordString());
        for (int currentNodeBitIndex = 0; currentNodeBitIndex < getBinarySearchWordString().length(); currentNodeBitIndex++) {
            if (
                    getBinarySearchWordString().charAt(currentNodeBitIndex)
                            !=
                            currentNodeNumberOfBitsFromFile.charAt(currentNodeBitIndex)
            ) {
                if (PatriciaTree.printProcessingInfo)
                    System.out.println("compareKeyToSearchWord() >>> return false;");
                return false;
            } else if (this.longestMatchingBitStreak < (currentNodeBitIndex + 1)) {
                this.longestMatchingBitStreak = (currentNodeBitIndex + 1);
                if (PatriciaTree.printProcessingInfo)
                    System.out.println("compareKeyToSearchWord() >>> this.longestMatchingBitStreak:= " + this.longestMatchingBitStreak + ", currentNodeBitIndex: " + currentNodeBitIndex);
            }
        }
        if (PatriciaTree.printProcessingInfo) System.out.println("compareKeyToSearchWord() >>> return true;");
        return true;
    }

    @Override
    public String toString() {
        return "PrefixLookUpLogic{" +
                "\n\t\towner=" + (owner==null?"PatriciaTree{ null }":"PatriciaTree{ !null }") +
                ",\n\t\tcurrentNode" + (currentNode==null?"=null":".getId()="+currentNode.getId()) +
                ",\n\t\tpreviousNode" + (previousNode==null?"=null":".getId()="+previousNode.getId()) +
                ",\n\t\tcurrentSearchWordBitIndex=" + currentSearchWordBitIndex +
                ",\n\t\tlongestMatchingBitStreak=" + longestMatchingBitStreak +
                ",\n\t\tbinarySearchWordString='" + binarySearchWordString + '\'' +
                "\n\t}";
    }
}
