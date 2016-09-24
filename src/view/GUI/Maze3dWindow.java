package view.GUI;

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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import view.MyView;

/**
 * class Maze3dWindow - this class will open the window of all the category we can select
 * extends BaseWindow
 * Data member boolean giveMeAHint, Maze3d  myMaze, String mazeName, String[] itemsFromDatabase
 * Data member MazeDisplayC mazeDisplay, List<Point> canMoveUp, List<Point> canMoveDown, int[][] crossSection
 * Data member int[][] upperCrossSection, int[][] lowerCrossSection, String WINNER
 * @author Itamar Mizrahi & Chen Erlich
 */
public class Maze3dWindow extends BaseWindow {
	
	private boolean giveMeAHint;
	private Maze3d myMaze;
	private String mazeName;
	private String[] itemsFromDatabase;
	private MazeDisplay mazeDisplay;
	private List<Point> canMoveUp;
	private List<Point> canMoveDown;
	private int[][] crossSection;
	private int[][] upperCrossSection;
	private int[][] lowerCrossSection;

	final String WINNER = "You are the putin!";
	
	/**
	 * Constructor
	 * @param view
	 */
	public Maze3dWindow(MyView view) {
		this.view = view;
		this.giveMeAHint = false;
		this.myMaze = null;
		this.mazeName = null;
		this.itemsFromDatabase = null;
		this.canMoveUp = null;
		this.canMoveDown = null;
		this.crossSection = null;
		this.upperCrossSection = null;
		this.lowerCrossSection = null;
		this.showSolutionByAnimation = null;
	}

	/**
	 * initWidgets
	 * New grid layout, create a new composite and all the button
	 * 
	 */
	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, false);
		this.shell.setLayout(grid);
		this.shell.setText("MyMaze3d");
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		this.shell.setBackgroundImage(new Image(null, "resources/images/backgroundBig.jpg"));
		
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
				executeCommand("exit");
			}
		});
		
		Composite buttons = new Composite(shell, SWT.NONE);
		buttons.setLayout(new GridLayout(1, false));
		
		Image imgMenu = new Image(display, "resources/images/menu.png");
		Label lblMenu = new Label(buttons, SWT.NONE);
		lblMenu.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, true, 1, 1));
		lblMenu.setImage(imgMenu);
		
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
				GenerateMazeWindows genWin = new GenerateMazeWindows(view);
				genWin.start(display);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		Button btnSolve = null;
		Button btnHint = new Button(cmpGenerateHint, SWT.PUSH | SWT.FILL);
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
		});
		
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
	
		btnSolve = new Button(cmpSolve, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSolve);
		btnSolve.setText("Solve");
		btnSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSolve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (mazeName == null)
					view.printMessage("Generate/Load a maze first!");
				else executeCommand("solve " + mazeName + " " + cmbSolveAlgo.getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Composite cmpLoad = new Composite(buttons, SWT.NONE);
		cmpLoad.setLayout(new GridLayout(1, false));
		cmpLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 15));
		
		Label lblLoadFromDatabase = new Label(cmpLoad, SWT.NONE);
		lblLoadFromDatabase.setText("Load maze from database");
		lblLoadFromDatabase.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));

		final Combo cmbLoadFromDatabase = new Combo(cmpLoad, SWT.READ_ONLY);
		executeCommand("GetDatabaseValues");
		cmbLoadFromDatabase.setItems(this.itemsFromDatabase);
		cmbLoadFromDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnLoadFromDatabase = new Button(cmpLoad, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnLoadFromDatabase);
		btnLoadFromDatabase.setText("Load Maze");
		btnLoadFromDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoadFromDatabase.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (cmbLoadFromDatabase.getText() != "")
					executeCommand("display " + cmbLoadFromDatabase.getText());
				else view.printMessage("Pick a maze from the list first!");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Composite cmpSaveLoadFromFile = new Composite(buttons, SWT.NONE);
		cmpSaveLoadFromFile.setLayout(new GridLayout(1, false));
		cmpSaveLoadFromFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));
		
		Label lblLoadSaveFromFile = new Label(cmpSaveLoadFromFile, SWT.NONE);
		lblLoadSaveFromFile.setText("Load/Save (Files)");
		lblLoadSaveFromFile.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		
		Button btnSave = new Button(cmpSaveLoadFromFile, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnSave);
		btnSave.setText("Save to file");
		btnSave.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSave.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				executeCommand("save_maze " + mazeName + " " + mazeName + ".maz");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		Button btnLoad = new Button(cmpSaveLoadFromFile, SWT.PUSH | SWT.FILL);
		this.shell.setDefaultButton(btnLoad);
		btnLoad.setText("Load from file");
		btnLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnLoad.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				executeCommand("dir ./resources/saved_mazes");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
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
				executeCommand("exit");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER, this.view);
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
			view.printMessage("Generate/Load a maze first!");
		else {
			giveMeAHint = true;
			executeCommand("hint " + mazeName + " BFS");
		}
	}

	/**
	 * This method  will intalise data member so we could call the mazeDisplay 
	 * and drew the maze
	 * @param Maze3d, the maze
	 * @param String, maze name  
	 */
	@Override
	public void mazeReady(Maze3d maze, String mazeName) {
		this.mazeName = mazeName;
		this.myMaze = maze;
		this.mazeDisplay.setCharacterPosition(this.myMaze.getStartPosition());
		this.crossSection = this.myMaze.getCrossSectionByZ(0);
		setIfCanGoUpOrDown(0);
		this.mazeDisplay.setCrossSection(this.crossSection, this.canMoveUp, this.canMoveDown);
		this.mazeDisplay.setGoalPosition(this.myMaze.getGoalPosition());
		this.mazeDisplay.setMazeName(this.mazeName);
	}

	/**
	 * Get the maze name
	 * @return String, mazeName
	 */
	public String getMazeName() {
		return mazeName;
	}

	/**
	 * Open Message Box and view the message msg on it
	 */
	@Override
	public void displayMessage(String msg) {
		MessageBox msgBox = new MessageBox(shell, SWT.ICON_INFORMATION);
		msgBox.setMessage(msg);
		msgBox.open();
		if (msg.equals(this.WINNER))	// new game
			this.mazeDisplay.setWinner(false);
	}

	/**
	 * Get a commandLine and call the view to execute it
	 */
	@Override
	public void executeCommand(String commandLine) {
		try {
			this.view.executeCommand(commandLine);
		} catch (IllegalArgumentException e) {
			displayMessage(e.getMessage());
		}
	}

	/**
	 * Display the solution of the maze
	 * If the user asked for a hint, it show only the first step of the solution
	 * else, the solution will be showed in animation
	 * @param Solution<Position>, the solution
	 */
	@Override
	public void displaySolution(final Solution<Position> solution) {
		
		if (this.giveMeAHint) { // the user asked for only one step from the solution (a hint)
			this.giveMeAHint = false;
			this.mazeDisplay.drawHint(solution.getSolution().get(1).getState());
		} else {
			this.animationSolutionTask = new TimerTask() {
				
				int i = 0;
				
				@Override
				public void run() {
					if (i < solution.getSolution().size())
						move(solution.getSolution().get(i++).getState());
					else {
						display.syncExec(new Runnable() {
	
							@Override
							public void run() {
								winner();
							}
							
						});
						cancel();
					}
				}
			};
			this.showSolutionByAnimation = new Timer();
			this.showSolutionByAnimation.scheduleAtFixedRate(this.animationSolutionTask, 0, 500);
		}
	}

	/**
	 * Initalise the crossSection data member, check if there is option to move up/down
	 * call the mazeDisplay data member and enter to the setCrossSection the crossSection of Maze3dWindow 
	 * and the list of upHint, the list of downHint
	 */
	@Override
	public void move(Position pos) {
		this.crossSection = this.myMaze.getCrossSectionByZ(pos.getZ());
		setIfCanGoUpOrDown(pos.getZ());
		this.mazeDisplay.setCrossSection(this.crossSection, this.canMoveUp, this.canMoveDown);
		this.mazeDisplay.setWhichFloorAmI(pos.getZ());
		this.mazeDisplay.moveTheCharacter(pos);
	}

	/**
	 * This method display the winner and after that ww would like to display another
	 * winner so we will set winner as false 
	 */
	@Override
	public void winner() {
		this.crossSection = this.myMaze.getCrossSectionByZ(this.myMaze.getGoalPosition().getZ());
		this.mazeDisplay.setWhichFloorAmI(this.myMaze.getGoalPosition().getZ());
		setIfCanGoUpOrDown(this.myMaze.getGoalPosition().getZ());
		this.mazeDisplay.setCrossSection(this.crossSection, this.canMoveUp, this.canMoveDown);
		this.mazeDisplay.setWinner(true);
		this.displayMessage(this.WINNER);
		this.mazeDisplay.setEnabled(false);
		this.mazeDisplay.setWinner(false);
	}
	
	/**
	 * check if we can go up or down in the floor the GameCharacter is
	 * we have list of can move up and a list of can move down
	 * if the up/down possible is true we will add it to the list
	 * @param floor, int
	 */
	private void setIfCanGoUpOrDown(int floor) {
		boolean upPossible = false;
		boolean downPossible = false;
		this.canMoveUp = new ArrayList<Point>();
		this.canMoveDown = new ArrayList<Point>();
		
		if (floor < this.myMaze.getMaze().length - 1) {
			this.upperCrossSection = this.myMaze.getCrossSectionByZ(floor + 1);
			upPossible = true;
		}
		if (floor > 0) {
			this.lowerCrossSection = this.myMaze.getCrossSectionByZ(floor - 1);
			downPossible = true;
		}
		
		for (int i = 0; i < this.crossSection.length; i++) {
			for (int j = 0; j < this.crossSection[0].length; j++) {
				if (upPossible)
					checkForUp(i, j);
				if (downPossible)
					checkForDown(i, j);
			}
		}
	}

	/**
	 * This method check if we can go down with the character
	 * @param y, the rows
	 * @param x, the cols
	 */
	private void checkForDown(int y, int x) {
		if (this.lowerCrossSection[y][x] == this.crossSection[y][x] && this.crossSection[y][x] == Maze3d.FREE)
			this.canMoveDown.add(new Point(y, x));
	}

	/**
	 * This method check if we can go up with the character
	 * @param y, the rows
	 * @param x, the cols
	 */
	private void checkForUp(int y, int x) {
		if (this.upperCrossSection[y][x] == this.crossSection[y][x] && this.crossSection[y][x] == Maze3d.FREE)
			this.canMoveUp.add(new Point(y, x));
	}

	/**
	 * get the databaseValues after split with ,
	 */
	@Override
	public void databaseValues(String databaseValues) {
		this.itemsFromDatabase = databaseValues.split(",");
	}

	/**
	 *create new load Window and open it
	 */
	@Override
	public void dirListReady(String[] dirList) {
		LoadWindow winLoad = new LoadWindow(view, dirList[0]);
		winLoad.start(display);
	}
	
}
