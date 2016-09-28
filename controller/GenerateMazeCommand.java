package controller;

import model.MyModel;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;

/***
 * generate_maze command. Generate a new instance of Maze3d.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class GenerateMazeCommand extends CommonCommand {

	public GenerateMazeCommand(CommonController controller){
		super(controller);
	}

	@Override
	public void doCommand(String cmd) {
		String[] params = cmd.split(" ");
		String name = params[0];
		// TODO perform in separated thread
		((MyModel)this.controller.model).generateMaze(name, Integer.parseInt(params[1]), Integer.parseInt(params[2]), 3);		
	}

}
