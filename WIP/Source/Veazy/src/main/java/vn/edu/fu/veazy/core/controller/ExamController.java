/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.CreateExamForm;
import vn.edu.fu.veazy.core.form.SubmitAnswerForm;
import vn.edu.fu.veazy.core.form.SubmitExamAnswerForm;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.ExamAnswer;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.data.GetExamResponseData;
import vn.edu.fu.veazy.core.response.data.GetQuestionDetailsResponseData;
import vn.edu.fu.veazy.core.service.ExamService;
import vn.edu.fu.veazy.core.service.QuestionBankService;
import vn.edu.fu.veazy.core.service.QuestionService;
import vn.edu.fu.veazy.core.service.UserService;

/**
 *
 * @author Hoang Linh
 */
@CrossOrigin
@Controller("Exam Controller")
public class ExamController {

    /**
     * Logger object
     */
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    /**
     * Question service instance
     */
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private ExamService examService;
    @Autowired
    private QuestionService questionService;

    /**
     *
     * @param form form submitted
     * @param principal
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_CREATE_EXAM, method = RequestMethod.POST)
    public @ResponseBody
    String createExam(@ModelAttribute("create-exam-form") CreateExamForm form,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to create exam controller successful");

            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            Integer courseId = form.getCourseId();
            //generate Question Bank
            List<QuestionModel> questionModels = questionBankService.generateTest(form.getQuestionNumber(), courseId, form.getExamSkill());
            List<GetQuestionDetailsResponseData> datas = new ArrayList<>();
            for (QuestionModel questionModel : questionModels) {
                GetQuestionDetailsResponseData data = new GetQuestionDetailsResponseData(questionModel);
                datas.add(data);
            }

            response.setCode(ResponseCode.SUCCESS);
            response.setData(datas);
            LOGGER.debug("Create exam successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     *
     * @param form form submitted
     * @param principal
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_SUBMIT_EXAM_ANSWER, method = RequestMethod.POST)
    public @ResponseBody
    String submitExamAnswer(@ModelAttribute("submit-exam-answer-form") SubmitExamAnswerForm form,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to submit exam answer controller successful");

            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            ExamModel exam = new ExamModel(form, user.getId());
            List<ExamAnswer> listQuestions = new ArrayList<>();

            //calculate result
            double rightAnswer = 0;
            List<SubmitAnswerForm> listQuestion = form.getListQuestion();
            Integer numberOfQuestion = listQuestion.size();
            for (SubmitAnswerForm answerForm : listQuestion) {
                Integer questionId = answerForm.getQuestionId();
                QuestionModel question = questionService.findQuestionById(questionId);
                List<AnswerModel> listAnswers = question.getListAnswers();
                List<String> correctAnswers = new ArrayList<>();
                for (AnswerModel answerModel : listAnswers) {
                    if (answerModel.getIsRight() == true) {
                        correctAnswers.add(answerModel.getAnswer());
                    }
                }
                List<String> userAnswers = answerForm.getUserAnswers();
                boolean isEquals = new HashSet(correctAnswers).equals(new HashSet(userAnswers));
                if (isEquals) {
                    rightAnswer++;
                }
                ExamAnswer answer = new ExamAnswer(exam, questionId, userAnswers, correctAnswers);
                listQuestions.add(answer);
            }
            exam.setResult(rightAnswer / numberOfQuestion);
            exam.setListQuestions(listQuestions);
            //save in case is new exam
            if (!form.getIsRedo()) {
                examService.saveExam(exam);
            }
            GetExamResponseData data = new GetExamResponseData(exam);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("submit exam successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     *
     * @param examId
     * @param principal
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_GET_EXAM, method = RequestMethod.GET)
    public @ResponseBody
    String getExamAnswer(@PathVariable("exam_id") Integer examId,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get exam answer controller successful");

            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            ExamModel exam = examService.findExamById(examId);
            if (!Objects.equals(user.getId(), exam.getUserId())) {
                LOGGER.debug("user not allow!");
                response.setCode(ResponseCode.USER_NOT_ALLOW);
                return response.toResponseJson();
            }
            GetExamResponseData data = new GetExamResponseData(exam);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("Get exam successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     *
     * @param examId
     * @param principal
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_GET_EXAM, method = RequestMethod.GET)
    public @ResponseBody
    String redoExam(@PathVariable("exam_id") Integer examId,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to redo exam controller successful");

            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            ExamModel exam = examService.findExamById(examId);
            // can't redo if not the creator of the exam
            if (!Objects.equals(user.getId(), exam.getUserId())) {
                LOGGER.debug("user not allow!");
                response.setCode(ResponseCode.USER_NOT_ALLOW);
                return response.toResponseJson();
            }
            List<GetQuestionDetailsResponseData> datas = new ArrayList<>();
            List<ExamAnswer> listQuestions = exam.getListQuestions();
            for (ExamAnswer answer : listQuestions) {
                Integer questionId = answer.getQuestionId();
                QuestionModel question = questionService.findQuestionById(questionId);
                GetQuestionDetailsResponseData data = new GetQuestionDetailsResponseData(question);
                datas.add(data);
            }
            response.setCode(ResponseCode.SUCCESS);
            response.setData(datas);
            LOGGER.debug("Redo exam successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
}
