package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.common.utils.JsonUtils;

public class UploadFileResponse {
    
	private String fileUrl;
	
	public UploadFileResponse() {
        super();
    }

    public UploadFileResponse(String fileUrl) {
        super();
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
