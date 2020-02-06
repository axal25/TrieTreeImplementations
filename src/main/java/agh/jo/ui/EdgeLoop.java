package agh.jo.ui;

import javafx.scene.paint.Color;

public class EdgeLoop extends Edge {
    public static final int X_POSITION_OFFSET = RectangleCell.width/4;
    public static final int Y_POSITION_OFFSET = RectangleCell.height/4;

    public Cell source;
    public Cell target;
    public Edge[] subEdges;
    public Cell[] subCells;

    public EdgeLoop(Cell source, Cell target, EdgeType edgeType, EdgeSide edgeSide) {
        super();
        this.source = source;
        this.target = target;
        this.edgeType = edgeType;
        this.edgeSide = edgeSide;

        source.addCellChild(this.target);
        target.addCellParent(this.source);

        subCells = new Cell[4];
        subEdges = new Edge[5];
        subCells[0] = new SubCell(source.cellId, target.cellId, "1");
        subCells[1] = new SubCell(source.cellId, target.cellId, "2");
        subCells[2] = new SubCell(source.cellId, target.cellId, "3");
        subCells[3] = new SubCell(source.cellId, target.cellId, "4");
        subEdges[0] = new Edge(source, subCells[0], EdgeType.LOOP, edgeSide);
        subEdges[1] = new Edge(subCells[0], subCells[1], EdgeType.LOOP, edgeSide);
        subEdges[2] = new Edge(subCells[1], subCells[2], EdgeType.LOOP, edgeSide);
        subEdges[3] = new Edge(subCells[2], subCells[3], EdgeType.LOOP, edgeSide);
        subEdges[4] = new Edge(subCells[3], target, EdgeType.LOOP, edgeSide);

        for (Cell subCell:subCells) {
            subCell.getStyleClass().add("loop");
            ((SubCell) subCell).getView().setStroke(Color.web("#f55d42"));
            ((SubCell) subCell).getView().setFill(Color.web("#f55d42"));
        }
        for (Edge subLine:subEdges) subLine.getStyleClass().add("loop");

        getChildren().addAll(subEdges[0], subEdges[1], subEdges[2], subEdges[3], subEdges[4], subCells[0], subCells[1], subCells[2], subCells[3]);
    }

    public void position() {
        double x1, x2;
        double y1, y2;
        double xSpacingOffset = ((double) PatriciaTreeLayout.X_POSITION_SPACING_AT_MAX_LEVEL/2.0);
        if(this.edgeSide == EdgeSide.LEFT) x1 = source.getLayoutX();
        else x1 = source.getLayoutX() + RectangleCell.width;
        y1 = source.getLayoutY() + xSpacingOffset -1 * Y_POSITION_OFFSET;
        subCells[0].relocate(x1, y1);

        if(this.edgeSide == EdgeSide.LEFT) x2 = source.getLayoutX() -1 * X_POSITION_OFFSET;
        else x2 = source.getLayoutX() + (2*xSpacingOffset) +1 * X_POSITION_OFFSET;
        subCells[1].relocate(x2, y1);

        y2 = source.getLayoutY() + xSpacingOffset +1 * Y_POSITION_OFFSET;
        subCells[2].relocate(x2, y2);

        subCells[3].relocate(x1, y2);
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
