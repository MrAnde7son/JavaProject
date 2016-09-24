package properties;
import java.io.Serializable;

/***
 * Properties of type Maze3d.
 * @author Itamar&Chen
 *
 */
public class Properties implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numOfThreads;
	private String generateMazeAlgorithm;
	private String solveMazeAlgorithm;
	private int x,y,z;
	
	public Properties() {
		this.numOfThreads = 5;
		this.generateMazeAlgorithm = "GrowingTree";
		this.solveMazeAlgorithm = "BFS";
		this.x = this.y = this.z = 5;
	}
			
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public String getGenerateMazeAlgorithm() {
		return generateMazeAlgorithm;
	}
	public void setGenerateMazeAlgorithm(String generateMazeAlgorithm) {
		this.generateMazeAlgorithm = generateMazeAlgorithm;
	}
	public String getSolveMazeAlgorithm() {
		return solveMazeAlgorithm;
	}
	public void setSolveMazeAlgorithm(String solveMazeAlgorithm) {
		this.solveMazeAlgorithm = solveMazeAlgorithm;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
}
