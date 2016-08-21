/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class GetLearnerExamsResponse {
    private List<GetExamResponse> exams = new ArrayList<>();

    public GetLearnerExamsResponse() {
    }

    public List<GetExamResponse> getExams() {
        return exams;
    }

    public void setExams(List<GetExamResponse> exams) {
        this.exams = exams;
    }
    
    public void addExam(GetExamResponse exam){
        exams.add(exam);
    }
}
