package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;





import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import properties.Properties;
import properties.PropertiesManager;
//import utils.MyJaxbUtil;

/***
 * MazeDisplay
 * @param maze Maze3d Type
 * @param currPos Position Type
 * @param crossSection int[][] Type
 * 
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class MazeDisplay extends Canvas {
	
	private int whichFloorAmI;
	private GameCharacter character;
	private Image imgGoal;
	private Image imgWinner;
	private Image imgUp;
	private Image imgDown;
	private Image imgUpDown;
	private Image imgWall;
	private Position goalPosition;
	private List<Point> downHint;
	private List<Point> upHint;
	private String mazeName;
	private Maze3d maze;
	private Position currPos;
	private int[][] crossSection = { {0}, {0} };
	private GameCharacter player;
	private boolean winner;
	private Timer timer;
	private TimerTask timerTask;
	private Properties properties;
	
	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		
		this.mazeName = null;
		this.whichFloorAmI = 0;
		this.character = new GameCharacter();
		this.character.setPos(new Position(-1, -1, -1));
		this.imgGoal = new Image(null,"resources/images/chicken.png");
		this.imgWinner = new Image(null,"resources/images/peter vs chicken.jpg");
		this.imgUp = new Image(null, "resources/images/up.gif");
		this.imgDown = new Image(null, "resources/images/down.gif");
		this.imgUpDown = new Image(null, "resources/images/updown.gif");
		this.imgWall = new Image(null, "resources/images/wall.png");
		this.goalPosition= new Position(-1, -1, -1);
		this.upHint = new ArrayList<Point>();
		this.downHint = new ArrayList<Point>();

		// draw the maze
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				int x, y;
				
				int canvasWidth = getSize().x;
				int canvasHeight = getSize().y;
				int cellWidth = canvasWidth / crossSection[0].length;
				int cellHeight = canvasHeight / crossSection.length;

				e.gc.setForeground(new Color(null, 1, 255, 0));
				e.gc.setBackground(new Color(null, 0, 0, 0));

				for (int i = 0; i < crossSection.length; i++) {
					for (int j = 0; j < crossSection[i].length; j++) {
						x = j * cellWidth;
						y = i * cellHeight;
						if (crossSection[i][j] != 0)
							//e.gc.fillRectangle(x, y, cellWidth, cellHeight);
							e.gc.drawImage(imgWall, 0, 0, imgWall.getBounds().width, imgWall.getBounds().height, x, y, cellWidth, cellHeight);
					}
				}
				
				if (!winner) {
					character.draw(cellWidth, cellHeight, e.gc);
					if (whichFloorAmI == goalPosition.getZ())
						e.gc.drawImage(imgGoal, 0, 0, imgGoal.getBounds().width, imgGoal.getBounds().height, cellWidth * goalPosition.getX(), cellHeight * goalPosition.getY(), cellWidth, cellHeight);
				} else e.gc.drawImage(imgWinner, 0, 0, imgWinner.getBounds().width, imgWinner.getBounds().height, cellWidth * goalPosition.getX(), cellHeight * goalPosition.getY(), cellWidth, cellHeight);
				
				forceFocus();
			}
		});
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				char direction = 0;
				switch (e.keyCode) {
				case SWT.ARROW_RIGHT:
					direction = 'r';
					break;
				case SWT.ARROW_LEFT:
					direction = 'l';
					break;
				case SWT.ARROW_UP:
					direction = 'u';
					break;
				case SWT.ARROW_DOWN:
					direction = 'd';
					break;
				case SWT.PAGE_DOWN:
					direction = 'i';
					break;
				case SWT.PAGE_UP:
					direction = 'o';
					break;
				default: break;
				}
				if (direction != 0) {
					moveCharacter(direction);
					redraw();
				}
			}
		});
		addZoomOption();
	}

	
	/**
	 * Move character by one to a given direction
	 * @param direction
	 */
	private void moveCharacter(char direction) {
		switch(direction){
		case 'r':
			this.currPos = new Position(this.currPos.getX(), this.currPos.getY(), this.currPos.getZ()+1);
			break;
		case 'l':
			this.currPos = new Position(this.currPos.getX(), this.currPos.getY(), this.currPos.getZ()-1);
			break;
		case 'd':
			this.currPos = new Position(this.currPos.getX(), this.currPos.getY()+1, this.currPos.getZ());
			break;
		case 'u':
			this.currPos = new Position(this.currPos.getX(), this.currPos.getY()-1, this.currPos.getZ());
			break;
		case 'o':
			this.currPos = new Position(this.currPos.getX()+1, this.currPos.getY(), this.currPos.getZ());
			break;
		case 'i':
			this.currPos = new Position(this.currPos.getX()-1, this.currPos.getY(), this.currPos.getZ());
			break;
		}
		
	}
	
	/***
	 * Zoom in\out when using Ctrl + Mouse Wheel
	 * @param 
	 */
	private void addZoomOption()
	{
		addKeyListener(new KeyListener() {
	        @Override
	        public void keyPressed(final KeyEvent e) {    
	           addMouseWheelListener(new MouseWheelListener() {
	
	                @Override
	                public void mouseScrolled(MouseEvent g) {
		                if(e.keyCode == SWT.CTRL){
		                    if(g.count > 0){
		                        int width = getSize().x;
		                        int height = getSize().y;
		                        setSize((int)(width * 1.05), (int)(height * 1.05));	
		                    }
		                    else {
		                        int width = getSize().x;
		                        int height = getSize().y;
		                        setSize((int)(width * 0.95), (int)(height * 0.95));
		                    }
		                  }
	
	                  }
	           });
	        }
	
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			} 
		});
	}

	public void initializeMaze(String name, Maze3d maze){
		this.mazeName = name;
		this.maze = maze;
		this.currPos = maze.getStartPosition();
		this.goalPosition = maze.getGoalPosition();
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public void setWhichFloorAmI(int whichFloorAmI) {
		this.whichFloorAmI = whichFloorAmI;
	}


	public void setCrossSection(int[][] crossSection) {
		this.crossSection = crossSection;
		redrawMe();
	}

	public void setCharacterPosition(Position pos) {
		this.character.setPos(pos);
		redrawMe();
	}

	public void moveRight(){
		this.character.setPos(new Position(currPos.getX(), currPos.getY(), currPos.getZ()+1));
	}
	
	public void moveLeft(){
		this.character.setPos(new Position(currPos.getX(), currPos.getY(), currPos.getZ()-1));
	}
	
	public void moveUp(){
		this.character.setPos(new Position(currPos.getX(), currPos.getY()+1, currPos.getZ()));
	}
	
	public void moveDown(){
		this.character.setPos(new Position(currPos.getX(), currPos.getY()-1, currPos.getZ()));
	}
	
	public void moveAbove(){
		this.character.setPos(new Position(currPos.getX()+1, currPos.getY(), currPos.getZ()));
	}
	
	public void moveBelow(){
		this.character.setPos(new Position(currPos.getX()-1, currPos.getY(), currPos.getZ()));
	}
	
	public void moveToPosition(Position pos) {
		this.character.setPos(pos);
		redrawMe();
	}

	public void setMazeName(String mazeName) {
		this.mazeName = mazeName;
	}
	
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	private void redrawMe() {
		getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				setEnabled(true);
				redraw();
			}
			
		});
	}


	public Maze3d getMaze() {
		return maze;
	}


	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}


	public String getMazeName() {
		return mazeName;
	}

}
