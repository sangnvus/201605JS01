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
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import vn.edu.fu.veazy.core.form.SubmitExamAnswerForm;

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
    private List<ExamAnswer> listQuestions = new ArrayList<>();
    @Column(name = "result", columnDefinition = "FLOAT4 DEFAULT 0.0", nullable = false)
    private Double result;
    @Column(name = "time", columnDefinition = "INT", nullable = false)
    private Integer time;

    public ExamModel() {
    }

    public ExamModel(SubmitExamAnswerForm form) {
        this.courseId = form.getCourseId();
        this.time = form.getTime();
        this.questionSkill = form.getQuestionSkill();
    }

    public ExamModel(SubmitExamAnswerForm form, Integer userId) {
        this.userId = userId;
        this.courseId = form.getCourseId();
        this.time = form.getTime();
        this.questionSkill = form.getQuestionSkill();
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<ExamAnswer> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamAnswer> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

}
