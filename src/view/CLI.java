package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;

/***
 * CLI in MVP architecture.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class CLI extends Observable implements View  {
	private BufferedReader in;
	private PrintWriter out;	
	
	public CLI(){
		
	}
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
	}
	
	private void printMenu() {
		out.print("Choose command: \n");
		displayMessage("\tdir <path>\n");
		displayMessage("\tgenerate_maze <name> <params>\n");
		displayMessage("\tdisplay <name>\n");
		displayMessage("\tdisplay_cross_section <name> <axis> <index>\n");
		displayMessage("\tsave_maze <name> <file_name>\n");
		displayMessage("\tload_maze <file_name> <name>\n");
		displayMessage("\tsolve <name> <algorithm>\n");
		displayMessage("\tdisplay_solution <name>\n");
		displayMessage("\texit\n");
		out.flush();
	}
	
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
				
					printMenu();
					try {
						String commandLine = in.readLine();
						sendCommand(commandLine);
						
						if (commandLine.equals("exit"))
							System.exit(0);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}			
		});
		thread.start();		
	}

	@Override
	public void notifyMazeIsReady(String name) {
		out.write("Maze " + name + " is ready.");
		out.flush();
	}

	@Override
	public void displayMaze(Maze3d maze) {
		out.write(maze.toString());
		out.flush();
	}

	@Override
	public void displayMessage(String msg) {
		out.write(msg);
		out.flush();
	}

	@Override
	public void sendCommand(String arg) {
		setChanged();
		notifyObservers(arg);
	}
	
}
