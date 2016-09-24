package algorithms.mazeGenerators;

import java.util.Random;

/*
 * Abstract class for defining a common 3d maze generator
 */

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	
	private Random rand = new Random();
	
	public abstract Maze3d generate(int x, int y, int z);
	
	public String measureAlgorithmTime(Maze3d maze) {
		
		long before = System.currentTimeMillis();
		this.generate(maze.getX(),maze.getY(),maze.getZ());
		long after = System.currentTimeMillis();
		return String.valueOf(after - before);
	}

// Gets a random entrance of the maze 
	public Position getEntrance(Maze3d maze){
		int x = 1;
		int y = rand.nextInt(maze.getY());
		int z = rand.nextInt(maze.getZ());
		while(y == 1 && z == 1)
		{
			y = rand.nextInt(maze.getY());
			z = rand.nextInt(maze.getZ());
		}
		return new Position(x, y, z);
	}
// Gets a random exit of the maze
	public Position getExit(Maze3d maze){
		int[][][] m = maze.getMaze();
		int x = maze.getX() - 2;
		int y = rand.nextInt(maze.getY());
		int z = rand.nextInt(maze.getZ());
		while(y == maze.getY()-2 && z == maze.getZ()-2 && m[x][y][z] == Maze3d.WALL)
		{
			y = rand.nextInt(maze.getY());
			z = rand.nextInt(maze.getZ());
		}
		return new Position(x, y, z);
	}
	/* Get random position in the given maze with the given value
	 * if the value is not 0 nor 1, get any random
	 */
		public Position getRandomPosition(Maze3d maze3d){
			int x = rand.nextInt(maze3d.getX());
			int y = rand.nextInt(maze3d.getY());
			int z = rand.nextInt(maze3d.getZ());
			while(x % 2 == 0){
				x = rand.nextInt(maze3d.getX());
			}
			while(y % 2 == 0){
				y = rand.nextInt(maze3d.getY());
			}
			while(z % 2 == 0){
				z = rand.nextInt(maze3d.getZ());
			}
			
			return new Position(x, y, z);
		}
}
