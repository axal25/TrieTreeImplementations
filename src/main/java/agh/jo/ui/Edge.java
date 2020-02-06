package agh.jo.ui;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Edge extends Group {

    public Cell source;
    public Cell target;
    public Line line;
    public EdgeType edgeType;
    public EdgeSide edgeSide;

    protected Edge() {}
    public Edge(Cell source, Cell target, EdgeType edgeType, EdgeSide edgeSide) {
        this.source = source;
        this.target = target;
        this.edgeType = edgeType;
        this.edgeSide = edgeSide;

        source.addCellChild(this.target);
        target.addCellParent(this.source);

        line = new Line();
        switch(this.edgeType) {
            case LOOP:
                line.getStyleClass().add("loop");
                break;
            case CHILD:
                break;
            case ANCESTOR:
                line.getStyleClass().add("ancestor");
                break;
            default:
                throw new UnsupportedOperationException();
        }

        line.startXProperty().bind(source.layoutXProperty().add( source.getBoundsInParent().getWidth() / 2.0 - SubCell.radius));
        line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0 - SubCell.radius));

        line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getWidth() / 2.0 - SubCell.radius));
        line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getHeight() / 2.0 - SubCell.radius));

        getChildren().add(line);
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
