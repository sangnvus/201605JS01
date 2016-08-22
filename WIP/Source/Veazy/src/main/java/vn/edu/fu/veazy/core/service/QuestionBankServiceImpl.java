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
import vn.edu.fu.veazy.core.form.AnswerForm;
import vn.edu.fu.veazy.core.form.ExamPartForm;
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.ExamQuestionModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.response.BriefAnswerResponse;
import vn.edu.fu.veazy.core.response.BriefQuestionResponse;
import vn.edu.fu.veazy.core.response.ExamPartResponse;

/**
 *
 * @author Hoang Linh
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private GenericDao<QuestionModel, Integer> questionDao;

    @Autowired
    private GenericDao<ExamModel, Integer> examDao;

    @Override
    @Transactional
    public List<ExamPartResponse> generateExam(Integer userId, Integer courseId,
            List<ExamPartForm> examPart) throws Exception {
        List<ExamPartResponse> result = new ArrayList<>();
        boolean offlineCheck = (userId == -1);
        for (ExamPartForm part : examPart) {
            List<BriefQuestionResponse> partQues = new ArrayList<>();
            List<ExamQuestionModel> partExamQues = new ArrayList<>();
            ExamModel exam = new ExamModel();
            exam.setCourseId(courseId);
            exam.setQuestionSkill(part.getSkill());
            exam.setResult(0d);
            exam.setTakenTime(0);
            exam.setFinishState(false);
            int time = genExam(partQues, part.getNumberOfQuestion(), courseId, part.getSkill(),
                    offlineCheck, exam, partExamQues);
            ExamPartResponse examPartResp = new ExamPartResponse(part.getSkill(), time);
            if (partQues.size() > 0) {
                examPartResp.setQuestions(partQues);
                if (partExamQues.size() > 0 && !offlineCheck) {
                    exam.setEtaTime(time);
                    exam.setListQuestions(partExamQues);
                    exam.setUserId(userId);
                    exam.setCreateDate(System.currentTimeMillis());
                    examDao.save(exam);
                    examPartResp.setExamId(exam.getId());
                }
                if (offlineCheck) {
                    examPartResp.setOfflineCheck(true);
                }
            }
            result.add(examPartResp);
        }
        return result;
    }
    
    private Integer genExam(
            List<BriefQuestionResponse> result,
            Integer questionNumber, Integer courseId, Integer examSkill, boolean offlineCheck,
            ExamModel ex, List<ExamQuestionModel> exam) throws Exception {
        int eta = 0;
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
                            letMeIn(result, randomQuestion, offlineCheck, ex, exam);
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
                        letMeIn(result, randomQuestion, offlineCheck, ex, exam);
                        eta += randomQuestion.getQuestionEtaTime();
                    }
                }
                return eta;
            }
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
        return eta + Const.EXAM_INSURANCE_TIME;
    }

    /**
     * 
     * @param result
     * @param question
     * @throws Exception
     */
    private void letMeIn(List<BriefQuestionResponse> result, QuestionModel question, boolean offlineCheck,
            ExamModel ex, List<ExamQuestionModel> exam) throws Exception {
        if (question.getQuestionType() == Const.QUESTIONTYPE_GROUP) {
            List<BriefQuestionResponse> myQues = new ArrayList<>();
            List<QuestionForm> myExamQues = new ArrayList<>();
            List<QuestionModel> questions = question.getListQuestions();
            for (QuestionModel q : questions) {
                List<AnswerModel> answer = q.getListAnswers();
                List<AnswerForm> ansForm = new ArrayList<>();
                List<BriefAnswerResponse> ans = new ArrayList<>();
                for (AnswerModel m : answer) {
                    BriefAnswerResponse ansResp = new BriefAnswerResponse();
                    ansResp.setAnswer(m.getAnswer());
                    ansForm.add(new AnswerForm(m.getAnswer(), m.getIsRight()));
                    if (offlineCheck) {
                        ansResp.setIsRight(m.getIsRight());
                    }
                    ans.add(ansResp);
                }
                myQues.add(new BriefQuestionResponse(q.getId(), q.getQuestion(),
                        ans, q.getAttachment()));
                QuestionForm form1 = new QuestionForm(q);
                form1.setListAnswers(ansForm);
                myExamQues.add(form1);
            }
            result.add(new BriefQuestionResponse(question.getId(), question.getQuestion(), question.getAttachment(), myQues));
            QuestionForm form1 = new QuestionForm(question);
            form1.setListQuestions(myExamQues);
            ExamQuestionModel m1 = new ExamQuestionModel(form1);
            m1.setExam(ex);
            exam.add(m1);
        } else if (question.getQuestionType() == Const.QUESTIONTYPE_SINGULAR) {
            List<AnswerModel> answer = question.getListAnswers();
            List<AnswerForm> ansForm = new ArrayList<>();
            List<BriefAnswerResponse> ans = new ArrayList<>();
            for (AnswerModel m : answer) {
                BriefAnswerResponse ansResp = new BriefAnswerResponse();
                ansResp.setAnswer(m.getAnswer());
                ansForm.add(new AnswerForm(m.getAnswer(), m.getIsRight()));
                if (offlineCheck) {
                    ansResp.setIsRight(m.getIsRight());
                }
                ans.add(ansResp);
            }
            result.add(new BriefQuestionResponse(question.getId(), question.getQuestion(),
                    ans, question.getAttachment()));
            QuestionForm form1 = new QuestionForm(question);
            form1.setListAnswers(ansForm);
            ExamQuestionModel m1 = new ExamQuestionModel(form1);
            m1.setExam(ex);
            exam.add(m1);
        }
    }
}
