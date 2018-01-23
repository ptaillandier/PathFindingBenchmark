package miat.pathfinding.shortestpath;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.results.Result;

public interface ShortestPathAlgorithm {

	String getLibrary();
	String getName();
	Result shortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target);
	Result spatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target);
	
}
