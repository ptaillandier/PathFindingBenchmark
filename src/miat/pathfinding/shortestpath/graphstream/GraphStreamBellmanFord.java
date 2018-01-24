package miat.pathfinding.shortestpath.graphstream;

import org.graphstream.algorithm.BellmanFord;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public class GraphStreamBellmanFord extends AbstractShortestPathAlgorihm<org.graphstream.graph.Graph> {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		org.graphstream.graph.Graph gsg = getCache() == null ? Translation.benchmarkGraphToGraphStream(graph)  : getCache();
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
	
	@Override
	public String getLibrary() {
		return "GraphStream";
	}

	@Override
	public String getName() {
		return "BellmanFord";
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		org.graphstream.graph.Graph gsg = getCache() == null ?Translation.benchmarkGraphToGraphStreamSpatial(graph) : getCache();
		BellmanFord bellman = new BellmanFord("weight");
		bellman.init(gsg);
		bellman.setSource(graph.getStrId(source));
		
		Node tar = gsg.getNode(graph.getStrId(target));
		
		long t = System.currentTimeMillis();
		bellman.compute();
		Path path = bellman.getShortestPath(tar);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgePath().isEmpty()) return new Result(t, 0.0);
		
		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
	}

}
