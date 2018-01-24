package miat.pathfinding.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import miat.pathfinding.shortestpath.ShortestPathAlgorithm;
import miat.pathfinding.shortestpath.coderodde.CodeRoddAStar;
import miat.pathfinding.shortestpath.coderodde.CodeRoddNBAStar;
import miat.pathfinding.shortestpath.jgrapht.JGraphtAStar;
import miat.pathfinding.shortestpath.jgrapht.JGraphtBidirectionalDijkstra;

public class BenchmarkShortestPathBest1PathAlgos {

	public static void main(String[] args) {
		
		List<ShortestPathAlgorithm>algorithms1path = new ArrayList<>();
		algorithms1path.add(new JGraphtBidirectionalDijkstra());
		algorithms1path.add(new JGraphtAStar());
		algorithms1path.add(new CodeRoddAStar());
		algorithms1path.add(new CodeRoddNBAStar());
	
		System.out.println("\n********** BARABASI GRAPH ***********");
		BenchmarkShortestPathBarabasi barabasi = new BenchmarkShortestPathBarabasi();
		barabasi.run(algorithms1path,100, 100, 50000, 5, true, new Random());
		
		System.out.println("\n********** SCALE FREE GRAPH ***********");
		BenchmarkShortestPathScaleFree scaleFree = new BenchmarkShortestPathScaleFree();
		scaleFree.run(algorithms1path,100, 50000, true, new Random());
		
		
		System.out.println("\n********** SPATIAL GRAPH ***********");
		BenchmarkShortestPathSpatial spatial = new BenchmarkShortestPathSpatial();
		String path = "data/shapefiles/routes_simplifieees_lanes_pont_guillaume_ok.shp";
		spatial.run(algorithms1path,100, path,  true,new Random());
		
	}

}
