package properties;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * PropertiesManager using Singleton Design Pattern.
 * @author Itamar&Chen
 */
public class PropertiesManager {
	
	private static PropertiesManager instance;
	
	private static Properties properties;
	
	public Properties getProperties() {
		return properties;
	}
	
	/***
	 * Private constructor to allow creation of one instance only.
	 */
	private PropertiesManager() 
	{
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("resources/properties.xml"));
			properties = (Properties)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * returns the created instance of type PropertiesManager
	 * @return
	 */
	public static PropertiesManager getInstance() {
		if (instance == null) 
			instance = new PropertiesManager();
		return instance;
	}
	
	public static Properties readXML() {
		File file = null;
		JAXBContext jaxbContext = null;
		Unmarshaller unmarshaller = null;
		try {
			file = new File("resources/properties.xml");
			jaxbContext = JAXBContext.newInstance(Properties.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			properties = (Properties)unmarshaller.unmarshal(file);
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		}
		return properties;
	}


	public static void writeXml(String viewType) {
		properties.setViewType(viewType);
		File file = null;
		JAXBContext jaxbContext = null;
		Marshaller marshaller = null;
		try {
			file = new File("resources/properties.xml");
			jaxbContext = JAXBContext.newInstance(properties.getClass());
			marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(properties, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
