package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.common.utils.Utils;
import vn.edu.fu.veazy.core.form.ChangeRoleForm;
import vn.edu.fu.veazy.core.form.ChgpwdForm;
import vn.edu.fu.veazy.core.exception.CorruptedFormException;
import vn.edu.fu.veazy.core.exception.InvalidEmailException;
import vn.edu.fu.veazy.core.exception.PasswordExpectedException;
import vn.edu.fu.veazy.core.form.LoginForm;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.form.UpdateUserForm;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.data.ExamResponseData;
import vn.edu.fu.veazy.core.response.data.GetLearnerExamsResponseData;
import vn.edu.fu.veazy.core.response.data.GetListUsersResponseData;
import vn.edu.fu.veazy.core.response.data.GetUserResponseData;
import vn.edu.fu.veazy.core.response.data.LoginResponseData;
import vn.edu.fu.veazy.core.service.ExamService;
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
@CrossOrigin(origins="http://localhost:3003")
@Controller("Core User Controller")
public class UserController {

    /**
     * Logger object
     */
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * User service instance
     */
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;

    /**
     * Process request of register page.
     *
     * @param registerForm form submitted
     * @return json string
     */
    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_REGISTER, method = RequestMethod.POST)
    public @ResponseBody
    String register(@ModelAttribute("register-form") RegisterForm registerForm) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to register proceed controller successful");
            
            String username = registerForm.getUsername();
            String pw = registerForm.getPassword();
            String email = registerForm.getEmail();
            LOGGER.debug("<" + username + ">");
            LOGGER.debug("<" + pw + ">");
            LOGGER.debug("<" + email + ">");

            if (!Utils.isValidEmail(registerForm.getEmail())) {
                LOGGER.error("Submit invalid email!");
                throw new InvalidEmailException("Invalid email");
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
            data.setRole(user.getRole());
//            TODO
//            data.setToken(token);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);

            LOGGER.debug("Register new user successfully!");

            return response.toResponseJson();
        } catch (CorruptedFormException e) {
            response.setCode(e.getCode());
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * Process request of login page.
     *
     * @param loginForm form submitted
     * @return json string
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_LOGIN, method = RequestMethod.POST)
    public @ResponseBody
    String loginProceed(Principal principal, @ModelAttribute("login-form") LoginForm loginForm) {
        Response response = new Response(ResponseCode.SUCCESS);
        try {
            String userName = principal.getName();
            LOGGER.debug(userName);
            UserModel user;
            user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.error("Username not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            LoginResponseData data = new LoginResponseData();
            data.setRole(user.getRole());
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_LOGOUT, method = RequestMethod.GET)
    public @ResponseBody
    String logoutProceed (HttpServletRequest request, HttpServletResponse response) {
        Response responseObj = new Response(ResponseCode.BAD_REQUEST);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
            responseObj.setCode(ResponseCode.SUCCESS);
        }
        return responseObj.toResponseJson();
    }

    /**
     * Process request of get current user detail.
     *
     * @return json string
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_GET_CURRENT_USER, method = RequestMethod.GET)
    public @ResponseBody
    String getCurrentUser(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            LOGGER.debug(userName);
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.error("Username not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }

            LOGGER.debug("Get user details successfully!");
            GetUserResponseData data = new GetUserResponseData(user);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * Process request of update current user detail.
     *
     * @return json string
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_UPDATE_CURRENT_USER, method = RequestMethod.POST)
    public @ResponseBody
    String updateCurrentUser(Principal principal,
            @ModelAttribute("update-user-form") UpdateUserForm form) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            LOGGER.debug(userName);
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.error("Username not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }

            LOGGER.debug("Get user details successfully!");
            userService.update(user, form);
            GetUserResponseData data = new GetUserResponseData(user);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * Process request of change password current user detail.
     *
     * @return json string
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_CHGPWD_CURRENT_USER, method = RequestMethod.POST)
    public @ResponseBody
    String changePasswordCurrentUser(Principal principal,
            @ModelAttribute("chgpwd-form") ChgpwdForm form) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            String userName = principal.getName();
            LOGGER.debug(userName);
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.error("Username not exist!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            userService.changePassword(user.getId(), form.getOldPassword(), form.getNewPassword());
            response.setCode(ResponseCode.SUCCESS);
        } catch (PasswordExpectedException e) {
            response.setCode(e.getCode());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * Process request of get user detail.
     *
     * @param userId url
     * @return json string
     */
    @PreAuthorize("hasAuthority(1)")
    @RequestMapping(value = Const.URLMAPPING_GET_USER, method = RequestMethod.GET)
    public @ResponseBody
    String getUser(@PathVariable("user_id") Integer userId) {
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
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * Process request of get list user.
     *
     * @return json string
     */
    @PreAuthorize("hasAuthority(1)")
    @RequestMapping(value = Const.URLMAPPING_GET_LIST_USERS, method = RequestMethod.GET)
    public @ResponseBody
    String getListUser() {
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
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * Process request of get learner exams.
     *
     * @param principal
     * @return json string
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_GET_LEARNER_EXAMS, method = RequestMethod.GET)
    public @ResponseBody
    String getLearnerExamss(Principal principal) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            LOGGER.debug("Get to get learner exams controller successful");
            String userName = principal.getName();
            UserModel user = userService.findUserByUsername(userName);
            if (user == null) {
                LOGGER.debug("user not found!");
                response.setCode(ResponseCode.USER_NOT_FOUND);
                return response.toResponseJson();
            }
            List<ExamModel> exams = examService.findLearnerExams(user.getId());
            if (exams == null || exams.isEmpty()) {
                LOGGER.debug("Cannot find Learner Exams!");
                response.setCode(ResponseCode.USER_EXAMS_NOT_FOUND);
            }
            GetLearnerExamsResponseData data = new GetLearnerExamsResponseData();
            for (ExamModel exam : exams) {
                ExamResponseData erd = new ExamResponseData(exam);
                data.addExam(erd);
            }
            LOGGER.debug("Get learner exams successfully!");
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    @PreAuthorize("hasAuthority(1)") // ADMIN
    @RequestMapping(value = Const.URLMAPPING_CHANGE_ROLE, method = RequestMethod.GET)
    public @ResponseBody
    String changeUserRoll(@PathVariable("user_id") Integer userId,@ModelAttribute("change-role-form") ChangeRoleForm form) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
        	userService.changeUserRoll(userId, form.getRole());
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
