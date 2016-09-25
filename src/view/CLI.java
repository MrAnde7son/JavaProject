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
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;		
	}
	
	private void printMenu() {
		out.print("Choose command: ");
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
						setChanged();
						notifyObservers(commandLine);
						
						if (commandLine.equals("exit"))
							break;
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		});
		thread.start();		
	}

	@Override
	public void notifyMazeIsReady(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMessage(String msg) {
		// TODO Auto-generated method stub
		
	}
	
//	private void printMenu()
//	{
//		this.view.printMessage("*****MENU*****");
//		this.view.printMessage("(0) <u/d/f/b/r/l> <maze_name>");
//		this.view.printMessage("(1) dir <path>");
//		this.view.printMessage("(2) generate_maze <name> <other params>");
//		this.view.printMessage("(3) display <name>");
//		this.view.printMessage("(4) display_cross_section <index> <{X,Y,Z}> <name>");
//		this.view.printMessage("(5) save_maze <name> <file name>");
//		this.view.printMessage("(6) load_maze <file name> <name>");
//		this.view.printMessage("(7) solve <name> <algorithm>");
//		this.view.printMessage("(8) display_solution <name>");
//		this.view.printMessage("(9) exit");
//	}
	
}
