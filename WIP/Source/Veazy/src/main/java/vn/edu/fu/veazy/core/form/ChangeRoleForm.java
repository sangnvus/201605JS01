package vn.edu.fu.veazy.core.form;

public class ChangeRoleForm {
	/**
	 * ユーザーのＩＤ
	 */
	private Integer userId;
	/**
	 * ユーザーの役割
	 */
	private Integer role;
	
	public ChangeRoleForm(Integer userId, Integer role) {
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
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	
}
