package miat.pathfinding.shortestpath.graphstream;

import org.graphstream.algorithm.BellmanFord;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class GraphStreamBellmanFord implements ShortestPathAlgorithm {

	@Override
	public Result shortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target) {
		org.graphstream.graph.Graph gsg = Translation.jGraphtToGraphStream(graph);
		BellmanFord bellman = new BellmanFord("weight");
		bellman.init(gsg);
		bellman.setSource(gsg.getNode(source));
		Node tar = gsg.getNode(target);
		
		long t = System.currentTimeMillis();
		bellman.compute();
		Path path = bellman.getShortestPath(tar);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgePath().isEmpty()) return new Result(t, 0.0);
		
		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
	}
	
	@Override
	public String getLibrary() {
		return "GraphStream";
	}

	@Override
	public String getName() {
		return "BellmanFord";
	}

	@Override
	public Result spatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		org.graphstream.graph.Graph gsg = Translation.jGraphtToGraphStreamSpatial(graph);
		BellmanFord bellman = new BellmanFord("weight");
		bellman.init(gsg);
		bellman.setSource(source.toString());
		
		Node tar = gsg.getNode(target.toString());
		
		long t = System.currentTimeMillis();
		bellman.compute();
		Path path = bellman.getShortestPath(tar);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgePath().isEmpty()) return new Result(t, 0.0);
		
		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
	}

}
