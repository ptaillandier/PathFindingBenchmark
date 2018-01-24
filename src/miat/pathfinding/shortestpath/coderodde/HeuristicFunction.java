package miat.pathfinding.shortestpath.coderodde;

/**
 * This interface defines the API for heuristic functions used in pathfinding.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 6, 2016)
 */
@FunctionalInterface
public interface HeuristicFunction {

    /**
     * Provides an optimistic (underestimated) distance between {@code nodeId1}
     * and {@code nodeId2} using a specific distance metric.
     * 
     * @param nodeId1 the first node.
     * @param nodeId2 the second node.
     * @return a shortest path estimate between the two input nodes.
     */
    public double estimateDistanceBetween(int nodeId1, int nodeId2);
}