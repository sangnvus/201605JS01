package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Lesson")
public class LessonModel extends BasicModel{
	@Column(name = "index", columnDefinition="int UNIQUE", nullable = false)
	private int index;
	@Column(name = "courseid", nullable = false)
	private String courseId;
	@Column(name = "versionId", nullable = false)
	private String currentVersionId;
	
	public LessonModel() {
		super();
	}
	public LessonModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag, int index,
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
