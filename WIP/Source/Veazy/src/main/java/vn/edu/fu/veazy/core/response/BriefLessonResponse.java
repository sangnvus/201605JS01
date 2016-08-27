package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.common.utils.JsonUtils;

public class BriefLessonResponse {
	private String lessonId;
	private String title;
    private Integer state;
    private Integer courseId;
    private Integer version;
    private String description;

    private String vocabulary;
    private String grammar;
    private String conversation;
    private String listening;
    private String practice;
    private String reading;

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
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
