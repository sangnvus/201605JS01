/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @Column(name = "questionId", nullable = false)
    private String questionId;
    @Column(name = "userAnswer", nullable = false)
    private String userAnswer;

    public ExamAnswer() {
    }

    public ExamAnswer(String QuestionId, String UserAnswer) {
        this.questionId = QuestionId;
        this.userAnswer = UserAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String QuestionId) {
        this.questionId = QuestionId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String UserAnswer) {
        this.userAnswer = UserAnswer;
    }

}
