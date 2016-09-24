package controller;

import java.util.HashMap;

import model.Model;
import view.View;
/***
 * Controller component in MVC. Designed to work with Maze3d.
 * @author Itamar & Chen
 *
 */
public class MyController extends CommonController {
	
	private HashMap<String, Command> commands;
	
	/**
	 * MyController constructor - get Model and View
	 * initialize the model and view in the CommonController
	 * create the HashMap from String to Command
	 * @param model - get object from type Model
	 * @param view - get object from type View
	 */
	
	public MyController(Model model, View view) {
		super(model, view);
		commands = new HashMap<>();
		commands.put("dir", new DirCommand(this));
		commands.put("display", new DisplayCommand(this));
		commands.put("generate", new GenerateMazeCommand(this));
		commands.put("solve", new SolveCommand(this));
		commands.put("save", new SaveMazeCommand(this));
		commands.put("load", new LoadMazeCommand(this));
		commands.put("display_cross_section", new DisplayCrossSectionCommand(this));
		commands.put("display_solution", new DisplaySolutionCommand(this));
		commands.put("exit", new ExitCommand(this));
	}
	
	@Override
	public void UpdateMessage(String name) {
		this.view.displayMessage(name);

	}
	
	public HashMap<String, Command> getCommands(){
		return this.commands;
	}

}
