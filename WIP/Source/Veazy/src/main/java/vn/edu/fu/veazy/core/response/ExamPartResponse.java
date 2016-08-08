package vn.edu.fu.veazy.core.response;

import java.util.List;

public class ExamPartResponse {
    private int skill;
    private List<BriefQuestionResponse> questions;
    
    public ExamPartResponse(int skill, List<BriefQuestionResponse> questions) {
        super();
        this.skill = skill;
        this.questions = questions;
    }
    
    public int getSkill() {
        return skill;
    }
    public void setSkill(int skill) {
        this.skill = skill;
    }
    public List<BriefQuestionResponse> getQuestions() {
        return questions;
    }
    public void setQuestions(List<BriefQuestionResponse> questions) {
        this.questions = questions;
    }
    
}
