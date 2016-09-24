package presenter;

import java.io.Serializable;

/***
 * Properties of type Maze3d.
 * @author Itamar&Chen
 *
 */
public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String generationAlgorithm;
	private int x,y,z;
	private String searchingAlgorthm;
	private String viewType;
	private int maxThreads;
	
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
	public String getSearchingAlgorthm() {
		return searchingAlgorthm;
	}
	public void setSearchingAlgorthm(String searchingAlgorthm) {
		this.searchingAlgorthm = searchingAlgorthm;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public int getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
