package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyInsertLogic {
    private PatriciaTree owner;
    private PatriciaNode latestInsertedNode;

    public KeyInsertLogic(PatriciaTree owner) {
        setOwner(owner);
        setLatestInsertedNode(getOwner().getHeader());
    }

    /** Setters **/

    /** Normal methods **/
    /** Public methods **/

    /** insertNextKeyIntoTree - Insert Key **/

    public void insertNextKeyIntoTree(String binaryStringNextKeyToInsertFromFile, int filePositionNextKeyToInsert) throws Exception {
        if(latestInsertedNode == null) setLatestInsertedNode(getOwner().getHeader());
        if( PatriciaTree.printProcessingInfo ) System.out.println("insertNextKeyIntoTree() >>> binaryStringNextKeyToInsertFromFile: " + binaryStringNextKeyToInsertFromFile);
        if( PatriciaTree.printProcessingInfo ) System.out.println("insertNextKeyIntoTree() >>> filePositionNextKeyToInsert: " + filePositionNextKeyToInsert);
        PrefixLookUpLogic prefixLookUpLogic = new PrefixLookUpLogic(getOwner());
        prefixLookUpLogic.setLongestMatchingBitStreak( getMaxLengthOfMatchingBits(prefixLookUpLogic, binaryStringNextKeyToInsertFromFile ) );
        prefixLookUpLogic.setCurrentNode( getNodeWithMaxMatchingBits(prefixLookUpLogic, binaryStringNextKeyToInsertFromFile ) );
        prefixLookUpLogic.setPreviousNode( getNodeHavingAsChildNodeWithMaxMatchingBits(prefixLookUpLogic) );
        insertNewNodeInPlaceOfNodeWithMaxMatchingBits(prefixLookUpLogic, binaryStringNextKeyToInsertFromFile, filePositionNextKeyToInsert);
    }

    /** Protected (internal) methods **/
    /** insertNextKeyIntoTree - Insert Key **/

    protected int getMaxLengthOfMatchingBits(PrefixLookUpLogic prefixLookUpLogic, String binaryStringKeyToInsertFromFile) throws Exception {
        if(prefixLookUpLogic.isContainingPrefix(binaryStringKeyToInsertFromFile)) throw new Exception("The key you are trying to insert is matching existing key. No key should be a prefix of another key.");
        else {
            if( PatriciaTree.printProcessingInfo ) System.out.println("getMaxLengthOfMatchingBits() >>> lookUp.getLongestMatchingBitStreak(): " + prefixLookUpLogic.getLongestMatchingBitStreak());
            return prefixLookUpLogic.getLongestMatchingBitStreak();
        }
    }

    protected PatriciaNode getNodeWithMaxMatchingBits(PrefixLookUpLogic prefixLookUpLogic, String binaryStringKeyToInsertFromFile) throws Exception {
        int maxLengthOfMatchingBits = prefixLookUpLogic.getLongestMatchingBitStreak();
        String matchingBitsKeyToInsertFromFile = this.owner.cutStringToLength(binaryStringKeyToInsertFromFile, maxLengthOfMatchingBits);
        if( PatriciaTree.printProcessingInfo ) System.out.println("getNodeWithMaxMatchingBits() >>> matchingBitsKeyToInsertFromFile: " + matchingBitsKeyToInsertFromFile);
        if(prefixLookUpLogic.isContainingPrefix(matchingBitsKeyToInsertFromFile)) {
            PatriciaNode latestComperedNodeToKeyToInsert = prefixLookUpLogic.getCurrentNode();
            if( PatriciaTree.printProcessingInfo ) System.out.println("getNodeWithMaxMatchingBits() >>> this.lookUp.getCurrentNode(): " + prefixLookUpLogic.getCurrentNode());
            return latestComperedNodeToKeyToInsert;
        } else throw new Exception("This should not happen. Execution of isContaining method should be successful " +
                "because we have chosen bits of new key to insert that are matching some existing node inside the tree. " +
                "Invalid \"matching\" bits of new key to insert into the tree: " + matchingBitsKeyToInsertFromFile);
    }

    protected PatriciaNode getNodeHavingAsChildNodeWithMaxMatchingBits(PrefixLookUpLogic prefixLookUpLogic) {
        PatriciaNode secondToLatestComperedNodeToKeyToInsert = prefixLookUpLogic.getPreviousNode();
        if( PatriciaTree.printProcessingInfo ) System.out.println("getNodeHavingAsChildNodeWithMaxMatchingBits() >>> lookUp.getPreviousNode(): " + prefixLookUpLogic.getPreviousNode());
        return secondToLatestComperedNodeToKeyToInsert;
    }

    protected void insertNewNodeInPlaceOfNodeWithMaxMatchingBits(PrefixLookUpLogic prefixLookUpLogic, String binaryStringKeyToInsertFromFile, int keyValueKeyToInsertFromFile) throws Exception {
        int id = this.owner.getNextNodeIdToInsert();
        this.owner.incrementNextNodeIdToInsert();
        int key = keyValueKeyToInsertFromFile;
        int skip = -1;
        PatriciaNode leftLink = null;
        PatriciaNode rightLink = null;
        boolean isLeftAncestor = false;
        boolean isRightAncestor = false;
        PatriciaNode newNode = new PatriciaNode(id, key, skip, leftLink, rightLink, isLeftAncestor, isRightAncestor);
        this.latestInsertedNode = newNode;

        boolean oldTagValueFromPreviousToCurrentNode = false;
        if(prefixLookUpLogic.getPreviousNode().getLeftLink() == prefixLookUpLogic.getCurrentNode()) {
            oldTagValueFromPreviousToCurrentNode = prefixLookUpLogic.getPreviousNode().getIsLeftAncestor();
            prefixLookUpLogic.getPreviousNode().setLeftLink(newNode);
            prefixLookUpLogic.getPreviousNode().setIsLeftAncestor(false);
        } else if(prefixLookUpLogic.getPreviousNode().getRightLink() == prefixLookUpLogic.getCurrentNode()) {
            oldTagValueFromPreviousToCurrentNode = prefixLookUpLogic.getPreviousNode().getIsRightAncestor();
            prefixLookUpLogic.getPreviousNode().setRightLink(newNode);
            prefixLookUpLogic.getPreviousNode().setIsRightAncestor(false);
        } else throw new Exception("Neither left or right link of previous node points to current node.");

        if(binaryStringKeyToInsertFromFile.charAt(prefixLookUpLogic.getLongestMatchingBitStreak()) == '0') {
            newNode.setIsLeftAncestor(true);
            newNode.setLeftLink(newNode);
            newNode.setIsRightAncestor(oldTagValueFromPreviousToCurrentNode);
            newNode.setRightLink(prefixLookUpLogic.getCurrentNode());
        } else if(binaryStringKeyToInsertFromFile.charAt(prefixLookUpLogic.getLongestMatchingBitStreak()) == '1') {
            newNode.setIsRightAncestor(true);
            newNode.setRightLink(newNode);
            newNode.setIsLeftAncestor(oldTagValueFromPreviousToCurrentNode);
            newNode.setLeftLink(prefixLookUpLogic.getCurrentNode());
        } else throw new Exception("The word you are trying to insert into a tree has in its binary representation" +
                " on position " + prefixLookUpLogic.getLongestMatchingBitStreak() + " an invalid character (not 0 or 1): " + binaryStringKeyToInsertFromFile.charAt(prefixLookUpLogic.getLongestMatchingBitStreak()));

        if(oldTagValueFromPreviousToCurrentNode) {
            newNode.setSkip(1 + prefixLookUpLogic.getLongestMatchingBitStreak() - (prefixLookUpLogic.getCurrentSearchWordBitIndex() + 1));
        } else {
            newNode.setSkip(1 + prefixLookUpLogic.getLongestMatchingBitStreak() - (prefixLookUpLogic.getCurrentSearchWordBitIndex() + 1) + prefixLookUpLogic.getCurrentNode().getSkip());
            prefixLookUpLogic.getCurrentNode().setSkip((prefixLookUpLogic.getCurrentSearchWordBitIndex() + 1) - prefixLookUpLogic.getLongestMatchingBitStreak() - 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder()
                .append("KeyInsertLogic{")
                .append("  \n\t\towner=" + (owner==null?"PatriciaTree{ null }":"PatriciaTree{ !null }"))
                .append(", \n\tlatestInsertedNode = ").append((latestInsertedNode==null?"null":latestInsertedNode.getId()))
                .append("\n}");
        return representation.toString();
    }
}
