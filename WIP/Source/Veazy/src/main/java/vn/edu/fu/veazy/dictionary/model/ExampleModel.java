package vn.edu.fu.veazy.dictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Example")
public class ExampleModel {
	@Id
    @Column(name = "id", nullable = false)
    private Integer id;
	@Column(name = "ja", nullable = false)
	private String ja;
	@Column(name = "vi", nullable = false)
	private String vi;
	public ExampleModel(Integer id, String ja, String vi) {
		super();
		this.id = id;
		this.ja = ja;
		this.vi = vi;
	}
	public ExampleModel() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJa() {
		return ja;
	}
	public void setJa(String ja) {
		this.ja = ja;
	}
	public String getVi() {
		return vi;
	}
	public void setVi(String vi) {
		this.vi = vi;
	}
}
