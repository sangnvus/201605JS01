/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import vn.edu.fu.veazy.core.controller.QuestionController;
import vn.edu.fu.veazy.core.form.AnswerForm;
import vn.edu.fu.veazy.core.form.QuestionForm;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Question`")
public class QuestionModel extends BasicModel implements Comparator<QuestionModel>{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(QuestionModel.class);

    @Column(name = "questionCode", columnDefinition="serial", nullable = false)
    @Generated(GenerationTime.INSERT)
    private Integer questionCode;
    @Column(name = "creatorId", nullable = false)
    private Integer creatorId;
    @Column(name = "questionAnswerType", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionAnswerType = 1;
    @Column(name = "questionType", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionType = 1;
    @Column(name = "questionSkill", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionSkill = 1;
    @Column(name = "numberOfQuestion", columnDefinition = "INT DEFAULT 1", nullable = false)
    // numberOfQuestion = 1 if Singular 
    // >1 if Group(the origin question)
    // =0 if is a question of Group
    private Integer numberOfQuestion = 1;
    @Column(name = "courseId", nullable = false)
    private Integer courseId;
    @Column(name = "question", nullable = false)
    private String question;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "question")
    @Column(name = "listAnswers", nullable = false)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AnswerModel> listAnswers = new ArrayList<>();
    @ElementCollection
    @Column(name = "content", nullable = true)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Integer> content = new ArrayList<>();
    @Column(name = "state", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer state = 1;
    @Column(name = "attachment", nullable = true)
    private String attachment;

    public QuestionModel() {
    }

    public QuestionModel(QuestionForm form) {
        updateProperty(form);
    }
    
    public void updateProperty(QuestionForm form) {
        this.attachment = form.getAttachment();
        this.courseId = form.getCourseId();
        this.listAnswers.clear();
        for (AnswerForm form1 : form.getListAnswers()) {
            AnswerModel model = new AnswerModel();
            model.setAnswer(form1.getAnswer());
            model.setIsRight(form1.getIsRight());
            model.setQuestion(this);
            this.listAnswers.add(model);
        }
        this.question = form.getQuestion();
        this.questionAnswerType = form.getQuestionAnswerType();
        this.questionSkill = form.getQuestionSkill();
        this.questionType = form.getQuestionType();
        this.numberOfQuestion = form.getNumberOfQuestion();
    }

    public Integer getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(Integer questionCode) {
        this.questionCode = questionCode;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public List<Integer> getContent() {
        return content;
    }

    public void setContent(List<Integer> content) {
        this.content = content;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public List<AnswerModel> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<AnswerModel> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public int compare(QuestionModel question1, QuestionModel question2) {
        return question1.getNumberOfQuestion().compareTo(question2.getNumberOfQuestion());
    }

}
