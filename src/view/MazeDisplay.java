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
 * @author Itamar&Chen
 *
 */
public class MazeDisplay extends Canvas {
	
	private Maze3d maze;
	private Position currPos;
	private Solution<Position> solution;
	
	public MazeDisplay(Shell parent, int style) {
		super(parent, style);
		this.currPos = new Position(0,0,0);
		
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				if (maze.getMaze() == null)
					return;
				
				   e.gc.setForeground(new Color(null,0,0,0));
				   e.gc.setBackground(new Color(null,0,0,0));

				   int width=getSize().x;
				   int height=getSize().y;

				   int w=width/maze.getMaze()[0].length;
				   int h=height/maze.getMaze().length;

				   for(int i=0;i<maze.getMaze().length;i++)
				      for(int j=0;j<maze.getMaze()[i].length;j++){
				          int x=j*w;
				          int y=i*h;
				          if(maze.getMaze()[i][j]!=0)
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

	public Solution<Position> getSolution() {
		return solution;
	}

	public void setSolution(Solution<Position> solution) {
		this.solution = solution;
	}
}
