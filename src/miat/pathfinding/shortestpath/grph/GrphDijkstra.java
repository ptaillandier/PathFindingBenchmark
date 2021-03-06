package miat.pathfinding.shortestpath.grph;

import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import grph.algo.search.DijkstraAlgorithm;
import grph.in_memory.InMemoryGrph;
import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;

public class GrphDijkstra extends GrphShortestPathAlgo {

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		InMemoryGrph grph = getCache() == null ? Translation.benchmarkGraphToGrph(graph) : getCache();
		return shortestPathComputation(new DijkstraAlgorithm(grph.getEdgeWidthProperty()), grph, graph.getType().isWeighted(), source, target);
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		InMemoryGrph grph = getCache() == null ? Translation.benchmarkGraphToGrphSpatial(graph) : getCache(); 
		return spatialShortestPathComputation(new DijkstraAlgorithm(grph.getEdgeWidthProperty()), grph, graph.getType().isWeighted(), graph.getVerticesIndex().get(source), graph.getVerticesIndex().get(target));
	}
	@Override
	public String getName() {
		return "Dijkstra";
	}

	

}
