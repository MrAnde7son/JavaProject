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
	
	private ArrayList<State<T>> states;
	
	public Solution(){
		this.states = new ArrayList<State<T>>();
	}
	
	public void addState(State<T> s) {
		this.states.add(s);
	}
	
	@Override
	public String toString() {
		String sol = new String();
		for(State<T> state: states){
			sol += state.getState() + " <- ";
		}
		sol += "Start";
		return sol;
	}
	
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		Solution<T> solution = (Solution<T>)obj;
		return this.states.equals(solution.states);
	}

	public ArrayList<State<T>> getStates() {
		return states;
	}

	public void setSolution(ArrayList<State<T>> states) {
		this.states = states;
	}
}
