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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.AnswerForm;
import vn.edu.fu.veazy.core.form.QuestionForm;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Indexed
@Table(name = "`Question`")
public class QuestionModel extends BasicModel implements Comparator<QuestionModel>{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(QuestionModel.class);

    @Column(name = "questionCode", columnDefinition="serial", nullable = false)
    @Generated(GenerationTime.INSERT)
    private Integer questionCode;
    @Column(name = "creatorId", nullable = false)
    private Integer creatorId;
    @Column(name = "questionAnswerType", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionAnswerType;
    @Column(name = "questionType", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionType;
    @Column(name = "questionSkill", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionSkill;
    @Column(name = "numberOfQuestion", columnDefinition = "INT DEFAULT 1", nullable = false)
    // numberOfQuestion = 1 if Singular 
    // >1 if Group(the origin question)
    // =0 if is a question of Group
    private Integer numberOfQuestion;
    // in seconds
    @Column(name = "questionEtaTime", columnDefinition = "INT DEFAULT 60", nullable = false)
    private Integer questionEtaTime;
    @Column(name = "courseId", nullable = false)
    private Integer courseId;
    @Field(store = Store.YES, index = Index.YES, analyze = Analyze.YES)
    @Column(name = "question", columnDefinition = "TEXT", nullable = false)
    private String question;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "question", orphanRemoval = true)
    @Column(name = "listAnswers", nullable = false)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AnswerModel> listAnswers = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parentQuestion", orphanRemoval = true)
    @Column(name = "listQuestions", nullable = false)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<QuestionModel> listQuestions = new ArrayList<>();
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="parentquestion_id")
    private QuestionModel parentQuestion;
    @Column(name = "attachment", nullable = true)
    private String attachment;

    public QuestionModel() {
    }

    public QuestionModel(QuestionForm form) {
        updateProperty(form);
    }
    
    public void updateProperty(QuestionForm form) {
        this.id = form.getQuestionId();
        this.attachment = form.getAttachment();
        this.courseId = form.getCourseId();
        this.creatorId = form.getCreatorId();
        this.question = form.getQuestion();
        this.questionAnswerType = form.getQuestionAnswerType();
        this.questionSkill = form.getQuestionSkill();
        this.questionType = form.getQuestionType();
        this.questionEtaTime = form.getEtaTime();
        this.listAnswers.clear();
        if (form.getQuestionType() != Const.QUESTIONTYPE_GROUP) {
            List<AnswerForm> listAns = form.getListAnswers();
            if (listAns != null && listAns.size() > 1) {
                for (AnswerForm form1 : listAns) {
                    AnswerModel model = new AnswerModel();
                    model.setAnswer(form1.getAnswer());
                    model.setIsRight(form1.getIsRight());
                    model.setQuestion(this);
                    this.listAnswers.add(model);
                }
            }
            this.numberOfQuestion = 1;
        } else {
            List<QuestionForm> listQuestions = form.getListQuestions();
            boolean checkmates = false;
            for (QuestionForm form1 : listQuestions) {
                checkmates = false;
                for (QuestionModel q : this.listQuestions) {
                    if (q.getId() != null && q.getId() == form1.getQuestionId()) {
                        form1.setCreatorId(q.getCreatorId());
                        q.updateProperty(form1);
                        q.setQuestionEtaTime(0);
                        q.setQuestionAnswerType(form.getQuestionAnswerType());
                        q.setQuestionSkill(form.getQuestionSkill());
                        q.setCourseId(form.getCourseId());
                        q.setQuestionType(Const.QUESTIONTYPE_SINGULAR);
                        q.setNumberOfQuestion(0);
                        checkmates = true;
                        break;
                    }
                }
                if (!checkmates) {
                    QuestionModel model1 = new QuestionModel(form1);
                    model1.setQuestionEtaTime(0);
                    model1.setQuestionAnswerType(form.getQuestionAnswerType());
                    model1.setQuestionSkill(form.getQuestionSkill());
                    model1.setQuestionType(Const.QUESTIONTYPE_SINGULAR);
                    model1.setCourseId(form.getCourseId());
                    model1.setNumberOfQuestion(0);
                    model1.setCreatorId(form.getCreatorId());
                    model1.setParentQuestion(this);
                    this.listQuestions.add(model1);
                }
            }
            this.numberOfQuestion = listQuestions.size();
        }
//        this.numberOfQuestion = form.getNumberOfQuestion();
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

    public QuestionModel getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(QuestionModel parentQuestion) {
        this.parentQuestion = parentQuestion;
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

    public Integer getQuestionEtaTime() {
        return questionEtaTime;
    }

    public void setQuestionEtaTime(Integer questionEtaTime) {
        this.questionEtaTime = questionEtaTime;
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
    public List<QuestionModel> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<QuestionModel> listQuestions) {
        this.listQuestions = listQuestions;
    }

//    public Integer getState() {
//        return state;
//    }
//
//    public void setState(Integer state) {
//        this.state = state;
//    }

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

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

}
