package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/***
 * BasicWindow
 * @author Itamar&Chen
 *
 */
public abstract class BasicWindow extends Observable implements Runnable {

	protected Display display;
	protected Shell shell;
	
	/***
	 * Initialize widgets in window.
	 */
	protected abstract void initWidgets();
	
	@Override
	public void run() {
		display = new Display();
		shell = new Shell(display);

		initWidgets();
		
		shell.open();
		
		// Main loop, running until shell is disposed
		while(!shell.isDisposed()){
		
		   // 1. reads events and put them inside the queue.
		   // 2. dispatches the assigned listener
		   if(!display.readAndDispatch()){
		      display.sleep(); 
		   }
		
		}

		display.dispose();
	}

}
