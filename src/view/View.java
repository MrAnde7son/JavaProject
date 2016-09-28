package view;

import algorithms.mazeGenerators.Maze3d;
/***
 * View in MVP architecture.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public interface View {
	public void notifyMazeIsReady(String name);
	public void displayMaze(Maze3d maze);
	public void displayMessage(String msg);	
	public void start();
	public void sendCommand(String arg);
}
