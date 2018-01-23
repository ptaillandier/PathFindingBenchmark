package miat.pathfinding.shortestpath.jgrapht;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.shortestpath.AStarShortestPath;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class JGraphtAStar implements ShortestPathAlgorithm {

	@Override
	public Result shortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target) {
		AStarAdmissibleHeuristic<Integer> heuristic = new AStarAdmissibleHeuristic<Integer>(){
			@Override
			public double getCostEstimate(Integer arg0, Integer arg1) {
				return 0;
			}
		};
		AStarShortestPath<Integer, DefaultEdge> dijkstra = new AStarShortestPath<>(graph,heuristic);
		Integer so = Integer.valueOf(source);
		Integer ta = Integer.valueOf(target);
		long t = System.currentTimeMillis();
		GraphPath<Integer, DefaultEdge> path =dijkstra.getPath(so, ta);
		
		t = System.currentTimeMillis() - t;
		return new Result(t, graph.getType().isWeighted() ? path.getWeight() : path.getLength());
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
	public Result spatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		AStarAdmissibleHeuristic<Coordinate> heuristic = new AStarAdmissibleHeuristic<Coordinate>(){
			@Override 
			public double getCostEstimate(Coordinate arg0, Coordinate arg1) {
				return (arg0.distance(arg1));
			}
		};
		AStarShortestPath<Coordinate, DefaultEdge> dijkstra = new AStarShortestPath<>(graph,heuristic);
		long t = System.currentTimeMillis();
		GraphPath<Coordinate, DefaultEdge> path =dijkstra.getPath(source, target);
		
		t = System.currentTimeMillis() - t;
		return new Result(t, graph.getType().isWeighted() ? path.getWeight() : path.getLength());
	}
}