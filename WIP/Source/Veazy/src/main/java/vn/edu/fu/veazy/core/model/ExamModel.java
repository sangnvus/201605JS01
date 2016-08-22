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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Hoang Linh
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`Exam`")
public class ExamModel extends BasicModel {

    @Column(name = "userId", nullable = false)
    private Integer userId;
    @Column(name = "courseId", nullable = false)
    private Integer courseId;
    @Column(name = "questionSkill", nullable = true)
    private Integer questionSkill;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "exam", orphanRemoval = true)
    @Column(name = "listQuestions", nullable = false)
    @Access(AccessType.PROPERTY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy
    private List<ExamQuestionModel> listQuestions = new ArrayList<>();
    @Column(name = "result", columnDefinition = "FLOAT4 DEFAULT 0.0", nullable = false)
    private Double result;
    @Column(name = "takenTime", columnDefinition = "INT DEFAULT 0", nullable = false)
    private Integer takenTime;
    @Column(name = "etaTime", columnDefinition = "INT DEFAULT 0", nullable = false)
    private Integer etaTime;
    @Column(name = "finishState", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private Boolean finishState;

    public ExamModel() {
    }

    public ExamModel(Integer userId, Integer courseId, Integer questionSkill, List<ExamQuestionModel> listQuestions,
            Double result, Integer takenTime, Integer etaTime, Boolean finishState) {
        super();
        this.userId = userId;
        this.courseId = courseId;
        this.questionSkill = questionSkill;
        this.listQuestions.clear();
        this.listQuestions.addAll(listQuestions);
        this.result = result;
        this.takenTime = takenTime;
        this.etaTime = etaTime;
        this.finishState = finishState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getQuestionSkill() {
        return questionSkill;
    }

    public void setQuestionSkill(Integer questionSkill) {
        this.questionSkill = questionSkill;
    }

    public List<ExamQuestionModel> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamQuestionModel> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Integer getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Integer takenTime) {
        this.takenTime = takenTime;
    }

    public Integer getEtaTime() {
        return etaTime;
    }

    public void setEtaTime(Integer etaTime) {
        this.etaTime = etaTime;
    }

    public Boolean getFinishState() {
        return finishState;
    }

    public void setFinishState(Boolean finishState) {
        this.finishState = finishState;
    }

}
