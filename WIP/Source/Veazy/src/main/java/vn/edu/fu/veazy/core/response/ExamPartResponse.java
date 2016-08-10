package vn.edu.fu.veazy.core.response;

import java.util.List;

public class ExamPartResponse {
    private int skill;
    private int etaTime;
    private List<BriefQuestionResponse> questions;
    
    public ExamPartResponse(int skill, int etaTime, List<BriefQuestionResponse> questions) {
        super();
        this.skill = skill;
        this.etaTime = etaTime;
        this.questions = questions;
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
