package vn.edu.fu.veazy.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.FileUploadForm;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.UploadFileResponse;
import vn.edu.fu.veazy.core.service.UserService;

/**
 * Main controller class of Core module.
 * 
 * @author MinhNN
 */
@CrossOrigin(origins="http://localhost:3003")
@Controller("Core Controller")
public class CoreController {

    /** Logger object . */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CoreController.class);
    
    @Autowired
    private ServletContext context;
    
    @Autowired
    private UserService userService;

    /**
     * Process request for Core module entry point.
     * 
     * @return path to view
     */
    @RequestMapping(value = Const.URLMAPPING_HOME, method = RequestMethod.GET)
    public @ResponseBody String entryPoint() {
        try {
            LOGGER.debug("Get to entry successfully");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("Retrieving view");
        return "Indexing page";
    }

    @PreAuthorize("hasAnyAuthority(1,2)")
    @RequestMapping(value = Const.URLMAPPING_UPLOADFILE, method = RequestMethod.POST)
    public @ResponseBody String uploadFile(@ModelAttribute("uploadForm") FileUploadForm uploadForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            List<MultipartFile> files = uploadForm.getFiles();
            List<UploadFileResponse> fileNames = new ArrayList<UploadFileResponse>();
            if (null != files && files.size() > 0) {
                for (MultipartFile multipartFile : files) {
                    String fileName = multipartFile.getOriginalFilename();
                    LOGGER.debug(context.getRealPath("/res"));
                    File f = new File(context.getRealPath("/res") + "/" + fileName);
                    LOGGER.debug(f.getCanonicalPath());
                    if (!f.exists()) f.createNewFile();
                    InputStream is = multipartFile.getInputStream();
                    Files.copy(is, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    is.close();
                    fileNames.add(new UploadFileResponse("/res/" + fileName));
                }
                response.setCode(ResponseCode.SUCCESS);
                response.setData(fileNames);
            }
        } catch (IOException e) {
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return response.toResponseJson();
    }

    // FIXME remove me
    @PreAuthorize("isAuthenticated()") // ADMIN
    @RequestMapping(value = Const.URLMAPPING_MAKE_ADMIN, method = RequestMethod.GET)
    public @ResponseBody
    String makeAdmin(@PathVariable("user_id") String userId) { // thuc chat la username luoi sua :))
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            UserModel user = userService.findUserByUsername(userId);
            if (user == null) {
                LOGGER.error("User not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            userService.changeUserRoll(user.getId(), 1);
            response.setCode(ResponseCode.SUCCESS);
            
            LOGGER.debug("change user role successfully!");
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    // FIXME remove me
    @PreAuthorize("isAuthenticated()") // EDITOR
    @RequestMapping(value = Const.URLMAPPING_MAKE_EDITOR, method = RequestMethod.GET)
    public @ResponseBody
    String makeEditor(@PathVariable("user_id") String userId) { // thuc chat la username luoi sua :))
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            UserModel user = userService.findUserByUsername(userId);
            if (user == null) {
                LOGGER.error("User not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            userService.changeUserRoll(user.getId(), 2);
            response.setCode(ResponseCode.SUCCESS);
            
            LOGGER.debug("change user role successfully!");
            
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

}
