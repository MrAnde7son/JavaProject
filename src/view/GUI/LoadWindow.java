package view.GUI;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import view.MyView;

/**
 * LoadWindow
 * @author Itamar Mizrahi & Chen Erlich
 */
public class LoadWindow extends DialogWindow {
	
	private ArrayList<String> listOfFiles;

	public LoadWindow(MyView view,String listFiles) {
		this.view = view;
		String[] splitedString = listFiles.split("\n");
		this.listOfFiles = new ArrayList<String>();
		for (int i = 0; i < splitedString.length; i++)
			if (splitedString[i].contains(".maz"))
				this.listOfFiles.add(splitedString[i]);
	}
	
	@Override
	protected void initWidgets() {
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		this.shell.setText("Load from file");
		this.shell.setLayout(new GridLayout(1, false));
		this.shell.setSize(200, 200);
		this.shell.setBackgroundImage(new Image(null, "resources/images/backgroundSmall.png"));
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label lblHead = new Label(shell, SWT.NONE);
		FontData fontData = lblHead.getFont().getFontData()[0];
		Font font = new Font(display, new FontData(fontData.getName(), fontData.getHeight()+3, SWT.BOLD));
		lblHead.setFont(font);
		lblHead.setText("Pick your maze's file");
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		final List lstFiles = new List(shell, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		for (int i = 0; i < this.listOfFiles.size(); i++)
			lstFiles.add(this.listOfFiles.get(i));
		
		lstFiles.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Button btnLoad = new Button(shell, SWT.PUSH);
		btnLoad.setText("Load maze");
		btnLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoad.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int index = lstFiles.getSelectionIndex();
				String[] mazeName = listOfFiles.get(index).split(".maz");
				try {
					view.executeCommand("load_maze " + mazeName[0] + ".maz " + mazeName[0]);
				} catch (IllegalArgumentException | IndexOutOfBoundsException e) {
					view.displayMessage(e.getMessage());
				}
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});	
	}
}
