package algorithms.mazeGenerators;

/*
 * Defines a position in a 3d array
 */

public class Position {
	private int x,y,z;
	
	public Position(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(Position p){
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
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
	
	@Override
	public String toString() {
		return "{" + this.x + "," + this.y + "," + this.z + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		Position p = (Position)obj;
		return p.x == this.x && p.y == this.y && p.z == this.z;
	}
}
