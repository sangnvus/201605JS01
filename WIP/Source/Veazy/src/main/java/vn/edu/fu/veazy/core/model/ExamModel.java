/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.util.List;
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
@Table(name = "`Exam`")
public class ExamModel extends BasicModel{

    @Column(name = "userId", nullable = false)
    private String userId;
    @Column(name = "courseId", nullable = false)
    private String courseId;
    @Column(name = "listQuestions", nullable = false)
    private List<ExamAnswer> listQuestions;
    @Column(name = "result", columnDefinition="DOUBLE DEFAULT 0.0",nullable = false)
    private Double result;

    public ExamModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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
