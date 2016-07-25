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
import vn.edu.fu.veazy.core.form.CreateExamForm;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.data.GetQuestionDetailsResponseData;
import vn.edu.fu.veazy.core.service.ExamService;
import vn.edu.fu.veazy.core.service.QuestionBankService;
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

    /**
     *
     * @param form form submitted
     * @param principal
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_CREATE_QUESTION, method = RequestMethod.POST)
    public @ResponseBody
    String createExam(@ModelAttribute("create-exam-form") CreateExamForm form,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to create exam controller successful");

            ExamModel model = new ExamModel();
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            //generate Question Bank
            List<QuestionModel> questionModels = questionBankService.generateTest(form.getQuestionNumber(), form.getCourseId(), form.getExamSkill());            
            List<GetQuestionDetailsResponseData> datas = new ArrayList<>();
            for(QuestionModel questionModel: questionModels){
                GetQuestionDetailsResponseData data = new GetQuestionDetailsResponseData(questionModel);
                datas.add(data);
            }
            response.setCode(ResponseCode.SUCCESS);
            response.setData(datas);
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
