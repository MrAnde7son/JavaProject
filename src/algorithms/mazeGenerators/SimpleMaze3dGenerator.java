package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {

	private Random rand = new Random();

// Generate a maze using Simple algorithm	
	@Override
	public Maze3d generate(int x, int y, int z) {

		if(x <= 0 || y <= 0 || z <= 0)
			throw new IndexOutOfBoundsException();
		Maze3d maze3d = new Maze3d(x, y, z);
		// Random number of walls from zero to the maximum possible excluding edges
		int numOfWalls = rand.nextInt(x * y * z - (x * y * z)/2) + (x * y * z)/2;
		for(int i = 0;i < numOfWalls;i++){
			Position p = getRandomPosition(maze3d);
			maze3d.setWall(p);
		}
		
		// Fills the surrounded walls
		maze3d.surroundWalls();
		// Sets entrance and exit
		maze3d.setStartPosition(getEntrance(maze3d));
		maze3d.setGoalPosition(getExit(maze3d));
		// Carve a path within the maze
		Position start = maze3d.getStartPosition();
		Position p = new Position(start.getX(), start.getY(), start.getZ());
		Position goal = maze3d.getGoalPosition();
		while(!p.equals(goal)){
			if(p.getX() > goal.getX())
			{
				p.setX(p.getX() - 1);
				maze3d.eraseWall(p);
			}
			else if(p.getX() < goal.getX())
			{
				p.setX(p.getX() + 1);
				maze3d.eraseWall(p);
			}	
			if(p.getY() > goal.getY())
			{
				p.setY(p.getY() - 1);
				maze3d.eraseWall(p);
			}
			else if(p.getY() < goal.getY())
			{
				p.setY(p.getY() + 1);
				maze3d.eraseWall(p);
			}
			if(p.getZ() > goal.getZ())
			{
				p.setZ(p.getZ() - 1);
				maze3d.eraseWall(p);
			}
			else if(p.getZ() < goal.getZ())
			{
				p.setZ(p.getZ() + 1);
				maze3d.eraseWall(p);
			}
			maze3d.eraseWall(p);
		}
		
		return maze3d;
	}

}
