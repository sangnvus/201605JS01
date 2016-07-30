package vn.edu.fu.veazy.core.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

    private List<MultipartFile> file;

    public FileUploadForm() {
        super();
    }

    public FileUploadForm(List<MultipartFile> file) {
        super();
        this.file = file;
    }

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }
    
}
