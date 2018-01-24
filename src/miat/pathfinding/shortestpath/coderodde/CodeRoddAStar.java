package miat.pathfinding.shortestpath.coderodde;

import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;

public class CodeRoddAStar extends CodeRoddAlgo {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		DirectedGraph gsg = getCache();
		if (gsg == null) {
			gsg = Translation.benchmarkGraphToCodeRode(graph);
		}
		HeuristicFunction heuristic = new HeuristicFunction() {
			@Override
			public double estimateDistanceBetween(int nodeId1, int nodeId2) {
				return 0;
			}
		};
		AStarPathfinder pf = new AStarPathfinder(gsg, gsg.getWeightFunction(), heuristic);
		return shortestPathComputation(pf, gsg, source, target);
	}
	
	

	@Override
	public String getName() {
		return "AStar";
	}
	
	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		DirectedGraph gsg = getCache();
		if (gsg == null) {
			gsg = Translation.benchmarkGraphToCodeRodeSpatial(graph);
		}
		EuclideanHeuristicFunction heuristic = new EuclideanHeuristicFunction(gsg.getNodeCoordinates());
		AStarPathfinder pf = new AStarPathfinder(gsg, gsg.getWeightFunction(), heuristic);
		return shortestPathComputation(pf, gsg, graph.getVerticesIndex().get(source), graph.getVerticesIndex().get(target));
	}

}
