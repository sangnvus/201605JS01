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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.StatsQuestionsResponse;
import vn.edu.fu.veazy.core.response.AddQuestionResponse;
import vn.edu.fu.veazy.core.response.QuestionResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
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
//@CrossOrigin
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
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_CREATE_QUESTION, method = RequestMethod.POST,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String createQuestion(@RequestBody QuestionForm form,
            Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to create question controller successful");
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            
            form.setCreatorId(user.getId());

            QuestionModel model = new QuestionModel(form);
//            if (model.getQuestionType() == Const.QUESTIONTYPE_GROUP) {
//                List<QuestionForm> listQuestions = form.getListQuestions();
//                model.setNumberOfQuestion(listQuestions.size());
//                LOGGER.debug("Out: " + listQuestions);
//            } else {
//                model.setNumberOfQuestion(1);
//            }
//            model = questionService.saveQuestion(model);
//            if (model.getQuestionType() == Const.QUESTIONTYPE_GROUP) {
//                List<QuestionForm> listQuestions = form.getListQuestions();
//                LOGGER.debug("list: " + listQuestions);
//                for (QuestionForm form1 : listQuestions) {
//                    QuestionModel model1 = new QuestionModel(form1);
//                    model1.setQuestionAnswerType(model.getQuestionAnswerType());
//                    model1.setQuestionSkill(model.getQuestionSkill());
//                    model1.setQuestionType(Const.QUESTIONTYPE_SINGULAR);
//                    model1.setCourseId(model.getCourseId());
//                    model1.setCreatorId(user.getId());
//                    model1.setNumberOfQuestion(0);
//                    model1.setParentQuestion(model);
//                    model1 = questionService.saveQuestion(model1);
//    //                content.add(model1.getId());
//                }
//            }
//            model.setContent(content);
            model = questionService.saveQuestion(model);
            AddQuestionResponse data = new AddQuestionResponse(model.getId());

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
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_UPDATE_QUESTION, method = RequestMethod.POST,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String updateQuestion(@RequestBody QuestionForm form,
            @PathVariable("question_id") Integer questionId, Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to update question controller successful");

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
//            if (!Objects.equals(user.getRole(), Const.EDITOR)) {
//                LOGGER.debug("user not allowed!");
//                response.setCode(ResponseCode.USER_NOT_ALLOW);
//                return response.toResponseJson();
//            }

            form.setQuestionId(questionId);
            form.setCreatorId(user.getId());
            question.updateProperty(form);
            //if (model.getQuestionType() == Const.SINGULAR)
//            if (Objects.equals(question.getQuestionType(), Const.QUESTIONTYPE_GROUP)) {
////                List<Integer> content = new ArrayList<>();
//                List<QuestionForm> listQuestions = form.getListQuestions();
//                for (QuestionForm form1 : listQuestions) {
//                    QuestionModel model1 = new QuestionModel(form1);
//                    model1.setQuestionAnswerType(question.getQuestionAnswerType());
//                    model1.setQuestionSkill(question.getQuestionSkill());
//                    model1.setQuestionType(question.getQuestionType());
//                    model1.setCreatorId(user.getId());
//                    model1.setNumberOfQuestion(0);
//                    model1.setParentQuestion(question);
//                    model1 = questionService.saveQuestion(model1);
////                    content.add(saveQuestion.getId());
//                }
////                question.setListQuestions(content);
//                question.setNumberOfQuestion(listQuestions.size());
//            } else {
//                question.setNumberOfQuestion(1);
//            }
            questionService.update(question);
            AddQuestionResponse data = new AddQuestionResponse(question.getId());

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
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_GET_LIST_QUESTIONS, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String getListQuestions() {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get list questions controller successful");
            List<QuestionModel> questions = questionService.findAllQuestion();

//            if (questions == null || questions.isEmpty()) {
//                LOGGER.debug("question not found!");
//                response.setCode(ResponseCode.QUESTION_NOT_FOUND);
//                return response.toResponseJson();
//            }
            List<QuestionResponse> datas = new ArrayList<>();
            if (questions != null && !questions.isEmpty()) {
                for (QuestionModel question : questions) {
                    if (question.getNumberOfQuestion() > 0) {
                        QuestionResponse data = new QuestionResponse(question);
                        datas.add(data);
                    }
                }
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
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_GET_QUESTION, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
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
            QuestionResponse data = new QuestionResponse(question);

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
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_GET_SIZE_QUESTION, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String getNumberQuestion() {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get number of question controller successful");
            
            int numberOfQuestions = questionService.size();
            
            StatsQuestionsResponse data = new StatsQuestionsResponse(numberOfQuestions);

            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Get number of question successfully!");

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
     * @param skillId url path
     * @return json string
     */
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_GET_QUESTION_OF_SKILL, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String getQuestionOfSkill(@PathVariable("skill_id") Integer skillId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get question controller successful");
            List<QuestionModel> questions = questionService.findQuestionBySkill(skillId);

//          if (questions == null || questions.isEmpty()) {
//              LOGGER.debug("question not found!");
//              response.setCode(ResponseCode.QUESTION_NOT_FOUND);
//              return response.toResponseJson();
//          }
          List<QuestionResponse> datas = new ArrayList<>();
          if (questions != null && !questions.isEmpty()) {
              for (QuestionModel question : questions) {
                  if (question.getNumberOfQuestion() > 0) {
                      QuestionResponse data = new QuestionResponse(question);
                      datas.add(data);
                  }
              }
          }
          response.setCode(ResponseCode.SUCCESS);
          response.setData(datas);

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
     * @param courseId url path
     * @return json string
     */
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_GET_QUESTION_OF_COURSE, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String getQuestionOfCourse(@PathVariable("course_id") Integer courseId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get question controller successful");
            List<QuestionModel> questions = questionService.findQuestionByCourse(courseId);

//          if (questions == null || questions.isEmpty()) {
//              LOGGER.debug("question not found!");
//              response.setCode(ResponseCode.QUESTION_NOT_FOUND);
//              return response.toResponseJson();
//          }
          List<QuestionResponse> datas = new ArrayList<>();
          if (questions != null && !questions.isEmpty()) {
              for (QuestionModel question : questions) {
                  if (question.getNumberOfQuestion() > 0) {
                      QuestionResponse data = new QuestionResponse(question);
                      datas.add(data);
                  }
              }
          }
          response.setCode(ResponseCode.SUCCESS);
          response.setData(datas);

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
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_DELETE_QUESTION, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String deleteQuestion(@PathVariable("question_id") Integer questionId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to delete question controller successful");
            QuestionModel question = questionService.findQuestionById(questionId);
            List<QuestionModel> questions = question.getListQuestions();
            if (questions != null && !questions.isEmpty()) {
                for (QuestionModel ques : questions) {
                    questionService.delete(ques);
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
}
