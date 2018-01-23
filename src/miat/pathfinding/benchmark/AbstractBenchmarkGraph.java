package miat.pathfinding.benchmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.ShortestPathAlgorithm;
import miat.pathfinding.shortestpath.ShortestPathAlgorithms;

public class AbstractBenchmarkGraph<V,E> {

	@SuppressWarnings("unchecked")
	public void runAlgorithms1Path(Graph<V, E> graph, final int nbTests,final Random rand, boolean spatialGraph){
		ShortestPathAlgorithms algos = new ShortestPathAlgorithms();
		List<V> nodes = new ArrayList<>(graph.vertexSet());
		Map<ShortestPathAlgorithm, List<Result>> results = new HashMap<ShortestPathAlgorithm, List<Result>>() ;
		
		for (int i = 0; i < nbTests; i++) {
			V source = nodes.get(rand.nextInt(nodes.size()));
			V target = nodes.get(rand.nextInt(nodes.size()));
			if (source == target) {
				i--; 
				continue;
			}
			for (ShortestPathAlgorithm algo: algos.getAlgorithms1Path()) {
				Result result =spatialGraph ?  algo.spatialShortestPathComputation((Graph<Coordinate, DefaultEdge>) graph, (Coordinate) source,(Coordinate) target)
						: algo.shortestPathComputation((Graph<Integer, DefaultEdge>) graph, source.toString(), target.toString()) ;
				
				if (!results.containsKey(algo)) results.put(algo, new ArrayList<>());
				results.get(algo).add(result);
			}
		}
		
		for (ShortestPathAlgorithm algo: algos.getAlgorithms1Path()) {
			List<Result> resultsAlgo = results.get(algo);
			long totalTime = resultsAlgo.stream().mapToLong(i -> i.getComputationTime()).sum();
			double totalPathWeight = resultsAlgo.stream().mapToDouble(i -> i.getPathWeight()).sum();
			System.out.println(algo.getLibrary() + " - " + algo.getName() + " time: " + totalTime + " weight:" + totalPathWeight);
		}
		
			
	}
}
