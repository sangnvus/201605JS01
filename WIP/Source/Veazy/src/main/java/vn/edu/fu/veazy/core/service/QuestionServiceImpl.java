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
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private GenericDao<QuestionModel, Integer> questionDao;

    @Override
    @Transactional
    public QuestionModel saveQuestion(QuestionModel question) throws Exception {
        try {
            questionDao.save(question);
            List<QuestionModel> listSearchResult = questionDao.findByExample(question);
            if (listSearchResult != null && listSearchResult.size() > 0) {
                return listSearchResult.get(0);
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public QuestionModel findQuestionById(Integer id) throws Exception {
        try {
            QuestionModel question = questionDao.findById(id);
            if (question != null) {
                return question;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public QuestionModel findQuestionByCode(String code) throws Exception {
        try {
            QuestionModel question = new QuestionModel();
            question.setQuestionCode(code);
            List<QuestionModel> listSearchResult = questionDao.findByExample(question);
            if (listSearchResult != null && listSearchResult.size() > 0) {
                return listSearchResult.get(0);
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<QuestionModel> findAllQuestion() throws Exception {
        try {
            List<QuestionModel> questions = questionDao.getAll();
            if (questions != null && questions.size() > 0) {
                return questions;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    
    }

    @Override
    @Transactional
    public void update(QuestionModel question) throws Exception {
        try {
            questionDao.update(question);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void delete(QuestionModel question) throws Exception {
        try {
            questionDao.delete(question);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public int size() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
