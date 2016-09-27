package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import properties.PropertiesManager;

public class StartWindow extends BasicWindow implements Runnable {
	
	@Override
	protected void initWidgets() {
		
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE | SWT.RESIZE);
		this.shell.setText("Start Menu");

		this.shell.setLayout(new GridLayout(7, false));
		this.shell.setSize(650, 500);
		this.shell.setBackgroundImage(new Image(null, "resources/images/1.jpg"));	
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT | SWT.FILL);
		
//		Rectangle rect = shell.getBounds();
//		Rectangle boundries = display.getPrimaryMonitor().getBounds();
//		int x = boundries.x + (boundries.width - rect.width) / 2;
//		int y = boundries.y + (boundries.height - rect.height) / 2;
//		shell.setLocation(x, y);
//		
//		// Listener for the closing X
//		shell.addListener(SWT.Close, new Listener() {
//			
//			@Override
//			public void handleEvent(Event arg0) {
//				System.exit(0);
//			}
//		});
		
		// Header Titles
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHead = new Label(shell, SWT.BOLD);
		FontData fontData = lblHead.getFont().getFontData()[0];
		lblHead.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+15, SWT.BOLD)));
		lblHead.setText("Peter Vs. Chicken Maze");
		lblHead.setForeground(new Color(display, 255,255,255));
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 4, 1));
		
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
		
		Label labelMiniHeader = new Label(shell, SWT.BOLD);
		fontData = labelMiniHeader.getFont().getFontData()[0];
		labelMiniHeader.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+7, SWT.BOLD)));
		labelMiniHeader.setText("Please Choose a view type:");
		labelMiniHeader.setForeground(new Color(display, 255,255,255));
		labelMiniHeader.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Image imageGUI = new Image(display, "resources/images/GUIButton.png");
		Image imageCLI = new Image(display, "resources/images/CLIButton.png");
		Image imagePutin = new Image(display, "resources/images/Peter_Griffin.png");
		
		Label labelPeter = new Label(shell, SWT.NONE);
		labelPeter.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		labelPeter.setImage(imagePutin);

		// GUI and CLI Images
		Label labelGUI = new Label(shell, SWT.PUSH);
		labelGUI.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		labelGUI.setImage(imageGUI);
		labelGUI.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				shell.dispose();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});
		
		Label labelCLI = new Label(shell, SWT.PUSH);
		labelCLI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
		labelCLI.setImage(imageCLI);
		labelCLI.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				PropertiesManager.writeXml("CLI");
				shell.dispose();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});
		
		labelGUI.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				PropertiesManager.writeXml("GUI");
				shell.dispose();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});
	
		
		// Yes Or No Radio Buttons
		
//		Label labelYesOrNo = new Label(shell, SWT.NONE);
//		labelYesOrNo.setText("Do you want some hints?\n      [Only For GUI]");
//		labelYesOrNo.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+3, SWT.BOLD)));
//		labelYesOrNo.setForeground(new Color(display, 255,255,255));
//		labelYesOrNo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
///		
//		Composite radioChoose = new Composite(shell, SWT.NONE);
//		radioChoose.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
//		
//		final Button[] radioBtns = new Button[2];
//		radioBtns[0] = new Button(radioChoose, SWT.RADIO);
//		radioBtns[0].setText("Yes");
//		radioBtns[0].setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+1, SWT.BOLD)));
//		radioBtns[0].setBounds(10, 0, 50, 15);
//		radioBtns[0].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
//		radioBtns[0].setEnabled(false);
//		radioBtns[1] = new Button(radioChoose, SWT.RADIO);
//		radioBtns[1].setText("No");
//		radioBtns[1].setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+1, SWT.BOLD)));
//		radioBtns[1].setBounds(100, 0, 50, 15);
//		radioBtns[1].setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
//		radioBtns[1].setEnabled(false);
//		

	}


}
