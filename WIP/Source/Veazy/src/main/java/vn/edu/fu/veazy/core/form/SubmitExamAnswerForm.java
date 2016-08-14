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
public class SubmitExamAnswerForm {

    private Integer time;
    private Integer courseId;
    private Integer questionSkill;
    private Boolean isRedo;
    private List<SubmitAnswerForm> listQuestions;

    public SubmitExamAnswerForm() {
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public Boolean getIsRedo() {
        return isRedo;
    }

    public void setIsRedo(Boolean isRedo) {
        this.isRedo = isRedo;
    }

    public List<SubmitAnswerForm> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<SubmitAnswerForm> listQuestions) {
        this.listQuestions = listQuestions;
    }

}
