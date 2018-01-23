package miat.pathfinding.shortestpath.graphstream;

import org.graphstream.algorithm.AStar;
import org.graphstream.graph.Path;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class GraphStreamAStar implements ShortestPathAlgorithm {

	@Override
	public Result shortestPathComputation(Graph<Integer, DefaultEdge> graph, String source, String target) {
		org.graphstream.graph.Graph gsg = Translation.jGraphtToGraphStream(graph);
		AStar astar = new AStar();
		astar.init(gsg);
		astar.setSource(source);
		astar.setTarget(target);
		
		long t = System.currentTimeMillis();
		astar.compute();
		Path path = astar.getShortestPath();
		t = System.currentTimeMillis() - t;

		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
		
	}
	
	

	@Override
	public String getLibrary() {
		return "GraphStream";
	}

	@Override
	public String getName() {
		return "AStar";
	}



	@Override
	public Result spatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		org.graphstream.graph.Graph gsg = Translation.jGraphtToGraphStreamSpatial(graph);
		AStar astar = new AStar();
		astar.init(gsg);
		astar.setSource(source.toString());
		astar.setTarget(target.toString());
		
		long t = System.currentTimeMillis();
		astar.compute();
		Path path = astar.getShortestPath();
		t = System.currentTimeMillis() - t;

		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
	}

}
