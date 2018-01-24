package miat.pathfinding.shortestpath;

import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.results.Result;

public interface KShortestPathAlgorithm {

	String getLibrary();
	String getName();
	Result KshortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target, int nb);
	Result KspatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target, int nb);
	
}
