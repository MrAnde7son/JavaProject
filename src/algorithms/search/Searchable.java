package algorithms.search;

import java.util.ArrayList;

/**
 * Defines a Searchable type, one who can operate with function search(Searchable s).
 * These three functions define a Searchable type.
 * 
 * @author Itamar Mizrahi & Chen Erlich
 * @version 1.0
 * 
 */
public interface Searchable<T> {
	public State<T> getInitialState();
	public State<T> getGoalState();
	public ArrayList<State<T>> getAllPossibleStates(State<T> s);
	public double getMoveCost(State<T> state1, State<T> state2);

}
