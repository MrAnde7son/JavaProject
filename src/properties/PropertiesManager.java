package properties;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * PropertiesManager
 * @author Itamar&Chen
 */
public class PropertiesManager {
	
	private static Properties properties;
	
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

	public static void writeXml(String userInterface, String GUIUpDownHints) {
		properties = new Properties();
		
		properties.setNumOfThreads(12);
		properties.setGenerationAlgorithm("GrowingTree");
		properties.setSearchingAlgorthm("BFS");
		properties.setViewType(userInterface);
		properties.setZipFilePath("resources/data/mymap.zip");
		properties.setHint(GUIUpDownHints);
		
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
	
	public static Properties getProperties() {
		return properties;
	}
	
}
