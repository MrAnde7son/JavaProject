package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;
/**
 * Represents a charcter in a Maze3d game.
 * @author Itamar&Erlich
 */
public class GameCharacter {
	private Position pos;
	private Image img;
	
	public GameCharacter() {
		this.img = new Image(null, "resources/Images/<someimage.jpg>");
	}

	public Position getPos() {
		return pos;
	}


	public void setPos(Position pos) {
		this.pos = pos;
	}
	/**
	 * Draws a GameCharacter
	 *
	 */
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, cellWidth * pos.getX(), cellHeight * pos.getY(), cellWidth, cellHeight);
	}
	
}
