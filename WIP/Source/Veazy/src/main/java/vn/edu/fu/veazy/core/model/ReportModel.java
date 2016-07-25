package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Report")
public class ReportModel {
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long createDate;
    private Long deleteDate;
    @Column(name = "isDeleted",columnDefinition="boolean default false", nullable = false)
    private boolean deleteFlag;
	@Column(name = "senderId")
	private Integer senderId;
	@Column(name = "receiverId")
	private Integer receiverId;
	@Column(name = "isRead", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = false)
	private boolean readFlag;
	@Column(name = "content")
	private String content;
	@Column(name = "lessonId")
	private Integer lessonId;
	@Column(name = "questionId")
	private Integer questionId;
	
	public ReportModel() {
		super();
	}

	public ReportModel(Integer id, Long createDate, Long deleteDate, boolean deleteFlag, Integer senderId,
			Integer receiverId, boolean readFlag, String content, Integer lessonId, Integer questionId) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.deleteDate = deleteDate;
		this.deleteFlag = deleteFlag;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.readFlag = readFlag;
		this.content = content;
		this.lessonId = lessonId;
		this.questionId = questionId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Long deleteDate) {
		this.deleteDate = deleteDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
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

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
}
