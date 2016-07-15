package vn.edu.fu.veazy.core.model;


public class CourseModel extends BasicModel{
	
	private String name;
	private int index;
	private String description;
	public CourseModel(String name, int index, String description) {
		super();
		this.name = name;
		this.index = index;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
