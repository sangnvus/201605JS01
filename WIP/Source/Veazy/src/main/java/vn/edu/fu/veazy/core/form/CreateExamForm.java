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
public class CreateExamForm {

    private Integer courseId;
    private List<ExamPartForm> parts;

    public CreateExamForm() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<ExamPartForm> getParts() {
        return parts;
    }

    public void setParts(List<ExamPartForm> parts) {
        this.parts = parts;
    }
    

}
