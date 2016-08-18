package vn.edu.fu.veazy.core.response;

import java.util.ArrayList;
import java.util.List;

public class ExamSinglePartResponse {
    private int examId;
    private int course;
    private int skill;
    private boolean offlineCheck;
    private int etaTime = 0;
    private List<BriefQuestionResponse> listQuestions = new ArrayList<>();
    
    public ExamSinglePartResponse(int course, List<ExamPartResponse> parts) {
        super();
        this.course = course;
        if (parts != null && parts.size() > 0) {
            ExamPartResponse part = parts.get(0);
            this.listQuestions.addAll(part.getQuestions());
            this.etaTime += part.getEtaTime();
            this.skill = part.getSkill();
            this.examId = part.getExamId();
            this.offlineCheck = part.isOfflineCheck();
        }
    }
    
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public boolean isOfflineCheck() {
        return offlineCheck;
    }

    public void setOfflineCheck(boolean offlineCheck) {
        this.offlineCheck = offlineCheck;
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
