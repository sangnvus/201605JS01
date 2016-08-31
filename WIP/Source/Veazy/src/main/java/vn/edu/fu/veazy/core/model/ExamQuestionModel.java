/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.util.ArrayList;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.AnswerForm;
import vn.edu.fu.veazy.core.form.QuestionForm;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`ExamQuestion`")
public class ExamQuestionModel extends BasicModel {

    @ManyToOne
    @JoinColumn(name = "exammodel_id")
    private ExamModel exam;
    @Column(name = "questionId", nullable = false)
    private Integer questionId;
    @Column(name = "question", columnDefinition = "TEXT", nullable = false)
    private String question;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "question", orphanRemoval = true)
    @Column(name = "listAnswers", nullable = false)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy
    private List<ExamAnswerModel> listAnswers = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parentQuestion", orphanRemoval = true)
    @Column(name = "listQuestions", nullable = false)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy
    private List<ExamQuestionModel> listQuestions = new ArrayList<>();
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="parentquestion_id")
    private ExamQuestionModel parentQuestion;
    @Column(name = "questionType", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionType;
    @Column(name = "numberOfQuestion", columnDefinition = "INT DEFAULT 1", nullable = false)
    // numberOfQuestion = 1 if Singular 
    // >1 if Group(the origin question)
    // =0 if is a question of Group
    private Integer numberOfQuestion;
    @Column(name = "questionAnswerType", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionAnswerType;
    @Column(name = "attachment", nullable = true)
    private String attachment;
    @Column(name = "isChanged", nullable = true)
    private Boolean isChanged;
    
    public ExamQuestionModel() {
    }
    
    public ExamQuestionModel(QuestionForm form) {
        updateProperty(form);
    }
    
    public void updateProperty(QuestionForm form) {
        this.questionId = form.getQuestionId();
        this.attachment = form.getAttachment();
        this.question = form.getQuestion();
        this.questionAnswerType = form.getQuestionAnswerType();
        this.questionType = form.getQuestionType();
        this.listAnswers.clear();
        if (form.getQuestionType() != Const.QUESTIONTYPE_GROUP) {
            List<AnswerForm> listAns = form.getListAnswers();
            if (listAns != null && listAns.size() > 1) {
                for (AnswerForm form1 : listAns) {
                    ExamAnswerModel model = new ExamAnswerModel();
                    model.setAnswer(form1.getAnswer());
                    model.setIsRight(form1.getIsRight());
                    model.setQuestion(this);
                    this.listAnswers.add(model);
                }
            }
            this.numberOfQuestion = 1;
        } else {
            List<QuestionForm> listQuestions = form.getListQuestions();
            for (QuestionForm form1 : listQuestions) {
                ExamQuestionModel model1 = new ExamQuestionModel(form1);
                model1.setQuestionAnswerType(form.getQuestionAnswerType());
                model1.setQuestionType(Const.QUESTIONTYPE_SINGULAR);
                model1.setNumberOfQuestion(0);
                model1.setParentQuestion(this);
                this.listQuestions.add(model1);
            }
            this.numberOfQuestion = listQuestions.size();
        }
    }

    public ExamModel getExam() {
        return exam;
    }

    public void setExam(ExamModel exam) {
        this.exam = exam;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setListAnswers(List<ExamAnswerModel> listAnswers) {
        this.listAnswers = listAnswers;

    }

    public List<ExamAnswerModel> getListAnswers() {
        return listAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public List<ExamQuestionModel> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamQuestionModel> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public ExamQuestionModel getParentQuestion() {
        return parentQuestion;
    }

    public void setParentQuestion(ExamQuestionModel parentQuestion) {
        this.parentQuestion = parentQuestion;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ExamQuestionModel q = new ExamQuestionModel();
        q.setAttachment(attachment);
        q.setIsChanged(isChanged);
        q.setNumberOfQuestion(numberOfQuestion);
        q.setQuestion(question);
        q.setQuestionAnswerType(questionAnswerType);
        q.setQuestionId(questionId);
        q.setQuestionType(questionType);
        if (q.getQuestionType() != Const.QUESTIONTYPE_GROUP) {
            List<ExamAnswerModel> listAns = this.getListAnswers();
            List<ExamAnswerModel> listAns1 = new ArrayList<>();
            for (ExamAnswerModel ans : listAns) {
                ExamAnswerModel eam1 = (ExamAnswerModel) ans.clone();
                eam1.setQuestion(q);
                listAns1.add(eam1);
            }
            q.setListAnswers(listAns1);
        } else {
            List<ExamQuestionModel> listQues = this.getListQuestions();
            List<ExamQuestionModel> listQues1 = new ArrayList<>();
            for (ExamQuestionModel ques : listQues) {
                ExamQuestionModel eqm1 = (ExamQuestionModel) ques.clone();
                eqm1.setParentQuestion(q);
                listQues1.add(eqm1);
            }
            q.setListQuestions(listQues1);
        }
        return q;
    }

}
