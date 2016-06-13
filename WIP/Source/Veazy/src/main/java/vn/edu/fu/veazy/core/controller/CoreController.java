package vn.edu.fu.veazy.core.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Main controller class of Core module.
 * 
 * @author MinhNN
 */
@Controller("Core Controller")
public class CoreController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CoreController.class);

    /** Core module entry return page. */
    private static final String ENTRY_PAGE = "core/index";

    /**
     * Process request for Core module entry point.
     * 
     * @return path to view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
