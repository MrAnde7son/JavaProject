package controller;


/***
 * display command. Displays a given maze by its name.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class DisplayCommand extends CommonCommand {

	public DisplayCommand(CommonController controller) {
		super(controller);
	}

	@Override
	public void doCommand(String cmd) {
		this.controller.getModel().getMaze(cmd);
		
	}

}
