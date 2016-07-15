package vn.edu.fu.veazy.core.controller;

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
import vn.edu.fu.veazy.core.form.ReportLessonForm;
import vn.edu.fu.veazy.core.form.UpdateLessonForm;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;
import vn.edu.fu.veazy.core.response.LessonOfCourseResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.service.LessonService;


@Controller("Lesson Controller")
public class LessonController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonController.class);
    
    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = Const.URLMAPPING_CREATE_LESSON, method = RequestMethod.POST)
    public @ResponseBody
    String createLesson(@ModelAttribute("create-lesson-form") CreateLessonForm form) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	String requesterId = null;
        	//TODO get requester id
            CreateLessonResponse data =  lessonService.createLesson(requesterId, form);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_GET_LESSON_VERSION, method = RequestMethod.GET)
    public @ResponseBody
    String getLessonVersion(@PathVariable("lesson_id") String lessonId,@PathVariable("version") Integer version) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	GetLessonVersionResponse data = lessonService.getLessonVersion(lessonId, version);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_UPDATE_LESSON, method = RequestMethod.POST)
    public @ResponseBody
    String updateLesson(@ModelAttribute("update-lesson-form") UpdateLessonForm form,@PathVariable("lesson_id") String lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	String requesterId = null;
        	//TODO get requester id
        	lessonService.updateLesson(requesterId, form);
            response.setCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_PUBLISH_LESSON, method = RequestMethod.POST)
    public @ResponseBody
    String publishLesson(@PathVariable("lesson_id") String lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	String requesterId = null;
        	//TODO get requester id
        	lessonService.publishLessonVersion(requesterId, lessonId);
            response.setCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_REPORT_LESSON, method = RequestMethod.POST)
    public @ResponseBody
    String reportLesson(@ModelAttribute("report-lesson-form") ReportLessonForm form,@PathVariable("lesson_id") String lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	String requesterId = null;
        	//TODO get requester id
        	lessonService.reportLesson(requesterId, lessonId, form.getContent());
            response.setCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_GET_LESSON_COURSE, method = RequestMethod.GET)
    public @ResponseBody
    String getLessonOfCourse(@PathVariable("course_id") String courseId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	List<LessonOfCourseResponse> data = lessonService.getLessonsOfCourse(courseId);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
    @RequestMapping(value = Const.URLMAPPING_GET_LESSON, method = RequestMethod.GET)
    public @ResponseBody
    String getLesson(@PathVariable("lesson_id") String lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	GetLessonResponse data = lessonService.getLesson(lessonId);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
}
