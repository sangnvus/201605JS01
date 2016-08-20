package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Indexed;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Lesson`")
public class LessonModel extends BasicModel{
    /**
     * レベルにレッソンの順序
     */
	@Column(name = "index", columnDefinition="serial", nullable = false)
	@Generated(GenerationTime.INSERT)
	private int index;
	
	/**
	 * レッソンのレベルのＩＤ
	 */
	@Column(name = "courseid", nullable = false)
	private Integer course;
	
	/**
	 * レッソンの現在なバージョンのＩＤ
	 */
	@Column(name = "versionId", nullable = true)
	private Integer currentVersionId;
	
	public LessonModel() {
		super();
	}
	public LessonModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag, int index,
	        Integer courseId, Integer currentVersionId) {
		super(id, createDate, updateDate, deleteDate, deleteFlag);
		this.index = index;
		this.course = courseId;
		this.currentVersionId = currentVersionId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Integer getCourse() {
		return course;
	}
	public void setCourse(Integer courseId) {
		this.course = courseId;
	}
	public Integer getCurrentVersionId() {
		return currentVersionId;
	}
	public void setCurrentVersionId(Integer currentVersionId) {
		this.currentVersionId = currentVersionId;
	}
	
}
