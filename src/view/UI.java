package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/**
 *  abstract class UI
 *  implements Runnable
 *  Data Member MyView view
 * @author Itamar Mizrahi & Chen Erlich
 *
 */
public abstract class UI implements Runnable {	
	protected MyView view;
	
	/**
	 * start
	 * abstract
	 */
	public abstract void start();
	
	/**
	 * exit
	 * abstract
	 */
	public abstract void exit();
	
	/**
	 * mazeReady
	 * abstract
	 * @param Maze3d maze
	 * @param String mazeName
	 */
	public abstract void mazeReady(Maze3d maze, String mazeName);
	/**
	 * printMessage
	 * abstract
	 * @param msg, String
	 */
	public abstract void displayMessage(String msg);
	
	/**
	 * displaySolution
	 * abstract
	 * @param solution, Solution<Position>
	 */
	public abstract void displaySolution(Solution<Position> solution);
	
	/**
	 * move
	 * abstract
	 * @param pos,Position
	 */
	public abstract void move(Position pos);
	
	/**
	 * winner
	 * abstract
	 */
	public abstract void winner();
	
	/**
	 * executeCommand
	 * abstract
	 * @param commandLine, String
	 */
	public abstract void executeCommand(String commandLine);

	/**
	 * databaseValues
	 * abstract
	 * @param databaseValues, String
	 */
	public abstract void databaseValues(String databaseValues);

	/**
	 * dirListReady
	 * abstract
	 * @param dirList, String[]
	 */
	public abstract void dirListReady(String[] dirList);
	
}
