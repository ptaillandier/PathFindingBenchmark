package miat.pathfinding.shortestpath.graphstream;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class GraphStreamDijkstra implements ShortestPathAlgorithm {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		org.graphstream.graph.Graph gsg = Translation.benchmarkGraphToGraphStream(graph);
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.init(gsg);
		dijkstra.setSource(gsg.getNode(source.toString()));
		Node tar = gsg.getNode(target.toString());
		
		long t = System.currentTimeMillis();
		dijkstra.compute();
		Path path = dijkstra.getPath(tar);
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
		return "Dijkstra";
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		org.graphstream.graph.Graph gsg = Translation.benchmarkGraphToGraphStreamSpatial(graph);
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.init(gsg);
		dijkstra.setSource(gsg.getNode(graph.getStrId(source)));
		Node tar = gsg.getNode(graph.getStrId(target));
		
		long t = System.currentTimeMillis();
		dijkstra.compute();
		Path path = dijkstra.getPath(tar);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgePath().isEmpty()) return new Result(t, 0.0);
		
		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
	}

}
