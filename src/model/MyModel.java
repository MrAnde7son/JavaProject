package model;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import properties.Properties;
import properties.PropertiesManager;
import algorithms.demo.Maze3dDomain;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFSSearcher;
import algorithms.search.DFSSearcher;
import algorithms.search.Searcher;
import algorithms.search.Solution;

/***
 * Implementation of Model in MVP for Maze3d.
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class MyModel extends Observable implements Model {
	
	private ExecutorService executor;
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	private Map<String, Solution<Position>> solutions = new ConcurrentHashMap<String, Solution<Position>>();
	private Properties properties;
		
	public MyModel() {
		properties = PropertiesManager.getInstance().getProperties();
		executor = Executors.newFixedThreadPool(properties.getNumOfThreads());
		loadSolutions();
	}				
				
	@Override
	public void generateMaze(final String name, final int x, final int y, final int z) {
		executor.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				Maze3dGenerator generator;
				if(properties.getGenerationAlgorithm().equals("GrowingTree"))
					generator = new GrowingTreeGenerator();
				else
					generator = new SimpleMaze3dGenerator();
				Maze3d maze = generator.generate(x,y,z);
				mazes.put(name, maze);
				setChanged();
				notifyObservers("maze_ready " + name);		
				return maze;
			}
			
		});			
	}

	@Override
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}

	public void addMaze(String name,Maze3d maze){
		this.mazes.put(name, maze);
	}
	
	public void addSolution(String name,Solution<Position> sol){
		this.solutions.put(name, sol);
	}
	
	
	@Override
	public Solution<Position> getSolution(String name){
		return this.solutions.get(name);
	}

	@Override
	public void solve(final String arg) {
		executor.execute(new Runnable() {
			
			String name = arg.split(" ")[0];
			
			@Override
			public void run() {
				Searcher<Position> searcher;
				if(properties.getSearchingAlgorithm().equals("BFS"))
					searcher = new BFSSearcher<Position>();
				else if(properties.getSearchingAlgorithm().equals("DFS"))
					searcher = new DFSSearcher<Position>();
				else{
					notifyObservers("Invalid searching algorithms");
					return;
				}
					
				Solution<Position> sol = searcher.search(new Maze3dDomain(mazes.get(name)));
				solutions.put(name, sol);
				
				setChanged();
				notifyObservers("solved_maze " + name);	
				
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void loadSolutions() {
		File file = new File("solutions.dat");
		if (!file.exists())
			return;
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream("solutions.dat")));
			mazes = (Map<String, Maze3d>)ois.readObject();
			solutions = (Map<String, Solution<Position>>)ois.readObject();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	private void saveSolutions() {
		ObjectOutputStream oos = null;
		try {
		    oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("solutions.dat")));
			oos.writeObject(mazes);
			oos.writeObject(solutions);			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void exit() {
		executor.shutdownNow();
		saveSolutions();	
	}

	
	@Override
	public void loadMaze(String arg) throws IOException {
		String[] params = arg.split(" ");
		String name = params[0];
		InputStream in=new MyDecompressorInputStream(new FileInputStream(params[1] + ".maz"));
		byte[] maze=new byte[4096];
		in.read(maze);
		in.close();
		Maze3d loaded = new Maze3d(maze);
		addMaze(name, loaded);
	}
	@Override
	public void saveMaze(String arg) throws IOException {
		String[] params = arg.split(" ");
		Maze3d maze = getMaze(params[0]);
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream(params[1] + ".maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		
	}
}
