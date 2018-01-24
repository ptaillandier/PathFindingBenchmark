package miat.pathfinding.shortestpath;

public abstract class AbstractShortestPathAlgorihm<G> implements ShortestPathAlgorithm<G>{
	G cache;

	public G getCache() {
		return cache;
	}

	public void setCache(G cache) {
		this.cache = cache;
	}

	@Override
	public void releaseCache() {
		cache = null;
		
	}
	
	
}
