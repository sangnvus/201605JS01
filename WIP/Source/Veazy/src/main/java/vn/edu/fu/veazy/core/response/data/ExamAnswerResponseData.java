/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import java.util.List;
import vn.edu.fu.veazy.core.model.ExamAnswer;

/**
 *
 * @author Hoang Linh
 */
public class ExamAnswerResponseData {

    private Integer questionId;
    private List<String> userAnswer;
    private List<String> correctAnswer;

    public ExamAnswerResponseData() {
    }

    public ExamAnswerResponseData(ExamAnswer answer) {
        this.questionId = answer.getQuestionId();
        this.correctAnswer = answer.getCorrectAnswer();
        this.userAnswer = answer.getUserAnswer();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<String> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<String> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
