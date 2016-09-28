package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import controller.Controller;
import properties.PropertiesManager;

/***
 * Implementation of View in MVP for Maze3d.
 * @author Itamar & Chen
 *
 */
public class MyView extends Observable implements View, Observer {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;
	private MazeWindow mazeWindow;
//	private Controller controller;

	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		this.mazeWindow = null;
		this.cli = null;
		
		String chosenView = chooseViewTypeFromProperties();
		if (chosenView == "CLI")
			this.cli = new CLI(in, out);
		else
			this.mazeWindow = new MazeWindow();

		
//		cli = new CLI(in, out);
//		cli.addObserver(this);
	}	
	
	/**
	 * choose which UI from the properties: CLI/GUI
	 * @return the choose 
	 */
	private String chooseViewTypeFromProperties() {
		switch (PropertiesManager.getProperties().getViewType()) {
		case "GUI":
		case "gui":
			return "GUI";
		case "CLI":
		case "cli":
			return "CLI";

		}
		return null;
	}
	@Override
	public void notifyMazeIsReady(String name) {
		out.println("maze " + name + " is ready");
		out.flush();
	}

	@Override
	public void displayMaze(Maze3d maze) {
		out.println(maze);
		out.flush();
	}

	@Override
	public void start() {
		if (cli != null)
			cli.start();
		else
			mazeWindow.start();
	}

	@Override
	public void displayMessage(String msg) {
		out.println(msg);
		out.flush();		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == cli || o == mazeWindow) {
			setChanged();
			notifyObservers(arg);
		}
	}
	
	public void sendCommand(String arg) {
		setChanged();
		notifyObservers(arg);
	}

}
