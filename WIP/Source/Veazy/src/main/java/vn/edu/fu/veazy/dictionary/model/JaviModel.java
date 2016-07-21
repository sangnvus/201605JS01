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
@Table(name = "Javi")
public class JaviModel {
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(name = "word", nullable = false)
	private String word;
	@Column(name = "kana", nullable = false)
	private String kana;
	@Column(name = "means", nullable = false)
	private List<WordMean> means;
	public JaviModel(Integer id, String word, String kana, List<WordMean> mean) {
		super();
		this.id = id;
		this.word = word;
		this.kana = kana;
		this.means = mean;
	}
	public JaviModel() {
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
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public List<WordMean> getMeans() {
		return means;
	}
	public void setMeans(List<WordMean> mean) {
		this.means = mean;
	}
}
