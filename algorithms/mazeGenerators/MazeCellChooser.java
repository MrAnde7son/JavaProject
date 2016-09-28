package algorithms.mazeGenerators;

import java.util.ArrayList;

public abstract class MazeCellChooser implements Chooser {

	public abstract <T> T choose(ArrayList<T> s);

}
