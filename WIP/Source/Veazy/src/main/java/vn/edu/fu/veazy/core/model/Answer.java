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
@Table(name = "`Answer`")
public class Answer extends BasicModel {

    @Column(name = "answer", nullable = false)
    private String answer;
    @Column(name = "isBanned", columnDefinition="BOOLEAN DEFAULT FALSE", nullable = true)
    private Boolean isRight = false;

    public Answer() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }
}
