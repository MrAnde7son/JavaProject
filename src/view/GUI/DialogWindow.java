package view.GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.MyView;

/**
 * abstract class DialogWindow
 * Data member Display, Shell, MyView
 * @author Itamar Mizrahi & Chen Erlich
 */
public abstract class DialogWindow {

	protected Display display;
	protected Shell shell;
	protected MyView view;
	
	/**
	 * Intialize widgests.
	 * 
	 */
	protected abstract void initWidgets();
	
	/**
	 * starts the window
	 * @param Display
	 */
	public void start(Display display) {
		this.display = display;
		this.shell = new Shell(this.display);
		initWidgets();
		this.shell.open();
	}
	
}
