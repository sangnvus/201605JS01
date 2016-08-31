package vn.edu.fu.veazy.core.form;

public class SubmitAnswerForm {

    private String answer;
    private Boolean isRight;
    private Boolean isSelected;
    
    public SubmitAnswerForm() {
        super();
    }

    public SubmitAnswerForm(String answer, Boolean isSelected) {
        super();
        this.answer = answer;
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
