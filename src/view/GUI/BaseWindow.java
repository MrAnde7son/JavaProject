package view.GUI;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.UI;

/**
 * Defines an abstract BaseWindow.
 * extends UI
 * @param display Display type
 * @param shell Shell type
 * @param showSolutionByAnimation Timer type
 * @param animationSolutionTask TimerTask type
 * 
 * @author Itamar Mizrahi & Chen Erlich
 * 
 */
public abstract class BaseWindow extends UI {
	
	protected Display display;
	protected Shell shell;
	protected Timer showSolutionByAnimation;
	protected TimerTask animationSolutionTask;
	
	/**
	 * Abstract initWidgets - initializes Widgets
	 */
	protected abstract void initWidgets();
	
	/**
	 * 
	 * start - Calls run()
	 * @override 
	 */
	@Override
	public void start() {
		run();
	}
	
	/**
	 * run - The main and important loop of the GUI
	 *@override
	 */
	@Override
	public void run() {
		this.display = new Display();
		this.shell = new Shell(this.display);
		
		initWidgets();
		shell.open();
		
		// main event loop
		while (!shell.isDisposed()) // windows isn't closed
			if (!this.display.readAndDispatch())
				this.display.sleep();
		exit();
	}
	
	/**
	 * exit	- dispose the display and cancel the Timer and TimerTask (if runs)
	 */
	@Override
	public void exit() {
		if (this.showSolutionByAnimation != null)
			this.showSolutionByAnimation.cancel();
		if (this.animationSolutionTask != null)
			this.animationSolutionTask.cancel();
		this.display.dispose();
	}

}
