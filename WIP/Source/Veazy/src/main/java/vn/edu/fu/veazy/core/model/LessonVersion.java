package vn.edu.fu.veazy.core.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LessonVersion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long lessonId;
	private int version;
	private LessonVersionState state;
	private String title;
	private String description;
	private long creatorId;
	private long reviewerId;
	private String vocabulary;
	private String grammar;
	private String reading;
	private String listening;
	private String practice;
	private Date createDate;
	private Date updateDate;
	private Date deleteDate;
	private boolean isDeleted;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLessonId() {
		return lessonId;
	}
	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public LessonVersionState getState() {
		return state;
	}
	public void setState(LessonVersionState state) {
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
	public long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	public long getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(long reviewerId) {
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}

enum LessonVersionState{
	PUBLISHED,
	UPDATING,
	REVIEWING
}
