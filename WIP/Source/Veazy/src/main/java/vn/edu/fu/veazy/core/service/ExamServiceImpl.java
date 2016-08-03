/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.ExamModel;

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

}
