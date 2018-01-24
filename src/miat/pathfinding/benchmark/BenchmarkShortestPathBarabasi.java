package miat.pathfinding.benchmark;


import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.RandomGraphs;

public class BenchmarkShortestPathBarabasi extends AbstractBenchmarkGraph<Integer, DefaultEdge>{
	
	public void run(final int nbTests, final Integer nbInitNodes, final Integer nbFinalNodes, final Integer nbEdgeAdded, boolean weighted, final Random rand){
		BenchmarkGraph<Integer, DefaultEdge> graph = RandomGraphs.generateBarabasiAlbert(nbInitNodes,nbFinalNodes, nbEdgeAdded, weighted, rand);
		runAlgorithms1Path(graph, nbTests, rand, false);	
	}
}
