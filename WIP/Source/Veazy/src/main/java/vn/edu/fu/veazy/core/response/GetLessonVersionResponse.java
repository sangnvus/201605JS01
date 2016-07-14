package vn.edu.fu.veazy.core.response;

import java.util.List;

import vn.edu.fu.veazy.core.common.JsonUtils;
import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;

public class GetLessonVersionResponse {
	private String lessonId;
	private String courseId;
	private Integer version;
	private String lessonTitle;
	private String description;
	private String creatorId;
	private String reviewerId;
	private Long createDate;
	private Long updateDate;
	private String vocabulary;
	private String grammar;
	private String reading;
	private String listening;
	private String practice;
	private String article;
	private List<Integer>listVersions;
	public GetLessonVersionResponse(LessonModel lesson, LessonVersionModel version, List<Integer> listVersions){
		lessonId = lesson.getId();
		courseId = lesson.getCourseId();
		this.version = version.getVersion();
		lessonTitle = version.getTitle();
		description= version.getDescription();
		creatorId = version.getCreatorId();
		reviewerId = version.getReviewerId();
		createDate = version.getCreateDate();
		updateDate = version.getUpdateDate();
		vocabulary=version.getVocabulary();
		grammar=version.getGrammar();
		reading=version.getReading();
		listening=version.getListening();
		practice=version.getPractice();
		article=version.getArticle();
		this.listVersions = listVersions;
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
	public String getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
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
	public List<Integer> getListVersions() {
		return listVersions;
	}
	public void setListVersions(List<Integer> listVersions) {
		this.listVersions = listVersions;
	}
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
