package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/***
 * MazeDisplay
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class MazeDisplay extends Canvas {
	
	private Maze3d maze;
	private Position currPos;
	private int[][] crossSection;
	
	public MazeDisplay(Shell parent, int style) {
		super(parent, style);
		this.currPos = new Position(0,0,0);
		
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				if (maze.getMaze() == null)
					return;
				
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
			}
		});
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

}
