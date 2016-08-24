package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Course`")
public class CourseModel{
	/**
	 * レベルのＩＤ
	 */
	@Id
    @Column(name = "id", nullable = false)
    private Integer id;
	
	/**
	 * レベルの表題
	 */
	@Column(name = "name", columnDefinition="VARCHAR(50) UNIQUE", nullable = false)
	private String name;
	
	/**
	 * レベルの説明
	 */
	@Column(name = "des")
	private String description;
	public CourseModel() {
        super();
    }
    public CourseModel(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
