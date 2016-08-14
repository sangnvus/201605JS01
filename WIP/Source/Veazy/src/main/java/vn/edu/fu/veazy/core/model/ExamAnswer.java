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
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`ExamAnswer`")
public class ExamAnswer extends BasicModel {

    @ManyToOne
    @JoinColumn(name = "exammodel_id")
    private ExamModel exam;
    @Column(name = "questionId", nullable = false)
    private Integer questionId;
    @Column(name = "question", columnDefinition = "TEXT", nullable = false)
    private String question;
    @ElementCollection
    @Access(AccessType.PROPERTY)
    @Column(name = "userAnswer", nullable = false)
    private List<String> userAnswer = new ArrayList<>();
    @ElementCollection
    @Access(AccessType.PROPERTY)
    @Column(name = "listAnswers", nullable = false)
    private List<String> listAnswers = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parentQuestion", orphanRemoval = true)
    @Column(name = "listQuestions", nullable = false)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ExamAnswer> listQuestions = new ArrayList<>();
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="examanswer_id")
    private ExamAnswer parentQuestion;
    @Column(name = "questionType", columnDefinition = "INT DEFAULT 1", nullable = false)
    private Integer questionType;
    @Column(name = "numberOfQuestion", columnDefinition = "INT DEFAULT 1", nullable = false)
    // numberOfQuestion = 1 if Singular 
    // >1 if Group(the origin question)
    // =0 if is a question of Group
    private Integer numberOfQuestion;

    public ExamAnswer() {
    }

    public ExamAnswer(ExamModel exam, Integer questionId, List<String> userAnswer, List<String> correctAnswer) {
        this.exam = exam;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.listAnswers = correctAnswer;
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

    public List<String> getUserAnswer() {
        return userAnswer;
    }

    public void setListAnswers(List<String> listAnswers) {
        this.listAnswers = listAnswers;

    }

    public List<String> getListAnswers() {
        return listAnswers;
    }

    public void setUserAnswer(List<String> userAnswer) {
        this.userAnswer = userAnswer;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public List<ExamAnswer> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamAnswer> listQuestions) {
        this.listQuestions = listQuestions;
    }

}
