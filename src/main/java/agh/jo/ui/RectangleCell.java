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
    public static double width = 100;
    public static double height = 125;
    public static int TEXT_Y_POSITION_OFFSET = 25;
    public static int PADDING = 5;

    private Text textId;
    private Text textKey;
    private Text textSkip;
    private Text textWord;
    private Rectangle rectangle;
    private AnchorPane anchorPane;

    public RectangleCell(int id, int skip, int key, String word) {
        super(String.valueOf(id));
        this.textId = new Text(PADDING, 0 * TEXT_Y_POSITION_OFFSET +PADDING, "id: " + id);
        this.textSkip = new Text(PADDING, 1 * TEXT_Y_POSITION_OFFSET +PADDING, "skip: " + skip);
        this.textKey = new Text(PADDING, 2 * TEXT_Y_POSITION_OFFSET +PADDING, "key: " + key);
        this.textWord = new Text(PADDING, 3 * TEXT_Y_POSITION_OFFSET +PADDING, word);

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
        StackPane stackPaneWord = new StackPane(textWord);
        AnchorPane.setLeftAnchor(stackPaneWord, textWord.getX());
        AnchorPane.setTopAnchor(stackPaneWord, textWord.getY());

        this.anchorPane = new AnchorPane(this.rectangle, stackPaneId, stackPaneKey, stackPaneSkip, stackPaneWord);

        setView((Node) this.anchorPane);
    }
}
