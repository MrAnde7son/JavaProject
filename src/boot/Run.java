package boot;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import properties.Properties;
import properties.PropertiesManager;
import view.MazeWindow;
import view.MyView;
import view.StartWindow;

public class Run {

	public static void main(String[] args) {		
		StartWindow startWin = new StartWindow();
		startWin.run();
		MyModel model = new MyModel();
		MyView view = new MyView(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true));
		Presenter presenter = new Presenter(model, view);
		view.addObserver(presenter);
		model.addObserver(presenter);
		view.start();
	}

}
