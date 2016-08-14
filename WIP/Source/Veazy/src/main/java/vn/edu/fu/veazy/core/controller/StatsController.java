package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import vn.edu.fu.veazy.core.service.StatsService;
import vn.edu.fu.veazy.core.service.UserService;

/**
 * Statistic controller class of Core module.
 * 
 * @author MinhNN
 */
@CrossOrigin(origins="http://localhost:3003")
@Controller("Statistic Controller")
public class StatsController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StatsController.class);
    
    @Autowired
    private StatsService statsService;
    
    @Autowired
    private UserService userService;

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

}
