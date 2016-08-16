package vn.edu.fu.veazy.core.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.service.LuceneService;

//@CrossOrigin(origins="http://localhost:3003")
/**
 * @author MinhNN
 *
 */
@Controller("Search Controller")
public class SearchController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    
    @Autowired
    /** Search service*/
    private LuceneService luceneService;

    @RequestMapping(value = Const.URLMAPPING_SEARCH_QUESTION, method = RequestMethod.GET,
            produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    String searchQuestion(@RequestParam(required = true) String text) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            List<QuestionModel> data =  luceneService.searchQuestion(text);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
            
            LOGGER.debug("Get search engine successfully!");
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
    
 
}