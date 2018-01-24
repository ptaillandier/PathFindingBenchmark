package miat.pathfinding.shortestpath.coderodde;

public class TargetUnreachableException extends RuntimeException {

    private final DirectedGraph graph;
    private final Integer sourceNode;
    private final Integer targetNode;

    public TargetUnreachableException(DirectedGraph graph,
                                      Integer sourceNode,
                                      Integer targetNode) {
        this.graph = graph;
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    public DirectedGraph getGraph() {
        return graph;
    }

    public int getSourceNode() {
        return sourceNode;
    }

    public int getTargetNode() {
        return targetNode;
    }

    @Override
    public String toString() {
        return "'" + targetNode + "' is not reachable from '" 
                   + sourceNode + "'.";
    }
}