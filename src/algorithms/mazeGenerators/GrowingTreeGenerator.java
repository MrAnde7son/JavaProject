package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class GrowingTreeGenerator extends CommonMaze3dGenerator {

	private Random rand = new Random();
	
	private ArrayList<Position> getUnivisitedNeighbors(Maze3d maze, Position p) {
		
		ArrayList<Position> neighbors = new ArrayList<Position>();
		
		int[][][] m = maze.getMaze();
		if (p.getX() - 2 > 1) {
			if (m[p.getX() - 2][p.getY()][p.getZ()] == Maze3d.WALL) {
				neighbors.add(new Position(p.getX() - 2,p.getY(),p.getZ()));
			}
		}
		
		if (p.getX() + 2 < maze.getX()-1) {
			if (m[p.getX() + 2][p.getY()][p.getZ()] == Maze3d.WALL) {
				neighbors.add(new Position(p.getX() + 2,p.getY(),p.getZ()));
			}
		}
		
		if (p.getY() - 2 >= 0) {
			if (m[p.getX()][p.getY() - 2][p.getZ()] == Maze3d.WALL) {
				neighbors.add(new Position(p.getX(),p.getY() - 2,p.getZ()));
			}
		}
		
		if (p.getY() + 2 < maze.getY()) {
			if (m[p.getX()][p.getY() + 2][p.getZ()] == Maze3d.WALL) {
				neighbors.add(new Position(p.getX(),p.getY() + 2,p.getZ()));
			}
		}
		
		if (p.getZ() - 2 >= 0) {
			if (m[p.getX()][p.getY()][p.getZ() - 2] == Maze3d.WALL) {
				neighbors.add(new Position(p.getX(),p.getY(),p.getZ() - 2));
			}
		}
		
		if (p.getZ() + 2 < maze.getZ()) {
			if (m[p.getX()][p.getY()][p.getZ() + 2] == Maze3d.WALL) {
				neighbors.add(new Position(p.getX(),p.getY(),p.getZ() + 2));
			}
		}
		
		return neighbors;
	}
	
	private void carve(Maze3d maze, Position p, Position neighbor) {
		if (neighbor.getX() == p.getX() - 2 && p.getX() - 2 > 0) {
			maze.eraseWall(p.getX() - 1, p.getY(), p.getZ());
			maze.eraseWall(p.getX() - 2, p.getY(), p.getZ());
		}
		else if (neighbor.getX() == p.getX() + 2 && p.getX() + 2 < maze.getX()-1) { 
			maze.eraseWall(p.getX() + 1, p.getY(), p.getZ());
			maze.eraseWall(p.getX() + 2, p.getY(), p.getZ());
		}
		else if (neighbor.getY() == p.getY() - 2 && p.getY() - 2 > 0) {  
			maze.eraseWall(p.getX(), p.getY() - 1, p.getZ());
			maze.eraseWall(p.getX(), p.getY() - 2, p.getZ());
		}
		else if (neighbor.getY() == p.getY() + 2 && p.getY() + 2 < maze.getY()-1) {
			maze.eraseWall(p.getX(), p.getY() + 1, p.getZ());
			maze.eraseWall(p.getX(), p.getY() + 2, p.getZ());
		}
		else if (neighbor.getZ() == p.getZ() - 2 && p.getZ() - 2 > 0) {  
			maze.eraseWall(p.getX(), p.getY(), p.getZ() - 1);
			maze.eraseWall(p.getX(), p.getY(), p.getZ() - 2);
		}
		else if (neighbor.getZ() == p.getZ() + 2 && p.getZ() + 2 < maze.getZ()-1) {
			maze.eraseWall(p.getX(), p.getY(), p.getZ() + 1);
			maze.eraseWall(p.getX(), p.getY(), p.getZ() + 2);
		}	
	}

	
// Generate maze using Growing Tree algorithm
	@Override
	public Maze3d generate(int x, int y, int z) {

		if(x <= 0 || y <= 0 || z <= 0)
			throw new IndexOutOfBoundsException();
		
		// Creates a maze full with walls
		Maze3d maze3d = new Maze3d(x, y, z);
		maze3d.fillWalls();
		
		ArrayList<Position> cells = new ArrayList<Position>();
		
		// Sets the starting position and add it to cells
		Position p = getEntrance(maze3d);
		maze3d.setStartPosition(p);
		cells.add(p);
		// Determines cell choose method
		int chooseMethod = rand.nextInt(2);
		Chooser c;
		if(chooseMethod == 0)
			c = new MazeLastCellChooser();
		else
			c = new MazeRandomCellChooser();
		
		// Maze generation algorithm
		while(!cells.isEmpty()){
			p = c.choose(cells);
			ArrayList<Position> unvisited = getUnivisitedNeighbors(maze3d, p);
			if(unvisited.isEmpty()){
				maze3d.setCell(p);
				cells.remove(p);
			}
			else
			{
				// Chooses a random neighbor and carves a passage into it
				Position neighbor = unvisited.get(rand.nextInt(unvisited.size()));
				
				carve(maze3d, p, neighbor);
				cells.add(neighbor);
			}
		}
		
		// Sets entrance and exit
		maze3d.setGoalPosition(getExit(maze3d));
		return maze3d;
	}

}
