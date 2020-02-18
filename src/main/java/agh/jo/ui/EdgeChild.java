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

        subCells = new Cell[1+3];
        subEdges = new Edge[2+2];

        // Arrow Line Cells
        subCells[0] = new SubCell(source.cellId, target.cellId, "1");

        // Arrow Line Edges
        subEdges[0] = new Edge(source, subCells[0], EdgeType.CHILD, edgeSide);
        subEdges[1] = new Edge(subCells[0], target, EdgeType.CHILD, edgeSide);

        // Arrow Head Cells
        subCells[1] = new SubCell(source.cellId, target.cellId, "2");
        subCells[2] = new SubCell(source.cellId, target.cellId, "3");
        subCells[3] = new SubCell(source.cellId, target.cellId, "4");

        // Arrow Head Edges
        subEdges[2] = new Edge(subCells[2], subCells[1], EdgeType.CHILD, edgeSide);
        subEdges[3] = new Edge(subCells[3], subCells[1], EdgeType.CHILD, edgeSide);

        for (Cell subCell:subCells) {
            ((SubCell) subCell).getView().setStroke(Color.BLACK);
            ((SubCell) subCell).getView().setFill(Color.BLACK);
        }

        getChildren().addAll(
                subEdges[0],
                subEdges[1],
                subEdges[2],
                subEdges[3],
                subCells[0],
                subCells[1],
                subCells[2],
                subCells[3]
        );
    }

    public void position() {
        double x, xArrowHead1, xArrowHead2, xArrowHead3;
        double y, yArrowHead1, yArrowHead2, yArrowHead3;

        x = target.getLayoutX() + ((double) RectangleCell.width/2.0);

        xArrowHead1 = x;
        xArrowHead2 = x + Edge.ARROW_HEAD_X_POS_OFFSET;
        xArrowHead3 = x - Edge.ARROW_HEAD_X_POS_OFFSET;

        y = source.getLayoutY() + ((double) RectangleCell.height/2.0);
        yArrowHead1 = target.getLayoutY();
        yArrowHead2 = yArrowHead1 - Edge.ARROW_HEAD_Y_POS_OFFSET;
        yArrowHead3 = yArrowHead2;

        subCells[0].relocate(x, y);
        subCells[1].relocate(xArrowHead1, yArrowHead1);
        subCells[2].relocate(xArrowHead2, yArrowHead2);
        subCells[3].relocate(xArrowHead3, yArrowHead3);
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
