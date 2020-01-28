package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LookUpLogic {
    private PatriciaTree owner;
    private PatriciaNode currentNode, previousNode;
    private int currentSearchWordBitIndex, longestMatchingBitStreak;
    private String binarySearchWordString;

    /** Setters **/

    private LookUpLogic() {}
    protected LookUpLogic(PatriciaTree owner) {
        this.owner = owner;
        setPreviousNode(getOwner().getHeader());
        setCurrentNode(getOwner().getHeader());
        setCurrentSearchWordBitIndex(0);
        setLongestMatchingBitStreak(-1);
        setBinarySearchWordString(null);
    }

    /** Normal methods **/
    /** Public methods **/

    /** Algorithm P - isContaining - Look-up **/

    protected boolean isContaining(String binarySearchWordString) throws Exception {
        setBinarySearchWordString( binarySearchWordString );
        setPreviousNode(getOwner().getHeader());
        setCurrentNode(getOwner().getHeader());
        setCurrentSearchWordBitIndex(0);
        setLongestMatchingBitStreak(0);
        System.out.println("isContaining() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString() + " >>> moveLeft()");
        return moveLeft();
    }

    /** Protected (internal) methods **/
    /** isContaining - Look-up **/

    // Ruch w lewo
    protected boolean moveLeft() throws Exception {
        setPreviousNode(getCurrentNode());
        setCurrentNode(getPreviousNode().getLeftLink());
        System.out.print("moveLeft() >>> getPreviousNode(): " + getPreviousNode());
        if(getPreviousNode().getIsLeftAncestor()) {
            System.out.println(" moveLeft() >>> getPreviousNode().getIsLeftAncestor() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return compareKeyToSearchWord();
        } else {
            System.out.println(" moveLeft() >>> !getPreviousNode().getIsLeftAncestor() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return skipBits();
        }
    }

    // Ominiecie bitow
    protected boolean skipBits() throws Exception {
        System.out.println("skipBits() >>> getCurrentSearchWordBitIndex(): " + getCurrentSearchWordBitIndex() + " := " + (getCurrentSearchWordBitIndex() + getCurrentNode().getSkip()));
        setCurrentSearchWordBitIndex(getCurrentSearchWordBitIndex() + getCurrentNode().getSkip());
        if(getCurrentSearchWordBitIndex() >= getBinarySearchWordString().length()) {
            System.out.println("skipBits() >>> getCurrentSearchWordBitIndex() >= getBinarySearchWordString().length() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return compareKeyToSearchWord();
        } else {
            System.out.println("skipBits() >>> getCurrentSearchWordBitIndex() < getBinarySearchWordString().length() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return bitCheck();
        }
    }

    // Sprawdzenie bitu
    protected boolean bitCheck() throws Exception {
        System.out.println("bitCheck() >>> getBinarySearchWordString(): " + getBinarySearchWordString() +
                ", getCurrentSearchWordBitIndex(): " + getCurrentSearchWordBitIndex() +
                ", getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()): " + getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()));
        if(getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '1') {
            System.out.println("bitCheck() >>> getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '1'" +
                    " >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return moveRight();
        }
        else if(getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '0') {
            System.out.println("bitCheck() >>> getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '0'" +
                    " >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return moveLeft();
        } else throw new Exception("Word you are trying to look-up has in its binary representation invalid character " +
                "(neither 1 or 0) at position: " + getCurrentSearchWordBitIndex() +
                " character: " + getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()));
    }

    // Ruch w prawo
    protected boolean moveRight() throws Exception {
        setPreviousNode(getCurrentNode());
        setCurrentNode(getPreviousNode().getRightLink());
        System.out.print("moveRight() >>> getPreviousNode(): " + getPreviousNode());
        if(getPreviousNode().getIsRightAncestor()) {
            System.out.println("moveRight() >>> getPreviousNode().getIsRightAncestor() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return compareKeyToSearchWord();
        }
        else {
            System.out.println("moveRight() >>> !getPreviousNode().getIsRightAncestor() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
            return skipBits();
        }
    }

    // Porownanie klucza do argumentu przeszukiwania
    protected boolean compareKeyToSearchWord() throws Exception {
        String currentNodeNumberOfBitsFromFile = this.owner.getNumberOfBitsFromFileAtPosition(
                getBinarySearchWordString().length(),
                getCurrentNode().getKey()
        );
        System.out.println("compareKeyToSearchWord() >>> ((AlgorithmP) this).toString(): " + ((LookUpLogic) this).toString());
        System.out.println("compareKeyToSearchWord() >>> currentNodeNumberOfBitsFromFile: " + currentNodeNumberOfBitsFromFile);
        System.out.println("compareKeyToSearchWord() >>> getBinarySearchWordString(): " + getBinarySearchWordString());
        for (int currentNodeBitIndex = 0; currentNodeBitIndex < getBinarySearchWordString().length(); currentNodeBitIndex++) {
            if(
                    getBinarySearchWordString().charAt(currentNodeBitIndex)
                    !=
                    currentNodeNumberOfBitsFromFile.charAt(currentNodeBitIndex)
            ) {
                System.out.println("compareKeyToSearchWord() >>> return false;");
                return false;
            } else if(this.longestMatchingBitStreak < (currentNodeBitIndex + 1)) {
                this.longestMatchingBitStreak = (currentNodeBitIndex + 1);
                System.out.println("compareKeyToSearchWord() >>> this.longestMatchingBitStreak:= " + this.longestMatchingBitStreak + ", currentNodeBitIndex: " + currentNodeBitIndex);
            }
        }
        System.out.println("compareKeyToSearchWord() >>> return true;");
        return true;
    }

    @Override
    public String toString() {
        return "LookUpLogic{" +
                "\n\t\towner=" + (owner==null?"PatriciaTree{ null }":"PatriciaTree{ !null }") +
                ",\n\t\tcurrentNode" + (currentNode==null?"=null":".getId()="+currentNode.getId()) +
                ",\n\t\tpreviousNode" + (previousNode==null?"=null":".getId()="+previousNode.getId()) +
                ",\n\t\tcurrentSearchWordBitIndex=" + currentSearchWordBitIndex +
                ",\n\t\tlongestMatchingBitStreak=" + longestMatchingBitStreak +
                ",\n\t\tbinarySearchWordString='" + binarySearchWordString + '\'' +
                "\n\t}";
    }
}
