/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

/**
 *
 * @author Hoang Linh
 */
public class AnswerForm {

    private String answer;
    private Boolean isRight;

    public AnswerForm() {
    }

    public AnswerForm(String answer, Boolean isRight) {
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

}
