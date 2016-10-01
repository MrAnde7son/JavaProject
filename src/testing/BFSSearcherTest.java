package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFSSearcher;
import algorithms.demo.Maze3dDomain;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;

/**
 * BFSTest
 * JUnit Testing for BFS Algorithm
 * @author Itamar&Chen
 */
public class BFSSearcherTest {

	Maze3d maze = new GrowingTreeGenerator().generate(4, 4, 4);
	Searchable<Position> mazeSearchable = new Maze3dDomain(maze);
	Searcher<Position> bfs = new BFSSearcher<Position>();
	Solution<Position> sol = bfs.search(mazeSearchable);

	@Test
	public void shouldReturnCountOfPathFromStartToFinishPosition() {
		assertEquals(5, sol.getStates().size());
	}
	
	@Test
	public void shouldReturnTheGoalStateOfTheMaze() {
		assertEquals(maze.getGoalPosition(), sol.getStates().get(sol.getStates().size()-1).getState());
	}
	
	@Test
	public void shouldReturnTheStartStateOfTheMaze() {
		assertEquals(maze.getStartPosition(), sol.getStates().get(0).getState());
	}
	
	@Test
	public void checkIfReturnsZeroNeighborsOfInvalidPosition() {
		assertEquals(0, mazeSearchable.getAllPossibleStates(new State<Position>(new Position(-1, 0, 0))).size());
	}
	
	@Test
	public void checkIfTheEvaluatedNumberOfNodesOfTheSolutionIsValid() {
		assertEquals(true, bfs.getNumberOfNodesEvaluated() >= sol.getStates().size());
	}
	
	@Test
	public void shouldReturnAllThePossibleMovesOfState() {
		Position posToBeChecked = new Position(0, 2, 3);
		List<State<Position>> desiredNeigborsList = new ArrayList<State<Position>>();
		List<State<Position>> possibleNeiborsFromTheAlgo = mazeSearchable.getAllPossibleStates(new State<Position>(posToBeChecked));
		desiredNeigborsList.add(new State<Position>(new Position(0, 2, 1)));
		desiredNeigborsList.add(new State<Position>(new Position(0, 2, 2)));
		assertEquals(true, desiredNeigborsList.containsAll(possibleNeiborsFromTheAlgo));
		assertEquals(true, possibleNeiborsFromTheAlgo.containsAll(desiredNeigborsList));
	}
}
