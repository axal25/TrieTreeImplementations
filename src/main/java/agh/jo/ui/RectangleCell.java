package agh.jo.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RectangleCell extends Cell {
    public static int width = 75;
    public static int height = 75;
    public static int TEXT_Y_POSITION_OFFSET = 25;
    public static int PADDING = 5;

    private Text textId;
    private Text textKey;
    private Text textSkip;
    private Rectangle rectangle;
    private AnchorPane anchorPane;

    public RectangleCell(int id, int key, int skip) {
        super(String.valueOf(id));
        this.textId = new Text(PADDING, 2 * TEXT_Y_POSITION_OFFSET +PADDING, "id: " + id);
        this.textKey = new Text(PADDING, 1 * TEXT_Y_POSITION_OFFSET +PADDING, "key: " + key);
        this.textSkip = new Text(PADDING, 0 * TEXT_Y_POSITION_OFFSET +PADDING, "skip: " + skip);

        this.rectangle = new Rectangle(width, height);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setFill(Color.DODGERBLUE);

        StackPane stackPaneId = new StackPane(textId);
        AnchorPane.setLeftAnchor(stackPaneId, textId.getX());
        AnchorPane.setTopAnchor(stackPaneId, textId.getY());
        StackPane stackPaneKey = new StackPane(textKey);
        AnchorPane.setLeftAnchor(stackPaneKey, textKey.getX());
        AnchorPane.setTopAnchor(stackPaneKey, textKey.getY());
        StackPane stackPaneSkip = new StackPane(textSkip);
        AnchorPane.setLeftAnchor(stackPaneSkip, textSkip.getX());
        AnchorPane.setTopAnchor(stackPaneSkip, textSkip.getY());

        this.anchorPane = new AnchorPane(this.rectangle, stackPaneId, stackPaneKey, stackPaneSkip);

        setView((Node) this.anchorPane);
    }
}
