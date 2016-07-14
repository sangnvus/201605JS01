package vn.edu.fu.veazy.core.model;

public class ReportModel extends BasicModel{
	private String senderId;
	private boolean readFlag;
	private String content;
	private String lessonId;
	private String questionId;
	
	public ReportModel() {
		super();
	}
	public ReportModel(String id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag,
			String senderId, boolean readFlag, String content, String lessonId, String questionId) {
		super(id, createDate, updateDate, deleteDate, deleteFlag);
		this.senderId = senderId;
		this.readFlag = readFlag;
		this.content = content;
		this.lessonId = lessonId;
		this.questionId = questionId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public boolean isReadFlag() {
		return readFlag;
	}
	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
}
