package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeySearchLogic {
    private PatriciaTree owner;

    public KeySearchLogic(PatriciaTree owner) {
        setOwner(owner);
    }

    protected PatriciaNode findNodeMatchingKey(String binarySearchWordString) throws Exception {
        KeyLookUpLogic keyLookUpLogic = new KeyLookUpLogic(getOwner());
        if(keyLookUpLogic.isContainingKey(binarySearchWordString)) {
            return keyLookUpLogic.getCurrentNode();
//            PrefixSearchLogic prefixSearchLogic = new PrefixSearchLogic(getOwner());
//            PatriciaNode[] nodesMatchingPrefix = prefixSearchLogic.findNodesMatchingPrefix(binarySearchWordString);
//            if(prefixLookUpLogic.getCurrentSearchWordBitIndex() < binarySearchWordString.length()) {
//                PatriciaNode[] onlyMatchingNode = new PatriciaNode[1];
//                onlyMatchingNode[0] = prefixLookUpLogic.getCurrentNode();
//                return onlyMatchingNode;
//            } else return getNodesMatchingPrefix(prefixLookUpLogic.getCurrentNode());
        } else return null;
    }

}
