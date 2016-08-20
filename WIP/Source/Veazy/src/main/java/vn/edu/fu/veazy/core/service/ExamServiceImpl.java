/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.common.utils.Utils;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.exception.CorruptedFormException;
import vn.edu.fu.veazy.core.form.SubmitAnswerForm;
import vn.edu.fu.veazy.core.form.SubmitExamForm;
import vn.edu.fu.veazy.core.form.SubmitQuestionForm;
import vn.edu.fu.veazy.core.model.ExamAnswerModel;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.ExamQuestionModel;

/**
 *
 * @author Hoang Linh
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private GenericDao<ExamModel, Integer> examDao;

    @Override
    @Transactional
    public List<ExamModel> findLearnerExams(Integer learnerId) throws Exception {
        try {
            ExamModel exam = new ExamModel();
            //TODO need to check about getUserById method in dao
            exam.setUserId(learnerId);
            List<ExamModel> exams = examDao.findByExample(exam);
            return exams;
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void saveExam(ExamModel exam) throws Exception {
        try {
            examDao.save(exam);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public ExamModel findExamById(Integer id) throws Exception {
        try {
            ExamModel exam = examDao.findById(id);
            if (exam != null) {
                return exam;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public void updateExam(ExamModel exam) throws Exception {
        try {
            examDao.update(exam);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        
    }

    @Override
    @Transactional
    public ExamModel calcResult(SubmitExamForm form) throws Exception {
        ExamModel exam = findExamById(form.getExamId());
        if (exam == null) {
            throw new Exception("Exam not found");
        }

        //calculate result
        List<SubmitQuestionForm> listQuestion = form.getListQuestions();
        List<ExamQuestionModel> listOriginQuestion = exam.getListQuestions();
        Double[] result = calcResult(listQuestion, listOriginQuestion);
        Double examResult = 0d;
        if (result.length == 2 && result[1] > 0) {
            examResult = Utils.round(result[0]/result[1], 2) * 100;
        }
        exam.setResult(examResult);
//        exam.setTakenTime(0);
        exam.setTakenTime(form.getTakenTime());
        //save in case is new exam
        if (!exam.getFinishState()) {
            exam.setFinishState(true);
            updateExam(exam);
        }
        return exam;
    }
    
    private Double[] calcResult(List<SubmitQuestionForm> listUserQuestions,
            List<ExamQuestionModel> listOriginQuestions) {
        Double userRight = 0d;
        Double totalRight = 0d;
        Integer singleQuesChoice = 0;
        Integer singleQuesRight = 0;
        for (SubmitQuestionForm answerForm : listUserQuestions) {
            Integer questionId = answerForm.getQuestionId();
            for (ExamQuestionModel m : listOriginQuestions) {
                if (questionId == m.getQuestionId()) {
                    if (m.getQuestionType() == Const.QUESTIONTYPE_GROUP) {
                        Double[] result = calcResult(answerForm.getListQuestions(), m.getListQuestions());
                        userRight += result[0];
                        totalRight += result[1];
                    } else {
                        totalRight++;
                        List<ExamAnswerModel> listAnswers = m.getListAnswers();
                        List<SubmitAnswerForm> listUserAnswers = answerForm.getListAnswers();
                        if (listAnswers.size() != listUserAnswers.size()) {
                            throw new CorruptedFormException("Wrong answer size");
                        }
                        int index = 0;
                        singleQuesChoice = 0;
                        singleQuesRight = 0;
                        boolean failedQues = false;
                        for (ExamAnswerModel ansModel : listAnswers) {
                            if (ansModel.getIsRight()) {
                                if (listUserAnswers.get(index).getIsSelected() && !failedQues) {
                                    singleQuesChoice++;
                                    ansModel.setIsSelected(true);
                                }
                                singleQuesRight++;
                            } else {
                                if (listUserAnswers.get(index).getIsSelected()) {
                                    singleQuesChoice = 0;
                                    ansModel.setIsSelected(true);
                                    failedQues = true;
                                }
                            }
                            index++;
                        }
                        if (singleQuesRight > 0)
                            userRight += Utils.round(singleQuesChoice/singleQuesRight, 2);
                    }
                }
            }
        }
//        if (totalRight > 0) return new Utils.round(userRight/totalRight, 2) * 100;
        return new Double[] {userRight, totalRight};
    }

}
