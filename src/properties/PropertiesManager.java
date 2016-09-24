package properties;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * PropertiesManager
 * Existing package in java.xml.bind that create xml file
 * Data member Properties
 * @author Gal Basre & Ido Dror
 */
public class PropertiesManager {
	
	private static Properties properties = null;
	
	/**
	 *  readXML from file
	 *  read all the properties fron the xml
	 * @return Properties
	 */
	public static Properties readXML() {
		File file = null;
		JAXBContext jaxbContext = null;
		Unmarshaller unmarshaller = null;
		try {
			file = new File("resources/properties.xml");
			jaxbContext = JAXBContext.newInstance(Properties.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			properties = (Properties)unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * writeXml
	 * write the the xml file 
	 */
	public static void writeXml(String userInterface, String GUIUpDownHints) {
		properties = new Properties();
		
		properties.setMaxThreads(12);
		properties.setGenerationAlgorithm("GrowingTree");
		properties.setSearchingAlgorthm("BFS");
		properties.setViewType(userInterface);
		properties.setZipFilePath("resources/data/mymap.zip");
		properties.setGUIUpDownHints(GUIUpDownHints);
		
		File file = null;
		JAXBContext jaxbContext = null;
		Marshaller marshaller = null;
		try {
			file = new File("resources/properties.xml");
			jaxbContext = JAXBContext.newInstance(properties.getClass());
			marshaller = jaxbContext.createMarshaller();
			
			// output printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(properties, file);
			//marshaller.marshal(properties, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * getProperties
	 * @return Properties
	 */
	public static Properties getProperties() {
		return properties;
	}
	
}
