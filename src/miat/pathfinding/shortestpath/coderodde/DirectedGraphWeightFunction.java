package miat.pathfinding.shortestpath.coderodde;

import java.util.HashMap;
import java.util.Map;

/**
 * This class maps directed arcs to their weights. An arc weight is not allowed
 * to be a <tt>NaN</tt> value or negative.
 * 
 * @author Rodion "rodde" Efremov
 * @vesion 1.6 (Oct 6, 2016)
 */
public class DirectedGraphWeightFunction {

    /**
     * Maps the arcs to the arc weights.
     */
    private final Map<Integer, Map<Integer, Double>> map = new HashMap<>();

    /**
     * Associates the weight {@code weight} with the arc 
     * <tt>(tailNodeId, headNodeId)</tt>.
     * 
     * @param tailNodeId the starting node of the arc.
     * @param headNodeId the ending node of the arc.
     * @param weight the arc weight.
     */
    public void put(int tailNodeId, int headNodeId, double weight) {
        checkWeight(weight);
        map.putIfAbsent(tailNodeId, new HashMap<>());
        map.get(tailNodeId).put(headNodeId, weight);
    }

    /**
     * Returns the weight of the given arc.
     * 
     * @param tailNodeId the starting node (tail node) of the arc.
     * @param headNodeId the ending node (head node) of the arc.
     * @return 
     */
    public double get(int tailNodeId, int headNodeId) {
        return map.get(tailNodeId).get(headNodeId);
    }

    private void checkWeight(double weight) {
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("The input weight is NaN.");
        }

        if (weight < 0.0) {
            throw new IllegalArgumentException(
                    "The input weight is negative: " + weight + ".");
        }
    }
}