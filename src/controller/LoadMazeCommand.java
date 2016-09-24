package controller;

import java.io.IOException;

/***
 * load_maze command. Loads maze from file.
 * @author Itamar&Chen
 *
 */
public class LoadMazeCommand extends CommonCommand {

	public LoadMazeCommand(CommonController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String cmd) {
		try {
			this.controller.getModel().loadMaze(cmd);
		} catch (IOException e) {
			this.controller.UpdateMessage("Invalid path.");
		}
	}

}
