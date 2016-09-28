package view;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
/**
 * Represents the GenerateWindow of Maze3d.
 * @author Itamar&Erlich
 */
public class GenerateWindow extends BasicWindow {

	private MyView view;

	public GenerateWindow(MyView view) {
		this.view = view;
	}
	
	@Override
	protected void initWidgets() {
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		this.shell.setText("Generation Window");
		this.shell.setLayout(new GridLayout(2, false));
		this.shell.setSize(215, 215);
		this.shell.setBackgroundImage(new Image(null, "<add image>"));
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rec = shell.getBounds();
		int x = bounds.x + (bounds.width - rec.width) / 2;
		int y = bounds.y + (bounds.height - rec.height) / 2;
		shell.setLocation(x, y);
		
		Label floorsLabel = new Label(this.shell, SWT.NONE);
		floorsLabel.setText("Floors:");
		final Text floorsTxt = new Text(this.shell, SWT.BORDER);
		floorsTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label rowsLabel = new Label(this.shell, SWT.NONE);
		rowsLabel.setText("Rows: ");
		final Text rowsTxt = new Text(this.shell, SWT.BORDER);
		rowsTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label colsLabel = new Label(this.shell, SWT.NONE);
		colsLabel.setText("Columns: ");
		final Text colsTxt = new Text(this.shell, SWT.BORDER);
		colsTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label nameLabel = new Label(this.shell, SWT.NONE);
		nameLabel.setText("Name: ");
		final Text nameTxt = new Text(this.shell, SWT.BORDER);
		nameTxt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button newGameButton = new Button(shell, SWT.PUSH);
		newGameButton.setText("New Game");
		newGameButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		newGameButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int floors = 0;
				int rows = 0;
				int cols = 0;
				String mazeName = null;
				try {
					floors = Integer.parseInt(floorsTxt.getText());
					rows = Integer.parseInt(rowsTxt.getText());
					cols = Integer.parseInt(colsTxt.getText());
					mazeName = nameTxt.getText();
				} catch (NullPointerException | NumberFormatException e) {
					view.displayMessage("Invalid arguments.");
				}
				view.sendCommand("generate_maze " + mazeName + " " + floors + " " + rows + " " + cols);
				shell.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});

	}

}
