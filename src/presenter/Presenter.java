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
		String cmd = (String)arg;
		if(o == view)
			commandsManager.executeCommand(cmd);
		if(o == model)
			view.displayMessage(cmd);
	}
		
}
