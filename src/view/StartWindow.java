package view;

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

public class StartWindow extends BasicWindow implements Runnable {

	private Display display;
	private Shell shell;

	public StartWindow(String title, int width, int height) {
		super(title, width, height);
	}
	
	@Override
	protected void initWidgets() {
		
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		this.shell.setText("Start Menu");
		
		this.shell.setLayout(new GridLayout(2, false));
		this.shell.setSize(400, 750);
		this.shell.setBackgroundImage(new Image(null, "resources/images/back.png"));	
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Rectangle rect = shell.getBounds();
		Rectangle boundries = display.getPrimaryMonitor().getBounds();
		int x = boundries.x + (boundries.width - rect.width) / 2;
		int y = boundries.y + (boundries.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		// Listener for the closing X
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				System.exit(0);
			}
		});
		
		// Header Titles
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHead = new Label(shell, SWT.BOLD);
		FontData fontData = lblHead.getFont().getFontData()[0];
		lblHead.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+15, SWT.BOLD)));
		lblHead.setText("The Putin Maze");
		lblHead.setForeground(new Color(display, 255,255,255));
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelQuestion = new Label(shell, SWT.BOLD);
		fontData = labelQuestion.getFont().getFontData()[0];
		labelQuestion.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+7, SWT.BOLD)));
		labelQuestion.setText("Please Choose A UI");
		labelQuestion.setForeground(new Color(display, 255,255,255));
		labelQuestion.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		// GUI and CLI Images
		
		Image imageGUI = new Image(display, "resources/images/GUIButton.png");
		Image imageCLI = new Image(display, "resources/images/CLIButton.png");
		
		Label labelCLI = new Label(shell, SWT.PUSH);
		labelCLI.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		labelCLI.setImage(imageCLI);
		labelCLI.addMouseListener(new MouseListener() {
			
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
		
		
		
		Label labelGUI = new Label(shell, SWT.NONE);
		labelGUI.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		labelGUI.setImage(imageGUI);
		
		// Yes Or No Radio Buttons
		
		Label labelYesOrNo = new Label(shell, SWT.NONE);
		labelYesOrNo.setText("Do you want some hints?\n      [Only For GUI]");
		labelYesOrNo.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+3, SWT.BOLD)));
		labelYesOrNo.setForeground(new Color(display, 255,255,255));
		labelYesOrNo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Composite radioChoose = new Composite(shell, SWT.NONE);
		radioChoose.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		final Button[] radioBtns = new Button[2];
		radioBtns[0] = new Button(radioChoose, SWT.RADIO);
		radioBtns[0].setText("Yes");
		radioBtns[0].setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+1, SWT.BOLD)));
		radioBtns[0].setBounds(10, 0, 50, 15);
		radioBtns[0].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		radioBtns[0].setEnabled(false);
		radioBtns[1] = new Button(radioChoose, SWT.RADIO);
		radioBtns[1].setText("No");
		radioBtns[1].setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+1, SWT.BOLD)));
		radioBtns[1].setBounds(100, 0, 50, 15);
		radioBtns[1].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		radioBtns[1].setEnabled(false);
		
		labelGUI.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				radioBtns[0].setEnabled(true);
				radioBtns[1].setEnabled(true);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});
	
		// If GUI selected
		
		radioBtns[0].addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			PropertiesManager.writeXml("GUI", "true");
			shell.dispose();
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
	
		// If GUI wasn't selected
		
		radioBtns[1].addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			PropertiesManager.writeXml("GUI", "false");
			shell.dispose();
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) { }
		
		});
	}

	
	// Run method
	@Override
	public void run() {
		start();
	}

	// Start method
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
