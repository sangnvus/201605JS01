package vn.edu.fu.veazy.core.response;

import javax.crypto.Cipher;

import vn.edu.fu.veazy.core.common.JsonUtils;
import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;

public class GetLessonResponse {
	private String lessonId;
	private String courseId;
	private String lessonTitle;
	private String description;

	private Long updateDate;

	private String vocabulary;
	private String grammar;
	private String reading;
	private String listening;
	private String practice;
	private String article;

	private String previousLessonId;
	private String nextLessonId;
	public GetLessonResponse(LessonModel lesson, LessonVersionModel currentVersion, String nextId, String previousId) {
		 lessonId = lesson.getId();
		 courseId = lesson.getCourseId();
		 lessonTitle = currentVersion.getTitle();
		 description = currentVersion.getDescription();
		 updateDate = currentVersion.getUpdateDate();
		 vocabulary = currentVersion.getVocabulary();
		 grammar = currentVersion.getGrammar();
		 reading = currentVersion.getReading();
		 listening = currentVersion.getListening();
		 practice = currentVersion.getPractice();
		 article = currentVersion.getArticle();
		 previousLessonId = previousId;
		 nextLessonId = nextId;
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
	public String getPreviousLessonId() {
		return previousLessonId;
	}
	public void setPreviousLessonId(String previousLessonId) {
		this.previousLessonId = previousLessonId;
	}
	public String getNextLessonId() {
		return nextLessonId;
	}
	public void setNextLessonId(String nextLessonId) {
		this.nextLessonId = nextLessonId;
	}
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
