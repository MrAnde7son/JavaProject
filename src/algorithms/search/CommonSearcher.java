package algorithms.search;

import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;
	
	// Constructor
	public CommonSearcher(){
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes = 0;
	}
	
	// Removes an element from openList for evaluation
	protected State<T> popOpenList(){
		evaluatedNodes++;
		return openList.poll();
	}
	
	// Abstract function, search and returns a solution
	public abstract Solution<T> search(Searchable<T> s);

	// Returns the number of nodes evaluated
	public int getNumberOfNodesEvaluated() {
		return this.evaluatedNodes;
	}
	
	// Returns the path from 2 nodes
	protected Solution<T> backTrace(State<T> s){
		Solution<T> sol = new Solution<T>(); // Initiate Solution
		State<T> itr = s; // Initiate Iterator as the given state (needs to be the goal state)
		sol.addState(itr);
		while(itr != null) {
			itr = itr.getCameFrom();
			sol.addState(itr); // add to the solution where it came from
		}
		return sol;
	}

}
