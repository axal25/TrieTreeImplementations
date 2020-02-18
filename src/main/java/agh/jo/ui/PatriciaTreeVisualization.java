package agh.jo.ui;

import agh.jo.Examples;
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
    private List<PatriciaNode> nodesToProcessForEdges;
    public static int DOT_PRINT_ITERATION_SPACING = 100;
    public static PatriciaTree patriciaTree = null;

    // to run via console:
    // export MAVEN_OPTS="-Xms1024M -Xmx2048M -Xss4M -XX:MaxMetaspaceSize=4096M"
    // mvn clean javafx:run -e -X
    // or
    // mvnDebug clean javafx:run -e -X

    public static void main(String[] args) { launch(); }

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

        if(this.patriciaTree == null) this.patriciaTree = Examples.getExamplePatriciaTree(0, false);
        System.out.println(">>> creating patricia tree visualization <<<");
        addComponentsToGraph(graph, this.patriciaTree);
        System.out.println(">>> created patricia tree visualization <<<");

        PatriciaTreeLayout patriciaTreeLayout = new PatriciaTreeLayout(graph);
        patriciaTreeLayout.execute(this.patriciaTree);
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
        allCells = getSubTreeCells(patriciaTree.getHeader(), patriciaTree);
        System.out.println(">>> created patricia tree's nodes <<<");
        allEdges = initAllEdges(patriciaTree);
        System.out.println(">>> created patricia tree's branches <<<");
        int counter = 0;
        for (Cell cell:allCells) {
            model.addCell(cell);
            counter++;
            if(counter % DOT_PRINT_ITERATION_SPACING == 0) System.out.print(".");
        }
        System.out.println("\n>>> added patricia tree's nodes <<<");
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
            counter++;
            if(counter % DOT_PRINT_ITERATION_SPACING == 0) System.out.print(".");
        }
        System.out.println("\n>>> added patricia tree's branches <<<");
        graph.endUpdate();
    }

    private LinkedList<Cell> getSubTreeCells(PatriciaNode currentSubTreeRoot, PatriciaTree patriciaTree) {
        LinkedList<Cell> cells = new LinkedList<>();
        int id = currentSubTreeRoot.getId();
        int skip = currentSubTreeRoot.getSkip();
        int key = currentSubTreeRoot.getKey();
        String word = null;
        try {
            word = "\"" + patriciaTree.getFileOps().getFileOpsStrategy().getWordStringFromFileStartingAtPosition(currentSubTreeRoot.getKey()) + "\"";
        } catch (Exception e) {
            word = e.getClass().getSimpleName();
        }
        RectangleCell currentCell = new RectangleCell(id, skip, key, word);
        cells.add(currentCell);
        if(!currentSubTreeRoot.getIsLeftAncestor() && currentSubTreeRoot.getLeftLink()!=null) cells.addAll( getSubTreeCells(currentSubTreeRoot.getLeftLink(), patriciaTree) );
        if(!currentSubTreeRoot.getIsRightAncestor() && currentSubTreeRoot.getRightLink()!=null) cells.addAll( getSubTreeCells(currentSubTreeRoot.getRightLink(), patriciaTree) );
        return cells;
    }

    private LinkedList<Edge> initAllEdges(PatriciaTree patriciaTree) {
        this.allEdges = new LinkedList<>();
        this.nodesToProcessForEdges = new LinkedList<>();
        this.nodesToProcessForEdges.add(patriciaTree.getHeader());
        int counter = 1;
        while (this.nodesToProcessForEdges.size()>0) {
            initSubTreeEdges();
            counter++;
            if(counter % DOT_PRINT_ITERATION_SPACING == 0) System.out.print(".");
        }
        return (LinkedList<Edge>) this.allEdges;
    }

    private void initSubTreeEdges() {
        PatriciaNode currentSubTreeRoot = this.nodesToProcessForEdges.get(0);

        Cell[] leftTargetAndCurrentSourceAndRightTargetCells = findLeftTargetAndCurrentSourceAndRightTargetCell(currentSubTreeRoot);
        Cell leftTargetCell = leftTargetAndCurrentSourceAndRightTargetCells[0];
        Cell currentSourceCell = leftTargetAndCurrentSourceAndRightTargetCells[1];
        Cell rightTargetCell = leftTargetAndCurrentSourceAndRightTargetCells[2];
        LinkedList<Edge> edges = new LinkedList<>();

        if(leftTargetCell!=null) edges.add( processOneSideEdge(currentSourceCell, leftTargetCell, true, currentSubTreeRoot) );
        if(rightTargetCell!=null) edges.add( processOneSideEdge(currentSourceCell, rightTargetCell, false, currentSubTreeRoot) );
        this.allEdges.addAll(edges);

        if(currentSubTreeRoot.getLeftLink()!=null && !currentSubTreeRoot.getIsLeftAncestor()) this.nodesToProcessForEdges.add(currentSubTreeRoot.getLeftLink());
        if(currentSubTreeRoot.getRightLink()!=null && !currentSubTreeRoot.getIsRightAncestor()) this.nodesToProcessForEdges.add(currentSubTreeRoot.getRightLink());

        this.nodesToProcessForEdges.remove(currentSubTreeRoot);
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

    private Cell[] findLeftTargetAndCurrentSourceAndRightTargetCell(PatriciaNode currentSubTreeRoot) {
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
