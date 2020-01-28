package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertLogic {
    private PatriciaTree owner;
    private PatriciaNode latestInsertedNode;

    public InsertLogic(PatriciaTree owner) {
        setOwner(owner);
        setLatestInsertedNode(getOwner().getHeader());
    }

    /** Setters **/

    /** Normal methods **/
    /** Public methods **/

    /** insertNextKeyIntoTree - Insert **/

    public void insertNextKeyIntoTree(String binaryStringNextKeyToInsertFromFile, int filePositionNextKeyToInsert) throws Exception {
        if(latestInsertedNode == null) setLatestInsertedNode(getOwner().getHeader());
        System.out.println("insertNextKeyIntoTree() >>> binaryStringNextKeyToInsertFromFile: " + binaryStringNextKeyToInsertFromFile);
        System.out.println("insertNextKeyIntoTree() >>> filePositionNextKeyToInsert: " + filePositionNextKeyToInsert);
        LookUpLogic lookUpLogic = new LookUpLogic(getOwner());
        lookUpLogic.setLongestMatchingBitStreak( getMaxLengthOfMatchingBits( lookUpLogic, binaryStringNextKeyToInsertFromFile ) );
        lookUpLogic.setCurrentNode( getNodeWithMaxMatchingBits( lookUpLogic, binaryStringNextKeyToInsertFromFile ) );
        lookUpLogic.setPreviousNode( getNodeHavingAsChildNodeWithMaxMatchingBits( lookUpLogic ) );
        insertNewNodeInPlaceOfNodeWithMaxMatchingBits(lookUpLogic, binaryStringNextKeyToInsertFromFile, filePositionNextKeyToInsert);
    }

    /** Protected (internal) methods **/
    /** insertNextKeyIntoTree - Insert **/

    protected int getMaxLengthOfMatchingBits(LookUpLogic lookUpLogic, String binaryStringKeyToInsertFromFile) throws Exception {
        if(lookUpLogic.isContaining(binaryStringKeyToInsertFromFile)) throw new Exception("The key you are trying to insert is matching existing key. No key should be a prefix of another key.");
        else {
            System.out.println("getMaxLengthOfMatchingBits() >>> lookUp.getLongestMatchingBitStreak(): " + lookUpLogic.getLongestMatchingBitStreak());
            return lookUpLogic.getLongestMatchingBitStreak();
        }
    }

    protected PatriciaNode getNodeWithMaxMatchingBits(LookUpLogic lookUpLogic, String binaryStringKeyToInsertFromFile) throws Exception {
        int maxLengthOfMatchingBits = lookUpLogic.getLongestMatchingBitStreak();
        String matchingBitsKeyToInsertFromFile = this.owner.cutStringToLength(binaryStringKeyToInsertFromFile, maxLengthOfMatchingBits);
        System.out.println("getNodeWithMaxMatchingBits() >>> matchingBitsKeyToInsertFromFile: " + matchingBitsKeyToInsertFromFile);
        if(lookUpLogic.isContaining(matchingBitsKeyToInsertFromFile)) {
            PatriciaNode latestComperedNodeToKeyToInsert = lookUpLogic.getCurrentNode();
            System.out.println("getNodeWithMaxMatchingBits() >>> this.lookUp.getCurrentNode(): " + lookUpLogic.getCurrentNode());
            return latestComperedNodeToKeyToInsert;
        } else throw new Exception("This should not happen. Execution of isContaining method should be successful " +
                "because we have chosen bits of new key to insert that are matching some existing node inside the tree. " +
                "Invalid \"matching\" bits of new key to insert into the tree: " + matchingBitsKeyToInsertFromFile);
    }

    protected PatriciaNode getNodeHavingAsChildNodeWithMaxMatchingBits(LookUpLogic lookUpLogic) {
        PatriciaNode secondToLatestComperedNodeToKeyToInsert = lookUpLogic.getPreviousNode();
        System.out.println("getNodeHavingAsChildNodeWithMaxMatchingBits() >>> lookUp.getPreviousNode(): " + lookUpLogic.getPreviousNode());
        return secondToLatestComperedNodeToKeyToInsert;
    }

    protected void insertNewNodeInPlaceOfNodeWithMaxMatchingBits(LookUpLogic lookUpLogic, String binaryStringKeyToInsertFromFile, int keyValueKeyToInsertFromFile) throws Exception {
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
        if(lookUpLogic.getPreviousNode().getLeftLink() == lookUpLogic.getCurrentNode()) {
            oldTagValueFromPreviousToCurrentNode = lookUpLogic.getPreviousNode().getIsLeftAncestor();
            lookUpLogic.getPreviousNode().setLeftLink(newNode);
            lookUpLogic.getPreviousNode().setIsLeftAncestor(false);
        } else if(lookUpLogic.getPreviousNode().getRightLink() == lookUpLogic.getCurrentNode()) {
            oldTagValueFromPreviousToCurrentNode = lookUpLogic.getPreviousNode().getIsRightAncestor();
            lookUpLogic.getPreviousNode().setRightLink(newNode);
            lookUpLogic.getPreviousNode().setIsRightAncestor(false);
        } else throw new Exception("Neither left or right link of previous node points to current node.");

        if(binaryStringKeyToInsertFromFile.charAt(lookUpLogic.getLongestMatchingBitStreak()) == '0') {
            newNode.setIsLeftAncestor(true);
            newNode.setLeftLink(newNode);
            newNode.setIsRightAncestor(oldTagValueFromPreviousToCurrentNode);
            newNode.setRightLink(lookUpLogic.getCurrentNode());
        } else if(binaryStringKeyToInsertFromFile.charAt(lookUpLogic.getLongestMatchingBitStreak()) == '1') {
            newNode.setIsRightAncestor(true);
            newNode.setRightLink(newNode);
            newNode.setIsLeftAncestor(oldTagValueFromPreviousToCurrentNode);
            newNode.setLeftLink(lookUpLogic.getCurrentNode());
        } else throw new Exception("The word you are trying to insert into a tree has in its binary representation" +
                " on position " + lookUpLogic.getLongestMatchingBitStreak() + " an invalid character (not 0 or 1): " + binaryStringKeyToInsertFromFile.charAt(lookUpLogic.getLongestMatchingBitStreak()));

        if(oldTagValueFromPreviousToCurrentNode) {
            newNode.setSkip(1 + lookUpLogic.getLongestMatchingBitStreak() - (lookUpLogic.getCurrentSearchWordBitIndex() + 1));
        } else {
            newNode.setSkip(1 + lookUpLogic.getLongestMatchingBitStreak() - (lookUpLogic.getCurrentSearchWordBitIndex() + 1) + lookUpLogic.getCurrentNode().getSkip());
            lookUpLogic.getCurrentNode().setSkip((lookUpLogic.getCurrentSearchWordBitIndex() + 1) - lookUpLogic.getLongestMatchingBitStreak() - 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder()
                .append("InsertLogic{")
                .append("  \n\t\towner=" + (owner==null?"PatriciaTree{ null }":"PatriciaTree{ !null }"))
                .append(", \n\tlatestInsertedNode = ").append((latestInsertedNode==null?"null":latestInsertedNode.getId()))
                .append("\n}");
        return representation.toString();
    }
}
