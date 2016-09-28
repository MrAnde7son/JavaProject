package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import properties.PropertiesManager;
import view.MazeWindow;
import view.MyView;
import view.StartWindow;

public class Run {

	public static void main(String[] args) {
		StartWindow startWindow = new StartWindow();
		startWindow.run();
		PropertiesManager.readXML();
		MyView view = new MyView(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true));
		MyModel model = new MyModel();	
		Presenter presenter = new Presenter(model, view);
		view.addObserver(presenter);
		model.addObserver(presenter);
		view.start();
	}

}
