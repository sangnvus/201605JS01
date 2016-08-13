package vn.edu.fu.veazy.core.response;

import java.util.ArrayList;
import java.util.List;

public class ExamSinglePartResponse {
    private int course;
    private int skill;
    private int etaTime = 0;
    private List<BriefQuestionResponse> listQuestions = new ArrayList<>();
    
    public ExamSinglePartResponse(int course, List<ExamPartResponse> parts) {
        super();
        this.course = course;
        for (ExamPartResponse part : parts) {
            this.listQuestions.addAll(part.getQuestions());
            this.etaTime += part.getEtaTime();
            this.skill = part.getSkill();
        }
    }
    
    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getEtaTime() {
        return etaTime;
    }

    public void setEtaTime(int etaTime) {
        this.etaTime = etaTime;
    }

    public List<BriefQuestionResponse> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<BriefQuestionResponse> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public int getCourse() {
        return course;
    }
    
    public void setCourse(int course) {
        this.course = course;
    }
    
//    public List<ExamPartResponse> getParts() {
//        return parts;
//    }
//    
//    public void setParts(List<ExamPartResponse> parts) {
//        this.parts = parts;
//    }
    
}
