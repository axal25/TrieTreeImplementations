package agh.jo.ui;

import agh.jo.knuth.patricia.PatriciaNode;
import agh.jo.knuth.patricia.PatriciaTree;

import java.util.List;

public class PatriciaTreeLayout extends Layout {
    public static int X_POSITION_SPACING_AT_MAX_LEVEL = RectangleCell.width;
    public int positionXOffset = -1;
    public static int POSITION_Y_OFFSET = 100;
    public static double POSITION_X_OFFSET_RATIO_MULTIPLIER = 0.5;
    private Graph graph;
//    private Random random = new Random();

    public PatriciaTreeLayout(Graph graph) { this.graph = graph; }
    public void execute() {}
    public void execute(PatriciaTree patriciaTree) {
        initPositionXOffSet(patriciaTree);
        List<Cell> cells = graph.getModel().getAllCells();
        positionSubTreeCells(patriciaTree.getHeader(), cells, 0, 0, 1);
    }

    private void positionSubTreeCells(PatriciaNode currentSubTreeRoot, List<Cell> cells, double previousCellX, double previousCellY, double xOffsetRatio) {
        if(!currentSubTreeRoot.getIsLeftAncestor() && currentSubTreeRoot.getLeftLink()!=null) positionLeftChildNode(currentSubTreeRoot.getLeftLink(), cells, previousCellX, previousCellY, xOffsetRatio);
        if(!currentSubTreeRoot.getIsRightAncestor() && currentSubTreeRoot.getRightLink()!=null) positionRightChildNode(currentSubTreeRoot.getRightLink(), cells, previousCellX, previousCellY, xOffsetRatio);
    }

    private void positionLeftChildNode(PatriciaNode currentSubTreeRoot, List<Cell> cells, double previousCellX, double previousCellY, double xOffsetRatio) {
        for (Cell cell : cells) {
            if(cell.cellId.compareTo(String.valueOf(currentSubTreeRoot.getId())) ==  0) {
                xOffsetRatio = POSITION_X_OFFSET_RATIO_MULTIPLIER * xOffsetRatio;
                double x = previousCellX + (-1 * xOffsetRatio * positionXOffset) - ((double) X_POSITION_SPACING_AT_MAX_LEVEL/2);
                double y = previousCellY + (+1 * POSITION_Y_OFFSET);
                cell.relocate(x, y);
                positionSubTreeCells(currentSubTreeRoot, cells, x, y, xOffsetRatio);
            }
        }
    }

    private void positionRightChildNode(PatriciaNode currentSubTreeRoot, List<Cell> cells, double previousCellX, double previousCellY, double xOffsetRatio) {
        for (Cell cell : cells) {
            if(cell.cellId.compareTo(String.valueOf(currentSubTreeRoot.getId())) ==  0) {
                xOffsetRatio = POSITION_X_OFFSET_RATIO_MULTIPLIER * xOffsetRatio;
                double x = previousCellX + (+1 * xOffsetRatio * positionXOffset) + ((double) X_POSITION_SPACING_AT_MAX_LEVEL/2);
                double y = previousCellY + (+1 * POSITION_Y_OFFSET);
                cell.relocate(x, y);
                positionSubTreeCells(currentSubTreeRoot, cells, x, y, xOffsetRatio);
            }
        }
    }

    private void initPositionXOffSet(PatriciaTree patriciaTree) {
        int patriciaTreeMaxLevel = getMaxLevel(patriciaTree);
        int rectangleWidth = RectangleCell.width;
        double positionXOffSetDouble = (double) rectangleWidth / 2;
        for (int i = 0; i < patriciaTreeMaxLevel; i++) {
            positionXOffSetDouble = positionXOffSetDouble * 2;
        }
        this.positionXOffset = (int) Math.round(positionXOffSetDouble);
    }

    private int getMaxLevel(PatriciaTree patriciaTree) {
        return getMaxLevelOfSubTree(patriciaTree.getHeader()) -1;
    }

    private int getMaxLevelOfSubTree(PatriciaNode currentSubTreeRoot) {
        int leftMaxLevel = 0;
        int rightMaxLevel = 0;
        if(!currentSubTreeRoot.getIsLeftAncestor() && currentSubTreeRoot.getLeftLink()!=null)
            leftMaxLevel = getMaxLevelOfSubTree(currentSubTreeRoot.getLeftLink());
        if(!currentSubTreeRoot.getIsRightAncestor() && currentSubTreeRoot.getRightLink()!=null)
            rightMaxLevel = getMaxLevelOfSubTree(currentSubTreeRoot.getRightLink());
        return leftMaxLevel > rightMaxLevel ? 1 + leftMaxLevel : 1 + rightMaxLevel;
    }

    public void positionHelperNodes(Graph graph) {
        Model model = graph.getModel();
        List<EdgeLoop> edgeLoops = model.allEdgeLoops;
        for (EdgeLoop edgeLoop:edgeLoops) {
            edgeLoop.position();
        }
        List<EdgeChild> edgeChildren = model.allEdgeChildren;
        for (EdgeChild edgeChild:edgeChildren) {
            edgeChild.position();
        }
        List<EdgeAncestor> edgeAncestors = model.allEdgeAncestors;
        for (EdgeAncestor edgeAncestor:edgeAncestors) {
            edgeAncestor.position();
        }
    }
}
