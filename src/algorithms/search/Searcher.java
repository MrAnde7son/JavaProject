package algorithms.search;

/**
 * Defines a Searcher for a searching problem.
 * Operates on type of Searchable.
 * 
 * @author  Itamar Mizrahi
 * @version 1.0
 * 
 */
public interface Searcher<T> {
	public Solution<T> search(Searchable<T> s);
	public int getNumberOfNodesEvaluated();
}
