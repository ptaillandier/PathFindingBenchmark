package miat.pathfinding.shortestpath.grph;

import grph.algo.search.SearchResult;
import grph.algo.search.SingleSourceSearchAlgorithm;
import grph.in_memory.InMemoryGrph;
import grph.path.ArrayListPath;
import it.unimi.dsi.fastutil.ints.IntSet;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public abstract class GrphShortestPathAlgo extends AbstractShortestPathAlgorihm<InMemoryGrph> {

	public Result shortestPathComputation(SingleSourceSearchAlgorithm<SearchResult> algo, InMemoryGrph grph, boolean isWeighted, Integer source, Integer target) {
		long t = System.currentTimeMillis();
		SearchResult result = algo.compute(grph,source);
		ArrayListPath path = result.computePathTo(target);		
		t = System.currentTimeMillis() - t;
		double weight = 0;
		if (isWeighted) {
			weight = computeWeight(path,grph);
		} else {
			weight = path.getLength();
		}

		return new Result(t,weight);
	}
	
	@Override
	public String getLibrary() {
		return "Grph";
	}
	
	public int computeWeight(ArrayListPath path, InMemoryGrph grph){
		int weight = 0;
		int[] vtable = path.toVertexArray();
		int v = vtable[0];
		for (int i = 1; i < vtable.length; i++) {
			int vt = vtable[i];
			IntSet es = grph.getEdgesConnecting(v, vt);
			long minV = Long.MAX_VALUE;
			for (Integer e : es) {
				long val = grph.getEdgeWidthProperty().getValue(e);
				if (val < minV) minV = val;
			}
			weight += minV;
			v = vt;	
		}
		return weight;		
	}

	public Result spatialShortestPathComputation(SingleSourceSearchAlgorithm<SearchResult> algo,InMemoryGrph grph, boolean isWeighted, Integer source, Integer target) {
		long t = System.currentTimeMillis();
		SearchResult result = algo.compute(grph,source);
		ArrayListPath path = result.computePathTo(target);
		t = System.currentTimeMillis() - t;
		double weight = 0;
		if (isWeighted) {
			weight = computeWeight(path,grph);
		} else {
			weight = path.getLength();
		}
		return new Result(t,weight);
	}
	
}
