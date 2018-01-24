package miat.pathfinding.shortestpath.coderodde;

	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Map;
	import java.util.Objects;
	import java.util.PriorityQueue;
	import java.util.Set;

	/**
	 * This pathfinding algorithm is due to Wim Pijls and Henk Post in "Yet another
	 * bidirectional algorithm for shortest paths." 15 June 2009.
	 * <p>
	 * <b>This class is not thread-safe.</b> If you need it in different threads,
	 * make sure each thread has its own object of this class.
	 *
	 * @author Rodion "rodde" Efremov
	 * @version 1.61 (Oct 13, 2016)
	 */
	public final class NBAStarPathfinder extends AbstractPathfinder {

	    private final HeuristicFunction heuristicFunction;
	    private final PriorityQueue<HeapEntry> OPENA = new PriorityQueue<>();
	    private final PriorityQueue<HeapEntry> OPENB = new PriorityQueue<>();
	    private final Map<Integer, Integer> PARENTSA = new HashMap<>();
	    private final Map<Integer, Integer> PARENTSB = new HashMap<>();
	    private final Map<Integer, Double> DISTANCEA = new HashMap<>();
	    private final Map<Integer, Double> DISTANCEB = new HashMap<>();
	    private final Set<Integer> CLOSED = new HashSet<>();

	    private double fA;
	    private double fB;
	    private double bestPathLength;
	    private Integer touchNode;
	    private Integer sourceNode;
	    private Integer targetNode;

	    public NBAStarPathfinder(DirectedGraph graph,
	            DirectedGraphWeightFunction weightFunction,
	            HeuristicFunction heuristicFunction) {
	        super(graph, weightFunction);
	        this.heuristicFunction
	                = Objects.requireNonNull(heuristicFunction,
	                        "The input heuristic function is null.");
	    }

	    @Override
	    public DirectedGraphPath search(int sourceNode, int targetNode) {
	        if (sourceNode == targetNode) {
	            return new DirectedGraphPath(Arrays.asList(sourceNode));
	        }

	        init(sourceNode, targetNode);

	        while (!OPENA.isEmpty() && !OPENB.isEmpty()) {
	            if (OPENA.size() < OPENB.size()) {
	                expandInForwardDirection();
	            } else {
	                expandInBackwardDirection();
	            }
	        }

	        if (touchNode == null) {
	            throw new TargetUnreachableException(graph, 
	                                                 sourceNode,
	                                                 targetNode);
	        }

	        return tracebackPath(touchNode, PARENTSA, PARENTSB);
	    }

	    private void expandInForwardDirection() {
	        Integer currentNode = OPENA.remove().getNode();

	        if (CLOSED.contains(currentNode)) {
	            return;
	        }

	        CLOSED.add(currentNode);

	        if (DISTANCEA.get(currentNode) +
	                heuristicFunction.estimateDistanceBetween(currentNode,
	                                                          targetNode)
	                >= bestPathLength
	                ||
	                DISTANCEA.get(currentNode) +
	                fB - 
	                heuristicFunction.estimateDistanceBetween(currentNode,
	                                                          sourceNode)
	                >= bestPathLength) {
	            // Reject the 'currentNode'.
	        } else {
	            // Stabilize the 'currentNode'.
	            for (Integer childNode : graph.getChildrenOf(currentNode)) {
	                if (CLOSED.contains(childNode)) {
	                    continue;
	                }

	                double tentativeDistance
	                        = DISTANCEA.get(currentNode)
	                        + weightFunction.get(currentNode, childNode);

	                if (!DISTANCEA.containsKey(childNode)
	                        || 
	                        DISTANCEA.get(childNode) > tentativeDistance) {
	                    DISTANCEA.put(childNode, tentativeDistance);
	                    PARENTSA.put(childNode, currentNode);
	                    HeapEntry e
	                            = new HeapEntry(
	                                    childNode,
	                                    tentativeDistance
	                                    + heuristicFunction
	                                    .estimateDistanceBetween(childNode,
	                                                             targetNode));
	                    OPENA.add(e);

	                    if (DISTANCEB.containsKey(childNode)) {
	                        double pathLength = tentativeDistance
	                                + DISTANCEB.get(childNode);

	                        if (bestPathLength > pathLength) {
	                            bestPathLength = pathLength;
	                            touchNode = childNode;
	                        }
	                    }
	                }
	            }
	        }

	        if (!OPENA.isEmpty()) {
	            fA = OPENA.peek().getDistance();
	        }
	    }

	    private void expandInBackwardDirection() {
	        Integer currentNode = OPENB.remove().getNode();

	        if (CLOSED.contains(currentNode)) {
	            return;
	        }

	        CLOSED.add(currentNode);

	        if (DISTANCEB.get(currentNode) +
	                heuristicFunction.estimateDistanceBetween(currentNode,
	                                                          sourceNode)
	                >= bestPathLength
	                || 
	                DISTANCEB.get(currentNode) +
	                fA -
	                heuristicFunction.estimateDistanceBetween(currentNode, 
	                                                          targetNode)
	                >= bestPathLength) {
	            // Reject the node 'currentNode'.
	        } else {
	            for (Integer parentNode : graph.getParentsOf(currentNode)) {
	                if (CLOSED.contains(parentNode)) {
	                    continue;
	                }

	                double tentativeDistance
	                        = DISTANCEB.get(currentNode)
	                        + weightFunction.get(parentNode, currentNode);

	                if (!DISTANCEB.containsKey(parentNode)
	                        ||
	                        DISTANCEB.get(parentNode) > tentativeDistance) {
	                    DISTANCEB.put(parentNode, tentativeDistance);
	                    PARENTSB.put(parentNode, currentNode);
	                    HeapEntry e
	                            = new HeapEntry(parentNode,
	                                    tentativeDistance
	                                    + heuristicFunction
	                                    .estimateDistanceBetween(parentNode,
	                                                             sourceNode));
	                    OPENB.add(e);

	                    if (DISTANCEA.containsKey(parentNode)) {
	                        double pathLength = tentativeDistance
	                                + DISTANCEA.get(parentNode);

	                        if (bestPathLength > pathLength) {
	                            bestPathLength = pathLength;
	                            touchNode = parentNode;
	                        }
	                    }
	                }
	            }
	        }

	        if (!OPENB.isEmpty()) {
	            fB = OPENB.peek().getDistance();
	        }
	    }

	    private void init(Integer sourceNode, Integer targetNode) {
	        OPENA.clear();
	        OPENB.clear();
	        PARENTSA.clear();
	        PARENTSB.clear();
	        DISTANCEA.clear();
	        DISTANCEB.clear();
	        CLOSED.clear();

	        double totalDistance
	                = heuristicFunction.estimateDistanceBetween(sourceNode,
	                                                            targetNode);

	        fA = totalDistance;
	        fB = totalDistance;
	        bestPathLength = Double.MAX_VALUE;
	        touchNode = null;
	        this.sourceNode = sourceNode;
	        this.targetNode = targetNode;

	        OPENA.add(new HeapEntry(sourceNode, fA));
	        OPENB.add(new HeapEntry(targetNode, fB));
	        PARENTSA.put(sourceNode, null);
	        PARENTSB.put(targetNode, null);
	        DISTANCEA.put(sourceNode, 0.0);
	        DISTANCEB.put(targetNode, 0.0);
	    }
	}

