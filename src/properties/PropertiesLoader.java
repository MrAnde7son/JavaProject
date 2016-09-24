package properties;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/***
 * Properties loader of Maze3d properties
 * @author Itamar Mizrahi&Chen Erlich
 *
 */
public class PropertiesLoader {
	private static PropertiesLoader instance;
	private Properties properties;
	
	public Properties getProperties() {
		return properties;
	}
	
	private PropertiesLoader() 
	{
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("properties.xml"));
			properties = (Properties)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PropertiesLoader getInstance() {
		if (instance == null) 
			instance = new PropertiesLoader();
		return instance;
	}
}
