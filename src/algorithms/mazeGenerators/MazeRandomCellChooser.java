package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MazeRandomCellChooser extends MazeCellChooser {

	private Random rand = new Random();
	
	@Override
	public <T> T choose(ArrayList<T> s) {
		return s.get(rand.nextInt(s.size()));
	}

}
