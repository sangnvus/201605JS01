package vn.edu.fu.veazy.core.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.common.Utils;
import vn.edu.fu.veazy.core.form.LoginForm;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.service.UserService;

/**
 * <p>
 * User Controller</p>
 *
 * <p>
 * This class contains all controllers related to user system such as register,
 * add, update, delete etc.</p>
 *
 * @author minhnn
 *
 */
@Controller("Core User Controller")
public class UserController {

    /**
     * Logger object
     */
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Register page path
     */
    private static final String REGISTER_PAGE = "core/register";
    private static final String LOGIN_PAGE = "core/login";

    /**
     * User service instance
     */
    @Autowired
    private UserService userService;

    /**
     * Process request to register page.
     *
     * @param model view model object
     * @return path to register view jsp
     */
    @RequestMapping(value = Const.URLMAPPING_REGISTER, method = RequestMethod.GET)
    public String registerUser(ModelMap model) {
        LOGGER.debug("Get to register controller successful");
        LOGGER.debug("Retrieving register view");
        return REGISTER_PAGE;
    }

    /**
     * Process request to register page.
     *
     * @param model view model object
     * @return path to register view jsp
     */
    @RequestMapping(value = Const.URLMAPPING_LOGIN, method = RequestMethod.GET)
    public String login(ModelMap model) {
        LOGGER.debug("Get to login controller successful");
        LOGGER.debug("Retrieving login view");
        return LOGIN_PAGE;
    }

    /**
     * Process request of register page.
     *
     * @param model view model object
     * @param registerForm form submitted
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_REGISTER_PROCEED, method = RequestMethod.POST)
    public @ResponseBody
    String registerUserProceed(ModelMap model,
            @ModelAttribute("register-form") RegisterForm registerForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            // FIXME json object
            LOGGER.debug("Get to register proceed controller successful");

            if (!Utils.isValidEmail(registerForm.getEmail())) {
                LOGGER.error("Submit invalid email!");
                response.setCode(ResponseCode.INVALID_EMAIL);
                return response.toResponseJson();
            }

            UserModel user = userService.findUserByEmail(registerForm.getEmail());
            if (user != null) {
                LOGGER.error("Submit duplicated email!");
                response.setCode(ResponseCode.DUPLICATED_EMAIL);
                return response.toResponseJson();
            }

            user = userService.findUserByUsername(registerForm.getUsername());
            if (user != null) {
                LOGGER.error("Submit duplicated username!");
                response.setCode(ResponseCode.DUPLICATED_USERNAME);
                return response.toResponseJson();
            }

            LOGGER.debug("Register new user successfully!");

            userService.saveUser(registerForm);
            response.setCode(ResponseCode.SUCCESS);
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * Process request of login page.
     *
     * @param model view model object
     * @param loginForm form submitted
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_LOGIN_PROCEED, method = RequestMethod.POST)
    public @ResponseBody
    String loginProceed(ModelMap model,
            @ModelAttribute("login-form") LoginForm loginForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            // FIXME json object
            LOGGER.debug("Get to login proceed controller successful");

            UserModel user = userService.findUserByUsername(loginForm.getUsername());
            if (user == null) {
                LOGGER.error("UserName not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            String password = loginForm.getEncryptedPassword();
            if (password == null || password.equals("") || !password.equals(user.getEncryptedPassword())) {
                LOGGER.error("Incorrect password!");
                response.setCode(ResponseCode.INCORRECT_PASSWORD);
                return response.toResponseJson();
            }

            LOGGER.debug("Login successfully!");
            response.setCode(ResponseCode.SUCCESS);
            //TODO 
            //response.setData(token authorize ...);
            return response.toResponseJson();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }
}
