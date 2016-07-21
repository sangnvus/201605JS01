package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.model.TaskModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.service.LessonService;
import vn.edu.fu.veazy.core.service.TaskService;
import vn.edu.fu.veazy.core.service.UserService;

@Controller("Task Controller")
public class TaskController {
	/**
     * Logger object .
     */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonController.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = Const.URLMAPPING_GET_TASK, method = RequestMethod.GET)
    public @ResponseBody
    String getTask(@PathVariable("task_id") Integer taskId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            TaskModel data = taskService.getTask(taskId);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("get task successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_GET_SENT_TASK, method = RequestMethod.GET)
    public @ResponseBody
    String getSentTasks(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);

            List<TaskModel> data = taskService.getSentTasks(user.getId());
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("get sent tasks successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_GET_RECEIVED_TASK, method = RequestMethod.GET)
    public @ResponseBody
    String getReceivedTasks(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);

            List<TaskModel> data = taskService.getReceivedTasks(user.getId());
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("get received successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_GET_ALL_TASK, method = RequestMethod.GET)
    public @ResponseBody
    String getAllTasks(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);

            List<TaskModel> data = taskService.getAllTasks(user.getId());
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("get all task successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
}
