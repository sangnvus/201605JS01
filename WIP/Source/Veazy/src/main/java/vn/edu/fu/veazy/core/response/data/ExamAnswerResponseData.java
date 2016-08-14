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
    private Integer questionType;
    private List<String> userAnswer;
    private List<String> listAnswers;
    private List<ExamAnswerResponseData> listQuestions;

    public ExamAnswerResponseData() {
    }

    public ExamAnswerResponseData(ExamAnswer answer) {
        this.questionId = answer.getQuestionId();
        this.listAnswers = answer.getListAnswers();
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

    public List<String> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<String> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public List<ExamAnswerResponseData> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamAnswerResponseData> listQuestions) {
        this.listQuestions = listQuestions;
    }

}
