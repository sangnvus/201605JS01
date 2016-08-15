/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

import java.util.ArrayList;
import java.util.List;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
public class QuestionForm {

    private Integer questionId;
    private Integer questionAnswerType;
    private Integer questionType;
    private Integer questionSkill;
    private Integer courseId;
    private Integer creatorId;
    private Integer numberOfQuestion;
    private Integer etaTime;
    private String question;
    private List<AnswerForm> listAnswers = new ArrayList<>();
    private List<QuestionForm> listQuestions = new ArrayList<>();
    private String attachment;

    public QuestionForm() {
    }
    
    public QuestionForm(QuestionModel question) {
        super();
        this.questionId = question.getId();
        this.questionAnswerType = question.getQuestionAnswerType();
        this.questionType = question.getQuestionType();
        this.questionSkill = question.getQuestionSkill();
        this.courseId = question.getCourseId();
        this.creatorId = question.getCreatorId();
        this.numberOfQuestion = question.getNumberOfQuestion();
        this.etaTime = question.getQuestionEtaTime();
        this.question = question.getQuestion();
        this.attachment = question.getAttachment();
        this.listAnswers.clear();
        if (question.getQuestionType() != Const.QUESTIONTYPE_GROUP) {
            List<AnswerModel> listAns = question.getListAnswers();
            if (listAns != null && listAns.size() > 1) {
                for (AnswerModel form1 : listAns) {
                    AnswerForm form = new AnswerForm();
                    form.setAnswer(form1.getAnswer());
                    form.setIsRight(form1.getIsRight());
                    this.listAnswers.add(form);
                }
            }
            this.numberOfQuestion = 1;
        } else {
            List<QuestionModel> listQuestions = question.getListQuestions();
            for (QuestionModel model1 : listQuestions) {
                QuestionForm quesForm = new QuestionForm(model1);
                quesForm.setQuestionAnswerType(question.getQuestionAnswerType());
                quesForm.setQuestionSkill(question.getQuestionSkill());
                quesForm.setQuestionType(Const.QUESTIONTYPE_SINGULAR);
                quesForm.setCourseId(question.getCourseId());
                quesForm.setNumberOfQuestion(0);
                quesForm.setCreatorId(question.getCreatorId());
                this.listQuestions.add(quesForm);
            }
            this.numberOfQuestion = listQuestions.size();
        }
    }

    public QuestionForm(Integer questionId, Integer questionAnswerType, Integer questionType, Integer questionSkill,
            Integer courseId, Integer creatorId, Integer numberOfQuestion, Integer etaTime, String question,
            List<AnswerForm> listAnswers, List<QuestionForm> listQuestions, String attachment) {
        super();
        this.questionId = questionId;
        this.questionAnswerType = questionAnswerType;
        this.questionType = questionType;
        this.questionSkill = questionSkill;
        this.courseId = courseId;
        this.creatorId = creatorId;
        this.numberOfQuestion = numberOfQuestion;
        this.etaTime = etaTime;
        this.question = question;
        this.listAnswers = listAnswers;
        this.listQuestions = listQuestions;
        this.attachment = attachment;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public Integer getEtaTime() {
        return etaTime;
    }

    public void setEtaTime(Integer etaTime) {
        this.etaTime = etaTime;
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
