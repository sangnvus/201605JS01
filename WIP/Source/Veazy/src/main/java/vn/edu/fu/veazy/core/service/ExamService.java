package vn.edu.fu.veazy.core.service;

import java.util.List;
import vn.edu.fu.veazy.core.model.ExamModel;

public interface ExamService {
    public List<ExamModel> findLearnerExams(Integer learnerId) throws Exception;
}
