package miat.pathfinding.shortestpath.coderodde;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * This class allows mapping each graph node to its coordinates on a 
 * two-dimensional plane.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 6, 2016)
 */
public class DirectedGraphNodeCoordinates {

    /**
     * Maps each node to its coordinates.
     */
    private final Map<Integer, Point2D.Double> map = new HashMap<>();

    /**
     * Associates the coordinates {@code point} to the node {@code nodeId}.
     * 
     * @param nodeId the node to map.
     * @param point  the coordinates to associate to the node.
     */
    public void put(int nodeId, Point2D.Double point) {
        map.put(nodeId, point);
    }

    /**
     * Return the point of the input node.
     * 
     * @param nodeId the node whose coordinates to return.
     * @return the coordinates.
     */
    public Point2D.Double get(int nodeId) {
        return map.get(nodeId);
    }
}