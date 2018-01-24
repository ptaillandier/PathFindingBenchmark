package miat.pathfinding.shortestpath.jgrapht;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class JGraphtFloydWarshall implements ShortestPathAlgorithm {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		FloydWarshallShortestPaths<Integer, DefaultEdge> bf = new FloydWarshallShortestPaths<>(graph.getGraph());
		long t = System.currentTimeMillis();
		GraphPath<Integer, DefaultEdge> path =bf.getPath(source, target);
		
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
		return "FloydWarshall";
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source,
			Coordinate target) {
		FloydWarshallShortestPaths<Coordinate, DefaultEdge> bf = new FloydWarshallShortestPaths<>(graph.getGraph());
		long t = System.currentTimeMillis();
		GraphPath<Coordinate, DefaultEdge> path =bf.getPath(source, target);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		return new Result(t, graph.getType().isWeighted() ? path.getWeight() : path.getLength());
	}
}