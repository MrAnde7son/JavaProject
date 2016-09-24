package view.GUI;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import algorithms.mazeGenerators.Position;
import properties.PropertiesManager;
import view.MyView;

/**
 * MazeDisplay, implements Canvas
 * @author Itamar Mizrahi & Chen Erlich
 */
public class MazeDisplay extends Canvas {
	
	private String name;
	private int currentFloor;
	private int[][] crossSection = { {0}, {0} };
	private GameCharacter character;
	private Image imgGoal;
	private Image imgWinner;
	private Image imgUp;
	private Image imgDown;
	private Image imgUpDown;
	private Image imgWall;
	private boolean drawMeAHint;
	private Position hintPosition;
	private boolean winner;
	private Position goalPosition;
	private List<Point> downHint;
	private List<Point> upHint;

	public MazeDisplay(Composite parent, int style, final MyView view) {
		super(parent, style);
		
		this.name = null;
		this.currentFloor = 0;
		this.character = new GameCharacter();
		this.character.setPos(new Position(-1, -1, -1));
		this.imgGoal = new Image(null,"resources/images/2.gif");
		this.imgWinner = new Image(null,"resources/images/putin_on_tiger.jpg");
		this.imgUp = new Image(null, "resources/images/up.gif");
		this.imgDown = new Image(null, "resources/images/down.gif");
		this.imgUpDown = new Image(null, "resources/images/updown.gif");
		this.imgWall = new Image(null, "resources/images/wall.png");
		this.drawMeAHint = false;
		this.hintPosition = null;
		this.winner = false;
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
							e.gc.drawImage(imgWall, 0, 0, imgWall.getBounds().width, imgWall.getBounds().height, x, y, cellWidth, cellHeight);
						if (PropertiesManager.getProperties().getHint().equals("true"))
							paintUpDownHints(e, i, j, cellWidth, cellHeight);
					}
				}
				
				if (drawMeAHint) {
					drawMeAHint = false;
					e.gc.drawImage(imgGoal, 0, 0, imgGoal.getBounds().width, imgGoal.getBounds().height, (cellWidth * hintPosition.getX()) + (cellWidth / 4), (cellHeight * hintPosition.getY()) + (cellHeight / 4), cellWidth/2, cellHeight/2);
				}
				
				if (!winner) {
					character.draw(cellWidth, cellHeight, e.gc);
					if (currentFloor == goalPosition.getZ())
						e.gc.drawImage(imgGoal, 0, 0, imgGoal.getBounds().width, imgGoal.getBounds().height, cellWidth * goalPosition.getX(), cellHeight * goalPosition.getY(), cellWidth, cellHeight);
				} else e.gc.drawImage(imgWinner, 0, 0, imgWinner.getBounds().width, imgWinner.getBounds().height, cellWidth * goalPosition.getX(), cellHeight * goalPosition.getY(), cellWidth, cellHeight);
				
				forceFocus();
			}
			
			private void paintUpDownHints(PaintEvent e, int i, int j, int cellWidth, int cellHeight) {
				Point upDownHint = new Point(i, j);
				if (upHint.contains(upDownHint) && downHint.contains(upDownHint))
					e.gc.drawImage(imgUpDown, 0, 0, imgUpDown.getBounds().width, imgUpDown.getBounds().height, cellWidth * j, cellHeight * i, cellWidth, cellHeight);
				else {
					if (upHint.contains(upDownHint))
						e.gc.drawImage(imgUp, 0, 0, imgUp.getBounds().width, imgUp.getBounds().height, cellWidth * j, cellHeight * i, cellWidth, cellHeight);
					else if (downHint.contains(upDownHint))
							e.gc.drawImage(imgDown, 0, 0, imgDown.getBounds().width, imgDown.getBounds().height, cellWidth * j, cellHeight * i, cellWidth, cellHeight);
				}
			}
		});
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				String command = null;
				switch (e.keyCode) {
				case SWT.ARROW_RIGHT:
					command = "r " + name;
					break;
				case SWT.ARROW_LEFT:
					command = "l " + name;
					break;
				case SWT.ARROW_UP:
					command = "b " + name;
					break;
				case SWT.ARROW_DOWN:
					command = "f " + name;
					break;
				case SWT.PAGE_DOWN:
					command = "d " + name;
					break;
				case SWT.PAGE_UP:
					command = "u " + name;
					break;
				default: break;
				}
				if (command != null) {
					view.executeCommand(command);
					redraw();
				}
			}
		});

	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public void setCrossSection(int[][] crossSection, List<Point> upHint, List<Point> downHint) {
		this.crossSection = crossSection;
		this.upHint = upHint;
		this.downHint = downHint;
		redrawMe();
	}

	
	public void setCharacterPosition(Position pos) {
		this.character.setPos(pos);
		redrawMe();
	}

	public void moveTheCharacter(Position pos) {
		this.character.setPos(pos);
		redrawMe();
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	public void drawHint(Position hintPos) {
		this.drawMeAHint = true;
		this.hintPosition = hintPos;
		redrawMe();
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
}
