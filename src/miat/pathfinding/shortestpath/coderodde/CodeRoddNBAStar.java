package miat.pathfinding.shortestpath.coderodde;

import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;

public class CodeRoddNBAStar extends CodeRoddAlgo {

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
		NBAStarPathfinder pf = new NBAStarPathfinder(gsg, gsg.getWeightFunction(), heuristic);
		return shortestPathComputation(pf, gsg, source, target);
	}
	
	

	@Override
	public String getName() {
		return "NBAStar";
	}
	
	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		DirectedGraph gsg = getCache();
		if (gsg == null) {
			gsg = Translation.benchmarkGraphToCodeRodeSpatial(graph);
		}
		EuclideanHeuristicFunction heuristic = new EuclideanHeuristicFunction(gsg.getNodeCoordinates());
		NBAStarPathfinder pf = new NBAStarPathfinder(gsg, gsg.getWeightFunction(), heuristic);
		return shortestPathComputation(pf, gsg, graph.getVerticesIndex().get(source), graph.getVerticesIndex().get(target));
	}

}
