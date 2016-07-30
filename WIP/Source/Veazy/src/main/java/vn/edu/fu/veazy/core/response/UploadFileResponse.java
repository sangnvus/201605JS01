package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.common.utils.JsonUtils;

public class UploadFileResponse {
    
	private String link;
	
	public UploadFileResponse() {
        super();
    }

    public UploadFileResponse(String link) {
        super();
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
