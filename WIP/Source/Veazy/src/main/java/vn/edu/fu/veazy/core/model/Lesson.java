package vn.edu.fu.veazy.core.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Lesson {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int index;
	private long courseId;
	private long currentVersionId;
	private LessonState state;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private boolean isDelete;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getCurrentVersionId() {
		return currentVersionId;
	}
	public void setCurrentVersionId(long currentVersionId) {
		this.currentVersionId = currentVersionId;
	}
	public LessonState getState() {
		return state;
	}
	public void setState(LessonState state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	
	
}

enum LessonState{
	
}
