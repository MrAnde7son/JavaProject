package algorithms.search;

import java.util.ArrayList;

/**
 * Implementation of Depth-First Search algorithm
 * 
 * @author  Itamar
 * @version 1.0
 *
 */
public class DFSSearcher<T> extends CommonSearcher<T> {

	@Override
	public Solution<T> search(Searchable<T> s) {
		
		ArrayList<State<T>> discovered = new ArrayList<State<T>>();
		ArrayList<State<T>> neighbors = null;
		State<T> state = null;
		
		openList.add(s.getInitialState());
		while(!openList.isEmpty()) {
			state = popOpenList();
			
			if(!state.equals(s.getGoalState())) {
				discovered.add(state);
				neighbors = s.getAllPossibleStates(state);
				for(State<T> st: neighbors) {
					st.setCameFrom(state);
					if(st.equals(s.getGoalState())) {
						return backTrace(st);
					}
					else if(!openList.contains(st)){
						openList.add(st);
						discovered.add(st);
					}
				}
				
			}
			else {
				return backTrace(state);
			}
		}
		return null;
	}

}
