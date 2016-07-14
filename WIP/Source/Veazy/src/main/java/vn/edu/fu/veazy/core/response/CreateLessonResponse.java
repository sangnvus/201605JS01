package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;

public class CreateLessonResponse {
	private String lessonId;
	private String courseId;
	private Integer version;
	private String lessonTitle;
	private String description;
	private String creatorId;
	private Long createDate;
	private String vocabulary;
	private String grammar;
	private String reading;
	private String listening;
	private String practice;
	private String article;
	public CreateLessonResponse(LessonModel lesson,LessonVersionModel version){
		lessonId = lesson.getId();
		courseId = lesson.getCourseId();
		this.version = version.getVersion();
		lessonTitle = version.getTitle();
		description = version.getDescription();
		creatorId = version.getCreatorId();
		createDate = version.getCreateDate();
		vocabulary = version.getVocabulary();
		grammar = version.getGrammar();
		reading = version.getReading();
		listening = version.getListening();
		practice = version.getPractice();
		article = version.getArticle();
	}
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getLessonTitle() {
		return lessonTitle;
	}
	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
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
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
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
