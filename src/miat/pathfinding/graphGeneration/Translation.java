package miat.pathfinding.graphGeneration;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import com.vividsolutions.jts.geom.Coordinate;

public class Translation {

	public static org.graphstream.graph.Graph jGraphtToGraphStream(Graph<Integer, DefaultEdge> graph) {
		org.graphstream.graph.Graph newGraph = new SingleGraph("graph");
		boolean isDirected = graph.getType().isDirected();
		boolean isWeighted = graph.getType().isWeighted();
		for (Integer v : graph.vertexSet()) {
			newGraph.addNode(v.toString());
		}
		int i = 0;
		for (DefaultEdge e : graph.edgeSet()) {
			newGraph.addEdge(i+"", graph.getEdgeSource(e).toString(),graph.getEdgeTarget(e).toString(),isDirected);
			if (isWeighted) newGraph.addAttribute("weight", graph.getEdgeWeight(e));
			i++;
		}
		return newGraph;
	} 
	
	public static org.graphstream.graph.Graph jGraphtToGraphStreamSpatial(Graph<Coordinate, DefaultEdge> graph) {
		org.graphstream.graph.Graph newGraph = new SingleGraph("graph");
		boolean isDirected = graph.getType().isDirected();
		boolean isWeighted = graph.getType().isWeighted();
		for (Coordinate v : graph.vertexSet()) {
			Node n = newGraph.addNode(v.toString());
			n.addAttribute("coordinate", v);
		}
		int i = 0;
		for (DefaultEdge e : graph.edgeSet()) {
			Edge edge = newGraph.addEdge(i+"", graph.getEdgeSource(e).toString(),graph.getEdgeTarget(e).toString(),isDirected);
			if (isWeighted) edge.setAttribute("weight", graph.getEdgeWeight(e));
			
			i++;
		}
		return newGraph;
	} 
	
	public static Graph<Integer, DefaultEdge> graphStreamToJGrapht(org.graphstream.graph.Graph graph) {
		boolean isWeighted = graph.hasAttribute("weight");
		boolean isDirected = graph.getEdgeSet().size() > 0 && graph.getEdge(0).isDirected();
		EdgeFactory<Integer, DefaultEdge> factory = new ClassBasedEdgeFactory<Integer,DefaultEdge>(DefaultEdge.class);
		Graph<Integer,DefaultEdge> newGraph = isDirected ? new SimpleDirectedGraph<Integer,DefaultEdge>(factory, isWeighted) : new SimpleGraph<>(factory, isWeighted);
		for (Node v : graph.getNodeSet()) {
			newGraph.addVertex(v.getIndex());
		}
		for (Edge e : graph.getEdgeSet()) {
			DefaultEdge edge = new DefaultEdge(e.getId());
			newGraph.addEdge(e.getSourceNode().getIndex(), e.getTargetNode().getIndex(), edge);
			newGraph.setEdgeWeight(edge, graph.getAttribute("weight"));
		}
		return newGraph;
	}
}
