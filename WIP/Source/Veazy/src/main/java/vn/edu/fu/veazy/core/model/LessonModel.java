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
	private Integer courseId;
	@Column(name = "versionId", nullable = false)
	private Integer currentVersionId;
	
	public LessonModel() {
		super();
	}
	public LessonModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag, int index,
			Integer courseId, Integer currentVersionId) {
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
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getCurrentVersionId() {
		return currentVersionId;
	}
	public void setCurrentVersionId(Integer currentVersionId) {
		this.currentVersionId = currentVersionId;
	}
	
}
