package miat.pathfinding.shortestpath.graphstream;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class GraphStreamDijkstra implements ShortestPathAlgorithm {

	@Override
	public Result shortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target) {
		org.graphstream.graph.Graph gsg = Translation.jGraphtToGraphStream(graph);
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.init(gsg);
		dijkstra.setSource(gsg.getNode(source));
		Node tar = gsg.getNode(target);
		
		long t = System.currentTimeMillis();
		dijkstra.compute();
		Path path = dijkstra.getPath(tar);
		t = System.currentTimeMillis() - t;
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
	public Result spatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		org.graphstream.graph.Graph gsg = Translation.jGraphtToGraphStreamSpatial(graph);
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.init(gsg);
		dijkstra.setSource(gsg.getNode(source.toString()));
		Node tar = gsg.getNode(target.toString());
		
		long t = System.currentTimeMillis();
		dijkstra.compute();
		Path path = dijkstra.getPath(tar);
		t = System.currentTimeMillis() - t;
		
		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
	}

}
