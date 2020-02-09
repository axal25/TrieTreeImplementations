package agh.jo.ui;

import javafx.scene.paint.Color;

public class EdgeChild extends Edge {

    public Cell source;
    public Cell target;
    public Edge[] subEdges;
    public Cell[] subCells;

    public EdgeChild(Cell source, Cell target, EdgeType edgeType, EdgeSide edgeSide) {
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
        subCells[0].getStyleClass().add("loop");
        ((SubCell) subCells[0]).getView().setStroke(Color.BLACK);
        ((SubCell) subCells[0]).getView().setFill(Color.BLACK);
        subEdges[0] = new Edge(source, subCells[0], EdgeType.CHILD, edgeSide);
        subEdges[1] = new Edge(subCells[0], target, EdgeType.CHILD, edgeSide);

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
        System.out.println();
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
