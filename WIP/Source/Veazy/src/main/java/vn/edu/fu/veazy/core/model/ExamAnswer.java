/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

/**
 *
 * @author Hoang Linh
 */
public class ExamAnswer {

    private String QuestionId;
    private String UserAnswer;

    public ExamAnswer() {
    }

    public ExamAnswer(String QuestionId, String UserAnswer) {
        this.QuestionId = QuestionId;
        this.UserAnswer = UserAnswer;
    }

    public String getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(String QuestionId) {
        this.QuestionId = QuestionId;
    }

    public String getUserAnswer() {
        return UserAnswer;
    }

    public void setUserAnswer(String UserAnswer) {
        this.UserAnswer = UserAnswer;
    }

}
