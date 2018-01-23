package miat.pathfinding.benchmark;

import java.util.Random;

public class BenchmarkShortestPath {

	public static void main(String[] args) {
		
		/*BenchmarkShortestPathBarabasi barabasi = new BenchmarkShortestPathBarabasi();
		
		barabasi.run(1, 1000, 10000, 10, false, new Random());*/
		
		BenchmarkShortestPathSpatial spatial = new BenchmarkShortestPathSpatial();
		String path = "data/shapefiles/routes_simplifieees_lanes_pont_guillaume_ok.shp";
		
		spatial.run(10, path,  false,new Random());
		

	}

}
