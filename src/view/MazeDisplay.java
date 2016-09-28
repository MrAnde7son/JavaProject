package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

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
	
	private String mazeName;
	private Maze3d maze;
	private Position currPos;
	private Position goal;
	private int[][] crossSection;
	private GameCharacter player;
	private Timer timer;
	private TimerTask timerTask;
	
	public MazeDisplay(Shell parent, int style) {
		super(parent, style);
		this.player = new GameCharacter();
		
		// Creating the board graphically
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				
				if (maze == null || maze.getMaze() == null)
					return;
				
				// foreground - white, background - black
			   e.gc.setForeground(new Color(null,255,255,255));
			   e.gc.setBackground(new Color(null,0,0,0));
			
			   int width=getSize().x;
			   int height=getSize().y;
			
			   int w=width/crossSection[0].length;
			   int h=height/crossSection.length;
			
			   for(int i=0;i<maze.getMaze().length;i++)
			      for(int j=0;j<maze.getMaze()[i].length;j++){
			          int x=j*w;
			          int y=i*h;
			          if(crossSection[i][j]!=0)
			              e.gc.fillRectangle(x,y,w,h);
			      }
			   player.draw(0, 0, new GC(player.getImage()));
			}
		});
	}
	
	// Start Method
	public void start(){
		
		// Initialize timer for 
		this.timer = new Timer();
		this.timerTask = new TimerTask() {
			 
			@Override
			public void run() {
				// Causes the run() method of the runnable to be invoked by the user-interface thread
				getDisplay().syncExec(new Runnable() {
					
					@Override
					public void run() {
						// Get random maze position and redraw maze
						currPos = new GrowingTreeGenerator().getRandomPosition(maze);
						redraw();
					}
				});
			}
		};
		timer.scheduleAtFixedRate(this.timerTask, 0, 30000);
	}
	public void stop(){
		this.timerTask.cancel();
		this.timer.cancel();
	}
	
	public Maze3d getMaze() {
		return maze;
	}

	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
	
	public void setMazeData(int[][][] maze) {
		this.maze.setMaze(maze);
	}

	public Position getCurrPos() {
		return currPos;
	}

	public void setCurrPos(Position currPos) {
		this.currPos = currPos;
	}

	public void setCharacterPosition(Position pos) {
		this.currPos = pos;
		
	}

	public void setGoalPosition(Position goal) {
		this.goal = goal;
	}

	public void setMazeName(String mazeName) {
		this.mazeName = mazeName;
	}


}
