package properties;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/***
 * Properties of type Maze3d.
 * @author Itamar Mizrahi & Chen Erlich
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String generationAlgorithm;
	private int x,y,z;
	private String searchingAlgorithm;
	private String viewType;
	private int numOfThreads;
	private String zipFilePath;
	private String hint;
	
	public Properties() {
		name = "";
		generationAlgorithm = null;
		x = 0;
		y = 0;
		z = 0;
		searchingAlgorithm = null;
		viewType = null;
		numOfThreads = 8;
		zipFilePath = null;
		hint = null;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenerationAlgorithm() {
		return generationAlgorithm;
	}
	public void setGenerationAlgorithm(String generationAlgorithm) {
		this.generationAlgorithm = generationAlgorithm;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public String getSearchingAlgorithm() {
		return searchingAlgorithm;
	}
	public void setSearchingAlgorithm(String searchingAlgorithm) {
		this.searchingAlgorithm = searchingAlgorithm;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getZipFilePath() {
		return zipFilePath;
	}
	public void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}

}
