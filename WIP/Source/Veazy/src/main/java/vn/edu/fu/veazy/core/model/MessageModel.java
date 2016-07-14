package vn.edu.fu.veazy.core.model;


public class MessageModel extends BasicModel{
	private String content;
	private String senderId;
	private String receiverId;
	private boolean readFlag;
	
	public MessageModel() {
		super();
	}
	public MessageModel(String id, Long createDate, Long updateDate, Long deleteDate, boolean deleteFlag,
			String content, String senderId, String receiverId, boolean readFlag) {
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
	public boolean isReadFlag() {
		return readFlag;
	}
	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}
	
}

