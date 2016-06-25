/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.model;

import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class TestModel extends BasicModel{

    private String userId;
    private String courseId;
    private List<TestAnswer> listQuestions;
    private Double result;

    public TestModel() {
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

    public List<TestAnswer> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<TestAnswer> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

}
