package vn.edu.fu.veazy.core.common;

/**
 * Common core constants
 * 
 * @author minhnn
 *
 */
public class Const {

    /************************* URL Mapping ******************************/
    /**
     * URL to home page
     */
    public static final String URLMAPPING_HOME = "/";
    /**
     * URL to register page
     */
    public static final String URLMAPPING_REGISTER = "/register";
    /**
     * URL to proceed register form
     */
    public static final String URLMAPPING_REGISTER_PROCEED = "/register/proceed";
    /**
     * URL to login page
     */
    public static final String URLMAPPING_LOGIN = "/login";
    /**
     * URL to proceed login form
     */
    public static final String URLMAPPING_LOGIN_PROCEED = "/login/proceed";
    /**
     * URL to reset password page
     */
    public static final String URLMAPPING_RESET_PASSWORD = "/users/reset_password";
    /**
     * URL to proceed reset password form
     */
    public static final String URLMAPPING_RESET_PASSWORD_PROCEED = "/users/reset_password/proceed";
    /**
     * URL to lesson detail page
     * 
     * @PathVariable lessonId lesson id
     */
    public static final String URLMAPPING_LESSON_DETAIL = "/lesson/{lessonId}";

}
