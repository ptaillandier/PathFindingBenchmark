package miat.pathfinding.shortestpath.jgrapht;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public class JGraphtDijkstra <V,E> extends AbstractShortestPathAlgorihm<Graph<V,E>> {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		DijkstraShortestPath<Integer, DefaultEdge> dijkstra = new DijkstraShortestPath<>(graph.getGraph());
		long t = System.currentTimeMillis();
		GraphPath<Integer, DefaultEdge> path =dijkstra.getPath(source, target);
		
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		return new Result(t, graph.getType().isWeighted() ? path.getWeight() : path.getLength());
	}
	
	@Override
	public String getLibrary() {
		return "JGrapht";
	}

	@Override
	public String getName() {
		return "Dijkstra";
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source,
			Coordinate target) {
		DijkstraShortestPath<Coordinate, DefaultEdge> dijkstra = new DijkstraShortestPath<>(graph.getGraph());
		long t = System.currentTimeMillis();
		GraphPath<Coordinate, DefaultEdge> path =dijkstra.getPath(source, target);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		return new Result(t, graph.getType().isWeighted() ? path.getWeight() : path.getLength());
	}
}