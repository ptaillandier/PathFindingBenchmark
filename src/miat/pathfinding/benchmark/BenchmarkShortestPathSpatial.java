package miat.pathfinding.benchmark;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graphGeneration.GeographicGraphGenerator;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;
import miat.pathfinding.shortestpath.ShortestPathAlgorithms;

public class BenchmarkShortestPathSpatial {
	int nbNode;
	
	public void run(final int nbTests, String path, boolean directed, final Random rand){
		Graph<Coordinate,DefaultEdge> graph = null;
		GeographicGraphGenerator gen = new GeographicGraphGenerator();
		try {
			graph = gen.generateFromShapefile(path, false);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		ShortestPathAlgorithms algos = new ShortestPathAlgorithms();
		List<Coordinate> nodes = new ArrayList<>(graph.vertexSet());
		for (int i = 0; i < nbTests; i++) {
			Coordinate source = nodes.get(rand.nextInt(nodes.size()));
			Coordinate target = nodes.get(rand.nextInt(nodes.size()));
			if (source == target) {
				i--; 
				continue;
			}
			for (ShortestPathAlgorithm algo: algos.getAlgorithms()) {
				Result result = algo.spatialShortestPathComputation(graph, source, target);
				System.out.println(algo.getLibrary() + " - " + algo.getName() + " path weight:" + result.getPathWeight() + " time: " + result.getComputationTime());
			}
			System.out.println("************************************\n");
		}
		
		
		
			
	}
}
