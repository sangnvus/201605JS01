/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import java.util.ArrayList;
import java.util.List;
import vn.edu.fu.veazy.core.model.ExamAnswer;
import vn.edu.fu.veazy.core.model.ExamModel;

/**
 *
 * @author Hoang Linh
 */
public class GetExamResponseData {

    private Integer courseId;
    private Integer questionSkill;
    private List<ExamAnswerResponseData> listQuestions;
    private Double result;
    private Integer time;

    public GetExamResponseData() {
    }

    public GetExamResponseData(ExamModel exam) {
        this.courseId = exam.getCourseId();
        this.questionSkill = exam.getQuestionSkill();
        this.result = exam.getResult();
        this.time = exam.getTime();
        List<ExamAnswer> examAnswers = exam.getListQuestions();
        this.listQuestions = new ArrayList<>();
        for(ExamAnswer answer: examAnswers){
            ExamAnswerResponseData data = new ExamAnswerResponseData(answer);
            listQuestions.add(data);
        }
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

    public List<ExamAnswerResponseData> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<ExamAnswerResponseData> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}
