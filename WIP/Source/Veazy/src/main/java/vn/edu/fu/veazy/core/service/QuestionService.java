/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
public interface QuestionService {
    public QuestionModel saveQuestion(QuestionModel question) throws Exception;
    public QuestionModel findQuestionById(Integer id) throws Exception;
    public List<AnswerModel> findAnswerByQuestionId(Integer id) throws Exception;
    public QuestionModel findQuestionByCode(Integer code) throws Exception;
    public List<QuestionModel> findQuestionBySkill(Integer code) throws Exception;
    public List<QuestionModel> findQuestionByCourse(Integer code) throws Exception;
    public List<QuestionModel> findAllQuestion() throws Exception;
    public void update(QuestionModel question) throws Exception;
    public void delete(QuestionModel question) throws Exception;
    public int size() throws Exception;
}
