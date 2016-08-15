package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Message`")
public class MessageModel extends BasicModel{
	@Column(name = "content")
	private String content;
	@Column(name = "senderId")
	private Integer senderId;
	@Column(name = "receiverId")
	private Integer receiverId;
	@Column(name = "isRead", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = false)
	private boolean readFlag;
	
	public MessageModel() {
		super();
	}
	public MessageModel(Integer id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag,
			String content, Integer senderId, Integer receiverId, boolean readFlag) {
		super(id, createDate, updateDate, deleteDate, deleteFlag);
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.readFlag = readFlag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
}

