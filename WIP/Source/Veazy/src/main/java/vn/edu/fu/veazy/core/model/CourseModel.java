package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Course")
public class CourseModel extends BasicModel{
	@Column(name = "name", columnDefinition="VARCHAR(50) UNIQUE", nullable = false)
	private String name;
	@Column(name = "index", columnDefinition="UNIQUE", nullable = false)
	private int index;
	@Column(name = "des")
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
