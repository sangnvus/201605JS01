/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
    @Column(name = "userAnswer", nullable = false)
    private List<String> userAnswer = new ArrayList<>();
    @Column(name = "correctAnswer", nullable = false)
    private List<String> correctAnswer = new ArrayList<>();

    public ExamAnswer() {
    }

    public ExamAnswer(ExamModel exam, Integer questionId, List<String> userAnswer, List<String> correctAnswer) {
        this.exam = exam;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
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

    public void setUserAnswer(List<String> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
