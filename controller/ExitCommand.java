package controller;

/***
 * exit command. Stops the exection of the programs with all its threads.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class ExitCommand extends CommonCommand {

	public ExitCommand(CommonController controller) {
		super(controller);
	}

	@Override
	public void doCommand(String cmd) {
		this.controller.getModel().exit();
	}

}
