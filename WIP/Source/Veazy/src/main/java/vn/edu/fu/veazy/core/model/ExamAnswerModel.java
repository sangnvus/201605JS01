/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

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
@Table(name = "`ExamAnswer`")
public class ExamAnswerModel extends BasicModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="examquestionmodel_id")
    private ExamQuestionModel question;
    @Column(name = "answer", nullable = false)
    private String answer;
    @Column(name = "isRight", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = true)
    private Boolean isRight = false;
    @Column(name = "isSelected", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = true)
    private Boolean isSelected = false;

    public ExamAnswerModel() {
    }

    public ExamAnswerModel(String ans, Boolean isRight) {
        super();
        this.answer = ans;
        this.isRight = isRight;
    }

    public ExamAnswerModel(ExamQuestionModel question, String answer, Boolean isRight) {
        super();
        this.question = question;
        this.answer = answer;
        this.isRight = isRight;
    }

    public ExamAnswerModel(AnswerForm form) {
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

    public ExamQuestionModel getQuestion() {
        return question;
    }

    public void setQuestion(ExamQuestionModel question) {
        this.question = question;
    }

    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ExamAnswerModel ans = new ExamAnswerModel();
        ans.setAnswer(answer);
        ans.setIsRight(isRight);
        ans.setIsSelected(isSelected);
        return ans;
    }
}
