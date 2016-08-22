package vn.edu.fu.veazy.core.model;

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
@Table(name = "`LessonVersion`")
public class LessonVersionModel{
	/**
	 * バージョンのＩＤ
	 */
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**
	 * 日付を作成
	 */
	@Column(name = "createDate")
    private Long createDate;
	
	/**
	 * アップデートを作成
	 */
	@Column(name = "updateDate")
    private Long updateDate;
	
	/**
	 * レッソンのＩＤ
	 */
	@Column(name = "lessonId", nullable = false)
	private Integer lessonId;
	
	/**
	 * バージョン
	 */
	@Column(name = "version",columnDefinition="INT DEFAULT 1", nullable = false)
	private Integer version;
	
	/**
	 * バージョン状態
	 */
	@Column(name = "state",columnDefinition="INT DEFAULT 1", nullable = false)
	private Integer state;
	
	/**
	 * レッソンの表題
	 */
	@Column(name = "title", columnDefinition="VARCHAR(254)", nullable = false)
	private String title;
	
	/**
	 * 説明
	 */
	@Column(name = "description",columnDefinition ="text",  nullable = false)
	private String description;
	
	
	@Column(name = "creatorId", nullable = false)
	private Integer creatorId;

	/**
	 * 語彙
	 */
	@Column(name = "vocab",columnDefinition ="text" )
	private String vocabulary;
	
	/**
	 * 文法
	 */
	@Column(name = "grammar",columnDefinition ="text")
	private String grammar;
	
	/**
	 * 読解
	 */
	@Column(name = "reading",columnDefinition ="text")
	private String reading;
	
	/**
	 * 聴解
	 */
	@Column(name = "listening",columnDefinition ="text")
	private String listening;
	
	/**
	 * 練習
	 */
	@Column(name = "practice",columnDefinition ="text")
	private String practice;
	
	/**
	 * 記事
	 */
	@Column(name = "article",columnDefinition ="text")
	private String article;
	
	public LessonVersionModel() {
		super();
	}

	public LessonVersionModel(Integer id, Long createDate, Long updateDate, Integer lessonId, Integer version,
			Integer state, String title, String description, Integer creatorId, String vocabulary,
			String grammar, String reading, String listening, String practice, String article) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.lessonId = lessonId;
		this.version = version;
		this.state = state;
		this.title = title;
		this.description = description;
		this.creatorId = creatorId;
		this.vocabulary = vocabulary;
		this.grammar = grammar;
		this.reading = reading;
		this.listening = listening;
		this.practice = practice;
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	
	public String getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(String vocabulary) {
		this.vocabulary = vocabulary;
	}

	public String getGrammar() {
		return grammar;
	}

	public void setGrammar(String grammar) {
		this.grammar = grammar;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getListening() {
		return listening;
	}

	public void setListening(String listening) {
		this.listening = listening;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}
	
}


