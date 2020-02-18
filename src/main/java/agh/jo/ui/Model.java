package agh.jo.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Model {
    Cell graphParent;

    List<Cell> allCells;
    List<Cell> addedCells;
    List<Cell> removedCells;

    List<EdgeLoop> allEdgeLoops;
    List<EdgeChild> allEdgeChildren;
    List<EdgeAncestor> allEdgeAncestors;
    List<Edge> allEdges;
    List<Edge> addedEdges;
    List<Edge> removedEdges;

    Map<String,Cell> cellMap; // <id,cell>

    public Model() {

        graphParent = new Cell( "_ROOT_");

        // clear model, create lists
        clear();
    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

        allEdges = new ArrayList<>();
        addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        cellMap = new HashMap<>(); // <id,cell>

    }

    public void clearAddedLists() {
        addedCells.clear();
        addedEdges.clear();
    }

    public void addCell(Cell cell) {
        addedCells.add(cell);
        cellMap.put(cell.getCellId(), cell);
    }

    public void addEdge(Edge edge) {
        if(edge instanceof EdgeLoop) {
            if(allEdgeLoops == null) allEdgeLoops = new LinkedList<>();
            allEdgeLoops.add((EdgeLoop) edge);
        } else if(edge instanceof EdgeChild) {
            if(allEdgeChildren == null) allEdgeChildren = new LinkedList<>();
            allEdgeChildren.add((EdgeChild) edge);
        } else if(edge instanceof EdgeAncestor) {
            if(allEdgeAncestors == null) allEdgeAncestors = new LinkedList<>();
            allEdgeAncestors.add((EdgeAncestor) edge);
        } else {
            Cell sourceCell = cellMap.get(edge.getSource().cellId);
            Cell targetCell = cellMap.get(edge.getTarget().cellId);
            addedEdges.add(edge);
        }
    }

    /**
     * Attach all cells which don't have a parent to graphParent
     * @param cellList
     */
    public void attachOrphansToGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            if( cell.getCellParents().size() == 0) {
                graphParent.addCellChild( cell);
            }
        }

    }

    /**
     * Remove the graphParent reference if it is set
     * @param cellList
     */
    public void disconnectFromGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            graphParent.removeCellChild( cell);
        }
    }

    public void merge() {

        // cells
        allCells.addAll( addedCells);
        allCells.removeAll( removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll( addedEdges);
        allEdges.removeAll( removedEdges);

        addedEdges.clear();
        removedEdges.clear();

    }
}
