package miat.pathfinding.graphGeneration;

import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.IntegerVertexFactory;
import org.jgrapht.generate.BarabasiAlbertGraphGenerator;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.generate.ScaleFreeGraphGenerator;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleGraph;

import miat.pathfinding.graph.BenchmarkGraph;

public class RandomGraphs {

	public static BenchmarkGraph<Integer,DefaultEdge> generateBarabasiAlbert(final Integer nbInitNodes, final Integer nbFinalNodes, final Integer nbEdgeAdded, boolean weighted, final Random rand) {
		BenchmarkGraph<Integer,DefaultEdge> g = new BenchmarkGraph<>();
		BarabasiAlbertGraphGenerator<Integer, DefaultEdge> generator = new BarabasiAlbertGraphGenerator<>(nbInitNodes, nbEdgeAdded, nbFinalNodes, rand);
		EdgeFactory<Integer, DefaultEdge> factory = new ClassBasedEdgeFactory<>(DefaultEdge.class);
		Graph<Integer,DefaultEdge> graph = new SimpleGraph<Integer,DefaultEdge>(factory, weighted); 
		generator.generateGraph(graph, new IntegerVertexFactory(), null);
		if (weighted) {
			for (DefaultEdge e : graph.edgeSet())
				graph.setEdgeWeight(e, 1.0);
		}
		g.setGraph(graph);
		return g;
	}
	
	public static BenchmarkGraph<Integer,DefaultEdge> generateCompleteGraph(final Integer nbNodes,boolean weighted) {
		BenchmarkGraph<Integer,DefaultEdge> g = new BenchmarkGraph<>();
		EdgeFactory<Integer, DefaultEdge> factory = new ClassBasedEdgeFactory<>(DefaultEdge.class);
		Graph<Integer,DefaultEdge> graph = new SimpleGraph<Integer,DefaultEdge>(factory, weighted); 
		CompleteGraphGenerator<Integer, DefaultEdge> generator = new CompleteGraphGenerator<Integer, DefaultEdge>(nbNodes);
		generator.generateGraph(graph, new IntegerVertexFactory(), null);
		if (weighted) {
			for (DefaultEdge e : graph.edgeSet())
				graph.setEdgeWeight(e, 1.0);
		}
		g.setGraph(graph);
		return g;
	}
	
	public static BenchmarkGraph<Integer,DefaultEdge> generateScaleFreeGraph(final Integer nbNodes, boolean weighted, final Random rand) {
		BenchmarkGraph<Integer,DefaultEdge> g = new BenchmarkGraph<>();
		ScaleFreeGraphGenerator<Integer, DefaultEdge> generator = new ScaleFreeGraphGenerator<>(nbNodes,  rand);
		EdgeFactory<Integer, DefaultEdge> factory = new ClassBasedEdgeFactory<>(DefaultEdge.class);
		Graph<Integer,DefaultEdge> graph = new SimpleGraph<Integer,DefaultEdge>(factory, weighted); 
		generator.generateGraph(graph, new IntegerVertexFactory(), null);
		if (weighted) {
			for (DefaultEdge e : graph.edgeSet())
				graph.setEdgeWeight(e, 1.0);
		}
		g.setGraph(graph);
		return g;
	}
}
