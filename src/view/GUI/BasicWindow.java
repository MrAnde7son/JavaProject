package view.GUI;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * BasicWindow
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public abstract class BasicWindow extends Observable implements Runnable {

	protected Display display;
	protected Shell shell;
	
	
	protected abstract void initWidgets();
	
	@Override
	public void run() {
		display = new Display();  // our display
		shell = new Shell(display); // our window

		initWidgets();
		
		shell.open();
		
		// main event loop
		while(!shell.isDisposed()){ // while window isn't closed
		
		   // 1. read events, put then in a queue.
		   // 2. dispatch the assigned listener
		   if(!display.readAndDispatch()){ 	// if the queue is empty
		      display.sleep(); 			// sleep until an event occurs 
		   }
		
		} // shell is disposed

		display.dispose(); // dispose OS components
	}

}
