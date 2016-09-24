package algorithms.demo;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;



public class Run {

	public static void main(String[] args) throws IOException {
		
		//Demo demo = new Demo();
		//demo.run(3,3,3);
		
		Maze3d maze = new GrowingTreeGenerator().generate(5, 5, 5); //... generate it
		System.out.println("=================");
		System.out.println(maze);
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		System.out.println("=================");
		System.out.println(loaded);
		System.out.println(loaded.equals(maze));

	}

}
