package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.common.utils.JsonUtils;

public class UploadFileResponse {
    
	private String link;
	private Double mp3Length;

    public UploadFileResponse() {
        super();
    }

    public UploadFileResponse(String link) {
        super();
        this.link = link;
    }

    public UploadFileResponse(String link, Double mp3Length) {
        super();
        this.link = link;
        this.mp3Length = mp3Length;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public Double getMp3Length() {
        return mp3Length;
    }

    public void setMp3Length(Double mp3Length) {
        this.mp3Length = mp3Length;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
