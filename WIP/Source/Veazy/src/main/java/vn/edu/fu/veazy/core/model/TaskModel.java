package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Task`")
public class TaskModel extends BasicModel{
	@Column(name = "senderId")
	private String senderId;
	@Column(name = "receiverId")
	private String receiverId;
	@Column(name = "content", nullable = false)
	private String content;
	@Column(name = "isRead", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = false)
	private boolean readFlag;
	
	public TaskModel() {
		super();
	}
	public TaskModel(String id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag, String senderId,
			String receiverId, String content, boolean readFlag) {
		super(id, createDate, updateDate, deleteDate, deleteFlag);
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.readFlag = readFlag;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isReadFlag() {
		return readFlag;
	}
	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}
}
