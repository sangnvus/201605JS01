package vn.edu.fu.veazy.core.model;


public class LessonModel extends BasicModel{
	private int index;
	private String courseId;
	private String currentVersionId;
	
	public LessonModel() {
		super();
	}
	public LessonModel(String id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag, int index,
			String courseId, String currentVersionId) {
		super(id, createDate, updateDate, deleteDate, deleteFlag);
		this.index = index;
		this.courseId = courseId;
		this.currentVersionId = currentVersionId;
	}
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
