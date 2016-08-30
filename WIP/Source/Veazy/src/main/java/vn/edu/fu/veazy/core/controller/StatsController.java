package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.StatsCourseAvgResponse;
import vn.edu.fu.veazy.core.response.StatsLastExamResponse;
import vn.edu.fu.veazy.core.response.StatsSkillAvgResponse;
import vn.edu.fu.veazy.core.response.StatsUsersResponse;
import vn.edu.fu.veazy.core.service.ExamService;
import vn.edu.fu.veazy.core.service.LessonService;
import vn.edu.fu.veazy.core.service.QuestionService;
import vn.edu.fu.veazy.core.service.ReportService;
import vn.edu.fu.veazy.core.service.StatsService;
import vn.edu.fu.veazy.core.service.UserService;

/**
 * Statistic controller class of Core module.
 * 
 * @author MinhNN
 */
//@CrossOrigin(origins="http://localhost:3003")
@Controller("Statistic Controller")
public class StatsController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StatsController.class);
    
    @Autowired
    private StatsService statsService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private LessonService lessonService;
    
    @Autowired
    private ExamService examService;

    /**
     * Process request for statistic.
     * 
     * @return json
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_STATS_COURSE_AVG, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody String getCourseAvg(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to course average stats successfully");
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            
            List<StatsCourseAvgResponse> listAvg = statsService.getCourseAvg(user.getId());
            List<Double> p = new ArrayList<Double>();
            for (StatsCourseAvgResponse s : listAvg) {
                p.add(s.getAvgResult());
            }
            response.setCode(ResponseCode.SUCCESS);
            response.setData(p);
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * Process request for statistic.
     * 
     * @return json
     */
    @SuppressWarnings("rawtypes")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_STATS_LAST_EXAM, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody String getLastExam(Principal principal,
            @PathVariable("number") Integer number) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to course average stats successfully");
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            
            List<StatsLastExamResponse> listAvg = statsService.getLastExamResult(user.getId(), number);
            List<Double> results = new ArrayList<>();
            List<Long> dates = new ArrayList<>();
            HashMap<String, List> resp = new HashMap<>();
            resp.put("results", results);
            resp.put("dates", dates);
            for (StatsLastExamResponse last : listAvg) {
                results.add(last.getResult());
                dates.add(last.getDate());
            }
            response.setCode(ResponseCode.SUCCESS);
            response.setData(resp);
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * Process request for statistic.
     * 
     * @return json
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_STATS_SKILL_AVG, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody String getSkillAvg(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to course average stats successfully");
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            
            StatsSkillAvgResponse listAvg = statsService.getSkillAvg(user.getId());
            response.setCode(ResponseCode.SUCCESS);
            response.setData(listAvg);
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * Process request for statistic.
     * 
     * @return json
     */
    @SuppressWarnings("rawtypes")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_STATS_ALL, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody String getAllStats(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to all stats successfully");
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            
            Map<String, Object> res = new HashMap<>();
            
            StatsSkillAvgResponse listSkillAvg = statsService.getSkillAvg(user.getId());
            res.put("skill", listSkillAvg);
            
            List<StatsLastExamResponse> listLast = statsService.getLastExamResult(user.getId(), 10);
            List<Double> results = new ArrayList<>();
            List<Long> dates = new ArrayList<>();
            HashMap<String, List> resp = new HashMap<>();
            resp.put("results", results);
            resp.put("dates", dates);
            for (StatsLastExamResponse last : listLast) {
                results.add(last.getResult());
                dates.add(last.getDate());
            }
            res.put("last10", resp);
            
            List<StatsCourseAvgResponse> listCourseAvg = statsService.getCourseAvg(user.getId());
            List<Double> p = new ArrayList<Double>();
            for (StatsCourseAvgResponse s : listCourseAvg) {
                p.add(s.getAvgResult());
            }
            res.put("course", p);
            
            response.setCode(ResponseCode.SUCCESS);
            response.setData(res);
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * Process request for statistic.
     * 
     * @return json
     */
//    @PreAuthorize("hasAuthority(1)")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_STATS_ADMIN_ALL, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody String getAllAdminStats(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to all stats successfully");
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            
            Map<String, Object> res = new HashMap<>();

            int active = userService.countActive();
            int total = userService.size();
            StatsUsersResponse userSize = new StatsUsersResponse(active, total);
            res.put("users", userSize);
            
            int totalReport = reportService.getAllReports(user.getId()).size();
            res.put("reports", totalReport);
            
            int totalQues = questionService.size();
            res.put("questions", totalQues);
            
            int totalLes = lessonService.size();
            res.put("lessons", totalLes);
            
            int takenExams = examService.findLearnerExams(null).size();
            res.put("takenExams", takenExams);
            
            response.setCode(ResponseCode.SUCCESS);
            response.setData(res);
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

}
