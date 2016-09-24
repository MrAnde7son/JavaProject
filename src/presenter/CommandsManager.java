package presenter;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

/***
 * Handles Maze3d commands in MVP architecture.
 * @author Itamar&Chen
 *
 */
public class CommandsManager {
	
	private Model model;
	private View view;
		
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;		
	}
	
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("maze_ready", new MazeReadyCommand());
		commands.put("solve_maze", new SolveMazeCommand());
		commands.put("display_solution", new DisplaySolutionCommand());
		
		return commands;
	}
	
	class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String args) {
			String[] params = args.split(" ");
			String name = params[0];
			int x = Integer.parseInt(params[1]);
			int y = Integer.parseInt(params[2]);
			int z = Integer.parseInt(params[3]);
			model.generateMaze(name, x, y, z);
		}		
	}
	
	class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String args) {
			String[] params = args.split(" ");
			String name = params[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}		
	}
	
	class MazeReadyCommand implements Command {

		@Override
		public void doCommand(String args) {
			String[] params = args.split(" ");
			String name = params[0];			
			view.notifyMazeIsReady(name);
		}		
	}
	
	class SolveMazeCommand implements Command {
		@Override
		public void doCommand(String args) {
			String[] params = args.split(" ");
			model.solve(params[0]);
	}
	}
	
	class DisplaySolutionCommand implements Command {

		@Override
		public void doCommand(String args) {
			String[] params = args.split(" ");
			model.getSolution(params[0]);
			
		}
	}
}

