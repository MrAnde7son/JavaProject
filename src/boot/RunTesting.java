package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import view.MyView;

public class RunTesting {
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
				
		MyView view = new MyView(in, out);
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(model, view);
		model.addObserver(presenter);
		view.addObserver(presenter);
				
		view.start(); 
		
//	      Scanner in = new Scanner(System.in);
//	      
//	      System.out.println("Enter a string");
//	      s = in.nextLine();
//	      System.out.println("You entered string "+s);
//	 
//	      System.out.println("Enter an integer");
//	      a = in.nextInt();
//	      System.out.println("You entered integer "+a);
//	 
//	      System.out.println("Enter a float");
//	      b = in.nextFloat();
//	      System.out.println("You entered float "+b);   
	}
}
