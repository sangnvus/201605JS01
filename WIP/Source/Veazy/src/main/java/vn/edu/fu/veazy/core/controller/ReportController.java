package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
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
     * レポートの詳細な内容をとる
     * @param reportId　レポートのＩＤ
     * @return　返事のＪＳＯＮ
     */
    @RequestMapping(value = Const.URLMAPPING_GET_REPORT, method = RequestMethod.GET)
    public @ResponseBody
    String getReport(@PathVariable("report_id") Integer reportId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            ReportModel data = reportService.getReport(reportId);
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
    @RequestMapping(value = Const.URLMAPPING_GET_ALL_REPORT, method = RequestMethod.GET)
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
     * 報告に読んだ状態をならせる
     * @param reportId 報告のＩＤ
     * @return　返事のＪＳＯＮ
     */
    @RequestMapping(value = Const.URLMAPPING_READ_REPORT, method = RequestMethod.GET)
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
    @RequestMapping(value = Const.URLMAPPING_DELETE_REPORT, method = RequestMethod.GET)
    public @ResponseBody
    String deleteReport(@PathVariable("report_id") Integer reportId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            reportService.deleteReport(reportId);
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

}
