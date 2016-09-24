package view.GUI;

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
import view.MyView;

/**
 * class GenerateMazeWindows
 * extends DialogWindow
 * @author Itamar Mizrahi & Chen Erlich
 */
public class GenerateMazeWindows extends DialogWindow {
	
	public GenerateMazeWindows(MyView view) {
		this.view = view;
	}
	
	@Override
	protected void initWidgets() {
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		this.shell.setText("Generate maze window");
		this.shell.setLayout(new GridLayout(2, false));
		this.shell.setSize(240, 240);
		this.shell.setBackgroundImage(new Image(null, "resources/images/genWinBack.png"));
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label labelName = new Label(this.shell, SWT.NONE);
		labelName.setText("Name: ");
		final Text txtName = new Text(this.shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelHeader = new Label(shell, SWT.BOLD);
		FontData fontData = labelHeader.getFont().getFontData()[0];
		Font font = new Font(display, new FontData(fontData.getName(), fontData.getHeight()+1, SWT.BOLD));
		labelHeader.setFont(font);
		labelHeader.setText("Enter maze dimensions");
		labelHeader.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		
		Label labelFloors = new Label(this.shell, SWT.NONE);
		labelFloors.setText("Floors: ");
		final Text txtFloors = new Text(this.shell, SWT.BORDER);
		txtFloors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelRows = new Label(this.shell, SWT.NONE);
		labelRows.setText("Rows: ");
		final Text txtRows = new Text(this.shell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelCols = new Label(this.shell, SWT.NONE);
		labelCols.setText("Cols: ");
		final Text txtCols = new Text(this.shell, SWT.BORDER);
		txtCols.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Button buttonStartGame = new Button(shell, SWT.PUSH);
		buttonStartGame.setText("Start Playing!");
		buttonStartGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		buttonStartGame.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int floors = 0;
				int rows = 0;
				int cols = 0;
				String mazeName = null;
				try {
					floors = Integer.parseInt(txtFloors.getText());
					rows = Integer.parseInt(txtRows.getText());
					cols = Integer.parseInt(txtCols.getText());
					mazeName = txtName.getText();
				} catch (NullPointerException | NumberFormatException e) {
					view.displayMessage("Invalid Arguments!");
				}
				view.executeCommand("generate_maze " + mazeName + " " + floors + " " + rows + " " + cols);
				shell.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
	}
}
