/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
public class GetQuestionResponseData {

    private Integer questionId;
    private Integer questionCode;
    private Integer questionAnswerType;
    private Integer questionType;
    private Integer questionSkill;
    private Integer courseId;
    private String question;
//    private Integer state;
    private Long createDate;
    private Long updateDate;

    public GetQuestionResponseData() {
    }

    public GetQuestionResponseData(QuestionModel question) {
        this.courseId = question.getCourseId();
        this.createDate = question.getCreateDate();
        this.question = question.getQuestion();
        this.questionAnswerType = question.getQuestionAnswerType();
        this.questionCode = question.getQuestionCode();
        this.questionId = question.getId();
        this.questionSkill = question.getQuestionSkill();
        this.questionType = question.getQuestionType();
//        this.state = question.getState();
        this.updateDate = question.getUpdateDate();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(Integer questionCode) {
        this.questionCode = questionCode;
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

    public Integer getQuestionSkill() {
        return questionSkill;
    }

    public void setQuestionSkill(Integer questionSkill) {
        this.questionSkill = questionSkill;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

//    public Integer getState() {
//        return state;
//    }
//
//    public void setState(Integer state) {
//        this.state = state;
//    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

}
