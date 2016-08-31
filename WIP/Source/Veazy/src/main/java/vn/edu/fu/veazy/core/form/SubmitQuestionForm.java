/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class SubmitQuestionForm {
    private Integer questionId;
    private Integer questionType;
    private String question;
    private List<SubmitAnswerForm> listAnswers = new ArrayList<>();
    private List<SubmitQuestionForm> listQuestions = new ArrayList<>();
    private Integer numberOfQuestion;
    private Integer questionAnswerType;
    private String attachment;
    private Boolean isChanged;

    public List<SubmitQuestionForm> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<SubmitQuestionForm> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public SubmitQuestionForm() {
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public List<SubmitAnswerForm> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<SubmitAnswerForm> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public Integer getQuestionAnswerType() {
        return questionAnswerType;
    }

    public void setQuestionAnswerType(Integer questionAnswerType) {
        this.questionAnswerType = questionAnswerType;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Boolean getIsChanged() {
        return isChanged;
    }

    public void setIsChanged(Boolean isChanged) {
        this.isChanged = isChanged;
    }
    
}
