package view.GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import properties.PropertiesManager;

/**
 * class StartWindow
 * implements Runnable
 * Data member Display display, Shell shell 
 * This is the first window, in this window we will choose our favorite UI
 * if it is GUICC we will choose with or without up/down hint
 * if it is CLI we will see in the console
 * @author Itamar Mizrahi & Chen Erlich
 */
public class StartWindow implements Runnable {

	private Display display;
	private Shell shell;

	/**
	 * Create the new window 
	 * create the new label of GUICC and CLI and allowed them to do SWT.PUSH
	 * GUIC - ask if  hse up/down hint
	 */
	private void initWidgets() {
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		this.shell.setText("Start");
		
		this.shell.setLayout(new GridLayout(2, false));
		this.shell.setSize(400, 750);
//		this.shell.setBackgroundImage(new Image(null, "resources/images/background.png"));		
		this.shell.setBackgroundImage(new Image(null, "resources/images/back.png"));	
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		// Open in center of screen
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		// handle with the RED X
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				System.exit(0);
			}
		});

		//Empty spaces
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHead = new Label(shell, SWT.BOLD);
		FontData fontData = lblHead.getFont().getFontData()[0];
		lblHead.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+7, SWT.BOLD)));
		lblHead.setText("Choose your favorite UI");
		lblHead.setForeground(new Color(display, 255,255,255));
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Image imgGUIC = new Image(display, "resources/images/GUICButton.png");
		Image imgCLI = new Image(display, "resources/images/CLIButton.png");
		
		Label lblCLI = new Label(shell, SWT.PUSH);
		lblCLI.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblCLI.setImage(imgCLI);
		lblCLI.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				PropertiesManager.writeXml("CLI", "false");
				shell.dispose();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});
		
		Label lblGUIC = new Label(shell, SWT.NONE);
		lblGUIC.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblGUIC.setImage(imgGUIC);
		
		Label lblYesNo = new Label(shell, SWT.NONE);
		lblYesNo.setText("Get up/down hints?\n      [GUICC ONLY]");
		lblYesNo.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+3, SWT.BOLD)));
		lblYesNo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Composite radioChoose = new Composite(shell, SWT.NONE);
		radioChoose.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		final Button[] radios = new Button[2];
		radios[0] = new Button(radioChoose, SWT.RADIO);
		radios[0].setText("YES");
		radios[0].setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+1, SWT.BOLD)));
		radios[0].setBounds(10, 0, 50, 15);
		radios[0].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		radios[0].setEnabled(false);
		radios[1] = new Button(radioChoose, SWT.RADIO);
		radios[1].setText("NO");
		radios[1].setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+1, SWT.BOLD)));
		radios[1].setBounds(100, 0, 50, 15);
		radios[1].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		radios[1].setEnabled(false);
		
		lblGUIC.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				radios[0].setEnabled(true);
				radios[1].setEnabled(true);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});
		
		radios[0].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				PropertiesManager.writeXml("GUI", "true");
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		radios[1].addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				PropertiesManager.writeXml("GUI", "false");
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
	}

	/**	
	 *This method call the start method 
	 */
	@Override
	public void run() {
		start();
	}
	/**
	 *This method loop of the first window
	 */
	private void start() {
		this.display = new Display();
		this.shell = new Shell(this.display);
		
		initWidgets();
		shell.open();
		
		// main event loop
		while (!shell.isDisposed()) // windows isn't closed
			if (!this.display.readAndDispatch())
				this.display.sleep();
		this.display.close();
	}
	
}
