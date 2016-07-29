package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.form.ReportLessonForm;
import vn.edu.fu.veazy.core.form.UpdateLessonForm;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.BriefLessonResponse;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.service.LessonService;
import vn.edu.fu.veazy.core.service.UserService;

@CrossOrigin(origins="http://localhost:3003")
/**
 * @author CuHo
 *
 */
@Controller("Lesson Controller")
public class LessonController {

    /**
     * Logger object .
     */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonController.class);

    /**
     * lesson service
     */
    @Autowired
    private LessonService lessonService;
    
    /**
     * user service
     */
    @Autowired
    private UserService userService;

    /**
     * 新しいレッソンを作りのコントローラー
     * @param principal 要求する人
     * @param form 新しいレッソンを作りの形式
     * @return 返事のＪＳＯＮ
     */
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_CREATE_LESSON, method = RequestMethod.POST)
    public @ResponseBody
    String createLesson(Principal principal, @ModelAttribute("create-lesson-form") CreateLessonForm form) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            LOGGER.debug(userName);
            UserModel user = userService.findUserByUsername(userName);

            CreateLessonResponse data = lessonService.createLesson(user.getId(), form);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("Create new lesson successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * レッソンをアップデートのコントローラー
     * @param principal 要求する人
     * @param form レッソンをアップデートの形式
     * @param lessonId レッソンのＩＤ
     * @return 返事のＪＳＯＮ
     */
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_UPDATE_LESSON, method = RequestMethod.POST)
    public @ResponseBody
    String updateLesson(Principal principal, @ModelAttribute("update-lesson-form") UpdateLessonForm form, @PathVariable("lesson_id") Integer lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            form.setLessonId(lessonId);
            lessonService.updateLesson(user.getId(), form);
            response.setCode(ResponseCode.SUCCESS);

            LOGGER.debug("Update lesson successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * レッソンを出版のコントローラー
     * @param principal 要求する人
     * @param lessonId レッソンのＩＤ
     * @return 返事のＪＳＯＮ
     */
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_PUBLISH_LESSON, method = RequestMethod.GET)
    public @ResponseBody
    String publishLesson(Principal principal, @PathVariable("lesson_id") Integer lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            lessonService.publishLessonVersion(user.getId(), lessonId);
            response.setCode(ResponseCode.SUCCESS);

            LOGGER.debug("Publish lesson successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * レッソンを報告のコントローラー
     * @param principal 要求する人
     * @param form レッソンを報告の形式
     * @param lessonId レッソンのＩＤ
     * @return 返事のＪＳＯＮ
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_REPORT_LESSON, method = RequestMethod.POST)
    public @ResponseBody
    String reportLesson(Principal principal, @ModelAttribute("report-lesson-form") ReportLessonForm form, @PathVariable("lesson_id") Integer lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            form.setLessonId(lessonId);
            lessonService.reportLesson(user.getId(), lessonId, form.getContent());
            response.setCode(ResponseCode.SUCCESS);

            LOGGER.debug("Report lesson successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * レベルの全部のレッソンをとる
     * @param courseId　レベルのＩＤ
     * @return　返事のＪＳＯＮ
     */
    @RequestMapping(value = Const.URLMAPPING_GET_LESSON_COURSE, method = RequestMethod.GET)
    public @ResponseBody
    String getLessonOfCourse(@PathVariable("course_id") Integer courseId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            List<BriefLessonResponse> data = lessonService.getLessonsOfCourse(courseId);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Get lesson of course successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * レッソンの詳細な内容をとる
     * @param lessonId　レッソンのＩＤ
     * @return　返事のＪＳＯＮ
     */
    @RequestMapping(value = Const.URLMAPPING_GET_LESSON, method = RequestMethod.GET)
    public @ResponseBody
    String getLesson(@PathVariable("lesson_id") Integer lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            GetLessonResponse data = lessonService.getLesson(lessonId);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Get lesson successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_GET_ALL_LESSON, method = RequestMethod.GET)
    public @ResponseBody
    String getAllLesson() {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            List<BriefLessonResponse> data = lessonService.getAllLesson();
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Get lesson of course successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * レッソンのバージョンをとる
     * @param lessonId　レッソンのＩＤ
     * @param version　バージョンの名前
     * @return　返事のＪＳＯＮ
     */
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_GET_LESSON_VERSION, method = RequestMethod.GET)
    public @ResponseBody
    String getLessonVersion(@PathVariable("lesson_id") Integer lessonId, @PathVariable("version") Integer version) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            GetLessonVersionResponse data = lessonService.getLessonVersion(lessonId, version);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("Get lesson version successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    
    /**
     * レッソンを消す
     * @param lessonId　レッソンのＩＤ
     * @return　返事のＪＳＯＮ
     */
    @PreAuthorize("hasAuthority(2)")
    @RequestMapping(value = Const.URLMAPPING_DELETE_LESSON, method = RequestMethod.GET)
    public @ResponseBody
    String delete(@PathVariable("lesson_id") Integer lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            lessonService.deleteLesson(lessonId);
            response.setCode(ResponseCode.SUCCESS);
            LOGGER.debug("delete lesson version successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
	
}
