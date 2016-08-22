package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.form.ExamPartForm;
import vn.edu.fu.veazy.core.response.ExamPartResponse;

/**
 *
 * @author Hoang Linh
 */
public interface QuestionBankService {

    public List<ExamPartResponse> generateExam(Integer userId, Integer courseId, List<ExamPartForm> examPart)
            throws Exception;
}
