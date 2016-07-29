package vn.edu.fu.veazy.core.form;

public class ChangeRoleForm {
	/**
	 * ユーザーのＩＤ
	 */
	private Integer userId;
	/**
	 * ユーザーの役割
	 */
	private int role;
	
	public ChangeRoleForm(Integer userId, int role) {
		super();
		this.userId = userId;
		this.role = role;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
}
