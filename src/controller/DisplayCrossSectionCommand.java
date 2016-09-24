package controller;


/***
 * display_by_cross_section command. Displays a 2D maze cutted by a given column.
 * @author Itamar&Chen
 *
 */
public class DisplayCrossSectionCommand extends CommonCommand {

	public DisplayCrossSectionCommand(CommonController controller) {
		super(controller);
	}

	@Override
	public void doCommand(String cmd) {
		String[] params = cmd.split(" ");
		String name = params[0];
		String axis = params[1].toLowerCase();
		int num = Integer.parseInt(params[2]);
		
		switch(axis){
			case("x"):
				this.controller.UpdateMessage(controller.getModel().getMaze(name).getCrossSectionByX(num).toString());
			case("y"):
				this.controller.UpdateMessage(controller.getModel().getMaze(name).getCrossSectionByY(num).toString());
			case("z"):
				this.controller.UpdateMessage(controller.getModel().getMaze(name).getCrossSectionByZ(num).toString());
		}
	}

}
