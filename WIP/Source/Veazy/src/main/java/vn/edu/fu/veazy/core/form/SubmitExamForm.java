/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.form;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class SubmitExamForm {

    private Integer examId;
    private Integer takenTime;
//    private Integer courseId;
//    private Integer questionSkill;
//    private Boolean isRedo;
    private List<SubmitQuestionForm> listQuestions = new ArrayList<>();

    public SubmitExamForm() {
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Integer takenTime) {
        this.takenTime = takenTime;
    }

    public List<SubmitQuestionForm> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<SubmitQuestionForm> listQuestions) {
        this.listQuestions = listQuestions;
    }

}
