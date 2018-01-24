package miat.pathfinding.benchmark;


import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.RandomGraphs;

public class BenchmarkShortestPathScaleFree extends AbstractBenchmarkGraph<Integer, DefaultEdge>{
	
	public void run(final int nbTests, final Integer nbNodes, boolean weighted, final Random rand){
		BenchmarkGraph<Integer, DefaultEdge> graph = RandomGraphs.generateScaleFreeGraph(nbNodes, weighted, rand);
		runAlgorithms1Path(graph, nbTests, rand, false);	
	}
}
