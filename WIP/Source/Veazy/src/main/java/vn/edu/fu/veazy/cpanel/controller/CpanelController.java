package vn.edu.fu.veazy.cpanel.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private static final String ENTRY_PAGE = "cpanel/index";

    /**
     * Process request for Cpanel module entry point.
     * 
     * @return path to view
     */
    @RequestMapping(value = "/cpanel", method = RequestMethod.GET)
    public String entryPoint() {
        try {
            LOGGER.debug("Get to entry successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return ENTRY_PAGE;
    }

}
