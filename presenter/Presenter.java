package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

/***
 * Presenter in MVP architecture.
 * The Presenter is Observer, he waits for notifications and reacts.
 * @param model Model Type (Observable)
 * @param view View Type (Observable)
 * @param commandsManager CommandsManager Type
 * @param commands HashMap<String, Command> Type
 * @author Itamar & Chen
 *
 */
public class Presenter implements Observer {
	private Model model;
	private View view;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;
	
	// Ctor
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
		
		// Creating new commandManager for mapping String to Command
		commandsManager = new CommandsManager(model, view);
		commands = commandsManager.getCommandsMap();
	}

	
	/**
	 * This method is called whenever the observed object is changed (hasChanged flag changed state or SetChanged() was made).
	 * An application calls an Observable object's notifyObservers method to have all the object's observers notified of the change.
	 * @param o Observable Type
	 * @param arg Object Generic Type
	 * @author Itamar & Chen
	 */
	@Override
	public void update(Observable o, Object arg) {
		String commandLine = (String)arg; // Get message from the observable
		
		String arr[] = commandLine.split(" "); // Parse message
		String command = arr[0];			
		
		if(!commands.containsKey(command)) { // Check if there is no a string to command
			view.displayMessage("Command doesn't exist");			
		}
		else {
			String commandArgs = null;
			if (arr.length > 1) {
				commandArgs = commandLine.substring(
						commandLine.indexOf(" ") + 1);							
			}
			Command cmd = commands.get(command); // get the parsed Command
			cmd.doCommand(commandArgs);	// Do the parsed Command
		}
	}
}
