package algorithms.mazeGenerators;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Represents a 3D Maze
 * Written by Itamar & Chen
*/
public class Maze3d {
	
	private int x;
	private int y;
	private int z;
	
	private int[][][] maze;
	
	private Position startPosition;
	private Position goalPosition;
	
	public static final int FREE = 0;
	public static final int WALL = 1;
	
//	Maze3D Constructor
/* Creates a new maze in the size of 2N+1*2N+1*2N+1
 * in order to create walls within the maze.
 * The surrounding of the maze are walls too.  
 */
	public Maze3d(int x, int y, int z){
		this.x = 2 * x + 1;
		this.y = 2 * y + 1;
		this.z = 2 * z + 1;
		this.maze = new int[this.x][this.y][this.z];
		this.fillWalls();
		
		startPosition = null;
		goalPosition = null;
	}
	
	// Constructor with byte array
	public Maze3d(byte[] byteArr) throws IOException {
		int i = 0;
		this.x = (int)byteArr[i++];
		this.y = (int)byteArr[i++];
		this.z = (int)byteArr[i++];

		this.startPosition = new Position((int)byteArr[i++], (int)byteArr[i++],(int)byteArr[i++]);
		this.goalPosition = new Position((int)byteArr[i++], (int)byteArr[i++],(int)byteArr[i++]);

		maze = new int[x][y][z];

		for (int x = 0; x < x; x++) {
			for (int y = 0; y < y; y++) {
				for (int z = 0; z < z; z++)
					maze[x][y][z] = byteArr[i++];
			}
		}
	}

// Getters for the coordinations	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getZ() {
		return this.z;
	}

/* Giving a position, this function return all the possible moves 
 * as an array of strings
 */
	public ArrayList<Position> getAllPossibleMoves(Position pos) {
		ArrayList<Position> moves = new ArrayList<Position>();
		
		if (pos.getX() > 0 && maze[pos.getX() - 1][pos.getY()][pos.getZ()] == FREE) {
			moves.add(new Position(pos.getX() - 1,pos.getY(),pos.getZ()));
		}
		if (pos.getX() < this.x - 1 && maze[pos.getX() + 1][pos.getY()][pos.getZ()] == FREE) {
			moves.add(new Position(pos.getX() + 1,pos.getY(),pos.getZ()));
		}
		
		if (pos.getY() > 0 && maze[pos.getX()][pos.getY() - 1][pos.getZ()] == FREE) {
			moves.add(new Position(pos.getX(),pos.getY() - 1,pos.getZ()));
		}
		if (pos.getY() < this.y - 1 && maze[pos.getX()][pos.getY() + 1][pos.getZ()] == FREE) {
			moves.add(new Position(pos.getX(),pos.getY() + 1,pos.getZ()));
		}
		
		if (pos.getZ() > 0 && maze[pos.getX()][pos.getY()][pos.getZ() - 1] == FREE) {
			moves.add(new Position(pos.getX(),pos.getY(),pos.getZ() - 1));
		}

		if (pos.getZ() < this.z - 1 && maze[pos.getX()][pos.getY()][pos.getZ() + 1] == FREE) {
			moves.add(new Position(pos.getX(),pos.getY(),pos.getZ() + 1));
		}
		return moves;
	}
	
// Returns the maze's entrance
	public Position getStartPosition() {
		return startPosition;
	}
	
// Returns the maze's exit
	public Position getGoalPosition() {
		return goalPosition;
	}

// Setters for starting and goal positions
	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
		this.eraseWall(startPosition);
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
		this.eraseWall(goalPosition);
	}
	
// Returns the maze's cross sections by specified value
	public int[][] getCrossSectionByX(int num) throws IndexOutOfBoundsException{
		if(num >= 0 && num-1 <= this.x)
		{
			int[][] maze2d = new int[this.y][this.z];
			
			for(int i=0;i<this.y;i++)
				for(int j=0;j<this.z;j++)
					maze2d[i][j] = this.maze[num][i][j];
			
			return maze2d;
		}
		else
			throw new IndexOutOfBoundsException();
	}
	public int[][] getCrossSectionByY(int num) throws IndexOutOfBoundsException{
		if(num >= 0 && num-1 <= this.y)
		{
			int[][] maze2d = new int[this.x][this.z];
			
			for(int i=0;i<this.x;i++)
				for(int j=0;j<this.z;j++)
					maze2d[i][j] = this.maze[i][num][j];
			
			return maze2d;
		}
		else
			throw new IndexOutOfBoundsException();	
	}
	public int[][] getCrossSectionByZ(int num) throws IndexOutOfBoundsException{
		if(num >= 0 && num-1 <= this.z)
		{
			int[][] maze2d = new int[this.x][this.y];
			
			for(int i = 0;i < this.x;i++)
				for(int j = 0;j < this.y;j++)
					maze2d[i][j] = this.maze[i][j][num];
			
			return maze2d;
		}
		else
			throw new IndexOutOfBoundsException();
	}
	
	// Sets walls or free cells
	public void setWall(int x, int y, int z){
		this.maze[x][y][z] = WALL;
	}
	public void setWall(Position p){
		this.maze[p.getX()][p.getY()][p.getZ()] = WALL;
	}
	public void eraseWall(int x, int y, int z){
		this.maze[x][y][z] = FREE;
	}
	public void eraseWall(Position p){
		this.maze[p.getX()][p.getY()][p.getZ()] = FREE;
	}

	public int[][][] getMaze() {
		return maze;
	}
	public void setMaze(int[][][] maze){
		this.maze = maze;
	}
	
	// Fills the walls within the maze
	public void fillWalls()
	{
		for(int i=0;i<this.x;i++)
			for(int j=0;j<this.y;j++)
				for(int k=0;k<this.z;k++)
					this.setWall(i, j, k);
	}
	
// Fills the surrounded walls
	public void surroundWalls()
	{
		for(int i = 0;i < this.x;i++)
			for(int j = 0;j < this.y;j++)
				this.maze[i][j][0] = WALL;
		for(int i = 0;i < this.x;i++)
			for(int j = 0;j < this.y;j++)
				this.maze[i][j][this.z - 1] = WALL;
		for(int i = 0;i < this.x;i++)
			for(int j = 0;j < this.z;j++)
				this.maze[i][0][j] = WALL;
		for(int i = 0;i < this.x;i++)
			for(int j = 0;j < this.z;j++)
				this.maze[i][this.y - 1][j] = WALL;
		for(int i = 0;i < this.y;i++)
			for(int j = 0;j < this.z;j++)
				this.maze[0][i][j] = WALL;
		for(int i = 0;i < this.y;i++)
			for(int j = 0;j < this.z;j++)
				this.maze[this.x - 1][i][j] = WALL;
	}
	
	@Override
	public String toString() {
		String maze = "";
		for(int i = 0;i < this.x; i++) {
			for(int j = 0;j < this.y; j++) {
				for(int k = 0;k < this.z; k++) {
					maze += this.maze[i][j][k] + " ";
				}
				maze += "\r\n";
			}
			maze += "\r\n\r\n\r\n";
		}
				
		return maze;
	}
	
	public void printMaze() {
		for(int i = 0;i < this.x; i++) {
			for(int j = 0;j < this.y; j++) {
				for(int k = 0;k < this.z; k++) {
					System.out.println(this.maze[i][j][k] + " ");
				}
				System.out.println("\r\n");
			}
			System.out.println("\r\n\r\n\r\n");
		}
	}
	
	public byte[] toByteArray() {
		
		ArrayList<Byte> byteArrayList = new ArrayList<Byte>();
		byteArrayList.add((byte)this.x);
		byteArrayList.add((byte)this.y);
		byteArrayList.add((byte)this.z);
		byteArrayList.add((byte)this.startPosition.getX());
		byteArrayList.add((byte)this.startPosition.getY());
		byteArrayList.add((byte)this.startPosition.getZ());
		byteArrayList.add((byte)this.goalPosition.getX());
		byteArrayList.add((byte)this.goalPosition.getY());
		byteArrayList.add((byte)this.goalPosition.getZ());
		
		
		for (int z = 0; z < this.x; z++) {
			for (int y = 0; y < this.y; y++) {
				for (int x = 0; x < this.z; x++)
					byteArrayList.add((byte)maze[x][y][z]);
			}
		}
		
		//Copy the array list to array of bytes
		byte[] bytes = new byte[byteArrayList.size()];
		for (int i=0;i<byteArrayList.size(); i++) {
			bytes[i] = byteArrayList.get(i);
		}
		
		return bytes;
	}
	
	
	public void setCell(Position p) {
		this.maze[p.getX()][p.getY()][p.getZ()] = 2;
	}
}
