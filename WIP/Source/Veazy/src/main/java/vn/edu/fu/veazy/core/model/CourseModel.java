package vn.edu.fu.veazy.core.model;


public class CourseModel extends BasicModel{
	
	private String name;
	private int index;
	
	public CourseModel(String name, int index) {
		super();
		this.name = name;
		this.index = index;
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
}
