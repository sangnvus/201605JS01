/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

import vn.edu.fu.veazy.core.model.AnswerModel;

/**
 *
 * @author Hoang Linh
 */
public class AnswerResponse {

    private String answer;
    private Boolean isRight;

    public AnswerResponse() {
    }

    public AnswerResponse(AnswerModel model) {
        this.answer = model.getAnswer();
        this.isRight = model.getIsRight();
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
