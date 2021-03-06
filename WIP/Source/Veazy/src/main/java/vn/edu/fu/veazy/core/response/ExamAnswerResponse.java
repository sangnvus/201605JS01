package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.model.ExamAnswerModel;

public class ExamAnswerResponse {

    private String answer;
    private Boolean isRight;
    private Boolean isSelected;
    
    public ExamAnswerResponse(ExamAnswerModel model) {
        super();
        this.answer = model.getAnswer();
        this.isRight = model.getIsRight();
        this.isSelected = model.getIsSelected();
    }
    
    public ExamAnswerResponse(String answer, Boolean isRight) {
        super();
        this.answer = answer;
        this.isRight = isRight;
    }

    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public Boolean getIsRight() {
        return isRight;
    }
    
    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}
