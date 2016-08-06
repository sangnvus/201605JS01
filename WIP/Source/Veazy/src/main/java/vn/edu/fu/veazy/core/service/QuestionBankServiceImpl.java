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

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.controller.ExamController;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.form.ExamPartForm;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.response.BriefLessonResponse;
import vn.edu.fu.veazy.core.response.BriefQuestionResponse;
import vn.edu.fu.veazy.core.response.ExamPartResponse;

/**
 *
 * @author Hoang Linh
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    private GenericDao<QuestionModel, Integer> questionDao;

    @Override
    @Transactional
    public List<ExamPartResponse> generateTest(Integer courseId, List<ExamPartForm> examPart) throws Exception {
        List<ExamPartResponse> result = new ArrayList<>();
        for (ExamPartForm part : examPart) {
            List<BriefQuestionResponse> partQues
                    = genTest(part.getNumberOfQuestion(), courseId, part.getSkill());
            result.add(new ExamPartResponse(part.getSkill(), partQues));
        }
        return result;
    }
    
    private List<BriefQuestionResponse> genTest(Integer questionNumber, Integer courseId, Integer examSkill) throws Exception {
        List<BriefQuestionResponse> result = new ArrayList<>();
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
                        if (randomQuestion.getNumberOfQuestion() <= (questionNumber - takedQuestionNumber)) {
                            takedQuestionNumber += randomQuestion.getNumberOfQuestion();
                            letMeIn(result, randomQuestion);
                        }
                    }
                    if (takedQuestionNumber == questionNumber) {
                        return result;
                    }
                }
                //default generate
                Collections.sort(questions, question);
                int takedQuestionNumber = 0;
                while (takedQuestionNumber <= questionNumber && questions.size() > 0) {
                    randomQuestion = questions.remove(0);
                    if (randomQuestion.getNumberOfQuestion() < (questionNumber - takedQuestionNumber)) {
                        takedQuestionNumber += randomQuestion.getNumberOfQuestion();
                        letMeIn(result, randomQuestion);
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

    /**
     * 
     * @param result
     * @param question
     * @throws Exception
     */
    private void letMeIn(List<BriefQuestionResponse> result, QuestionModel question) throws Exception {
        if (question.getQuestionType() == Const.QUESTIONTYPE_GROUP) {
            List<Integer> questionIds = question.getContent();
            for (Integer id : questionIds) {
                QuestionModel findById = questionDao.findById(id);
                List<AnswerModel> answer = findById.getListAnswers();
                List<String> ans = new ArrayList<>();
                for (AnswerModel m : answer)
                    ans.add(m.getAnswer());
                result.add(new BriefQuestionResponse(findById.getQuestion(), ans, findById.getAttachment()));
            }
        } else if (question.getQuestionType() == Const.QUESTIONTYPE_SINGULAR) {
            List<AnswerModel> answer = question.getListAnswers();
            List<String> ans = new ArrayList<>();
            for (AnswerModel m : answer)
                ans.add(m.getAnswer());
            result.add(new BriefQuestionResponse(question.getQuestion(), ans, question.getAttachment()));
        }
    }
}
