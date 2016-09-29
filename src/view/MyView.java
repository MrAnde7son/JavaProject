package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import properties.PropertiesManager;

/***
 * Implementation of View in MVP for Maze3d.
 * @author Itamar & Chen
 *
 */
public class MyView extends Observable implements View {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;
	private MazeWindow mazeWindow;
//	private Controller controller;

	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		this.cli = null;
		this.mazeWindow = null;
		
		String chosenView = chooseViewTypeFromProperties();
		if (chosenView == "CLI")
			this.cli = new CLI(in, out);
		else
			this.mazeWindow = new MazeWindow(this);
	}	
	
	/**
	 * choose which UI from the properties: CLI/GUI
	 * @return the choose 
	 */
	private String chooseViewTypeFromProperties() {
		switch (PropertiesManager.getInstance().getProperties().getViewType().toLowerCase()) {
		case "gui":
			return "GUI";
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

	public void start() {
		if (cli != null)
			cli.start();
		else
			mazeWindow.start();
	}

	@Override
	public void displayMessage(String msg) {
		MessageBox messageBox = new MessageBox(this.mazeWindow.shell, SWT.ICON_INFORMATION | SWT.OK );
		messageBox.setText("Message");
		messageBox.setMessage(msg);
		messageBox.open();	
	}
	
	@Override
	public void sendCommand(String arg) {
		setChanged();
		notifyObservers(arg);
	}

}
