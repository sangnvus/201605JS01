package vn.edu.fu.veazy.core.controller;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import vn.edu.fu.veazy.core.response.data.GetListUsersResponseData;
import vn.edu.fu.veazy.core.response.data.GetUserResponseData;
import vn.edu.fu.veazy.core.response.data.LoginResponseData;
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
     * Process request of register page.
     *
     * @param model view model object
     * @param registerForm form submitted
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_REGISTER, method = RequestMethod.POST)
    public @ResponseBody
    String register(ModelMap model,
            @ModelAttribute("register-form") RegisterForm registerForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
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

            userService.saveUser(registerForm);
            LoginResponseData data = new LoginResponseData();

            user = userService.findUserByUsername(registerForm.getUsername());
            data.setRoll(user.getRole());
//            TODO
//            data.setToken(token);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Register new user successfully!");
            
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
    @RequestMapping(value = Const.URLMAPPING_LOGIN, method = RequestMethod.POST)
    public @ResponseBody
    String loginProceed(ModelMap model,
            @ModelAttribute("login-form") LoginForm loginForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
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
            LoginResponseData data = new LoginResponseData();
            data.setRoll(user.getRole());
//            TODO
//            data.setToken(token);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * Process request of get user detail.
     *
     * @param model view model object
     * @param userId url
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_GET_USER, method = RequestMethod.GET)
    public @ResponseBody
    String getUser(ModelMap model,
            @PathVariable("user_id") String userId) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            // FIXME json object
            LOGGER.debug("Get to login proceed controller successful");

            UserModel user = userService.findUserById(userId);
            if (user == null) {
                LOGGER.error("User not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }

            LOGGER.debug("Get user details successfully!");
            GetUserResponseData data = new GetUserResponseData(user);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

    /**
     * Process request of get list user.
     *
     * @param model view model object
     * @return json string
     */
    @RequestMapping(value = Const.URLMAPPING_GET_LIST_USERS, method = RequestMethod.GET)
    public @ResponseBody
    String getListUser(ModelMap model) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            // FIXME json object
            LOGGER.debug("Get to login proceed controller successful");

            List<UserModel> users = userService.findAllUser();
            if (users != null && users.size() > 0) {
                GetListUsersResponseData listUsers = new GetListUsersResponseData();
                for (UserModel user : users) {
                    GetUserResponseData data = new GetUserResponseData(user);
                    listUsers.addUser(data);
                }
                LOGGER.debug("Get user details successfully!");
                response.setCode(ResponseCode.SUCCESS);
                response.setData(listUsers);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.error("Unknown error occured!");
        response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        return response.toResponseJson();
    }

}
