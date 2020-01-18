package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AlgorithmP {
    private PatriciaTree owner;
    private PatriciaNode currentNode, previousNode, latestInsertedNode;
    private int currentSearchWordBitIndex, currentNodeBitIndex, longestMatchingBitStreak, keyValueKeyToInsertFromFile;
    private String binarySearchWordString, binaryStringKeyToInsertFromFile;

    /** Setters **/

    private AlgorithmP() {}
    protected AlgorithmP(PatriciaTree owner) {
        this.owner = owner;
        setPreviousNode(getOwner().getHeader());
        setCurrentNode(getOwner().getHeader());
        setLatestInsertedNode(getOwner().getHeader());
        setCurrentSearchWordBitIndex(0);
        setCurrentNodeBitIndex(0);
        setLongestMatchingBitStreak(-1);
        setKeyValueKeyToInsertFromFile(-1);
        setBinarySearchWordString(null);
        setBinaryStringKeyToInsertFromFile(null);
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
        setCurrentNodeBitIndex(0);
        System.out.println("isContaining() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString() + " >>> moveLeft()");
        return moveLeft();
    }

    /** insertNextKeyIntoTree - Insert **/

    public void insertNextKeyIntoTree(String binaryStringNextKeyToInsertFromFile, int filePositionNextKeyToInsert) throws Exception {
        setBinaryStringKeyToInsertFromFile(binaryStringNextKeyToInsertFromFile);
        setKeyValueKeyToInsertFromFile(filePositionNextKeyToInsert);
        System.out.println("insertNextKeyIntoTree() >>> this.binaryStringKeyToInsertFromFile: " + this.binaryStringKeyToInsertFromFile);
        System.out.println("insertNextKeyIntoTree() >>> this.keyValueKeyToInsertFromFile: " + this.keyValueKeyToInsertFromFile);
        this.longestMatchingBitStreak = getMaxLengthOfMatchingBits();
        this.currentNode = getNodeWithMaxMatchingBits(this.longestMatchingBitStreak);
        this.previousNode = getNodeHavingAsChildNodeWithMaxMatchingBits();
        insertNewNodeInPlaceOfNodeWithMaxMatchingBits(this.longestMatchingBitStreak, this.currentNode, this.previousNode);
    }

    /** Protected (internal) methods **/
    /** insertNextKeyIntoTree - Insert **/

    protected int getMaxLengthOfMatchingBits() throws Exception {
        if(isContaining(this.binaryStringKeyToInsertFromFile)) throw new Exception("The key you are trying to insert is matching existing key. No key should be a prefix of another key.");
        else {
            System.out.println("getMaxLengthOfMatchingBits() >>> this.longestMatchingBitStreak: " + this.longestMatchingBitStreak);
            return this.longestMatchingBitStreak;
        }
    }

    protected PatriciaNode getNodeWithMaxMatchingBits(int maxLengthOfMatchingBits) throws Exception {
        String matchingBitsKeyToInsertFromFile = this.owner.cutStringToLength(this.binaryStringKeyToInsertFromFile, maxLengthOfMatchingBits);
        System.out.println("getNodeWithMaxMatchingBits() >>> matchingBitsKeyToInsertFromFile: " + matchingBitsKeyToInsertFromFile);
        if(isContaining(matchingBitsKeyToInsertFromFile)) {
            PatriciaNode latestComperedNodeToKeyToInsert = this.currentNode;
            System.out.println("getNodeWithMaxMatchingBits() >>> this.currentNode: " + this.currentNode);
            return latestComperedNodeToKeyToInsert;
        } else throw new Exception("This should not happen. Execution of isContaining method should be successful " +
                "because we have chosen bits of new key to insert that are matching some existing node inside the tree. " +
                "Invalid \"matching\" bits of new key to insert into the tree: " + matchingBitsKeyToInsertFromFile);
    }

    protected PatriciaNode getNodeHavingAsChildNodeWithMaxMatchingBits() {
        PatriciaNode secondToLatestComperedNodeToKeyToInsert = this.previousNode;
        System.out.println("getNodeHavingAsChildNodeWithMaxMatchingBits() >>> this.previousNode: " + this.previousNode);
        return secondToLatestComperedNodeToKeyToInsert;
    }

    protected void insertNewNodeInPlaceOfNodeWithMaxMatchingBits(int maxLengthOfMatchingBits,
                                                                 PatriciaNode nodeWithMaxMatchingBits,
                                                                 PatriciaNode nodeHavingAsChildNodeWithMaxMatchingBits
    ) throws Exception {
        int id = this.owner.getCurrentNodeId();
        this.owner.incrementCurrentNodeId();
        int key = this.keyValueKeyToInsertFromFile;
        int skip = -1;
        PatriciaNode leftLink = null;
        PatriciaNode rightLink = null;
        boolean isLeftAncestor = false;
        boolean isRightAncestor = false;
        PatriciaNode newNode = new PatriciaNode(id, key, skip, leftLink, rightLink, isLeftAncestor, isRightAncestor);
        this.latestInsertedNode = newNode;

        this.longestMatchingBitStreak = maxLengthOfMatchingBits;
        this.currentNode = nodeWithMaxMatchingBits;
        this.previousNode = nodeHavingAsChildNodeWithMaxMatchingBits;

        boolean oldTagValueFromPreviousToCurrentNode = false;
        if(this.previousNode.getLeftLink() == this.currentNode) {
            oldTagValueFromPreviousToCurrentNode = this.previousNode.getIsLeftAncestor();
            this.previousNode.setLeftLink(newNode);
            this.previousNode.setIsLeftAncestor(false);
        } else if(this.previousNode.getRightLink() == this.currentNode) {
            oldTagValueFromPreviousToCurrentNode = this.previousNode.getIsRightAncestor();
            this.previousNode.setRightLink(newNode);
            this.previousNode.setIsRightAncestor(false);
        } else throw new Exception("Neither left or right link of previous node points to current node.");

        if(this.binaryStringKeyToInsertFromFile.charAt(this.longestMatchingBitStreak) == '0') {
            newNode.setIsLeftAncestor(true);
            newNode.setLeftLink(newNode);
            newNode.setIsRightAncestor(oldTagValueFromPreviousToCurrentNode);
            newNode.setRightLink(this.currentNode);
        } else if(this.binaryStringKeyToInsertFromFile.charAt(this.longestMatchingBitStreak) == '1') {
            newNode.setIsRightAncestor(true);
            newNode.setRightLink(newNode);
            newNode.setIsLeftAncestor(oldTagValueFromPreviousToCurrentNode);
            newNode.setLeftLink(this.currentNode);
        } else throw new Exception("The word you are trying to insert into a tree has in its binary representation" +
                " on position " + this.longestMatchingBitStreak + " an invalid character (not 0 or 1): " + this.binaryStringKeyToInsertFromFile.charAt(this.longestMatchingBitStreak));

        if(oldTagValueFromPreviousToCurrentNode) {
            newNode.setSkip(1 + this.longestMatchingBitStreak - (this.currentSearchWordBitIndex + 1)); //(this.currentSearchWordBitIndex + 1) i zmiana pozycji startowej na 0?
//            if(newNode.getSkip() < 0) newNode.setSkip(0);
        } else {
            newNode.setSkip(1 + this.longestMatchingBitStreak - (this.currentSearchWordBitIndex + 1) + this.currentNode.getSkip());
//            if(newNode.getSkip() < 0) newNode.setSkip(0);
            this.currentNode.setSkip((this.currentSearchWordBitIndex + 1) - this.longestMatchingBitStreak - 1);
//            if(this.currentNode.getSkip() < 0) this.currentNode.setSkip(0);
        }
    }

    /** Protected (internal) methods **/
    /** isContaining - Look-up **/

    // Ruch w lewo
    protected boolean moveLeft() throws Exception {
        setPreviousNode(getCurrentNode());
        setCurrentNode(getPreviousNode().getLeftLink());
        System.out.print("moveLeft() >>> getPreviousNode(): " + getPreviousNode());
        if(getPreviousNode().getIsLeftAncestor()) {
            System.out.println(" moveLeft() >>> getPreviousNode().getIsLeftAncestor() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
            return compareKeyToSearchWord();
        } else {
            System.out.println(" moveLeft() >>> !getPreviousNode().getIsLeftAncestor() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
            return skipBits();
        }
    }

    // Ominiecie bitow
    protected boolean skipBits() throws Exception {
        System.out.println("skipBits() >>> getCurrentSearchWordBitIndex(): " + getCurrentSearchWordBitIndex() + " := " + (getCurrentSearchWordBitIndex() + getCurrentNode().getSkip()));
        setCurrentSearchWordBitIndex(getCurrentSearchWordBitIndex() + getCurrentNode().getSkip());
        if(getCurrentSearchWordBitIndex() >= getBinarySearchWordString().length()) {
            System.out.println("skipBits() >>> getCurrentSearchWordBitIndex() >= getBinarySearchWordString().length() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
            return compareKeyToSearchWord();
        } else {
            System.out.println("skipBits() >>> getCurrentSearchWordBitIndex() < getBinarySearchWordString().length() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
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
                    " >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
            return moveRight();
        }
        else if(getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '0') {
            System.out.println("bitCheck() >>> getBinarySearchWordString().charAt(getCurrentSearchWordBitIndex()) == '0'" +
                    " >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
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
            System.out.println("moveRight() >>> getPreviousNode().getIsRightAncestor() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
            return compareKeyToSearchWord();
        }
        else {
            System.out.println("moveRight() >>> !getPreviousNode().getIsRightAncestor() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
            return skipBits();
        }
    }

    // Porownanie klucza do argumentu przeszukiwania
    protected boolean compareKeyToSearchWord() throws Exception {
        String currentNodeNumberOfBitsFromFile = this.owner.getNumberOfBitsFromFileAtPosition(
                getBinarySearchWordString().length(),
                getCurrentNode().getKey()
        );
        System.out.println("compareKeyToSearchWord() >>> ((AlgorithmP) this).toString(): " + ((AlgorithmP) this).toString());
        System.out.println("compareKeyToSearchWord() >>> currentNodeNumberOfBitsFromFile: " + currentNodeNumberOfBitsFromFile);
        System.out.println("compareKeyToSearchWord() >>> getBinarySearchWordString(): " + getBinarySearchWordString());
        for (this.currentNodeBitIndex = 0; this.currentNodeBitIndex < getBinarySearchWordString().length(); this.currentNodeBitIndex++) {
            if(
                    getBinarySearchWordString().charAt(this.currentNodeBitIndex)
                    !=
                    currentNodeNumberOfBitsFromFile.charAt(this.currentNodeBitIndex)
            ) {
                System.out.println("compareKeyToSearchWord() >>> return false;");
                return false;
            } else if(this.longestMatchingBitStreak < this.currentNodeBitIndex) {
                this.longestMatchingBitStreak = this.currentNodeBitIndex;
                System.out.println("compareKeyToSearchWord() >>> this.longestMatchingBitStreak:= " + this.longestMatchingBitStreak);
            }
        }
        System.out.println("compareKeyToSearchWord() >>> return true;");
        return true;
    }

    @Override
    public String toString() {
        return "AlgorithmP{" +
                "\n\t\towner=" + (owner==null?"PatriciaTree{ null }":"PatriciaTree{ !null }") +
                ",\n\t\tcurrentNode" + (currentNode==null?"=null":".getId()="+currentNode.getId()) +
                ",\n\t\tpreviousNode" + (previousNode==null?"=null":".getId()="+previousNode.getId()) +
                ",\n\t\tlatestInsertedNode" + (latestInsertedNode==null?"=null":".getId()="+latestInsertedNode.getId()) +
                ",\n\t\tcurrentSearchWordBitIndex=" + currentSearchWordBitIndex +
                ",\n\t\tcurrentNodeBitIndex=" + currentNodeBitIndex +
                ",\n\t\tlongestMatchingBitStreak=" + longestMatchingBitStreak +
                ",\n\t\tkeyValueKeyToInsertFromFile=" + keyValueKeyToInsertFromFile +
                ",\n\t\tbinarySearchWordString='" + binarySearchWordString + '\'' +
                ",\n\t\tbinaryStringKeyToInsertFromFile='" + binaryStringKeyToInsertFromFile + '\'' +
                "\n\t}";
    }
}
