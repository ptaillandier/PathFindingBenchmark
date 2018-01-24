package miat.pathfinding.shortestpath.grph;

import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import grph.algo.search.StackBasedBellmanFordAlgorithm;
import grph.in_memory.InMemoryGrph;
import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;

public class GrphStackBasedBellmanFord extends GrphShortestPathAlgo {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		InMemoryGrph grph = Translation.benchmarkGraphToGrph(graph);
		return shortestPathComputation(new StackBasedBellmanFordAlgorithm(grph.getEdgeWidthProperty()), grph, graph.getType().isWeighted(), source, target);
	}

	@Override
	public String getName() {
		return "StackBasedBellmanFord";
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		InMemoryGrph grph = Translation.benchmarkGraphToGrphSpatial(graph); 
		return spatialShortestPathComputation(new StackBasedBellmanFordAlgorithm(grph.getEdgeWidthProperty()), grph, graph.getType().isWeighted(), graph.getVerticesIndex().get(source), graph.getVerticesIndex().get(target));
	}
}
