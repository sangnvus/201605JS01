package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.common.utils.JsonUtils;

public class BriefLessonResponse {
	private String lessonId;
	private String title;
	private Integer courseId;
    private Integer version;
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
