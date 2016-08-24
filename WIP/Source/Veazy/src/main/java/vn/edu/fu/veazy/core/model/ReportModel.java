package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import vn.edu.fu.veazy.core.form.ReportForm;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Report`")
public class ReportModel {
	/**
	 * 報告のＩＤ
	 */
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	/**
	 * 作成の日付
	 */
	@Column(name = "createDate")
    private Long createDate;
	
	/**
	 * 削除の日付
	 */
	@Column(name = "deleteDate")
    private Long deleteDate;
	
	/**
	 * 削除の印
	 */
    @Column(name = "isDeleted",columnDefinition="boolean default false", nullable = false)
    private boolean deleteFlag;
    
    /**
	 * 報告を送る人
	 */
	@Column(name = "senderId")
	private Integer senderId;
	
	/**
	 * 報告を受け取る人
	 */
	@Column(name = "receiverId")
	private Integer receiverId;
	
	/**
	 * 読んだことの印
	 */
	@Column(name = "isRead", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = false)
	private Boolean readFlag;
	
	/**
	 * 報告の内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 報告のレッソンのＩＤ
	 */
	@Column(name = "lessonId")
	private Integer lessonId;
	
	/**
	 * 報告の質問のＩＤ
	 */
	@Column(name = "questionId")
	private Integer questionId;
	
	public ReportModel() {
		super();
	}
        
    public ReportModel(ReportForm form, Integer senderId, Integer questionId) {
        super();
        this.content = form.getContent();
        this.senderId = senderId;
        this.questionId = questionId;
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
