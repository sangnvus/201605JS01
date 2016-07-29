package vn.edu.fu.veazy.dictionary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author CuHo
 *　越和言葉のクラス
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Vija")
public class VijaModel {
	
	/**
	 * 越和言葉のモデル
	 */
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**
	 * 越和言葉
	 */
	@Column(name = "word",columnDefinition ="text", nullable = false)
	private String word;
	
	/**
	 * 越和言葉の意味
	 */
	@Column(name = "mean",columnDefinition ="text", nullable = false)
	private String means;
	
	public VijaModel() {
		super();
	}
	public VijaModel(Integer id, String word, String means) {
		super();
		this.id = id;
		this.word = word;
		this.means = means;
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
	public String getMeans() {
		return means;
	}
	public void setMeans(String means) {
		this.means = means;
	}
	
}
