package miat.pathfinding.shortestpath.coderodde;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class implements a directed graph data structure via adjacency lists. 
 * This implementation represents each graph node as an unique integer.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.61 (Oct 13, 2016)
 */
public class DirectedGraph {

	private DirectedGraphWeightFunction weightFunction;
	private DirectedGraphNodeCoordinates nodeCoordinates;
    /**
     * This map maps each directed graph node to the list of its child nodes.
     */
    private final Map<Integer, Set<Integer>> childMap  = new HashMap<>();

    /**
     * This map maps each directed graph node to the list of its parent nodes.
     */
    private final Map<Integer, Set<Integer>> parentMap = new HashMap<>();

    /**
     * Adds a new node represented by integer {@code nodeId} to this graph if
     * it is not yet present in it.
     * 
     * @param nodeId the node to add.
     */
    public void addNode(int nodeId) {
        childMap .putIfAbsent(nodeId, new HashSet<>());
        parentMap.putIfAbsent(nodeId, new HashSet<>());
    }

    /**
     * Creates a directed arc <tt>(tailNodeId, headNodeId)</tt> if it is not yet
     * present in the graph.
     * 
     * @param tailNodeId the tail node of the arc.
     * @param headNodeId the head node of the arc.
     */
    public void addArc(int tailNodeId, int headNodeId) {
        childMap .get(tailNodeId).add(headNodeId);
        parentMap.get(headNodeId).add(tailNodeId);
    }

    /**
     * Returns the view of all the nodes in this graph.
     * 
     * @return the set of all nodes.
     */
    public Set<Integer> getNodeSet() {
        return Collections.unmodifiableSet(childMap.keySet());
    }

    /**
     * Returns the set of all child nodes of the given node {@code nodeId}.
     * 
     * @param nodeId the node whose children to return.
     * @return the set of child nodes of {@code nodeId}.
     */
    public Set<Integer> getChildrenOf(int nodeId) {
        return Collections.<Integer>unmodifiableSet(childMap.get(nodeId));
    }

    /**
     * Returns the set of all parent nodes of the given node {@code nodeId}.
     * 
     * @param nodeId the node whose parents to return.
     * @return the set of parent nodes of {@code nodeId}.
     */
    public Set<Integer> getParentsOf(int nodeId) {
        return Collections.<Integer>unmodifiableSet(parentMap.get(nodeId));
    }

	public DirectedGraphWeightFunction getWeightFunction() {
		return weightFunction;
	}

	public void setWeightFunction(DirectedGraphWeightFunction weightFunction) {
		this.weightFunction = weightFunction;
	}

	public DirectedGraphNodeCoordinates getNodeCoordinates() {
		return nodeCoordinates;
	}

	public void setNodeCoordinates(DirectedGraphNodeCoordinates nodeCoordinates) {
		this.nodeCoordinates = nodeCoordinates;
	}
	
    
    
}