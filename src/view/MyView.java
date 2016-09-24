package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

import view.GUI.Maze3dWindow;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import utils.MyJaxbUtil;

/**
 * This is the view layer of the MVP
 * get all the IO from the CLI
 * @author Gal Basre & Ido Dror
 */
public class MyView extends Observable implements View {
	
	private BufferedReader in;
	private PrintWriter out;
	private UI ui;

	/**
	 * Constructor
	 * @param controller,BufferedReader ,PrintWriter
	 */
	public MyView(BufferedReader in, PrintWriter out) {
		this.ui = chooseUIFromProperties();
		this.in = in;
		this.out = out;
	}

	/**
	 * choose which UI from the properties: CLI/GUI
	 * @return the choose 
	 */
	private UI chooseUIFromProperties() {
		switch (MyJaxbUtil.getProperties().getUserInterface()) {
		case "CLI":
		case "cli":
			return new CLI(this);
		case "GUI":
		case "gui":
			return new Maze3dWindow(this);
		}
		return null;
	}

	/**
	 * Get the input stream
	 * @return BufferedReader 
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * Get the output stream
	 * @return PrintWriter 
	 */
	public PrintWriter getOut() {
		return out;
	}
	
	/**
	 * start
	 * call the start of cli
	 */
	@Override
	public void start() {
		this.ui.start();
	}
	
	/**
	 * executeCommand
	 * call the executeCommand of CLI
	 * @param  String
	 */
	public void executeCommand(String commandLine) {
		setChanged();
		notifyObservers(commandLine);		
	}
	
	/**
	 * Prints the string to the output stream
	 * @param out, String to print
	 */
	@Override
	public void printMessage(String msg) {
		this.ui.printMessage(msg);
	}

	/**
	 * Get line from the input stream
	 * @return String, the input
	 */
	@Override
	public String getLine() {
		String line = null;
		try {
			line = this.in.readLine();
		} catch (IOException e) {
			printMessage("IO Exception");
		}
		return line;
	}
	
	/**
	 * exit
	 * close all the input and output
	 */
	@Override
	public void exit() {
		try {
			in.close();
		} catch (IOException e) {
		}
		out.close();
		this.ui.exit();
	}

	/**
	 * call the ui.mazeReady
	 * @param maze, Maze3d
	 * @param mazeName, String
	 */
	@Override
	public void generatedMaze(Maze3d maze, String mazeName) {
		this.ui.mazeReady(maze, mazeName);
	}
	
	/**
	 * Print the message to the output stream
	 * @param msg, String
	 */
	public void printToOutputStream(String msg) {
		this.out.println(msg);
	}
	
	/**
	 * call the ui.move of the position
	 * @param pos, Position
	 */
	@Override
	public void move(Position pos) {
		this.ui.move(pos);
	}

	/**
	 * Call the ui.winner  
	 */
	@Override
	public void winner() {
		this.ui.winner();
	}

	/**
	 * display the solution - call the ui.displaySolution
	 * @param solution, Solution<Position>
	 */
	@Override
	public void displaySolution(Solution<Position> solution) {
		this.ui.displaySolution(solution);
	}
	
	/**
	 * Call the ui. databaseValues
	 * @param databaseValues, String
	 */
	@Override
	public void databaseValues(String databaseValues) {
		this.ui.databaseValues(databaseValues);
	}

	/**
	 * display all the dir in the path
	 * @param dirList, String[]
	 */
	@Override
	public void dirListReady(String[] dirList) {
		if(dirList.length != 0)
			this.ui.dirListReady(dirList);
		else this.printMessage("This path is empty!");
		
		
	}
}
