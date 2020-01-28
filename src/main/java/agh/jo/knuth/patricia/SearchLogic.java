package agh.jo.knuth.patricia;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class SearchLogic {
    PatriciaTree owner;

    private SearchLogic() {}
    public SearchLogic(PatriciaTree owner) {
        setOwner(owner);
    }

    protected PatriciaNode[] findNodesMatching(String binarySearchWordString) throws Exception {
        LookUpLogic lookUpLogic = new LookUpLogic(getOwner());
        if(lookUpLogic.isContaining(binarySearchWordString)) {
            if(lookUpLogic.getCurrentSearchWordBitIndex() < binarySearchWordString.length()) {
                PatriciaNode[] onlyMatchingNode = new PatriciaNode[1];
                onlyMatchingNode[0] = lookUpLogic.getCurrentNode();
                return onlyMatchingNode;
            } else return getMatchingNodes(lookUpLogic.getCurrentNode());
        } else return null;
    }

    protected PatriciaNode[] getMatchingNodes(PatriciaNode currentNode) throws Exception {
        Map<String, Map<Integer, PatriciaNode>> subtreeNodesAndTheirAncestors = getSubtreeNodesAndTheirAncestors(currentNode, null);
        PatriciaNode[] subTreeNodes = mapToArray(subtreeNodesAndTheirAncestors.get("subTreeNodes"));
        PatriciaNode[] subTreeAncestors = mapToArray(subtreeNodesAndTheirAncestors.get("subTreeAncestors"));
        checkSubtreeNodesLength(subTreeNodes, subTreeAncestors);
        return subTreeAncestors;
    }

    protected Map<String, Map<Integer, PatriciaNode>> getSubtreeNodesAndTheirAncestors(
            PatriciaNode currentNode,
            Map<String, Map<Integer, PatriciaNode>> subtreeNodesAndTheirAncestors
    ) {
        subtreeNodesAndTheirAncestors = initSubtreeNodesAndTheirAncestorsIfNeeded(currentNode, subtreeNodesAndTheirAncestors);
        if(currentNode.getIsLeftAncestor()) {
            put(currentNode.getLeftLink(), subtreeNodesAndTheirAncestors.get("subTreeAncestors"));
        } else {
            if(subtreeNodesAndTheirAncestors.get("subTreeNodes").containsKey(currentNode.getLeftLink().getId())) {/**do nothing**/}
            else {
                put(currentNode.getLeftLink(), subtreeNodesAndTheirAncestors.get("subTreeNodes"));
                subtreeNodesAndTheirAncestors = getSubtreeNodesAndTheirAncestors(currentNode.getLeftLink(), subtreeNodesAndTheirAncestors);
            }
        }
        if(currentNode.getIsRightAncestor()) {
            put(currentNode.getRightLink(), subtreeNodesAndTheirAncestors.get("subTreeAncestors"));
        } else {
            if(subtreeNodesAndTheirAncestors.get("subTreeNodes").containsKey(currentNode.getRightLink().getId())) {/**do nothing**/}
            else {
                put(currentNode.getRightLink(), subtreeNodesAndTheirAncestors.get("subTreeNodes"));
                subtreeNodesAndTheirAncestors = getSubtreeNodesAndTheirAncestors(currentNode.getRightLink(), subtreeNodesAndTheirAncestors);
            }
        }
        return subtreeNodesAndTheirAncestors;
    }

    private Map<String, Map<Integer, PatriciaNode>> initSubtreeNodesAndTheirAncestorsIfNeeded(PatriciaNode currentNode, Map<String, Map<Integer, PatriciaNode>> subtreeNodesAndTheirAncestors) {
        if(subtreeNodesAndTheirAncestors == null) subtreeNodesAndTheirAncestors = new HashMap<>();
        if(!subtreeNodesAndTheirAncestors.containsKey("subTreeAncestors")) subtreeNodesAndTheirAncestors.put("subTreeAncestors", new HashMap<>());
        if(!subtreeNodesAndTheirAncestors.containsKey("subTreeNodes")) {
            subtreeNodesAndTheirAncestors.put("subTreeNodes", new HashMap<>());
            subtreeNodesAndTheirAncestors.get("subTreeNodes").put(currentNode.getId(), currentNode);
        }
        return subtreeNodesAndTheirAncestors;
    }

    protected void checkSubtreeNodesLength(PatriciaNode[] subTreeNodes, PatriciaNode[] subTreeAncestors) throws Exception {
        if(subTreeAncestors.length-1 != subTreeNodes.length)
            throw new Exception("Subtree Nodes amount is not smaller by 1 compared to Subtree Nodes' Ancestors amount. " +
                    "(subTreeAncestors.length-1): " + (subTreeAncestors.length-1) +
                    " != (subTreeNodes.length): " + subTreeNodes.length +
                    "\nsubTreeAncestors: " + Arrays.toString(subTreeAncestors) +
                    "\n\nsubTreeNodes: " + Arrays.toString(subTreeNodes));
    }

    private Map<String, Map<Integer, PatriciaNode>> addMapToMap(Map<String, Map<Integer, PatriciaNode>> map1, Map<String, Map<Integer, PatriciaNode>> map2) {
        Map<String, Map<Integer, PatriciaNode>> sumMap = new HashMap<>();
        map1.forEach(
                (key, value) -> { sumMap.put(key, value); }
        );
        map2.forEach(
                (key, value) -> { sumMap.put(key, value); }
        );
        map1.forEach((key, value) -> {
                    map1.merge(key, value, (value1, value2) -> {
                        return value1!=value2?null:value2;
                    });
                });
        return sumMap;
    }

    private Map<Integer, PatriciaNode> put(PatriciaNode node, Map<Integer, PatriciaNode> map) {
        map.put(node.getId(), node);
        return map;
    }

    private PatriciaNode[] mapToArray(Map<Integer, PatriciaNode> map) {
        Object[] arrayOfObjects = map.values().toArray();
        PatriciaNode[] patriciaNodes = new PatriciaNode[arrayOfObjects.length];
        for (int i=0; i<arrayOfObjects.length; i++) {
            patriciaNodes[i] = (PatriciaNode) arrayOfObjects[i];
        }
        return patriciaNodes;
    }

    @Override
    public String toString() {
        return "SearchLogic{" +
                "\n\t\towner=" + (owner==null?"PatriciaTree{ null }":"PatriciaTree{ !null }") +
                "\n\t}";
    }
}
