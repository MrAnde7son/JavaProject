package algorithms.search;

import java.util.ArrayList;

/**
 * Defines a solution for a searching problem.
 * Solution is defined by the path taken, to get from the initial state to the goal.
 * 
 * @author  Itamar Mizrahi
 * @version 1.0
 * 
 */
public class Solution<T> {
	
	private ArrayList<State<T>> solution;
	
	public Solution(){
		this.solution = new ArrayList<State<T>>();
	}
	
	public void addState(State<T> s) {
		this.solution.add(s);
	}
	
	@Override
	public String toString() {
		String sol = new String();
		for(State<T> state: solution){
			sol += state.getState() + " <- ";
		}
		sol += "Start";
		return sol;
	}
	
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		Solution<T> solution = (Solution<T>)obj;
		return this.solution.equals(solution.solution);
	}

	public ArrayList<State<T>> getSolution() {
		return solution;
	}

	public void setSolution(ArrayList<State<T>> solution) {
		this.solution = solution;
	}
}
