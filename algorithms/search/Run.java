package algorithms.search;

public class Run<T> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void testSearcher(Searcher<T> searcher, Searchable<T> searchable) {
		Solution<T> sol = searcher.search(searchable);
		int n = searcher.getNumberOfNodesEvaluated();
		System.out.println(sol.toString());
		System.out.println(n);
	}
}
