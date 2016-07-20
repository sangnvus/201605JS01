package vn.edu.fu.veazy.core.form;

public class DeclineLessonChangeForm {
	private Integer lessonId;
	private String comment;
	public DeclineLessonChangeForm(Integer lessonId, String comment) {
		super();
		this.lessonId = lessonId;
		this.comment = comment;
	}
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
