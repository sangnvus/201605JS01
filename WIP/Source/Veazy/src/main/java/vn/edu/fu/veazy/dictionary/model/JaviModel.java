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
 *　和越言葉のクラス
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Javi")
public class JaviModel {
	
	/**
	 * 和越言葉のモデル
	 */
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**
	 * 和越言葉
	 */
	@Column(name = "word", columnDefinition ="text", nullable = false)
	private String word;
	
	/**
	 * 和越言葉のかな
	 */
	@Column(name = "kana", columnDefinition ="text", nullable = true)
	private String kana;
	
	/**
	 * 和越言葉の意味
	 */
	@Column(name = "mean", columnDefinition ="text", nullable = false)
	private String means;
	public JaviModel(Integer id, String word, String kana, String means) {
		super();
		this.id = id;
		this.word = word;
		this.kana = kana;
		this.means = means;
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
	public String getMeans() {
		return means;
	}
	public void setMeans(String means) {
		this.means = means;
	}
	
}
