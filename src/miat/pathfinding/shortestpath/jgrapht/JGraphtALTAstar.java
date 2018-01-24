package miat.pathfinding.shortestpath.jgrapht;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.ALTAdmissibleHeuristic;
import org.jgrapht.alg.shortestpath.AStarShortestPath;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public class JGraphtALTAstar<V,E> extends AbstractShortestPathAlgorihm<Graph<V,E>> {
	double proportionLandmarks = 0.1;
	Random rand;
	
	
	
	public JGraphtALTAstar(double proportionLandmarks, Random rand) {
		super();
		this.proportionLandmarks = proportionLandmarks;
		this.rand = rand;
	}



	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		Set<Integer> landmarks = new HashSet<>();
		List<Integer> nodes = new ArrayList<Integer>(graph.getGraph().vertexSet());
		long nbLandmarks = Math.min(nodes.size() - 3,Math.max(3, Math.round(nodes.size() * proportionLandmarks)));
		
		while (landmarks.size() < nbLandmarks){
			int index = rand.nextInt(nodes.size() - 1);
			Integer node = nodes.get(index);
			landmarks.add(node);
			nodes.remove(index);
		}
		ALTAdmissibleHeuristic<Integer, DefaultEdge> heuristic = new ALTAdmissibleHeuristic<Integer, DefaultEdge>(graph.getGraph(),landmarks);
		
		AStarShortestPath<Integer, DefaultEdge> astar = new AStarShortestPath<>(graph.getGraph(),heuristic);
		long t = System.currentTimeMillis();
		GraphPath<Integer, DefaultEdge> path =astar.getPath(source, target);
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		
		t = System.currentTimeMillis() - t;
		return new Result(t, graph.getGraph().getType().isWeighted() ? path.getWeight() : path.getLength());
	}
	
	
	
	@Override
	public String getLibrary() {
		return "JGrapht";
	}

	@Override
	public String getName() {
		return "ALT AStar " + proportionLandmarks;
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		Set<Coordinate> landmarks = new HashSet<>();
		List<Coordinate> nodes = new ArrayList<Coordinate>(graph.getGraph().vertexSet());
		long nbLandmarks = Math.min(nodes.size() - 3,Math.max(3, Math.round(nodes.size() * proportionLandmarks)));
		while (landmarks.size() < nbLandmarks){
			int index = rand.nextInt(nodes.size() - 1);
			Coordinate node = nodes.get(index);
			landmarks.add(node);
			nodes.remove(index);
		}
		ALTAdmissibleHeuristic<Coordinate, DefaultEdge> heuristic = new ALTAdmissibleHeuristic<Coordinate, DefaultEdge>(graph.getGraph(),landmarks);
		AStarShortestPath<Coordinate, DefaultEdge> astar = new AStarShortestPath<>(graph.getGraph(),heuristic);
		long t = System.currentTimeMillis();
		GraphPath<Coordinate, DefaultEdge> path =astar.getPath(source, target);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		
		return new Result(t, graph.getGraph().getType().isWeighted() ? path.getWeight() : path.getLength());
	}
}