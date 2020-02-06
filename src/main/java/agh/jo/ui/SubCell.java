package agh.jo.ui;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SubCell extends Cell {
    public static final double radius = 0.5;
    public Circle view;

    public SubCell(String source, String target, String id) {
        super("from" + source + "|to|" + target + "|" + id);
        view = new Circle(radius);
        setView(view);
    }
}