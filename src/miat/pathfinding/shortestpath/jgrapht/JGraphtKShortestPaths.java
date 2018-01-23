package miat.pathfinding.shortestpath.jgrapht;

import java.util.List;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.KShortestPaths;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.KShortestPathAlgorithm;

public class JGraphtKShortestPaths implements KShortestPathAlgorithm {
	
	@Override
	public Result KshortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target, int nb) {
		KShortestPaths<Integer, DefaultEdge> bf = new KShortestPaths<>(graph, nb);
		Integer so = Integer.valueOf(source);
		Integer ta = Integer.valueOf(target);
		long t = System.currentTimeMillis();
		List<GraphPath<Integer, DefaultEdge>> paths =bf.getPaths(so, ta);
		
		t = System.currentTimeMillis() - t;
		if (paths == null || paths.isEmpty()) return new Result(t, 0.0);
		
		double weights = paths.stream().mapToDouble(i -> graph.getType().isWeighted() ? i.getWeight() : i.getLength()).sum();
		return new Result(t, weights);
	}
	
	@Override
	public String getLibrary() {
		return "JGrapht";
	}

	@Override
	public String getName() {
		return "KShortestPaths";
	}

	@Override
	public Result KspatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source,
			Coordinate target, int nb) {
		KShortestPaths<Coordinate, DefaultEdge> bf = new KShortestPaths<>(graph, nb);
		long t = System.currentTimeMillis();
		List<GraphPath<Coordinate, DefaultEdge>> paths =bf.getPaths(source, target);
		
		t = System.currentTimeMillis() - t;
		if (paths == null || paths.isEmpty()) return new Result(t, 0.0);
		
		double weights = paths.stream().mapToDouble(i -> graph.getType().isWeighted() ? i.getWeight() : i.getLength()).sum();
		return new Result(t, weights);
	}
}