package miat.pathfinding.benchmark;


import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import miat.pathfinding.graphGeneration.RandomGraphs;

public class BenchmarkShortestPathBarabasi extends AbstractBenchmarkGraph<Integer, DefaultEdge>{
	
	public void run(final int nbTests, final Integer nbInitNodes, final Integer nbFinalNodes, final Integer nbEdgeAdded, boolean weighted, final Random rand){
		Graph<Integer, DefaultEdge> graph = RandomGraphs.generateGraphstreamBarabasiAlbert(nbInitNodes,nbFinalNodes, nbEdgeAdded, weighted, rand);
		super.run(graph, nbTests, rand, false);	
	}
}
