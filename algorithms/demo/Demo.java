package algorithms.demo;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFSSearcher;
import algorithms.search.DFSSearcher;
import algorithms.search.Searchable;
import algorithms.search.Searcher;

public class Demo {
	public void run(int x, int y, int z) {
		Maze3d maze3d = (new GrowingTreeGenerator()).generate(x, y, z);
		Searcher<Position> dfs = new DFSSearcher<Position>();
		Searcher<Position> bfs = new BFSSearcher<Position>();
		Searchable<Position> maze = new Maze3dDomain(maze3d);
		dfs.search(maze);
		bfs.search(maze);
		System.out.println("DFS Results:" + dfs.getNumberOfNodesEvaluated());
		System.out.println("BFS Results:" + bfs.getNumberOfNodesEvaluated());
	}

}
