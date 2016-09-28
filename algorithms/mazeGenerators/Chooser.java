package algorithms.mazeGenerators;

import java.util.ArrayList;

public interface Chooser {
	public <T> T choose(ArrayList<T> s);
}
