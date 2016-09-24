package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.State;
import algorithms.search.Searchable;

/**
 * Object adapter class for Maze3d.
 * Implements Searchable and contains a Maze3d object.
 * This class is domain specific for Maze3d domain.
 * 
 * @author Itamar Mizrahi & Chen Erlich
 *
 */

public class Maze3dDomain implements Searchable<Position> {

	private Maze3d maze;
	
	public Maze3d getMaze() {return this.maze;}
	
	public Maze3dDomain(Maze3d maze) {
		this.maze = maze;
	}
	public State<Position> getInitialState() {
		return new State<Position>(maze.getStartPosition());
	}

	public State<Position> getGoalState() {
		return new State<Position>(maze.getGoalPosition());
	}

	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		Position p = s.getState();
		
		ArrayList<Position> moves = maze.getAllPossibleMoves(p);
		ArrayList<State<Position>> states = new ArrayList<State<Position>>();
		
		for (Position pos: moves) {
			states.add(new State<Position>(pos));
		}
		return states;	
	}
	
	public double getMoveCost(State<Position> state1, State<Position> state2){
		return 1;
	}
}
