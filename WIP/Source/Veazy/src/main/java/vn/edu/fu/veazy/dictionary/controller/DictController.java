package vn.edu.fu.veazy.dictionary.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.controller.LessonController;
import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.form.LookupForm;
import vn.edu.fu.veazy.core.response.LookupWordResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.dictionary.service.DictService;


@Controller("Dictionary Controller")
public class DictController {

	/**
     * Logger object .
     */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonController.class);

    @Autowired
    private DictService dictService;
    

    @RequestMapping(value = Const.URLMAPPING_LOOKUP_JAVI, method = RequestMethod.POST)
    public @ResponseBody
    String lookupJavi(@ModelAttribute("lookup-form") LookupForm form) {
    	Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	LOGGER.debug("look up javi key = " + form.getKey());
            List<LookupWordResponse> data = dictService.lookupJavi(form.getKey());
             
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("look up javi dictionary successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }


    
    @RequestMapping(value = Const.URLMAPPING_LOOKUP_VIJA, method = RequestMethod.POST)
    public @ResponseBody
    String lookupVija(@ModelAttribute("lookup-form") LookupForm form) {
    	Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	LOGGER.debug("look up vija key = " + form.getKey());
            List<LookupWordResponse> data = dictService.lookupVija(form.getKey());
             
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("look up vija dictionary successfully!");

            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
}
