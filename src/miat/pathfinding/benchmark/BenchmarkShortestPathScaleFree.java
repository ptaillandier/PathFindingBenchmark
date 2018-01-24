package miat.pathfinding.benchmark;


import java.util.List;
import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.RandomGraphs;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;

public class BenchmarkShortestPathScaleFree extends AbstractBenchmarkGraph<Integer, DefaultEdge>{
	
	public void run(List<ShortestPathAlgorithm> algos,final int nbTests, final Integer nbNodes, boolean weighted, final Random rand){
		BenchmarkGraph<Integer, DefaultEdge> graph = RandomGraphs.generateScaleFreeGraph(nbNodes, weighted, rand);
		runAlgorithms1Path(algos,graph, nbTests, rand, false);	
	}
}
