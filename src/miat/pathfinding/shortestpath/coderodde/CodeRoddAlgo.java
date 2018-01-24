package miat.pathfinding.shortestpath.coderodde;

import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public abstract class CodeRoddAlgo extends AbstractShortestPathAlgorihm<DirectedGraph> {

	public Result shortestPathComputation(AbstractPathfinder pf, DirectedGraph gsg, Integer source, Integer target) {
		long t = System.currentTimeMillis();
		DirectedGraphPath path = pf.search(source, target);
		t = System.currentTimeMillis() - t;
		if (path == null) return new Result(t, 0.0);
		
		return new Result(t, path.getCost(gsg.getWeightFunction()));
	}
	
	@Override
	public String getLibrary() {
		return "CodeRode";
	}

}
