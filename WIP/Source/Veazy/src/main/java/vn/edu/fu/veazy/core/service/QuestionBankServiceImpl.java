/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private GenericDao<QuestionModel, Integer> questionDao;

    @Override
    @Transactional
    public List<QuestionModel> generateTest(Integer questionNumber, Integer courseId, Integer examSkill) throws Exception {
        List<QuestionModel> result = new ArrayList<>();
        try {
            QuestionModel question = new QuestionModel();

            question.setCourseId(courseId);
            question.setQuestionSkill(examSkill);
            List<QuestionModel> questions = questionDao.findByExample(question);

            if (questions != null && questions.size() > 0) {
                int triedTime = 0;
                QuestionModel randomQuestion;
                Random randomizer = new Random();
                //try to random generate exam
                while (triedTime < 3) {
                    triedTime++;
                    int takedQuestionNumber = 0;
                    while (takedQuestionNumber < questionNumber && questions.size() > 0) {
                        randomQuestion = questions.remove(randomizer.nextInt(questions.size()));
                        if (randomQuestion.getNumberOfQuestion() < (questionNumber - takedQuestionNumber)) {
                            takedQuestionNumber += randomQuestion.getNumberOfQuestion();
                            getSingleQuestion(result, question);
                        }
                    }
                    if (takedQuestionNumber == questionNumber) {
                        return result;
                    }
                }
                //default generate
                Collections.sort(questions, question);
                int takedQuestionNumber = 0;
                while (takedQuestionNumber < questionNumber && questions.size() > 0) {
                    randomQuestion = questions.remove(0);
                    if (randomQuestion.getNumberOfQuestion() < (questionNumber - takedQuestionNumber)) {
                        takedQuestionNumber += randomQuestion.getNumberOfQuestion();
                        getSingleQuestion(result, question);
                    }
                }
                return result;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return result;
    }

    private void getSingleQuestion(List<QuestionModel> result, QuestionModel question) throws Exception {
        if (Objects.equals(question.getQuestionType(), Const.GROUP)) {
            List<Integer> questionIds = question.getContent();
            for (Integer id : questionIds) {
                QuestionModel findById = questionDao.findById(id);
                result.add(findById);
            }
        } else if (Objects.equals(question.getQuestionType(), Const.SINGULAR)) {
            result.add(question);
        }
    }
}
