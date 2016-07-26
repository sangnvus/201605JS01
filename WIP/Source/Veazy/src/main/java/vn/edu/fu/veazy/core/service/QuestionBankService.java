package vn.edu.fu.veazy.core.service;

import java.util.List;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
public interface QuestionBankService {

    public List<QuestionModel> generateTest(Integer numberOfQuestion, Integer courseId, Integer questionType) throws Exception;
}
