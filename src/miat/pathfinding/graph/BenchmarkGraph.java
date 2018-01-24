package miat.pathfinding.graph;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;

public class BenchmarkGraph<V,E> {
	
	Graph<V, E> graph;
	Map<V,Integer> verticesIndex = new HashMap<V, Integer>();
	
	
	public Graph<V, E> getGraph() {
		return graph;
	}
	public void setGraph(Graph<V, E> graph) {
		this.graph = graph;
	}
	public Map<V, Integer> getVerticesIndex() {
		return verticesIndex;
	}
	public GraphType getType() {
		return graph.getType();
	}
	
	public String getStrId(V v) {
		return verticesIndex.get(v).toString();
	}
	
	public V getEdgeSource(E e) {
		return graph.getEdgeSource(e);
	}
	
	public V getEdgeTarget(E e) {
		return graph.getEdgeTarget(e);
	}

}
