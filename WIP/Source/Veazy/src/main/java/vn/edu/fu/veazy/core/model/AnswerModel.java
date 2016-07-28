/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import vn.edu.fu.veazy.core.form.AnswerForm;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Answer`")
public class AnswerModel extends BasicModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="questionmodel_id")
    private QuestionModel question;
    @Column(name = "answer", nullable = false)
    private String answer;
    @Column(name = "isRight", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = true)
    private Boolean isRight = false;

    public AnswerModel() {
    }

    public AnswerModel(AnswerForm form) {
        super();
        this.answer = form.getAnswer();
        this.isRight = form.getIsRight();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionModel getQuestion() {
        return question;
    }

    public void setQuestion(QuestionModel question) {
        this.question = question;
    }

    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }
}
