package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`LessonVersion`")
public class LessonVersionModel extends BasicModel{
	@Column(name = "lessonId", nullable = false)
	private String lessonId;
	@Column(name = "version",columnDefinition="INT DEFAULT 1", nullable = false)
	private Integer version;
	@Column(name = "state",columnDefinition="INT DEFAULT 1", nullable = false)
	private Integer state;
	@Column(name = "title", columnDefinition="VARCHAR(254)", nullable = false)
	private String title;
	@Column(name = "title", nullable = false)
	private String description;
	@Column(name = "creatorId", nullable = false)
	private String creatorId;
	@Column(name = "reviewerId", nullable = true)
	private String reviewerId;
	@Column(name = "vocab")
	private String vocabulary;
	@Column(name = "grammar")
	private String grammar;
	@Column(name = "reading")
	private String reading;
	@Column(name = "listening")
	private String listening;
	@Column(name = "practice")
	private String practice;
	@Column(name = "article")
	private String article;
	
	public LessonVersionModel() {
		super();
	}
	public LessonVersionModel(String id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag,
			String lessonId, Integer version, Integer state, String title, String description, String creatorId,
			String reviewerId, String vocabulary, String grammar, String reading, String listening, String practice,
			String article) {
		super(id, createDate, updateDate, deleteDate, deleteFlag);
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
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
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
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(String reviewerId) {
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


