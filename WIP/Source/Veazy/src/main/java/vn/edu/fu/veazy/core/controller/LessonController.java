package vn.edu.fu.veazy.core.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fu.veazy.core.common.Const;

/**
 * Lesson controller class of Core module.
 * 
 * @author MinhNN
 */
@Controller("Lesson Controller")
public class LessonController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonController.class);

    /** Core module entry return page. */
    private static final String LESSON_DETAIL_PAGE = "core/lesson_detail";

    /**
     * Process request for Core module lesson details page.
     * 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_LESSON_DETAIL, method = RequestMethod.GET)
    public String viewLessonDetail(ModelMap model, @PathVariable String lessonId) {
        try {
            LOGGER.debug("Get to lesson detail successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return LESSON_DETAIL_PAGE;
    }

}
