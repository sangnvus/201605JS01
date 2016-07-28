package vn.edu.fu.veazy.core.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.response.GetCourseResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.service.CourseService;

/**
 * @author CuHo
 *
 */
@Controller("Course Controller")
public class CourseController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonController.class);
    
    @Autowired
    /** Course service*/
    private CourseService courseService;

    /**
     * 全部のレベルをとる
     * @return 返事のＪＳＯＮ
     */
    @RequestMapping(value = Const.URLMAPPING_GET_COURSES, method = RequestMethod.GET)
    public @ResponseBody
    String getCourses() {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            List<GetCourseResponse> data =  courseService.getCourses();
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            
            LOGGER.debug("Get courses successfully!");
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
 
}