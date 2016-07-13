package vn.edu.fu.veazy.cpanel.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.edu.fu.veazy.cpanel.common.Const;

/**
 * Lesson controller class of Core module.
 * 
 * @author MinhNN
 */
@Controller("Lesson Management Controller")
public class LessonMgmtController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonMgmtController.class);

    /** Core module entry return page. */
    private static final String EDITOR_LESSON_DETAIL_PAGE = "cpanel/editor/lesson_detail";
    private static final String CM_LESSON_DETAIL_PAGE = "cpanel/cm/lesson_detail";

    /**
     * Process request for editor lesson details page.
     * 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_EDITOR_LESSON_DETAIL, method = RequestMethod.GET)
    public String viewEditorCpLessonDetail(ModelMap model, @PathVariable String lessonId) {
        try {
            // TODO check anthorize
            LOGGER.debug("Get to editor lesson detail successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return EDITOR_LESSON_DETAIL_PAGE;
    }

    /**
     * Process request for cm lesson details page.
     * 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_CM_LESSON_DETAIL, method = RequestMethod.GET)
    public String viewCmCpLessonDetail(ModelMap model, @PathVariable String lessonId) {
        try {
            LOGGER.debug("Get to cm lesson detail successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return CM_LESSON_DETAIL_PAGE;
    }

}
