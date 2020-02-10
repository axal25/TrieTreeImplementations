package agh.jo.ui;

import javafx.scene.paint.Color;

public class EdgeLoop extends Edge {
    public static final double X_POSITION_OFFSET = ((double) RectangleCell.width)/4.0;
    public static final double Y_POSITION_OFFSET = ((double) RectangleCell.height)/4.0;

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

        // Arrow Line Cells
        subCells = new Cell[4+3];
        subCells[0] = new SubCell(source.cellId, target.cellId, "1");
        subCells[1] = new SubCell(source.cellId, target.cellId, "2");
        subCells[2] = new SubCell(source.cellId, target.cellId, "3");
        subCells[3] = new SubCell(source.cellId, target.cellId, "4");

        // Arrow Line Edges
        subEdges = new Edge[5+2];
        subEdges[0] = new Edge(source, subCells[0], EdgeType.LOOP, edgeSide);
        subEdges[1] = new Edge(subCells[0], subCells[1], EdgeType.LOOP, edgeSide);
        subEdges[2] = new Edge(subCells[1], subCells[2], EdgeType.LOOP, edgeSide);
        subEdges[3] = new Edge(subCells[2], subCells[3], EdgeType.LOOP, edgeSide);
        subEdges[4] = new Edge(subCells[3], target, EdgeType.LOOP, edgeSide);

        // Arrow Head Cells
        subCells[4] = new SubCell(source.cellId, target.cellId, "5");
        subCells[5] = new SubCell(source.cellId, target.cellId, "6");
        subCells[6] = new SubCell(source.cellId, target.cellId, "7");

        // Arrow Head Edges
        subEdges[5] = new Edge(subCells[5], subCells[4], EdgeType.LOOP, edgeSide);
        subEdges[6] = new Edge(subCells[6], subCells[4], EdgeType.LOOP, edgeSide);

        for (Cell subCell:subCells) {
            ((SubCell) subCell).getView().setStroke(Color.web("#f55d42"));
            ((SubCell) subCell).getView().setFill(Color.web("#f55d42"));
        }

        getChildren().addAll(
                subEdges[0],
                subEdges[1],
                subEdges[2],
                subEdges[3],
                subEdges[4],
                subEdges[5],
                subEdges[6],
                subCells[0],
                subCells[1],
                subCells[2],
                subCells[3],
                subCells[4],
                subCells[5],
                subCells[6]
        );
    }

    public void position() {
        double x1, x2, xArrowHead1, xArrowHead2, xArrowHead3;
        double y1, y2, yArrowHead1, yArrowHead2, yArrowHead3;

        x1 = source.getLayoutX() + ((double) RectangleCell.width/2.0);
        y1 = source.getLayoutY() + ((double) RectangleCell.height/2.0) - Y_POSITION_OFFSET;

        subCells[0].relocate(x1, y1);

        if(this.edgeSide == EdgeSide.LEFT) x2 = x1 - 3 * X_POSITION_OFFSET;
        else x2 = x1 + 3 * X_POSITION_OFFSET;

        subCells[1].relocate(x2, y1);

        y2 = source.getLayoutY() + ((double) RectangleCell.height/2.0) + Y_POSITION_OFFSET;

        subCells[2].relocate(x2, y2);
        subCells[3].relocate(x1, y2);

        if(this.edgeSide == EdgeSide.LEFT) xArrowHead1 = x1 - ((double) RectangleCell.width/2.0);
        else xArrowHead1 = x1 + ((double) RectangleCell.width/2.0);
        yArrowHead1 = y2;

        subCells[4].relocate(xArrowHead1, yArrowHead1);

        if(this.edgeSide == EdgeSide.LEFT) xArrowHead2 = xArrowHead1 - Edge.ARROW_HEAD_X_POS_OFFSET;
        else xArrowHead2 = xArrowHead1 + Edge.ARROW_HEAD_X_POS_OFFSET;
        yArrowHead2 = yArrowHead1 - Edge.ARROW_HEAD_Y_POS_OFFSET;

        subCells[5].relocate(xArrowHead2, yArrowHead2);

        xArrowHead3 = xArrowHead2;
        yArrowHead3 = yArrowHead1 + Edge.ARROW_HEAD_Y_POS_OFFSET;

        subCells[6].relocate(xArrowHead3, yArrowHead3);
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
