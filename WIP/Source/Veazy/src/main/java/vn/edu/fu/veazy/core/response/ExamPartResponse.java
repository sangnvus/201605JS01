package vn.edu.fu.veazy.core.response;

import java.util.List;

public class ExamPartResponse {
    private int examId;
    private int skill;
    private int etaTime;
    private boolean offlineCheck;
    private List<BriefQuestionResponse> questions;
    
    public ExamPartResponse(int skill, int etaTime, List<BriefQuestionResponse> questions) {
        super();
        this.skill = skill;
        this.etaTime = etaTime;
        this.questions = questions;
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
    public List<BriefQuestionResponse> getQuestions() {
        return questions;
    }
    public void setQuestions(List<BriefQuestionResponse> questions) {
        this.questions = questions;
    }
    
}
