package vn.edu.fu.veazy.cpanel.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fu.veazy.cpanel.common.Const;

/**
 * Main controller class of Cpanel module.
 * 
 * @author MinhNN
 */
@Controller("CPanel MainController")
public class CpanelController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CpanelController.class);

    /** Cpanel module entry return page. */
    private static final String USER_DASHBOARD_PAGE = "cpanel/user/index";
    private static final String EDITOR_DASHBOARD_PAGE = "cpanel/editor/index";
    private static final String CM_DASHBOARD_PAGE = "cpanel/cm/index";
    private static final String AD_DASHBOARD_PAGE = "cpanel/ad/index";

    /**
     * Process request for Cpanel module usercp dashboard.
     * 
     * @param model 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_USER_CP, method = RequestMethod.GET)
    public String viewUserCpDashboard(ModelMap model) {
        try {
            LOGGER.debug("Get to usercp dashboard successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return USER_DASHBOARD_PAGE;
    }

    /**
     * Process request for Cpanel module editorcp dashboard.
     * 
     * @param model 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_EDITOR_CP, method = RequestMethod.GET)
    public String viewEditorCpDashboard(ModelMap model) {
        try {
            LOGGER.debug("Get to editorcp dashboard successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return EDITOR_DASHBOARD_PAGE;
    }

    /**
     * Process request for Cpanel module cmcp dashboard.
     * 
     * @param model 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_CM_CP, method = RequestMethod.GET)
    public String viewCmCpDashboard(ModelMap model) {
        try {
            LOGGER.debug("Get to cmcp dashboard successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return CM_DASHBOARD_PAGE;
    }

    /**
     * Process request for Cpanel module admincp dashboard.
     * 
     * @param model 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_AD_CP, method = RequestMethod.GET)
    public String viewAdCpDashboard(ModelMap model) {
        try {
            LOGGER.debug("Get to admincp dashboard successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return AD_DASHBOARD_PAGE;
    }

}
