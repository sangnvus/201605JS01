package vn.edu.fu.veazy.core.response;

public class GetCourseResponse {
	private String name;
	private String description;
	public GetCourseResponse(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
