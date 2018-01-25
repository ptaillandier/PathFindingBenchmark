package miat.pathfinding.shortestpath.graphstream;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public class GraphStreamDijkstra extends AbstractShortestPathAlgorihm<org.graphstream.graph.Graph>{

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		org.graphstream.graph.Graph gsg = getCache() == null ? Translation.benchmarkGraphToGraphStream(graph) : getCache();
		Dijkstra dijkstra;
		if(graph.getType().isWeighted()){
			dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
		} else {
			dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, null);
		}
		dijkstra.init(gsg);
		dijkstra.setSource(gsg.getNode(source.toString()));
		Node tar = gsg.getNode(target.toString());

		long t = System.currentTimeMillis();
		dijkstra.compute();
		double distance = dijkstra.getPathLength(tar);
		t = System.currentTimeMillis() - t;
		if (distance == Double.POSITIVE_INFINITY ) return new Result(t, 0.0);
		return new Result(t, distance);
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
		org.graphstream.graph.Graph gsg = getCache() == null ? Translation.benchmarkGraphToGraphStreamSpatial(graph) : getCache();
		Dijkstra dijkstra;
		if(graph.getType().isWeighted()){
			dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
		} else {
			dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, null);
		}
		dijkstra.init(gsg);
		dijkstra.setSource(gsg.getNode(graph.getStrId(source)));
		Node tar = gsg.getNode(graph.getStrId(target));

		long t = System.currentTimeMillis();
		dijkstra.compute();
		double distance = dijkstra.getPathLength(tar);
		t = System.currentTimeMillis() - t;
		if (distance == Double.POSITIVE_INFINITY ) return new Result(t, 0.0);
		return new Result(t, distance);
	}

}
