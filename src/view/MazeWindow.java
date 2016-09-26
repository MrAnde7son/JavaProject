package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;

/***
 * MazeWindow
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class MazeWindow extends BasicWindow implements View {

	private MazeDisplay mazeDisplay;
	private SelectionListener generateListener, saveListener, loadListener, solveListener, exitListener;
	
	@Override
	protected void initWidgets() {
		
		GridLayout gridLayout = new GridLayout(2, false);
		shell.setLayout(gridLayout);				
		
		Composite btnGroup = new Composite(shell, SWT.BORDER);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		btnGroup.setLayout(rowLayout);
	
		Button btnGenerateMaze = new Button(btnGroup, SWT.PUSH);
		btnGenerateMaze.setText("Generate");	
		btnGenerateMaze.addSelectionListener(generateListener);
		
		Button btnSolveMaze = new Button(btnGroup, SWT.PUSH);
		btnSolveMaze.setText("Solve");
		btnSolveMaze.addSelectionListener(solveListener);
		
		Button btnSaveMaze = new Button(btnGroup, SWT.PUSH);
		btnSolveMaze.setText("Save");
		btnSolveMaze.addSelectionListener(saveListener);
		
		Button btnLoadMaze = new Button(btnGroup, SWT.PUSH);
		btnSolveMaze.setText("Load");
		btnSolveMaze.addSelectionListener(loadListener);
	}

	protected void showGenerateMazeOptions() {
		final Shell shell = new Shell();
		shell.setText("Generate Maze");
		shell.setSize(300, 200);
		
		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		final Text txtRows = new Text(shell, SWT.BORDER);
		
		Label lblCols = new Label(shell, SWT.NONE);
		lblCols.setText("Cols: ");
		final Text txtCols = new Text(shell, SWT.BORDER);
		
		Button btnGenerate = new Button(shell, SWT.PUSH);
		btnGenerate.setText("Generate");
		btnGenerate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("generate_maze " + txtRows.getText() + " " + txtCols.getText());
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mazeDisplay = new MazeDisplay(shell, SWT.NONE);			
		shell.open();		
	}

	@Override
	public void notifyMazeIsReady(final String name) {
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox msg = new MessageBox(shell);
				msg.setMessage("Maze " + name + " is ready");
				msg.open();	
				
				setChanged();
				notifyObservers("display_maze " + name);
			}
		});			
	}

	@Override
	public void displayMaze(Maze3d maze) {
		
		int[][][] mazeData={
				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}},
				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}},
				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}}
			}
			;
		mazeDisplay.setMazeData(mazeData);
	}

	@Override
	public void displayMessage(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		run();		
	}

}
