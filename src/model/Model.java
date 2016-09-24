package model;

import java.io.IOException;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
/***
 * Model in MVP architecutr
 * @author Itamar&Chen
 *
 */
public interface Model {
	public void generateMaze(String name, int x, int y, int z);
	public Maze3d getMaze(String name);
	public void solve(String arg);
	public Solution<Position> getSolution(String name);
	public void saveMaze(String arg) throws IOException;
	public void loadMaze(String arg) throws IOException;
	void exit();	
}
