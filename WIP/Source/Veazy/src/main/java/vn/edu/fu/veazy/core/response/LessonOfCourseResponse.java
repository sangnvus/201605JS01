package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.common.JsonUtils;

public class LessonOfCourseResponse {
	private String lessonId;
	private String title;
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
