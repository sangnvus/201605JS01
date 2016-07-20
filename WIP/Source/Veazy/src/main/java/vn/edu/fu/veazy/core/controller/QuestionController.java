/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.data.QuestionResponseData;
import vn.edu.fu.veazy.core.service.QuestionService;
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
            QuestionResponseData data = new QuestionResponseData(model.getId());

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
}
