package vn.edu.fu.veazy.core.model;

public class TaskModel extends BasicModel{
	private String senderId;
	private String receiverId;
	private String content;
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
