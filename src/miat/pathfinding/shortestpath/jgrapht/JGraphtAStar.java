package miat.pathfinding.shortestpath.jgrapht;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.shortestpath.AStarShortestPath;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public class JGraphtAStar<V,E> extends AbstractShortestPathAlgorihm<Graph<V,E>> {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		AStarAdmissibleHeuristic<Integer> heuristic = new AStarAdmissibleHeuristic<Integer>(){
			@Override
			public double getCostEstimate(Integer arg0, Integer arg1) {
				return 0;
			}
		};
		AStarShortestPath<Integer, DefaultEdge> dijkstra = new AStarShortestPath<>(graph.getGraph(),heuristic);
		long t = System.currentTimeMillis();
		GraphPath<Integer, DefaultEdge> path =dijkstra.getPath(source, target);
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		
		t = System.currentTimeMillis() - t;
		return new Result(t, graph.getGraph().getType().isWeighted() ? path.getWeight() : path.getLength());
	}
	
	
	
	@Override
	public String getLibrary() {
		return "JGrapht";
	}

	@Override
	public String getName() {
		return "AStar";
	}



	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		AStarAdmissibleHeuristic<Coordinate> heuristic = new AStarAdmissibleHeuristic<Coordinate>(){
			@Override 
			public double getCostEstimate(Coordinate arg0, Coordinate arg1) {
				return (arg0.distance(arg1));
			}
		};
		AStarShortestPath<Coordinate, DefaultEdge> astar = new AStarShortestPath<>(graph.getGraph(),heuristic);
		long t = System.currentTimeMillis();
		GraphPath<Coordinate, DefaultEdge> path =astar.getPath(source, target);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		
		return new Result(t, graph.getGraph().getType().isWeighted() ? path.getWeight() : path.getLength());
	}
}