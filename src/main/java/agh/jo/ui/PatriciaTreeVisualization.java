package agh.jo.ui;

import agh.jo.App;
import agh.jo.knuth.patricia.PatriciaNode;
import agh.jo.knuth.patricia.PatriciaTree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

// Source: https://stackoverflow.com/questions/30679025/graph-visualisation-like-yfiles-in-javafx
public class PatriciaTreeVisualization extends Application {
    public static int windowWidth = 640;
    public static int windowHeight = 480;
    public static String javaVersion = System.getProperty("java.version");
    public static String javafxVersion = System.getProperty("javafx.version");
    public static String pathToStyleFile = "/application/application.css";
    private List<Cell> allCells;
    private List<Edge> allEdges;
    public static PatriciaTree[] patriciaTrees = {
            App.getExamplePatriciaTree1(),
            App.getExamplePatriciaTree2(),
            App.getExamplePatriciaTree3()
    };

    // to run via console:
    // mvn clean javafx:run

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Graph graph = new Graph();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(graph.getScrollPane());
        Scene scene = new Scene(borderPane, windowWidth, windowHeight);
        scene.getStylesheets().add(getStyleForApplication(pathToStyleFile));

        primaryStage.setScene(scene);
        primaryStage.show();

        PatriciaTree patriciaTree = patriciaTrees[2];
        addComponentsToGraph(graph, patriciaTree);

        PatriciaTreeLayout patriciaTreeLayout = new PatriciaTreeLayout(graph);
        patriciaTreeLayout.execute(patriciaTree);
        patriciaTreeLayout.positionHelperNodes(graph);
    }

    private String getStyleForApplication(String pathToStyleFile) {
        URL styleUrl = getClass().getResource(pathToStyleFile);
        String style = null;
        if(styleUrl!=null) {
            style = styleUrl.toExternalForm();
            return style;
        }
        else {
            System.err.println("Could not retrieve resource (style .css file for application) at path: " + pathToStyleFile);
            return null;
        }
    }

    private void addComponentsToGraph(Graph graph, PatriciaTree patriciaTree) {
        Model model = graph.getModel();
        graph.beginUpdate();
        allCells = getSubTreeCells(patriciaTree.getHeader());
        allEdges = initAllEdges(patriciaTree.getHeader(), (LinkedList<Cell>) allCells);
        for (Cell cell:allCells) {
            model.addCell(cell);
        }
        for (Edge edge:allEdges) {
            if(edge instanceof EdgeLoop) {
                EdgeLoop edgeLoop = (EdgeLoop) edge;
                for (Cell subCell:edgeLoop.subCells) { model.addCell(subCell); }
                for (Edge subEdge:edgeLoop.subEdges) { model.addEdge(subEdge); }
            } else if(edge instanceof  EdgeChild) {
                EdgeChild edgeChild = (EdgeChild) edge;
                for (Cell subCell:edgeChild.subCells) { model.addCell(subCell); }
                for (Edge subEdge:edgeChild.subEdges) { model.addEdge(subEdge); }
            } else if(edge instanceof EdgeAncestor) {
                EdgeAncestor edgeAncestor = (EdgeAncestor) edge;
                for (Cell subCell:edgeAncestor.subCells) { model.addCell(subCell); }
                for (Edge subEdge:edgeAncestor.subEdges) { model.addEdge(subEdge); }
            }
            model.addEdge(edge);
        }
        graph.endUpdate();
    }

    private LinkedList<Cell> getSubTreeCells(PatriciaNode currentSubTreeRoot) {
        LinkedList<Cell> cells = new LinkedList<>();
        int id = currentSubTreeRoot.getId();
        int key = currentSubTreeRoot.getKey();
        int skip = currentSubTreeRoot.getSkip();
        RectangleCell currentCell = new RectangleCell(id, key, skip);
        cells.add(currentCell);
        if(!currentSubTreeRoot.getIsLeftAncestor() && currentSubTreeRoot.getLeftLink()!=null) cells.addAll( getSubTreeCells(currentSubTreeRoot.getLeftLink()) );
        if(!currentSubTreeRoot.getIsRightAncestor() && currentSubTreeRoot.getRightLink()!=null) cells.addAll( getSubTreeCells(currentSubTreeRoot.getRightLink()) );
        return cells;
    }

    private LinkedList<Edge> initAllEdges(PatriciaNode currentSubTreeRoot, LinkedList<Cell> allCells) {
        this.allEdges = new LinkedList<>();
        int level = 0;
        this.allEdges = getSubTreeEdges(currentSubTreeRoot, allCells);
        return (LinkedList<Edge>) this.allEdges;
    }

    private LinkedList<Edge> getSubTreeEdges(PatriciaNode currentSubTreeRoot, LinkedList<Cell> allCells) {
        Cell[] leftTargetAndCurrentSourceAndRightTargetCells = findLeftTargetAndCurrentSourceAndRightTargetCell(currentSubTreeRoot, allCells);
        Cell leftTargetCell = leftTargetAndCurrentSourceAndRightTargetCells[0];
        Cell currentSourceCell = leftTargetAndCurrentSourceAndRightTargetCells[1];
        Cell rightTargetCell = leftTargetAndCurrentSourceAndRightTargetCells[2];
        LinkedList<Edge> edges = new LinkedList<>();

        if(leftTargetCell!=null) edges.add( processOneSideEdge(currentSourceCell, leftTargetCell, true, currentSubTreeRoot) );
        if(rightTargetCell!=null) edges.add( processOneSideEdge(currentSourceCell, rightTargetCell, false, currentSubTreeRoot) );

        if(currentSubTreeRoot.getLeftLink()!=null && !currentSubTreeRoot.getIsLeftAncestor()) edges.addAll( getSubTreeEdges(currentSubTreeRoot.getLeftLink(), allCells) );
        if(currentSubTreeRoot.getRightLink()!=null && !currentSubTreeRoot.getIsRightAncestor()) edges.addAll( getSubTreeEdges(currentSubTreeRoot.getRightLink(), allCells) );

        return edges;
    }

    private Edge processOneSideEdge(Cell currentSourceCell, Cell sideTargetCell, boolean isLeftSide, PatriciaNode currentSubTreeRoot) {
        Edge newEdge = null;
        if(isLoopEdge(currentSourceCell, sideTargetCell)) newEdge = getLoopEdge(currentSourceCell, isLeftSide);
        else if(isAncestorEdge(currentSubTreeRoot, isLeftSide)) newEdge = getAncestorEdge(currentSourceCell, sideTargetCell, isLeftSide);
        else newEdge = getChildEdge(currentSourceCell, sideTargetCell, isLeftSide);
        return newEdge;
    }

    private boolean isLoopEdge(Cell currentSourceCell, Cell sideTargetCell) {
        if(currentSourceCell.cellId.compareTo(sideTargetCell.cellId) == 0) return true;
        else return false;
    }

    private Edge getLoopEdge(Cell currentSourceAndSideTargetCell, boolean isLeftEdge) {
        return new EdgeLoop(currentSourceAndSideTargetCell, currentSourceAndSideTargetCell, EdgeType.LOOP, isLeftEdge?EdgeSide.LEFT:EdgeSide.RIGHT);
    }

    private boolean isAncestorEdge(PatriciaNode currentSubTreeRoot, boolean isLeftSide) {
        if(isLeftSide) return currentSubTreeRoot.getIsLeftAncestor();
        else return currentSubTreeRoot.getIsRightAncestor();
    }

    private Edge getAncestorEdge(Cell currentSourceCell, Cell sideTargetCell, boolean isLeftEdge) {
        return new EdgeAncestor(currentSourceCell, sideTargetCell, EdgeType.ANCESTOR, isLeftEdge?EdgeSide.LEFT:EdgeSide.RIGHT);
    }

    private Edge getChildEdge(Cell currentSourceCell, Cell sideTargetCell, boolean isLeftEdge) {
        return new EdgeChild(currentSourceCell, sideTargetCell, EdgeType.CHILD, isLeftEdge?EdgeSide.LEFT:EdgeSide.RIGHT);
    }

    private Cell[] findLeftTargetAndCurrentSourceAndRightTargetCell(PatriciaNode currentSubTreeRoot, LinkedList<Cell> allCells) {
        int currentSourceId = currentSubTreeRoot.getId();
        int leftTargetId = -1;
        int rightTargetId = -1;
        if(currentSubTreeRoot.getLeftLink()!=null) leftTargetId = currentSubTreeRoot.getLeftLink().getId();
        if(currentSubTreeRoot.getRightLink()!=null) rightTargetId = currentSubTreeRoot.getRightLink().getId();
        Cell currentSourceCell = null;
        Cell leftTargetCell = null;
        Cell rightTargetCell = null;
        for (Cell cell:allCells) {
            if(cell.cellId.compareTo(String.valueOf(currentSourceId)) == 0) currentSourceCell = cell;
            if(leftTargetId != -1) if(cell.cellId.compareTo(String.valueOf(leftTargetId)) == 0) leftTargetCell = cell;
            if(rightTargetId != -1) if(cell.cellId.compareTo(String.valueOf(rightTargetId)) == 0) rightTargetCell = cell;
            if((leftTargetId == -1 || leftTargetCell != null) && (rightTargetId == -1 || rightTargetCell != null) && currentSourceCell != null) break;
        }
        return new Cell[]{leftTargetCell, currentSourceCell, rightTargetCell};
    }
}
