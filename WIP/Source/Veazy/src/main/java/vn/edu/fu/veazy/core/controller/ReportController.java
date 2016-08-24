package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
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
import vn.edu.fu.veazy.core.form.ReportForm;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetReportDataResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.service.LessonService;
import vn.edu.fu.veazy.core.service.ReportService;
import vn.edu.fu.veazy.core.service.UserService;

@Controller("Report Controller")
public class ReportController {
	/**
     * Logger object .
     */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonController.class);

    /**
     * report service
     */
    @Autowired
    private ReportService reportService;
    
    /**
     * user service
     */
    @Autowired
    private UserService userService;

    
    /**
     * lesson service
     */
    @Autowired
    private LessonService lessonService;
    /**
     * レポートの詳細な内容をとる
     * @param reportId　レポートのＩＤ
     * @return　返事のＪＳＯＮ
     */
    @PreAuthorize("hasAnyAuthority(1,2)")
    @RequestMapping(value = Const.URLMAPPING_GET_REPORT, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String getReport(@PathVariable("report_id") Integer reportId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            ReportModel report = reportService.getReport(reportId);
            GetReportDataResponse data = new GetReportDataResponse(report);
            Integer lessonId = data.getLessonId();
            Integer senderId = data.getSenderId();
            if(lessonId != null){
                GetLessonResponse lesson = lessonService.getLesson(lessonId, false);
                data.setLesonTitle(lesson.getLessonTitle());
            }
            if(senderId != null){
                UserModel user = userService.findUserById(senderId);
                data.setUsername(user.getUserName());
            }
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("get report successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
    
    /**
     * 全部のレポートをとる
     * @param principal　要求する人
     * @return　返事のＪＳＯＮ
     */
    @PreAuthorize("hasAnyAuthority(1,2)")
    @RequestMapping(value = Const.URLMAPPING_GET_ALL_REPORT, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String getAllReport(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);

            List<ReportModel> data = reportService.getAllReports(user.getId());
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            LOGGER.debug("get all reports successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
    
    /**
     * 報告を読んだ状態に変更
     * @param reportId 報告のＩＤ
     * @return　返事のＪＳＯＮ
     */
    @PreAuthorize("hasAnyAuthority(1,2)")
    @RequestMapping(value = Const.URLMAPPING_READ_REPORT, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String readReport(@PathVariable("report_id") Integer reportId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            reportService.readReport(reportId);
            response.setCode(ResponseCode.SUCCESS);
            LOGGER.debug("make report as read successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
    
    /**
     * 報告を消す
     * @param reportId　報告のＩＤ
     * @return　返事のＪＳＯＮ
     */
    @PreAuthorize("hasAnyAuthority(1,2)")
    @RequestMapping(value = Const.URLMAPPING_DELETE_REPORT, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String deleteReport(Principal principal,@PathVariable("report_id") Integer reportId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            reportService.deleteReport(user.getId(),reportId);
            response.setCode(ResponseCode.SUCCESS);
            LOGGER.debug("delete report successfully!");

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
    @RequestMapping(value = Const.URLMAPPING_REPORT_LESSON, method = RequestMethod.POST,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String reportLesson(Principal principal, @RequestBody ReportForm reportForm,
            @PathVariable("lesson_id") Integer lessonId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            reportService.reportLesson(user.getId(), lessonId, reportForm.getContent());
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
    *
    * @param questionId url path
    * @param principal authentication
    * @param reportForm form submitted
    * @return json string
    */
   @PreAuthorize("isAuthenticated()")
   @RequestMapping(value = Const.URLMAPPING_REPORT_QUESTION, method = RequestMethod.POST,
           produces={"application/json; charset=UTF-8"})
   public @ResponseBody
   String reportQuestion(@PathVariable("question_id") Integer questionId,
           Principal principal, @RequestBody ReportForm reportForm) {
       Response response = new Response(ResponseCode.BAD_REQUEST);
       try {
    	   String userName = principal.getName();
           UserModel user = userService.findUserByUsername(userName);
           reportService.reportQuestion(user.getId(), questionId, reportForm.getContent());
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
}
