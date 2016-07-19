package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Task")
public class TaskModel extends BasicModel{
	@Column(name = "senderId")
	private Integer senderId;
	@Column(name = "receiverId")
	private Integer receiverId;
	@Column(name = "content", nullable = false)
	private String content;
	@Column(name = "isRead", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = false)
	private boolean readFlag;
	
	public TaskModel() {
		super();
	}
	public TaskModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag, Integer senderId,
			Integer receiverId, String content, boolean readFlag) {
		super(id, createDate, updateDate, deleteDate, deleteFlag);
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.readFlag = readFlag;
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
