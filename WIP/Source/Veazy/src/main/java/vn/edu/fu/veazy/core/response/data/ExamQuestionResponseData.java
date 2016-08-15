/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import java.util.ArrayList;
import java.util.List;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.AnswerForm;
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.ExamAnswerModel;
import vn.edu.fu.veazy.core.model.ExamQuestionModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
public class ExamQuestionResponseData {

    private Integer questionId;
    private Integer questionType;
    private String question;
    private List<ExamAnswerResponseData> listAnswers = new ArrayList<>();
    private List<ExamQuestionResponseData> listQuestions = new ArrayList<>();
    private Integer numberOfQuestion;
    private Integer questionAnswerType;
    private String attachment;
    private Boolean isChanged;

    public ExamQuestionResponseData() {
    }

    public ExamQuestionResponseData(ExamQuestionModel question) {
        this.questionId = question.getQuestionId();
        this.questionType = question.getQuestionType();
        this.question = question.getQuestion();
        this.numberOfQuestion = question.getNumberOfQuestion();
        this.questionAnswerType = question.getQuestionAnswerType();
        this.attachment = question.getAttachment();
        this.isChanged = question.getIsChanged();
        this.listQuestions.clear();
        this.listAnswers.clear();
        if (this.questionType == Const.QUESTIONTYPE_GROUP) {
            List<ExamQuestionModel> subQuestions = question.getListQuestions();
            if (subQuestions != null && !subQuestions.isEmpty()) {
                for (ExamQuestionModel q : subQuestions) {
                    listQuestions.add(new ExamQuestionResponseData(q));
                }
            }
        } else {
            List<ExamAnswerModel> listAns = question.getListAnswers();
            if (listAns != null && !listAns.isEmpty()) {
                for (ExamAnswerModel ans : listAns) {
                    listAnswers.add(new ExamAnswerResponseData(ans));
                }
            }
        }
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<ExamAnswerResponseData> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<ExamAnswerResponseData> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public List<ExamQuestionResponseData> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamQuestionResponseData> listQuestions) {
        this.listQuestions = listQuestions;
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