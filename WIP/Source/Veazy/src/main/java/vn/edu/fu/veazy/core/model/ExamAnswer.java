/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

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
    @JoinColumn(name="exammodel_id")
    private ExamModel question;
    @Column(name = "userAnswer", nullable = false)
    private String userAnswer;

    public ExamAnswer() {
    }

    public ExamAnswer(ExamModel question, String UserAnswer) {
        this.question = question;
        this.userAnswer = UserAnswer;
    }

    public ExamModel getQuestionId() {
        return question;
    }

    public void setQuestionId(ExamModel question) {
        this.question = question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String UserAnswer) {
        this.userAnswer = UserAnswer;
    }

}
