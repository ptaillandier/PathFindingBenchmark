package miat.pathfinding.benchmark;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import miat.pathfinding.graphGeneration.RandomGraphs;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;
import miat.pathfinding.shortestpath.ShortestPathAlgorithms;

public class BenchmarkShortestPathBarabasi {
	int nbNode;
	
	public void run(final int nbTests, final Integer nbInitNodes, final Integer nbFinalNodes, final Integer nbEdgeAdded, boolean weighted, final Random rand){
		Graph<Integer, DefaultEdge> graph = RandomGraphs.generateGraphstreamBarabasiAlbert(nbInitNodes,nbFinalNodes, nbEdgeAdded, weighted, rand);
		 
		ShortestPathAlgorithms algos = new ShortestPathAlgorithms();
		List<Integer> nodes = new ArrayList<>(graph.vertexSet());
		for (int i = 0; i < nbTests; i++) {
			Integer source = nodes.get(rand.nextInt(nodes.size()));
			Integer target = nodes.get(rand.nextInt(nodes.size()));
			if (source == target) {
				i--; 
				continue;
			}
			for (ShortestPathAlgorithm algo: algos.getAlgorithms()) {
				Result result = algo.shortestPathComputation(graph, source.toString(), target.toString());
				System.out.println(algo.getLibrary() + " - " + algo.getName() + " path weigth:" + result.getPathWeight() + " time: " + result.getComputationTime());
			}
			System.out.println("************************************\n");
		}
		
		
		
			
	}
}
