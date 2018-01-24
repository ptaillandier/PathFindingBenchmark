package miat.pathfinding.graphGeneration;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import grph.algo.mobility.Location;
import grph.algo.topology.manet.EuclideanGrph;
import grph.in_memory.InMemoryGrph;
import miat.pathfinding.graph.BenchmarkGraph;

public class Translation {

	public static org.graphstream.graph.Graph benchmarkGraphToGraphStream(BenchmarkGraph<Integer, DefaultEdge> graph) {
		org.graphstream.graph.Graph newGraph = new SingleGraph("graph");
		boolean isDirected = graph.getGraph().getType().isDirected();
		boolean isWeighted = graph.getGraph().getType().isWeighted();
		for (Integer v : graph.getGraph().vertexSet()) {
			newGraph.addNode(v.toString());
		}
		int i = 0;
		for (DefaultEdge e : graph.getGraph().edgeSet()) {
			Edge edge = newGraph.addEdge(i+"", graph.getGraph().getEdgeSource(e).toString(),graph.getGraph().getEdgeTarget(e).toString(),isDirected);
			if (isWeighted) edge.setAttribute("weight", graph.getGraph().getEdgeWeight(e));
			i++;
		}
		return newGraph;
	} 
	
	public static org.graphstream.graph.Graph benchmarkGraphToGraphStreamSpatial(BenchmarkGraph<Coordinate, DefaultEdge> graph) {
		org.graphstream.graph.Graph newGraph = new SingleGraph("graph");
		boolean isDirected = graph.getGraph().getType().isDirected();
		boolean isWeighted = graph.getGraph().getType().isWeighted();
		for (Coordinate v : graph.getGraph().vertexSet()) {
			Node n = newGraph.addNode(graph.getVerticesIndex().get(v).toString());
			n.addAttribute("coordinate", v);
		}
		int i = 0;
		for (DefaultEdge e : graph.getGraph().edgeSet()) {
			Edge edge = newGraph.addEdge(i+"", graph.getVerticesIndex().get(graph.getGraph().getEdgeSource(e)).toString(),graph.getVerticesIndex().get(graph.getGraph().getEdgeTarget(e)).toString(),isDirected);
			if (isWeighted) edge.setAttribute("weight", graph.getGraph().getEdgeWeight(e));
			i++;
		}
		return newGraph;
	} 
	
	public static InMemoryGrph benchmarkGraphToGrph(BenchmarkGraph<Integer, DefaultEdge> graph) {
		InMemoryGrph newGraph = new InMemoryGrph();
		boolean isDirected = graph.getGraph().getType().isDirected();
		boolean isWeighted = graph.getGraph().getType().isWeighted();
		for (Integer v : graph.getGraph().vertexSet()) {
			newGraph.addVertex(v);
		}
		for (DefaultEdge e : graph.getGraph().edgeSet()) {
			int edge = newGraph.addSimpleEdge(graph.getGraph().getEdgeSource(e), graph.getGraph().getEdgeTarget(e), isDirected);
			if (isWeighted) newGraph.getEdgeWidthProperty().setValue(edge, (int) Math.round(graph.getGraph().getEdgeWeight(e))); 
		}
		return newGraph;
	} 
	
	
	
	public static InMemoryGrph benchmarkGraphToGrphSpatial(BenchmarkGraph<Coordinate, DefaultEdge> graph) {
		EuclideanGrph newGraph = new EuclideanGrph();
		boolean isDirected = graph.getGraph().getType().isDirected();
		boolean isWeighted = graph.getGraph().getType().isWeighted();
		for (Coordinate v : graph.getGraph().vertexSet()) {
			int id = graph.getVerticesIndex().get(v);
			newGraph.addVertex(id);
			newGraph.setLocation(id, new Location(v.x, v.y));	
		}
		for (DefaultEdge e : graph.getGraph().edgeSet()) {
			int edge = newGraph.addSimpleEdge(graph.getVerticesIndex().get(graph.getGraph().getEdgeSource(e)), graph.getVerticesIndex().get(graph.getGraph().getEdgeTarget(e)), isDirected);
			if (isWeighted) newGraph.getEdgeWidthProperty().setValue(edge, (int) Math.round(graph.getGraph().getEdgeWeight(e))); 
		
		}
		return newGraph;
	} 
	
	public static SparseGraph<Integer,Integer> benchmarkGraphToJung (BenchmarkGraph<Integer, DefaultEdge> graph) {
		boolean isDirected = graph.getGraph().getType().isDirected();  
		SparseGraph<Integer, Integer> newGraph = new SparseGraph<>();
		
		for (Integer v : graph.getGraph().vertexSet()) {
			newGraph.addVertex(v);
		}
		int i = 0;
		for (DefaultEdge e : graph.getGraph().edgeSet()) {
			newGraph.addEdge(i, graph.getEdgeSource(e),graph.getEdgeTarget(e),isDirected ? EdgeType.DIRECTED : EdgeType.UNDIRECTED);
			i++;
		}
		return newGraph;
	} 
	
	public static SparseGraph<Coordinate, Geometry> benchmarkGraphToJungSpatial(BenchmarkGraph<Coordinate, DefaultEdge> graph) {
		boolean isDirected = graph.getGraph().getType().isDirected();
		SparseGraph<Coordinate, Geometry> newGraph = new SparseGraph<>();
		
		for (Coordinate v : graph.getGraph().vertexSet()) {
			newGraph.addVertex(v);
		}
		for (DefaultEdge e : graph.getGraph().edgeSet()) {
			newGraph.addEdge((Geometry) e.getUserObject(),graph.getEdgeSource(e),graph.getEdgeTarget(e),isDirected ? EdgeType.DIRECTED : EdgeType.UNDIRECTED);
		}
		return newGraph;
	} 
}
