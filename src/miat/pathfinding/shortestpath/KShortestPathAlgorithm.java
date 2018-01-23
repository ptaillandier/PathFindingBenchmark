package miat.pathfinding.shortestpath;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.results.Result;

public interface KShortestPathAlgorithm {

	String getLibrary();
	String getName();
	Result KshortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target, int nb);
	Result KspatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target, int nb);
	
}
