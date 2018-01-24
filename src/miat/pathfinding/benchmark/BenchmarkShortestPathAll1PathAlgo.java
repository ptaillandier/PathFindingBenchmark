package miat.pathfinding.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import miat.pathfinding.shortestpath.ShortestPathAlgorithm;
import miat.pathfinding.shortestpath.coderodde.CodeRoddAStar;
import miat.pathfinding.shortestpath.coderodde.CodeRoddDijstra;
import miat.pathfinding.shortestpath.coderodde.CodeRoddNBAStar;
import miat.pathfinding.shortestpath.graphstream.GraphStreamAStar;
import miat.pathfinding.shortestpath.graphstream.GraphStreamBellmanFord;
import miat.pathfinding.shortestpath.graphstream.GraphStreamDijkstra;
import miat.pathfinding.shortestpath.grph.GrphDijkstra;
import miat.pathfinding.shortestpath.grph.GrphStackBasedBellmanFord;
import miat.pathfinding.shortestpath.jgrapht.JGraphtALTAstar;
import miat.pathfinding.shortestpath.jgrapht.JGraphtAStar;
import miat.pathfinding.shortestpath.jgrapht.JGraphtBellmanFord;
import miat.pathfinding.shortestpath.jgrapht.JGraphtBidirectionalDijkstra;
import miat.pathfinding.shortestpath.jgrapht.JGraphtDijkstra;
import miat.pathfinding.shortestpath.jung.JungDijkstra;

public class BenchmarkShortestPathAll1PathAlgo {

	public static void main(String[] args) {
		
		List<ShortestPathAlgorithm>algorithms1path = new ArrayList<>();
		algorithms1path.add(new JGraphtDijkstra());
		algorithms1path.add(new JGraphtBidirectionalDijkstra());
		algorithms1path.add(new JGraphtAStar());
		algorithms1path.add(new JGraphtALTAstar(0.01, new Random()));
		algorithms1path.add(new JGraphtBellmanFord<>());
		algorithms1path.add(new GraphStreamDijkstra());
		algorithms1path.add(new GraphStreamAStar());
	//	algorithms1path.add(new GraphStreamBellmanFord()); TOO SLOW !!!!
		algorithms1path.add(new GrphDijkstra());
		algorithms1path.add(new GrphStackBasedBellmanFord());
		algorithms1path.add(new JungDijkstra<>());
		algorithms1path.add(new CodeRoddAStar());
		algorithms1path.add(new CodeRoddDijstra());
		algorithms1path.add(new CodeRoddNBAStar());
	
		System.out.println("\n**********BARABASI GRAPH***********");
		BenchmarkShortestPathBarabasi barabasi = new BenchmarkShortestPathBarabasi();
		barabasi.run(algorithms1path,5, 100, 5000, 2, true, new Random());
		
		System.out.println("\n**********SCALE FREE GRAPH***********");
		BenchmarkShortestPathScaleFree scaleFree = new BenchmarkShortestPathScaleFree();
		scaleFree.run(algorithms1path,10, 10000, true, new Random());
		
		
		System.out.println("\n**********SPATIAL GRAPH***********");
		BenchmarkShortestPathSpatial spatial = new BenchmarkShortestPathSpatial();
		String path = "data/shapefiles/routes_simplifieees_lanes_pont_guillaume_ok.shp";
		spatial.run(algorithms1path,5, path,  true,new Random());
		
	}

}
