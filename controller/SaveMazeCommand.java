package controller;

import java.io.IOException;

/***
 * save_maze command. Saves maze to file.
 * @author Itamar Mizrahi
 *
 */
public class SaveMazeCommand extends CommonCommand {

	public SaveMazeCommand(CommonController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String cmd) {
		try {
			this.controller.getModel().saveMaze(cmd);
		} catch (IOException e) {
			this.controller.UpdateMessage("Invalid path.");
		}
	}

}
