package algorithms.mazeGenerators;

public interface Maze3dGenerator {
	public abstract Maze3d generate(int x, int y, int z);
	public String measureAlgorithmTime(Maze3d maze); 
	public Position getEntrance(Maze3d maze);
	public Position getExit(Maze3d maze);
	public Position getRandomPosition(Maze3d maze);
}
