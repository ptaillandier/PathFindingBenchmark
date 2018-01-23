package miat.pathfinding.results;

public class Result {
	Long computationTime;
	Double pathWeight;
	
	
	public Result(Long computationTime, Double pathWeight) {
		super();
		this.computationTime = computationTime;
		this.pathWeight = pathWeight;
	}
	public Long getComputationTime() {
		return computationTime;
	}
	public void setComputationTime(Long computationTime) {
		this.computationTime = computationTime;
	}
	public Double getPathWeight() {
		return pathWeight;
	}
	public void setPathWeight(Double pathWeight) {
		this.pathWeight = pathWeight;
	}
	
	
}
