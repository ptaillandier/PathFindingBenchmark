package miat.pathfinding.benchmark;

import java.util.Random;

public class BenchmarkShortestPath {

	public static void main(String[] args) {
		
		System.out.println("\n**********BARABASI GRAPH***********");
		BenchmarkShortestPathBarabasi barabasi = new BenchmarkShortestPathBarabasi();
		barabasi.run(10, 100, 1000, 2, true, new Random());
		
		System.out.println("\n**********SCALE FREE GRAPH***********");
		BenchmarkShortestPathScaleFree scaleFree = new BenchmarkShortestPathScaleFree();
		scaleFree.run(10, 1000, true, new Random());
		
		
		System.out.println("\n**********SPATIAL GRAPH***********");
		BenchmarkShortestPathSpatial spatial = new BenchmarkShortestPathSpatial();
		String path = "data/shapefiles/routes_simplifieees_lanes_pont_guillaume_ok.shp";
		spatial.run(1, path,  false,new Random());
		
	}

}
