package miat.pathfinding.benchmark;


import java.io.IOException;
import java.util.Random;

import org.jgraph.graph.DefaultEdge;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.GeographicGraphGenerator;

public class BenchmarkShortestPathSpatial extends AbstractBenchmarkGraph<Coordinate, DefaultEdge>{

	public void run(final int nbTests, String path, boolean directed, final Random rand){
		BenchmarkGraph<Coordinate,DefaultEdge> graph = null;
		GeographicGraphGenerator gen = new GeographicGraphGenerator();
		try {
			graph = gen.generateFromShapefile(path, false);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		runAlgorithms1Path(graph, nbTests, rand, true);	
	}
}
