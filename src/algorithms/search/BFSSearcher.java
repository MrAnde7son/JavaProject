package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of Best-First Search algorithm
 * 
 * @author  Itamar Mizrahi
 * @version 1.0
 * 
 */
public class BFSSearcher<T> extends CommonSearcher<T> {

	@Override
	public Solution<T> search(Searchable<T> s){
		openList.add(s.getInitialState());
		Set<State<T>> closedSet = new HashSet<State<T>>();
		
		// Search for path within the maze
		while(!openList.isEmpty()){
			State<T> n = popOpenList();
			closedSet.add(n);
			
			// Returns the path if goal state is achieved
			if(n.equals(s.getGoalState())){
				return backTrace(n);
			}
				
			// Gets all n's successors
			// iterates through each and determines the best path
			ArrayList<State<T>> successors = s.getAllPossibleStates(n);

			for(State<T> state: successors){
				if(!closedSet.contains(state) && !openList.contains(state)){
					state.setCameFrom(n);
					state.setCost(state.getCost() + s.getMoveCost(n, state));
					openList.add(state);
				}
				else {
					double newPathCost = n.getCost() + s.getMoveCost(n, state);
					if(state.getCost() > newPathCost) {
						state.setCost(newPathCost);
						state.setCameFrom(n);
						
						if(!openList.contains(state)) {
							openList.add(state);
						}
						// Adjust priority queue
						else {
							openList.remove(state);
							openList.add(state);
						}
					}		
				}
			}
		}
			
		
		return null;
	}
	
}
