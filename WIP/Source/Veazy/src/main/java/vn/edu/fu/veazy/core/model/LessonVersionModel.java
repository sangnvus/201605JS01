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
@Table(name = "LessonVersion")
public class LessonVersionModel{
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long createDate;
    private Long updateDate;
	@Column(name = "lessonId", nullable = false)
	private Integer lessonId;
	@Column(name = "version",columnDefinition="INT DEFAULT 1", nullable = false)
	private Integer version;
	@Column(name = "state",columnDefinition="INT DEFAULT 1", nullable = false)
	private Integer state;
	@Column(name = "title", columnDefinition="VARCHAR(254)", nullable = false)
	private String title;
	@Column(name = "description",columnDefinition ="text",  nullable = false)
	private String description;
	@Column(name = "creatorId", nullable = false)
	private Integer creatorId;
	@Column(name = "reviewerId", nullable = true)
	private Integer reviewerId;
	@Column(name = "vocab",columnDefinition ="text" )
	private String vocabulary;
	@Column(name = "grammar",columnDefinition ="text")
	private String grammar;
	@Column(name = "reading",columnDefinition ="text")
	private String reading;
	@Column(name = "listening",columnDefinition ="text")
	private String listening;
	@Column(name = "practice",columnDefinition ="text")
	private String practice;
	@Column(name = "article",columnDefinition ="text")
	private String article;
	
	public LessonVersionModel() {
		super();
	}

	public LessonVersionModel(Integer id, Long createDate, Long updateDate, Integer lessonId, Integer version,
			Integer state, String title, String description, Integer creatorId, Integer reviewerId, String vocabulary,
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
		this.reviewerId = reviewerId;
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

	public Integer getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Integer reviewerId) {
		this.reviewerId = reviewerId;
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


