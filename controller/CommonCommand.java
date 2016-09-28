package controller;
/***
 * Abstract class for Command types.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public abstract class CommonCommand implements Command {

	protected CommonController controller;
	
	public CommonCommand(CommonController controller) {
		this.controller = controller;
	}	
	
	@Override
	public abstract void doCommand(String cmd);
}
