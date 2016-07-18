package vn.edu.fu.veazy.core.response;

public class GetCourseResponse {
	private String name;
	private int index;
	private String description;
	public GetCourseResponse(String name, int index, String description) {
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
