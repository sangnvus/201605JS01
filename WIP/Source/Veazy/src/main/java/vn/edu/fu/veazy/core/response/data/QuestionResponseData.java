/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import java.util.ArrayList;
import java.util.List;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
public class QuestionResponseData {

    private Integer questionId;
    private Integer questionCode;
    private Integer questionAnswerType;
    private Integer questionType;
    private Integer questionSkill;
    private Integer courseId;
    private String question;
    private Integer numberOfQuestion;
    //    private Integer state;
    private Long createDate;
    private Long updateDate;
    private List<AnswerResponseData> listAnswers = new ArrayList<>();
    private List<QuestionResponseData> listQuestions = new ArrayList<>();

    public QuestionResponseData() {
    }

    public QuestionResponseData(QuestionModel question) {
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
        if (this.questionType == Const.QUESTIONTYPE_GROUP) {
            List<QuestionModel> subQuestions = question.getListQuestions();
            if (subQuestions != null && !subQuestions.isEmpty()) {
                for (QuestionModel q : subQuestions) {
                    listQuestions.add(new QuestionResponseData(q));
                }
            }
        } else {
            List<AnswerModel> listAns = question.getListAnswers();
            if (listAns != null && !listAns.isEmpty()) {
                for (AnswerModel ans : listAns) {
                    listAnswers.add(new AnswerResponseData(ans));
                }
            }
        }
    }

    public List<QuestionResponseData> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<QuestionResponseData> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public List<AnswerResponseData> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<AnswerResponseData> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
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
