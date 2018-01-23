package miat.pathfinding.shortestpath.jgrapht;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.JohnsonShortestPaths;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class JGraphtJohnsonShortestPaths implements ShortestPathAlgorithm {

	@Override
	public Result shortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target) {
		JohnsonShortestPaths<Integer, DefaultEdge> bf = new JohnsonShortestPaths<>(graph, Integer.class);
		Integer so = Integer.valueOf(source);
		Integer ta = Integer.valueOf(target);
		long t = System.currentTimeMillis();
		GraphPath<Integer, DefaultEdge> path =bf.getPath(so, ta);
		
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
		return "Johnson";
	}

	@Override
	public Result spatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source,
			Coordinate target) {
		JohnsonShortestPaths<Coordinate, DefaultEdge> bf = new JohnsonShortestPaths<>(graph, Coordinate.class);
		long t = System.currentTimeMillis();
		GraphPath<Coordinate, DefaultEdge> path =bf.getPath(source, target);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		return new Result(t, graph.getType().isWeighted() ? path.getWeight() : path.getLength());
	}
}