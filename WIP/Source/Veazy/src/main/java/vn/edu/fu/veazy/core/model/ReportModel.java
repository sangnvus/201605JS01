package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Report")
public class ReportModel extends BasicModel{
	@Column(name = "senderId")
	private String senderId;
	@Column(name = "isRead", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = false)
	private boolean readFlag;
	@Column(name = "content")
	private String content;
	@Column(name = "lessonId")
	private String lessonId;
	@Column(name = "questionId")
	private String questionId;
	
	public ReportModel() {
		super();
	}
	public ReportModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag,
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
