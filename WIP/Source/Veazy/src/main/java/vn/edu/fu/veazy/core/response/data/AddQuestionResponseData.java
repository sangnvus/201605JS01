/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

/**
 *
 * @author Hoang Linh
 */
public class AddQuestionResponseData {
    private Integer questionId;

    public AddQuestionResponseData() {
    }

    public AddQuestionResponseData(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    
}
