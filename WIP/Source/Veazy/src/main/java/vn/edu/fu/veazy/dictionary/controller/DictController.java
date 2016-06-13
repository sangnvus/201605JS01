package vn.edu.fu.veazy.dictionary.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Main controller class of Dictionary module.
 * 
 * @author MinhNN
 */
@Controller("Dictionary MainController")
public class DictController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DictController.class);

    /** Dictionary module entry return page. */
    private static final String ENTRY_PAGE = "dictionary/index";

    /**
     * Process request for Dictionary module entry point.
     * 
     * @return path to view
     */
    @RequestMapping(value = "/dictionary", method = RequestMethod.GET)
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
