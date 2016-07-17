/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response.data;

import java.util.List;

/**
 *
 * @author Hoang Linh
 */
public class GetLearnerExamsResponseData {
    private List<ExamResponseData> exams;

    public GetLearnerExamsResponseData() {
    }

    public List<ExamResponseData> getExams() {
        return exams;
    }

    public void setExams(List<ExamResponseData> exams) {
        this.exams = exams;
    }
    
    public void addExam(ExamResponseData exam){
        exams.add(exam);
    }
}
