/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class CreateExamSinglePartForm {

    private Integer courseId;
    private Integer skill;
    private Integer numberOfQuestion;
//    private List<ExamPartForm> parts;

    public CreateExamSinglePartForm() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        this.skill = skill;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

//    public List<ExamPartForm> getParts() {
//        return parts;
//    }
//
//    public void setParts(List<ExamPartForm> parts) {
//        this.parts = parts;
//    }
    

}
