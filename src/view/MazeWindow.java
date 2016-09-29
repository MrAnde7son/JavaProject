package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/***
 * MazeWindow
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class MazeWindow extends BasicWindow implements View {

	private MazeDisplay mazeDisplay;
	private boolean hint;
	private Maze3d maze;
	private String mazeName;
	private List<Point> canUp;
	private List<Point> canDown;
	private int[][] crossSection;
	private int[][] upperCrossSection;
	private int[][] lowerCrossSection;
	private MyView view;
	
	final String WINNER = "You are the winner!";
	
	public MazeWindow(MyView view) {
		this.view = view;
	}
/*	
	public MazeWindow() {
		this.view = null;
		this.hint = false;
		this.maze = null;
		this.mazeName = null;
		this.canUp = null;
		this.canDown = null;
		this.crossSection = null;
		this.upperCrossSection = null;
		this.lowerCrossSection = null;
	}
*/
	
	/**
	 * initWidgets
	 * New grid layout, create a new composite and all the button
	 * 
	 */
	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, false);
		this.shell.setLayout(grid);
		this.shell.setText("Peter Vs. Chicken Maze");
		this.shell.setSize(800,600);
		Image imgMain = new Image(this.display, "resources/images/main2.jpg");
		this.shell.setBackgroundMode(SWT.FILL | SWT.INHERIT_FORCE);
		this.shell.setBackgroundImage(imgMain);
		this.shell.setFullScreen(true);
		
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
				sendCommand("exit");
			}
		});
		
		Composite buttons = new Composite(shell, SWT.NONE);
		buttons.setLayout(new GridLayout(1, false));
		
//		Image imgMenu = new Image(display, "resources/images/main.png");
//		Label lblMenu = new Label(buttons, SWT.NONE);
//		lblMenu.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, true, 1, 1));
//		lblMenu.setImage(imgMenu);
//		
		Composite cmpGenerateHint = new Composite(buttons, SWT.NONE);
		cmpGenerateHint.setLayout(new GridLayout(1, false));
		cmpGenerateHint.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));
		
		Button btnGenerateMaze = new Button(cmpGenerateHint, SWT.PUSH);
		this.shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setText("Generate new maze");
		btnGenerateMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateWindow genWin = new GenerateWindow(view);
				genWin.start(display);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		Composite cmpSaveLoad = new Composite(buttons, SWT.NONE);
		cmpSaveLoad.setLayout(new GridLayout(1, false));
		cmpSaveLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));
		
		Label lblLoadSaveFromFile = new Label(cmpSaveLoad, SWT.NONE);
		lblLoadSaveFromFile.setText("Load/Save");
		lblLoadSaveFromFile.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		
		Button btnSave = new Button(cmpSaveLoad, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSave);
		btnSave.setText("Save Maze");
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSave.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				sendCommand("save_maze " + mazeName + " " + mazeName + ".maz");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Button btnLoad = new Button(cmpSaveLoad, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnLoad);
		btnLoad.setText("Load Maze");
		btnLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoad.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				sendCommand("load_maze " + mazeName + " " + mazeName + ".maz");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		
		// Label(this.shell, SWT.NONE);
		
		
		/*Button btnHint = new Button(cmpGenerateHint, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnHint);
		btnHint.setText("Hint");
		btnHint.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnHint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				createHint();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});*/
		
		Composite cmpSolve = new Composite(buttons, SWT.NONE);
		cmpSolve.setLayout(new GridLayout(1, false));
		cmpSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));
		
		Label lblSolve = new Label(cmpSolve, SWT.NONE);
		lblSolve.setText("Choose Solve Algorithm");
		lblSolve.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		final Combo cmbSolveAlgo = new Combo(cmpSolve, SWT.READ_ONLY | SWT.FILL);
		String items[] = {"BFS", "DFS"};
		cmbSolveAlgo.setItems(items);
		cmbSolveAlgo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	
		Button btnSolve = new Button(cmpSolve, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSolve);
		btnSolve.setText("Solve");
		btnSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSolve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (mazeName == null)
					view.displayMessage("You must generate maze first.");
				else sendCommand("solve " + mazeName + " " + cmbSolveAlgo.getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Composite cmpLoad = new Composite(buttons, SWT.NONE);
		cmpLoad.setLayout(new GridLayout(1, false));
		cmpLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 15));

		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");
		
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);
		MenuItem saveItem = new MenuItem(fileMenu, SWT.PUSH);
		saveItem.setText("&Save");
		saveItem.setEnabled(false);
		saveItem.addSelectionListener(new SelectionListener() {
			
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			sendCommand("save_maze " + mazeName + " " + mazeName + ".maz");
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) { }
	});
		shell.setMenuBar(menuBar);
		
		MenuItem loadItem = new MenuItem(fileMenu, SWT.PUSH);
		loadItem.setText("&Load");
		shell.setMenuBar(menuBar);
		loadItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				sendCommand("load_maze " + mazeName + " " + mazeName + ".maz");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Exit");
		shell.setMenuBar(menuBar);
		
		Composite cmpExit = new Composite(buttons, SWT.NONE);
		cmpExit.setLayout(new GridLayout(1, false));
		cmpExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 10));
		
		Button btnExit = new Button(cmpExit, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnExit);
		btnExit.setText("Exit");
		btnExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnExit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				sendCommand("exit");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		mazeDisplay.setFocus();
	}
	
	/**
	 * This method check if we have maze name
	 * else we will sent with executeCommand the "hint " + mazeName + " BFS" 
	 * to call the hint command
	 */
	protected void createHint() {
		if (mazeName == null)
			view.displayMessage("You must generate maze first.");
		else {
			hint = true;
			sendCommand("hint " + mazeName + " BFS");
		}
	}

	@Override
	public void sendCommand(String commandLine) {
		try {
			this.view.sendCommand(commandLine);
		} catch (IllegalArgumentException e) {
			displayMessage(e.getMessage());
		}
	}

	@Override
	public void notifyMazeIsReady(final String name) {
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				displayMessage("Maze " + name + " is ready");
				
				setChanged();
				notifyObservers("display_maze " + name);
			}
		});			
	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMessage(String msg) {
		MessageBox messageBox = new MessageBox(this.shell, SWT.ICON_INFORMATION | SWT.OK );
		messageBox.setText("Message");
		messageBox.setMessage(msg);
		messageBox.open();
	}
	
//	@Override
//	protected void initWidgets() {
//		
//		GridLayout gridLayout = new GridLayout(2, false);
//		shell.setLayout(gridLayout);				
//		
//		Composite btnGroup = new Composite(shell, SWT.BORDER);
//		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
//		btnGroup.setLayout(rowLayout);
//	
//		Button btnGenerateMaze = new Button(btnGroup, SWT.PUSH);
//		btnGenerateMaze.setText("Generate");	
//		btnGenerateMaze.addSelectionListener(generateListener);
//		
//		Button btnSolveMaze = new Button(btnGroup, SWT.PUSH);
//		btnSolveMaze.setText("Solve");
//		btnSolveMaze.addSelectionListener(solveListener);
//		
//		Button btnSaveMaze = new Button(btnGroup, SWT.PUSH);
//		btnSolveMaze.setText("Save");
//		btnSolveMaze.addSelectionListener(saveListener);
//		
//		Button btnLoadMaze = new Button(btnGroup, SWT.PUSH);
//		btnSolveMaze.setText("Load");
//		btnSolveMaze.addSelectionListener(loadListener);
//	}
//
//	protected void showGenerateMazeOptions() {
//		final Shell shell = new Shell();
//		shell.setText("Generate Maze");
//		shell.setSize(300, 200);
//		
//		GridLayout layout = new GridLayout(2, false);
//		shell.setLayout(layout);
//		
//		Label lblRows = new Label(shell, SWT.NONE);
//		lblRows.setText("Rows: ");
//		final Text txtRows = new Text(shell, SWT.BORDER);
//		
//		Label lblCols = new Label(shell, SWT.NONE);
//		lblCols.setText("Cols: ");
//		final Text txtCols = new Text(shell, SWT.BORDER);
//		
//		Button btnGenerate = new Button(shell, SWT.PUSH);
//		btnGenerate.setText("Generate");
//		btnGenerate.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				setChanged();
//				notifyObservers("generate_maze " + txtRows.getText() + " " + txtCols.getText());
//				shell.close();
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		mazeDisplay = new MazeDisplay(shell, SWT.NONE);			
//		shell.open();		
//	}
//
//	@Override
//	public void notifyMazeIsReady(final String name) {
//		display.syncExec(new Runnable() {
//			
//			@Override
//			public void run() {
//				MessageBox msg = new MessageBox(shell);
//				msg.setMessage("Maze " + name + " is ready");
//				msg.open();	
//				
//				setChanged();
//				notifyObservers("display_maze " + name);
//			}
//		});			
//	}
//
//	@Override
//	public void displayMaze(Maze3d maze) {
//		
//		int[][][] mazeData={
//				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
//				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
//				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
//				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
//				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
//				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
//				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
//				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
//				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}},
//				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
//				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
//				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
//				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
//				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
//				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
//				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
//				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
//				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}},
//				{{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
//				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
//				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
//				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
//				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
//				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
//				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
//				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
//				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}}
//			}
//			;
//		mazeDisplay.setMazeData(mazeData);
//	}
//
//	@Override
//	public void displayMessage(String msg) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void start() {
//		run();		
//	}

}
