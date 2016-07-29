package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.common.utils.JsonUtils;
import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;

public class GetLessonResponse {
	private Integer lessonId;
	private Integer courseId;
	private String lessonTitle;
	private String description;

	private Long updateDate;

	private String vocabulary;
	private String grammar;
	private String conversation;
	private String listening;
	private String practice;
	private String reading;

	private Integer previousLessonId;
	private Integer nextLessonId;
	public GetLessonResponse(LessonModel lesson, LessonVersionModel currentVersion, Integer nextId, Integer previousId) {
		 lessonId = lesson.getId();
		 courseId = lesson.getCourseId();
		 lessonTitle = currentVersion.getTitle();
		 description = currentVersion.getDescription();
		 updateDate = currentVersion.getUpdateDate();
		 vocabulary = currentVersion.getVocabulary();
		 grammar = currentVersion.getGrammar();
		 conversation = currentVersion.getReading();
		 listening = currentVersion.getListening();
		 practice = currentVersion.getPractice();
		 reading = currentVersion.getArticle();
		 previousLessonId = previousId;
		 nextLessonId = nextId;
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
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
	public String getConversation() {
		return conversation;
	}
	public void setConversation(String conversation) {
		this.conversation = conversation;
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
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}
	public Integer getPreviousLessonId() {
		return previousLessonId;
	}
	public void setPreviousLessonId(Integer previousLessonId) {
		this.previousLessonId = previousLessonId;
	}
	public Integer getNextLessonId() {
		return nextLessonId;
	}
	public void setNextLessonId(Integer nextLessonId) {
		this.nextLessonId = nextLessonId;
	}
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
