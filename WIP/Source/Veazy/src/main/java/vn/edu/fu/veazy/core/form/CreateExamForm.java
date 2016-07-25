/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

/**
 *
 * @author Hoang Linh
 */
public class CreateExamForm {

    private Integer courseId;
    private Integer examSkill;
    private Integer questionNumber;

    public CreateExamForm() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getExamSkill() {
        return examSkill;
    }

    public void setExamSkill(Integer examSkill) {
        this.examSkill = examSkill;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

}
