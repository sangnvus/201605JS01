/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class GetLearnerExamsResponse {
    private List<ExamResultResponse> exams;

    public GetLearnerExamsResponse() {
    }

    public List<ExamResultResponse> getExams() {
        return exams;
    }

    public void setExams(List<ExamResultResponse> exams) {
        this.exams = exams;
    }
    
    public void addExam(ExamResultResponse exam){
        exams.add(exam);
    }
}
