package algorithms.mazeGenerators;

import java.util.ArrayList;



public class Run {
	private static void testMazeGenerator(Maze3dGenerator mg){
		Maze3d maze=mg.generate(12,12,12);
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(maze));
		// generate another 3d maze
		maze=mg.generate(10,5,3);
		// get the maze entrance
		Position p=maze.getStartPosition();
		// print the position
		System.out.println(p); // format "{x,y,z}"
		// get all the possible moves from a position
		ArrayList<Position> moves=maze.getAllPossibleMoves(p);
		// print the moves
		for(Position move : moves)
			System.out.println(move);
		// prints the maze exit position
		System.out.println(maze.getGoalPosition());
		try{
			// get 2d cross sections of the 3d maze
			System.out.println("X");
			int[][] maze2dx=maze.getCrossSectionByX(2);
			printMaze2d(maze2dx);
			System.out.println("Y");
			int[][] maze2dy=maze.getCrossSectionByY(5);
			printMaze2d(maze2dy);
			System.out.println("Z");
			int[][] maze2dz=maze.getCrossSectionByZ(1);
			printMaze2d(maze2dz);
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		} 
		catch (IndexOutOfBoundsException e){
			System.out.println("good!");
			}
	}
	private static void printMaze2d(int[][] maze2d)
	{
		for(int i = 0;i < maze2d.length;i++){
			for(int j = 0;j < maze2d[i].length;j++){
				System.out.print(maze2d[i][j] + " ");
			}
			System.out.println();
		}	
	}
	public static void main(String[] args) {
		System.out.println("Simple Algorithm:");
		testMazeGenerator(new SimpleMaze3dGenerator());
		System.out.println("GrowingTree Algorithm:");
		testMazeGenerator(new GrowingTreeGenerator());

	}

}
