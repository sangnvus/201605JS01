package vn.edu.fu.veazy.core.form;

public class ReportLessonForm {
	private String lessonId;
	private String content;
	
	public ReportLessonForm() {
		super();
	}
	public ReportLessonForm(String lessonId, String content) {
		super();
		this.lessonId = lessonId;
		this.content = content;
	}
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
