package vn.edu.fu.veazy.core.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Test controller class of Core module.
 * 
 * @author MinhNN
 */
@Controller("Test Controller")
public class TestController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    /** Core module entry return page. */
    private static final String TEST_PAGE = "core/test";

    /**
     * Process request for Core module test page.
     * 
     * @return path to view
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String viewTest(ModelMap model) {
        try {
            LOGGER.debug("Get to test successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return TEST_PAGE;
    }

}
