package vn.edu.fu.veazy.core.service;

import java.util.List;
import vn.edu.fu.veazy.core.model.ExamModel;

public interface ExamService {
    public ExamModel saveExam(ExamModel exam) throws Exception;
    public List<ExamModel> findLearnerExams(Integer learnerId) throws Exception;
}
