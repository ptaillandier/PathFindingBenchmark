package miat.pathfinding.shortestpath.coderodde;


import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a type for representing paths in directed graphs.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 16, 2016)
 */
public final class DirectedGraphPath {

    /**
     * The actual list of nodes on a path.
     */
    private final List<Integer> path;

    public DirectedGraphPath(List<Integer> path) {
        checkNotEmpty(path);
        this.path = new ArrayList<>(path);
    }

    public int getNode(int index) {
        return path.get(index);
    }

    public int getNumberOfNodes() {
        return path.size();
    }

    public int getNumberOfEdges() {
        return path.size() - 1;
    }

    public double getCost(DirectedGraphWeightFunction weightFunction) {
        double cost = 0.0;

        for (int i = 0; i < path.size() - 1; ++i) {
            cost += weightFunction.get(path.get(i), path.get(i + 1));
        }

        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !o.getClass().equals(getClass())) {
            return false;
        }

        return path.equals(((DirectedGraphPath) o).path);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String separator = "";

        for (Integer node : path) {
            sb.append(separator).append(node);
            separator = ", ";
        }

        return sb.append(']').toString();
    }

    private void checkNotEmpty(List<Integer> path) {
        if (path.isEmpty()) {
            throw new IllegalArgumentException(
                    "The input path is not allowed to be empty.");
        }
    }
}