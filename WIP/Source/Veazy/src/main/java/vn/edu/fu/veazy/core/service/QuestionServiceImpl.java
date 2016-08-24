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
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.ExamQuestionModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private GenericDao<QuestionModel, Integer> questionDao;

    @Autowired
    private GenericDao<AnswerModel, Integer> answerDao;

    @Autowired
    private GenericDao<ExamQuestionModel, Integer> examQuestionDao;
    
    @Override
    @Transactional
    public QuestionModel saveQuestion(QuestionModel question) throws Exception {
        try {
            questionDao.save(question);
//            List<QuestionModel> listSearchResult = questionDao.findByExample(question);
//            if (listSearchResult != null && listSearchResult.size() > 0) {
//                return listSearchResult.get(0);
//            }
            return question;
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
//        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public QuestionModel findQuestionById(Integer id) throws Exception {
        try {
            String sql = "select * from \"Question\" ques where ques.id = " + id
                    + " and deleteflag = false";
            List<QuestionModel> questions =
                    (List<QuestionModel>) answerDao.executeSql(sql, QuestionModel.class);
            if (questions != null && !questions.isEmpty()) {
                return questions.get(0);
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AnswerModel> findAnswerByQuestionId(Integer id) throws Exception {
        try {
            String sql = "select * from \"Answer\" ans where ans.questionmodel_id = " + id
                    + " and deleteflag = false";
            List<AnswerModel> listAns = (List<AnswerModel>) answerDao.executeSql(sql, AnswerModel.class);
            return listAns;
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public QuestionModel findQuestionByCode(Integer code) throws Exception {
        try {
            QuestionModel question = new QuestionModel();
            question.setQuestionCode(code);
            question.setDeleteFlag(false);
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

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<QuestionModel> findAllQuestion() throws Exception {
        try {
            String sql = "select * from \"Question\" ques where deleteflag = false";
            List<QuestionModel> questions =
                    (List<QuestionModel>) answerDao.executeSql(sql, QuestionModel.class);
            return questions;
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    
    }

    @Override
    @Transactional
    public void update(QuestionModel question) throws Exception {
        try {
			updateExamQuestion(question.getId());
            questionDao.update(question);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    private void updateExamQuestion(Integer questionId) throws Exception {
    	ExamQuestionModel sample = new ExamQuestionModel();
    	sample.setQuestionId(questionId);
    	List<ExamQuestionModel> examQuestions = examQuestionDao.findByExample(sample);
    	if(examQuestions != null && examQuestions.size()>0){
    		for (ExamQuestionModel model : examQuestions) {
				model.setIsChanged(true);
				examQuestionDao.update(model);
			}
    	}
	}

	@Override
    @Transactional
    public void delete(QuestionModel question) throws Exception {
        try {
        	updateExamQuestion(question.getId());
            question.setDeleteFlag(true);
            question.setDeleteDate(System.currentTimeMillis());
            questionDao.update(question);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public int size() throws Exception {
        return findAllQuestion().size();
    }

    @Override
    @Transactional
    public List<QuestionModel> findQuestionBySkill(Integer skill) throws Exception {
        try {
            QuestionModel question = new QuestionModel();
            question.setQuestionSkill(skill);
            question.setDeleteFlag(false);
            List<QuestionModel> listSearchResult = questionDao.findByExample(question);
            return listSearchResult;
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public List<QuestionModel> findQuestionByCourse(Integer courseId) throws Exception {
        try {
            QuestionModel question = new QuestionModel();
            question.setCourseId(courseId);
            question.setDeleteFlag(false);
            List<QuestionModel> listSearchResult = questionDao.findByExample(question);
            return listSearchResult;
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

	
}
