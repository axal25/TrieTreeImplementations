package agh.jo.knuth.patricia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PatriciaNode {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int key;

    @Getter
    @Setter
    private int skip;

    @Getter
    @Setter
    private PatriciaNode leftLink;

    @Getter
    @Setter
    private PatriciaNode rightLink;

    private boolean isLeftAncestor;
    private boolean isRightAncestor;

    public boolean getIsLeftAncestor() {
        return isLeftAncestor;
    }

    public void setIsLeftAncestor(boolean leftAncestor) {
        isLeftAncestor = leftAncestor;
    }

    public boolean getIsRightAncestor() {
        return isRightAncestor;
    }

    public void setIsRightAncestor(boolean rightAncestor) {
        isRightAncestor = rightAncestor;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    public String toString(int level) {
        StringBuilder representation = new StringBuilder();
        representation.append("PatriciaNode{\n");
        for (int i = 0; i < level+1; i++) representation.append("\t");
        representation.append("id = ").append(id).append(",\n");
        for (int i = 0; i < level+1; i++) representation.append("\t");
        representation.append("key = ").append(key).append(",\n");
        for (int i = 0; i < level+1; i++) representation.append("\t");
        representation.append("skip = ").append(skip).append(",\n");
        for (int i = 0; i < level+1; i++) representation.append("\t");

        representation.append("isLeftAncestor = ").append(isLeftAncestor).append(",\n");
        for (int i = 0; i < level+1; i++) representation.append("\t");
        representation.append("isRightAncestor = ").append(isRightAncestor).append(",\n");
        for (int i = 0; i < level+1; i++) representation.append("\t");

        if(leftLink == null) representation.append("leftLink = ").append(leftLink);
        else if(isLeftAncestor) representation.append("leftLink.id = ").append(leftLink.id);
        else representation.append("leftLink = ").append(leftLink.toString(level+1));

        representation.append(",\n");
        for (int i = 0; i < level+1; i++) representation.append("\t");

        if(rightLink == null) representation.append("rightLink = ").append(rightLink);
        else if(isRightAncestor) representation.append("rightLink.id = ").append(rightLink.id);
        else representation.append("rightLink = ").append(rightLink.toString(level+1));

        representation.append("\n");
        for (int i = 0; i < level; i++) representation.append("\t");

        return representation.append("}").toString();
    }
}
