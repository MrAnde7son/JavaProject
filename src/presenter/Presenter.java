package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

/***
 * Presenter in MVP architecture.
 * @author Itamar&Chen
 *
 */
public class Presenter implements Observer {
	private Model model;
	private View view;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;
	
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
			
		commandsManager = new CommandsManager(model, view);
		commands = commandsManager.getCommandsMap();
	}

	@Override
	public void update(Observable o, Object arg) {
		String commandLine = (String)arg;
		
		String arr[] = commandLine.split(" ");
		String command = arr[0];			
		
		if(!commands.containsKey(command)) {
			view.displayMessage("Command doesn't exist");			
		}
		else {
			String commandArgs = null;
			if (arr.length > 1) {
				commandArgs = commandLine.substring(
						commandLine.indexOf(" ") + 1);							
			}
			Command cmd = commands.get(command);
			cmd.doCommand(commandArgs);	
		}
	}
}
