package vn.edu.fu.veazy.core.controller;

import java.security.Principal;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.common.utils.MailUtils;
import vn.edu.fu.veazy.core.common.utils.Utils;
import vn.edu.fu.veazy.core.exception.CorruptedFormException;
import vn.edu.fu.veazy.core.exception.InvalidEmailException;
import vn.edu.fu.veazy.core.exception.PasswordExpectedException;
import vn.edu.fu.veazy.core.exception.PasswordIncorrectException;
import vn.edu.fu.veazy.core.form.ChangeRoleForm;
import vn.edu.fu.veazy.core.form.ChgpwdForm;
import vn.edu.fu.veazy.core.form.ForgotPwdForm;
import vn.edu.fu.veazy.core.form.LoginForm;
import vn.edu.fu.veazy.core.form.Mail;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.form.UpdateUserForm;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.GetExamResponse;
import vn.edu.fu.veazy.core.response.GetLearnerExamsResponse;
import vn.edu.fu.veazy.core.response.GetListUsersResponse;
import vn.edu.fu.veazy.core.response.GetUserResponse;
import vn.edu.fu.veazy.core.response.LoginResponse;
import vn.edu.fu.veazy.core.response.Response;
import vn.edu.fu.veazy.core.response.ResponseCode;
import vn.edu.fu.veazy.core.response.StatsUsersResponse;
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
//@CrossOrigin(origins="http://localhost:3003")
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
    @RequestMapping(value = Const.URLMAPPING_REGISTER, method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String register(@RequestBody RegisterForm registerForm) {
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
            LoginResponse data = new LoginResponse();
            
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
    @RequestMapping(value = Const.URLMAPPING_LOGIN, method = RequestMethod.POST,
            produces = {"application/x-www-form-urlencoded; charset=UTF-8"})
    public @ResponseBody
    String loginProceed(Principal principal, @ModelAttribute LoginForm loginForm) {
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
            LoginResponse data = new LoginResponse();
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
    String logoutProceed(HttpServletRequest request, HttpServletResponse response) {
        Response responseObj = new Response(ResponseCode.BAD_REQUEST);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
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
    @RequestMapping(value = Const.URLMAPPING_GET_CURRENT_USER, method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
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
            GetUserResponse data = new GetUserResponse(user);
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
    @RequestMapping(value = Const.URLMAPPING_UPDATE_CURRENT_USER, method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String updateCurrentUser(Principal principal,
            @RequestBody UpdateUserForm form) {
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
            GetUserResponse data = new GetUserResponse(user);
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
    @RequestMapping(value = Const.URLMAPPING_CHGPWD_CURRENT_USER, method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String changePasswordCurrentUser(Principal principal,
            @RequestBody ChgpwdForm form) {
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
            userService.changePassword(user, form.getOldPassword(), form.getNewPassword());
            response.setCode(ResponseCode.SUCCESS);
        } catch (PasswordExpectedException e) {
            response.setCode(e.getCode());
        } catch (PasswordIncorrectException e) {
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
    @RequestMapping(value = Const.URLMAPPING_GET_USER, method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
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
            GetUserResponse data = new GetUserResponse(user);
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
     * Process request of banning an user.
     *
     * @param userId url
     * @return json string
     */
    @PreAuthorize("hasAuthority(1)")
    @RequestMapping(value = Const.URLMAPPING_BAN_USER, method = RequestMethod.GET)
    public @ResponseBody
    String banUser(@PathVariable("user_id") Integer userId) {
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
            
            user.setIsBanned(true);
            userService.update(user);
            
            LOGGER.debug("Ban user successfully!");
            response.setCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }

    /**
     * Process request of unbanning an user.
     *
     * @param userId url
     * @return json string
     */
    @PreAuthorize("hasAuthority(1)")
    @RequestMapping(value = Const.URLMAPPING_UNBAN_USER, method = RequestMethod.GET)
    public @ResponseBody
    String unbanUser(@PathVariable("user_id") Integer userId) {
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
            
            user.setIsBanned(false);
            userService.update(user);
            
            LOGGER.debug("Ban user successfully!");
            response.setCode(ResponseCode.SUCCESS);
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
    @RequestMapping(value = Const.URLMAPPING_GET_LIST_USERS, method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String getListUser() {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            // FIXME json object
            LOGGER.debug("Get to login proceed controller successful");
            
            List<UserModel> users = userService.findAllUser();
            if (users != null && users.size() > 0) {
                GetListUsersResponse listUsers = new GetListUsersResponse();
                for (UserModel user : users) {
                    GetUserResponse data = new GetUserResponse(user);
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
     * Process request of get number of user.
     *
     * @return json string
     */
    @PreAuthorize("hasAuthority(1)")
    @RequestMapping(value = Const.URLMAPPING_GET_SIZE_USERS, method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String getNumberUser() {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            // FIXME json object
            LOGGER.debug("Get to get number of user controller successful");
            
            int active = userService.countActive();
            int total = userService.size();
            StatsUsersResponse resp = new StatsUsersResponse(active, total);
            LOGGER.debug("Get number user successfully!");
            response.setCode(ResponseCode.SUCCESS);
            response.setData(resp);
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
    @RequestMapping(value = Const.URLMAPPING_GET_LEARNER_EXAMS, method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
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
            GetLearnerExamsResponse data = new GetLearnerExamsResponse();
            Collections.reverse(exams);
            for (ExamModel exam : exams) {
                GetExamResponse erd = new GetExamResponse(exam, false);
                data.addExam(erd);
            }
            LOGGER.debug("Get learner exams successfully!");
            response.setCode(ResponseCode.SUCCESS);
            response.setData(data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return response.toResponseJson();
    }
    
    @PreAuthorize("hasAuthority(1)") // ADMIN
    @RequestMapping(value = Const.URLMAPPING_CHANGE_ROLE, method = RequestMethod.GET)
    public @ResponseBody
    String changeUserRoll(@PathVariable("user_id") Integer userId,
            @RequestBody ChangeRoleForm form) {
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

    /**
     * Process request of change password current user detail.
     *
     * @return json string
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Const.URLMAPPING_FOTGOTPWD, method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public @ResponseBody
    String forgotPassword(@RequestBody ForgotPwdForm form,
            HttpServletRequest req) {
        Response response = new Response(ResponseCode.BAD_REQUEST);
        try {
            if (form != null) {
                String email = form.getEmail();
                UserModel user = userService.findUserByEmail(email);
                if (user == null) {
                    response.setCode(ResponseCode.EMAIL_NOT_FOUND);
                }
                Mail mail = new Mail(user, req);
                MailUtils.sentMail(mail);
                response.setCode(ResponseCode.SUCCESS);
            }
        } catch (PasswordExpectedException e) {
            response.setCode(e.getCode());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Unknown error occured!");
            response.setCode(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return response.toResponseJson();
    }
}
