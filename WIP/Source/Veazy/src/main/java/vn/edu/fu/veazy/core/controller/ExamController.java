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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.common.utils.Utils;
import vn.edu.fu.veazy.core.form.CreateExamSinglePartForm;
import vn.edu.fu.veazy.core.form.ExamPartForm;
import vn.edu.fu.veazy.core.form.SubmitAnswerForm;
import vn.edu.fu.veazy.core.form.SubmitExamAnswerForm;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.ExamAnswer;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.ExamSinglePartResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.data.GetExamResponseData;
import vn.edu.fu.veazy.core.response.data.QuestionResponseData;
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
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

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
    @PreAuthorize("permitAll()")
    @RequestMapping(value = Const.URLMAPPING_CREATE_EXAM, method = RequestMethod.POST,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String createExamSinglePart(@RequestBody CreateExamSinglePartForm form,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to create exam controller successful");

            if (principal != null) {
                String userName = principal.getName();
                UserModel user = userService.findUserByUsername(userName);
                if (user == null) {
                    LOGGER.debug("user not found!");
                    response.setCode(ResponseCode.USER_NOT_FOUND);
                    return response.toResponseJson();
                }
            }
            Integer courseId = form.getCourseId();
            //generate Question Bank
            List<ExamPartForm> listParts = new ArrayList<>();
            ExamPartForm part = new ExamPartForm();
            part.setSkill(form.getSkill());
            part.setNumberOfQuestion(form.getNumberOfQuestion());
            listParts.add(part);
            ExamSinglePartResponse resp = new ExamSinglePartResponse(courseId,
                    questionBankService.generateTest(courseId, listParts));

            response.setCode(ResponseCode.SUCCESS);
            response.setData(resp);
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PreAuthorize("permitAll()")
    @RequestMapping(value = Const.URLMAPPING_SUBMIT_EXAM_ANSWER, method = RequestMethod.POST,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String submitExamAnswer(@RequestBody SubmitExamAnswerForm form,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to submit exam answer controller successful");
            
            UserModel user = null;

            if (principal != null) {
                String userName = principal.getName();
                user = userService.findUserByUsername(userName);
                if (user == null) {
                    LOGGER.debug("user not found!");
                    response.setCode(ResponseCode.USER_NOT_FOUND);
                    return response.toResponseJson();
                }
            }
            ExamModel exam = new ExamModel(form);
            List<ExamAnswer> listQuestions = new ArrayList<>();

            //calculate result
            double rightAnswer = 0;
            List<SubmitAnswerForm> listQuestion = form.getListQuestions();
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
                List<String> userAnswers = answerForm.getListAnswers();
                boolean isEquals = new HashSet(correctAnswers).equals(new HashSet(userAnswers));
                if (isEquals) {
                    rightAnswer++;
                }
                ExamAnswer answer = new ExamAnswer(exam, questionId, userAnswers, correctAnswers);
                listQuestions.add(answer);
            }
            exam.setResult(Utils.round(rightAnswer / numberOfQuestion, 2) * 100);
            exam.setListQuestions(listQuestions);
            //save in case is new exam
            if (!form.getIsRedo() && user != null) {
                exam.setUserId(user.getId());
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
    @PreAuthorize("hasRole(3)")
    @RequestMapping(value = Const.URLMAPPING_GET_EXAM, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
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
    @PreAuthorize("hasRole(3)")
    @RequestMapping(value = Const.URLMAPPING_REDO_EXAM, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
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
            List<QuestionResponseData> datas = new ArrayList<>();
            List<ExamAnswer> listQuestions = exam.getListQuestions();
            for (ExamAnswer answer : listQuestions) {
                Integer questionId = answer.getQuestionId();
                QuestionModel question = questionService.findQuestionById(questionId);
                QuestionResponseData data = new QuestionResponseData(question);
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
