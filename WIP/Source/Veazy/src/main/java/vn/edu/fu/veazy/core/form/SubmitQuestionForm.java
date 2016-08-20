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
    private List<SubmitAnswerForm> listAnswers = new ArrayList<>();
    private List<SubmitQuestionForm> listQuestions = new ArrayList<>();

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

    public List<SubmitAnswerForm> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<SubmitAnswerForm> listAnswers) {
        this.listAnswers = listAnswers;
    }
    
}
