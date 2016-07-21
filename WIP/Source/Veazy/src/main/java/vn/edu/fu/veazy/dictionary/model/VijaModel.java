package vn.edu.fu.veazy.dictionary.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Vija")
public class VijaModel {
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(name = "word", nullable = false)
	private String word;
	@Column(name = "mean", nullable = false)
	private List<WordMean> means;
	public VijaModel(Integer id, String word, List<WordMean> means) {
		super();
		this.id = id;
		this.word = word;
		this.means = means;
	}
	public VijaModel() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<WordMean> getMeans() {
		return means;
	}
	public void setMeans(List<WordMean> means) {
		this.means = means;
	}
}
