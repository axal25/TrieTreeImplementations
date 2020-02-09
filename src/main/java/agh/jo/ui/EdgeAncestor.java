package agh.jo.ui;

import javafx.scene.paint.Color;

public class EdgeAncestor extends Edge {

    public Cell source;
    public Cell target;
    public Edge[] subEdges;
    public Cell[] subCells;

    public EdgeAncestor(Cell source, Cell target, EdgeType edgeType, EdgeSide edgeSide) {
        super();
        this.source = source;
        this.target = target;
        this.edgeType = edgeType;
        this.edgeSide = edgeSide;

        source.addCellChild(this.target);
        target.addCellParent(this.source);

        subCells = new Cell[1];
        subEdges = new Edge[2];
        subCells[0] = new SubCell(source.cellId, target.cellId, "1");
        subCells[0].getStyleClass().add("ancestor");
        ((SubCell) subCells[0]).getView().setStroke(Color.web("#018a23"));
        ((SubCell) subCells[0]).getView().setFill(Color.web("#018a23"));
        subEdges[0] = new Edge(source, subCells[0], EdgeType.ANCESTOR, edgeSide);
        subEdges[1] = new Edge(subCells[0], target, EdgeType.ANCESTOR, edgeSide);

        getChildren().addAll(subEdges[0], subEdges[1], subCells[0]);
    }

    public void position() {
        double x;
        double y;
        double xSpacingOffset = ((double) PatriciaTreeLayout.X_POSITION_SPACING_AT_MAX_LEVEL/4.0);

        if(this.edgeSide == EdgeSide.LEFT) x = target.getLayoutX() + xSpacingOffset;
        else x = target.getLayoutX() + xSpacingOffset;

        y = source.getLayoutY() + ((double) RectangleCell.height)/2;
        subCells[0].relocate(x, y);
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
