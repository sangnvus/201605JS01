package vn.edu.fu.veazy.core.form;

public class LookupForm {
	/**
	 * 検索の言葉
	 */
	private String key;
	
	public LookupForm() {
		super();
	}

	public LookupForm(String key) {
		super();
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}