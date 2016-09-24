package controller;

import model.Model;
import view.View;
/***
 * Abstract class for Controller types.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public abstract class CommonController implements Controller {

	protected Model model;
	protected View view;
	
	public CommonController(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public abstract void UpdateMessage(String name);
	
	public Model getModel() {
		return model;
	}

	public View getView() {
		return view;
	}
}
