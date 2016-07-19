package vn.edu.fu.veazy.core.form;

public class ReportLessonForm {
	private Integer lessonId;
	private String content;
	
	public ReportLessonForm() {
		super();
	}
	public ReportLessonForm(Integer lessonId, String content) {
		super();
		this.lessonId = lessonId;
		this.content = content;
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
