package vn.edu.fu.veazy.core.response;

public class BriefAnswerResponse {

    private String answer;
    private Boolean isRight = false;
    private Boolean isSelected = false;
    
    public BriefAnswerResponse() {
        super();
    }
    
    public BriefAnswerResponse(String answer, Boolean isRight, Boolean isSelected) {
        super();
        this.answer = answer;
        this.isRight = isRight;
        this.isSelected = isSelected;
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
