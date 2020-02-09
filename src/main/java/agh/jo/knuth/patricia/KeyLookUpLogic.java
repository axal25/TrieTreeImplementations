package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeyLookUpLogic {
    private PatriciaTree owner;
    private PatriciaNode currentNode = null;

    private KeyLookUpLogic() {}
    public KeyLookUpLogic(PatriciaTree owner) {
        setOwner(owner);
    }

    /** Normal methods **/
    /** Public methods **/

    /** isContainingKey - Key Look-up **/

    protected boolean isContainingKey(String binarySearchWordString) throws Exception {
        PrefixLookUpLogic prefixLookUpLogic = new PrefixLookUpLogic(getOwner());
        if(prefixLookUpLogic.isContainingPrefix(binarySearchWordString)) {
            PatriciaNode nodeMatchingPrefix = prefixLookUpLogic.getCurrentNode();
            String keyWordMatchingPrefix = getOwner().getFileOps().getFileOpsStrategy().getWordStringFromFileStartingAtPosition(nodeMatchingPrefix.getKey());
            String binaryRepresentationOfKeyWordMatchingPrefix = getOwner().getMixMachine().getBinaryString(keyWordMatchingPrefix);
            if(binarySearchWordString.length() == binaryRepresentationOfKeyWordMatchingPrefix.length()) {
                this.currentNode = nodeMatchingPrefix;
                return true;
            }
            else {
                this.currentNode = null;
                return false;
            }
        } else {
            this.currentNode = null;
            return false;
        }
    }

    /** Protected (internal) methods **/
    /** isContainingKey - Key Look-up **/

    @Override
    public String toString() {
        return "KeyLookUpLogic{" +
                "\n\towner=" + (owner==null?"PatriciaTree{ null }":"PatriciaTree{ !null }") +
                "\n}";
    }
}
