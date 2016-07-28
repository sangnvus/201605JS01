/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class QuestionForm {

    private Integer questionAnswerType;
    private Integer questionType;
    private Integer questionSkill;
    private Integer courseId;
    private Integer numberOfQuestion;
    private String question;
    private List<AnswerForm> listAnswers = new LinkedList();
    private List<QuestionForm> listQuestions = new LinkedList();
    private String attachment;

    public QuestionForm() {
    }

    public Integer getQuestionAnswerType() {
        return questionAnswerType;
    }

    public void setQuestionAnswerType(Integer questionAnswerType) {
        this.questionAnswerType = questionAnswerType;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionSkil() {
        return questionSkill;
    }

    public void setQuestionSkil(Integer questionSkil) {
        this.questionSkill = questionSkil;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerForm> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<AnswerForm> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public List<QuestionForm> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<QuestionForm> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    
    
}
