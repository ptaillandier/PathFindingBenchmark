package miat.pathfinding.graphGeneration;

import java.util.Random;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.IntegerVertexFactory;
import org.jgrapht.generate.BarabasiAlbertGraphGenerator;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleGraph;

public class RandomGraphs {

	public static Graph<Integer,DefaultEdge> generateGraphstreamBarabasiAlbert(final Integer nbInitNodes, final Integer nbFinalNodes, final Integer nbEdgeAdded, boolean weighted, final Random rand) {
		BarabasiAlbertGraphGenerator<Integer, DefaultEdge> generator = new BarabasiAlbertGraphGenerator<>(nbInitNodes, nbEdgeAdded, nbFinalNodes, rand);
		EdgeFactory<Integer, DefaultEdge> factory = new ClassBasedEdgeFactory<>(DefaultEdge.class);
		Graph<Integer,DefaultEdge> graph = new SimpleGraph<Integer,DefaultEdge>(factory, weighted); 
		generator.generateGraph(graph, new IntegerVertexFactory(), null);
		return graph;
	}
	
	public static Graph<Integer,DefaultEdge> generateCompleteGraph(final Integer nbNodes,boolean weighted) {
		EdgeFactory<Integer, DefaultEdge> factory = new ClassBasedEdgeFactory<>(DefaultEdge.class);
		Graph<Integer,DefaultEdge> graph = new SimpleGraph<Integer,DefaultEdge>(factory, weighted); 
		CompleteGraphGenerator<Integer, DefaultEdge> generator = new CompleteGraphGenerator<Integer, DefaultEdge>(nbNodes);
		generator.generateGraph(graph, new IntegerVertexFactory(), null);
		return graph;
	}
}
