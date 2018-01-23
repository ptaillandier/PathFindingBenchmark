package miat.pathfinding.shortestpath;

import java.util.ArrayList;
import java.util.List;

import miat.pathfinding.shortestpath.graphstream.GraphStreamAStar;
import miat.pathfinding.shortestpath.graphstream.GraphStreamDijkstra;
import miat.pathfinding.shortestpath.jgrapht.JGraphtAStar;
import miat.pathfinding.shortestpath.jgrapht.JGraphtDijkstra;

public class ShortestPathAlgorithms {

	private final List<ShortestPathAlgorithm> algorithms;
	
	public ShortestPathAlgorithms(){
		algorithms = new ArrayList<>();
		algorithms.add(new JGraphtDijkstra());
		algorithms.add(new JGraphtAStar());
		algorithms.add(new GraphStreamDijkstra());
		algorithms.add(new GraphStreamAStar());
	}

	public List<ShortestPathAlgorithm> getAlgorithms() {
		return algorithms;
	}
	
	
}
