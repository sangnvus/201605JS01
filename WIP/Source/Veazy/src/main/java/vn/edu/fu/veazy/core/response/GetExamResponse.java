/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

import java.util.ArrayList;
import java.util.List;
import vn.edu.fu.veazy.core.model.ExamQuestionModel;
import vn.edu.fu.veazy.core.model.ExamModel;

/**
 *
 * @author Hoang Linh
 */
public class GetExamResponse {

    private Integer examId;
    private Integer courseId;
    private Integer questionSkill;
    private List<ExamQuestionResponse> listQuestions;
    private Double result;
    private Integer takenTime;
    private Integer etaTime;

    public GetExamResponse() {
    }

    public GetExamResponse(ExamModel exam) {
        this.examId = exam.getId();
        this.courseId = exam.getCourseId();
        this.questionSkill = exam.getQuestionSkill();
        this.result = exam.getResult();
        this.takenTime = exam.getTakenTime();
        this.etaTime = exam.getEtaTime();
        List<ExamQuestionModel> examQuestions = exam.getListQuestions();
        this.listQuestions = new ArrayList<>();
        for(ExamQuestionModel question: examQuestions){
            ExamQuestionResponse data = new ExamQuestionResponse(question);
            listQuestions.add(data);
        }
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
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

    public List<ExamQuestionResponse> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamQuestionResponse> listQuestions) {
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

}