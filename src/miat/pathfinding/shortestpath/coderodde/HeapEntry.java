package miat.pathfinding.shortestpath.coderodde;

/**
 * This class implements an entry for {@link java.util.PriorityQueue}.
 *
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 13, 2016)
 */
final class HeapEntry implements Comparable<HeapEntry> {

    private final int nodeId;
    private final double distance; // The priority key.

    public HeapEntry(int nodeId, double distance) {
        this.nodeId = nodeId;
        this.distance = distance;
    }

    public int getNode() {
        return nodeId;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(HeapEntry o) {
        return Double.compare(distance, o.distance);
    }
}