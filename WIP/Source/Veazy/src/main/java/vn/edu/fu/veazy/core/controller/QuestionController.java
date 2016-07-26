/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.form.ReportForm;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.data.GetQuestionResponseData;
import vn.edu.fu.veazy.core.response.data.AddQuestionResponseData;
import vn.edu.fu.veazy.core.response.data.GetQuestionDetailsResponseData;
import vn.edu.fu.veazy.core.service.QuestionService;
import vn.edu.fu.veazy.core.service.ReportService;
import vn.edu.fu.veazy.core.service.UserService;

/**
 * <p>
 * Question Controller</p>
 *
 * <p>
 * This class contains all controllers related to question system such as
 * create, update, delete etc.</p>
 *
 * @author LinhNH
 *
 */
@CrossOrigin
@Controller("Core Question Controller")
public class QuestionController {

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
    private QuestionService questionService;
    @Autowired
    private ReportService reportService;

    /**
     *
     * @param form form submitted
     * @param principal
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_CREATE_QUESTION, method = RequestMethod.POST)
    public @ResponseBody
    String createQuestion(@ModelAttribute("question-form") QuestionForm form,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to create question controller successful");

            QuestionModel model = new QuestionModel(form);
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            model.setCreatorId(user.getId());
            //if (model.getQuestionType() == Const.SINGULAR)  
            model.setNumberOfQuestion(1);
            if (model.getQuestionType() == Const.GROUP) {
                List<Integer> content = new ArrayList<>();
                List<QuestionForm> listQuestions = form.getListQuestions();
                for (QuestionForm form1 : listQuestions) {
                    QuestionModel model1 = new QuestionModel(form1);
                    model1.setQuestionAnswerType(model.getQuestionAnswerType());
                    model1.setQuestionSkill(model.getQuestionSkill());
                    model1.setQuestionType(model.getQuestionType());
                    model1.setCreatorId(user.getId());
                    model1.setNumberOfQuestion(0);
                    QuestionModel saveQuestion = questionService.saveQuestion(model1);
                    content.add(saveQuestion.getId());
                }
                model.setContent(content);
                model.setNumberOfQuestion(listQuestions.size());
            }
            model = questionService.saveQuestion(model);
            AddQuestionResponseData data = new AddQuestionResponseData(model.getId());

            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Create question successfully!");

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
     * @param questionId url path
     * @param principal authentication
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_UPDATE_QUESTION, method = RequestMethod.POST)
    public @ResponseBody
    String updateQuestion(@ModelAttribute("question-form") QuestionForm form,
            @PathVariable("question_id") Integer questionId, Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to update question controller successful");

            QuestionModel model = new QuestionModel(form);
            QuestionModel question = questionService.findQuestionById(questionId);

            if (question == null) {
                LOGGER.debug("question not found!");
                response.setCode(ResponseCode.QUESTION_NOT_FOUND);
                return response.toResponseJson();
            }
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            if (!Objects.equals(user.getRole(), Const.EDITOR)) {
                LOGGER.debug("user not allowed!");
                response.setCode(ResponseCode.USER_NOT_ALLOW);
                return response.toResponseJson();
            }

            model.setId(question.getId());
            //if (model.getQuestionType() == Const.SINGULAR)  
            model.setNumberOfQuestion(1);
            if (Objects.equals(model.getQuestionType(), Const.GROUP)) {
                List<Integer> content = new ArrayList<>();
                List<QuestionForm> listQuestions = form.getListQuestions();
                for (QuestionForm form1 : listQuestions) {
                    QuestionModel model1 = new QuestionModel(form1);
                    model1.setQuestionAnswerType(model.getQuestionAnswerType());
                    model1.setQuestionSkill(model.getQuestionSkill());
                    model1.setQuestionType(model.getQuestionType());
                    model1.setCreatorId(user.getId());
                    model1.setNumberOfQuestion(0);
                    QuestionModel saveQuestion = questionService.saveQuestion(model1);
                    content.add(saveQuestion.getId());
                }
                model.setContent(content);
                model.setNumberOfQuestion(listQuestions.size());
            }
            questionService.update(model);
            AddQuestionResponseData data = new AddQuestionResponseData(model.getId());

            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Update question successfully!");

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
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_GET_LIST_QUESTIONS, method = RequestMethod.GET)
    public @ResponseBody
    String getListQuestions() {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get list questions controller successful");
            List<QuestionModel> questions = questionService.findAllQuestion();

            if (questions == null || questions.isEmpty()) {
                LOGGER.debug("question not found!");
                response.setCode(ResponseCode.QUESTION_NOT_FOUND);
                return response.toResponseJson();
            }
            List<GetQuestionResponseData> datas = new ArrayList<>();
            for (QuestionModel question : questions) {
                GetQuestionResponseData data = new GetQuestionResponseData(question);
                datas.add(data);
            }
            response.setCode(ResponseCode.SUCCESS);
            response.setData(datas);
            LOGGER.debug("get list questions successfully!");

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
     * @param questionId url path
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_GET_QUESTION, method = RequestMethod.GET)
    public @ResponseBody
    String getQuestion(@PathVariable("question_id") Integer questionId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get question controller successful");
            QuestionModel question = questionService.findQuestionById(questionId);
            if (question == null) {
                LOGGER.debug("question not found!");
                response.setCode(ResponseCode.QUESTION_NOT_FOUND);
                return response.toResponseJson();
            }
            List<QuestionModel> subQuestions = new ArrayList<>();
            List<Integer> questionIds = question.getContent();
            if (questionIds != null && !questionIds.isEmpty()) {
                for (Integer id : questionIds) {
                    QuestionModel q = questionService.findQuestionById(id);
                    subQuestions.add(q);
                }
            }
            GetQuestionDetailsResponseData data = new GetQuestionDetailsResponseData(question, subQuestions);

            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Get question successfully!");

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
     * @param questionId url path
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_DELETE_QUESTION, method = RequestMethod.GET)
    public @ResponseBody
    String deleteQuestion(@PathVariable("question_id") Integer questionId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to delete question controller successful");
            QuestionModel question = questionService.findQuestionById(questionId);
            List<Integer> questionIds = question.getContent();
            if (questionIds != null && !questionIds.isEmpty()) {
                for (Integer id : questionIds) {
                    QuestionModel q = questionService.findQuestionById(id);
                    questionService.delete(q);
                }
            }
            questionService.delete(question);
            response.setCode(ResponseCode.SUCCESS);
            LOGGER.debug("Delete question successfully!");

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
     * @param questionId url path
     * @param principal authentication
     * @param reportForm form submitted
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_DELETE_QUESTION, method = RequestMethod.GET)
    public @ResponseBody
    String reportQuestion(@PathVariable("question_id") Integer questionId,
            Principal principal, @ModelAttribute("report-form") ReportForm reportForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to report question controller successful");
            QuestionModel question = questionService.findQuestionById(questionId);
            if (question == null) {
                LOGGER.debug("question not found!");
                response.setCode(ResponseCode.QUESTION_NOT_FOUND);
                return response.toResponseJson();
            }
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            ReportModel report = new ReportModel(reportForm, user.getId(), questionId);
            reportService.saveReport(report);

            response.setCode(ResponseCode.SUCCESS);
            LOGGER.debug("Report question successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
}
