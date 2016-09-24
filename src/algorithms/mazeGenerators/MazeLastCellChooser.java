package algorithms.mazeGenerators;

import java.util.ArrayList;

public class MazeLastCellChooser extends MazeCellChooser {

	@Override
	public <T> T choose(ArrayList<T> s) {
		return s.get(s.size() - 1);
	}

}
