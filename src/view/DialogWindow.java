package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.MyView;

/**
 * DialogWindow
 * @author Itamar Mizrahi & Chen Erlich
 */
public abstract class DialogWindow {

	protected Display display;
	protected Shell shell;
	protected MyView view;

	protected abstract void initWidgets();
	
	public void start(Display display) {
		this.display = display;
		this.shell = new Shell(this.display);
		initWidgets();
		this.shell.open();
	}
	
}
