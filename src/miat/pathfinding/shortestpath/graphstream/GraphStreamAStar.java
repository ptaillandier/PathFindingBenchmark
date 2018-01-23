package miat.pathfinding.shortestpath.graphstream;

import org.graphstream.algorithm.AStar;
import org.graphstream.algorithm.AStar.Costs;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
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
		if (path == null || path.getEdgePath().isEmpty()) return new Result(t, 0.0);
		
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

	public class DistanceCosts implements Costs {

		@Override
		public double cost(Node arg0, Edge arg1, Node arg2) {
			return arg1.getAttribute("weight");
		}

		@Override
		public double heuristic(Node arg0, Node arg1) {
			((Coordinate)arg0.getAttribute("coordinate")).distance((Coordinate)arg1.getAttribute("coordinate"));
			return 0;
		}	
	}

	@Override
	public Result spatialShortestPathComputation(Graph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		org.graphstream.graph.Graph gsg = Translation.jGraphtToGraphStreamSpatial(graph);
		AStar astar = new AStar();
		astar.init(gsg);
		astar.setSource(source.toString());
		astar.setTarget(target.toString());
		astar.setCosts(new DistanceCosts());
		long t = System.currentTimeMillis();
		astar.compute();
		Path path = astar.getShortestPath();
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgePath().isEmpty()) return new Result(t, 0.0);
		
		return new Result(t, graph.getType().isWeighted() ? path.getPathWeight("weight") : path.getEdgeSet().size());
	}

}
