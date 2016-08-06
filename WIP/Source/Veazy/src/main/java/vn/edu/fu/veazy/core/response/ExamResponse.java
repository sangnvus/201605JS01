package vn.edu.fu.veazy.core.response;

import java.util.List;

public class ExamResponse {
    private int course;
    private List<ExamPartResponse> parts;
    
    public ExamResponse(int course, List<ExamPartResponse> parts) {
        super();
        this.course = course;
        this.parts = parts;
    }
    
    public int getCourse() {
        return course;
    }
    
    public void setCourse(int course) {
        this.course = course;
    }
    
    public List<ExamPartResponse> getParts() {
        return parts;
    }
    
    public void setParts(List<ExamPartResponse> parts) {
        this.parts = parts;
    }
    
}
