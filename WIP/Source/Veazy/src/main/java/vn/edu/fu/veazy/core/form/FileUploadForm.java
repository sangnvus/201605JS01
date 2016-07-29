package vn.edu.fu.veazy.core.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

    private List<MultipartFile> files;

    public FileUploadForm() {
        super();
    }

    public FileUploadForm(List<MultipartFile> files) {
        super();
        this.files = files;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
    
}
