package algorithms.search;

/**
 * Defines a State<T> in a searching problem.
 * 
 * @author  Itamar Mizrahi
 * @version 1.0
 * 
 */
public class State<T> implements Comparable<State<T>> {
	
	private T state;
	private double cost;
	private State<T> cameFrom;
	
	// Constructor
	public State(T state2){
		this.state = state2;
	}

	// Getters and Setters
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	@Override
	public String toString() {
		return this.state + " " + this.cost + " " + this.cameFrom;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.state.equals(((State<T>)obj).state);
	}
	
	public int compareTo(State<T> s) {
		return (int)(this.cost - s.cost);
	}
}
