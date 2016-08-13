/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.form.ExamPartForm;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.response.BriefQuestionResponse;
import vn.edu.fu.veazy.core.response.ExamPartResponse;

/**
 *
 * @author Hoang Linh
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(QuestionBankServiceImpl.class);

    @Autowired
    private GenericDao<QuestionModel, Integer> questionDao;

    @Override
    @Transactional
    public List<ExamPartResponse> generateTest(Integer courseId, List<ExamPartForm> examPart) throws Exception {
        List<ExamPartResponse> result = new ArrayList<>();
        for (ExamPartForm part : examPart) {
            List<BriefQuestionResponse> partQues = new ArrayList<>();
            int time = genTest(partQues, part.getNumberOfQuestion(), courseId, part.getSkill());
            if (partQues.size() > 0) {
                result.add(new ExamPartResponse(part.getSkill(), time, partQues));
            }
        }
        return result;
    }
    
    private Integer genTest(
            List<BriefQuestionResponse> result,
            Integer questionNumber, Integer courseId, Integer examSkill) throws Exception {
        int eta = Const.EXAM_INSURANCE_TIME;
        try {
            QuestionModel question = new QuestionModel();

            question.setCourseId(courseId);
            question.setQuestionSkill(examSkill);
            question.setDeleteFlag(false);
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
                        if (randomQuestion.getNumberOfQuestion() <= (questionNumber - takedQuestionNumber)
                                && randomQuestion.getNumberOfQuestion() > 0) {
                            takedQuestionNumber += randomQuestion.getNumberOfQuestion();
                            letMeIn(result, randomQuestion);
                            eta += randomQuestion.getQuestionEtaTime();
                        }
                    }
                    if (takedQuestionNumber == questionNumber) {
                        return eta;
                    }
                }
                //default generate
                Collections.sort(questions, question);
                int takedQuestionNumber = 0;
                for (int i = takedQuestionNumber;
                        (i < questionNumber && i < questions.size() && questions.size() > 0); i++) {
                    randomQuestion = questions.get(i);
                    if (randomQuestion.getNumberOfQuestion() <= (questionNumber - takedQuestionNumber)
                            && randomQuestion.getNumberOfQuestion() > 0) {
                        takedQuestionNumber += randomQuestion.getNumberOfQuestion();
                        letMeIn(result, randomQuestion);
                        eta += randomQuestion.getQuestionEtaTime();
                    }
                }
                return eta;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return eta;
    }

    /**
     * 
     * @param result
     * @param question
     * @throws Exception
     */
    private void letMeIn(List<BriefQuestionResponse> result, QuestionModel question) throws Exception {
        if (question.getQuestionType() == Const.QUESTIONTYPE_GROUP) {
            List<BriefQuestionResponse> myQues = new ArrayList<>();
            List<QuestionModel> questions = question.getListQuestions();
            for (QuestionModel q : questions) {
                List<AnswerModel> answer = q.getListAnswers();
                List<String> ans = new ArrayList<>();
                for (AnswerModel m : answer)
                    ans.add(m.getAnswer());
                myQues.add(new BriefQuestionResponse(q.getId(), q.getQuestion(), ans, q.getAttachment()));
            }
            result.add(new BriefQuestionResponse(question.getId(), question.getQuestion(), question.getAttachment(), myQues));
        } else if (question.getQuestionType() == Const.QUESTIONTYPE_SINGULAR) {
            List<AnswerModel> answer = question.getListAnswers();
            List<String> ans = new ArrayList<>();
            for (AnswerModel m : answer)
                ans.add(m.getAnswer());
            result.add(new BriefQuestionResponse(question.getId(), question.getQuestion(), ans, question.getAttachment()));
        }
    }
}
