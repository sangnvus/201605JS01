package vn.edu.fu.veazy.core.model;


public class LessonModel extends BasicModel{
	private int index;
	private String courseId;
	private String currentVersionId;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCurrentVersionId() {
		return currentVersionId;
	}
	public void setCurrentVersionId(String currentVersionId) {
		this.currentVersionId = currentVersionId;
	}
	
}
